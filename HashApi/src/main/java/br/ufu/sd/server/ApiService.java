package br.ufu.sd.server;

import br.ufu.sd.grpc.*;
import br.ufu.sd.grpc.APIGrpc.APIImplBase;
import com.google.protobuf.ByteString;
import io.grpc.stub.StreamObserver;

import java.io.*;
import java.util.*;
import java.util.concurrent.*;

public class ApiService extends APIImplBase {
    private final ConcurrentMap<Chave, Valor> map;
    private final int INTERVALO_BACKUP;
    private final String ARQUIVO_DISCO;

    public ApiService(int intervaloBackup) {
        map = new ConcurrentHashMap<>();
        INTERVALO_BACKUP = intervaloBackup;
        ARQUIVO_DISCO = "database.csv";
        try {
            carregar();
            ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
            executor.scheduleAtFixedRate(() -> {
                try {
                    salvar();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }, 0, INTERVALO_BACKUP, TimeUnit.SECONDS);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void set(ChaveValor kv, StreamObserver<Saida> responseObserver) {
        Chave chave = kv.getKey();
        Valor valorRequest = kv.getValue();
        Valor vLinha;

        //Define a mensagem padrao de erro
        String erro = "SUCCESS";

        //Se o valorRequest nao existe no map
        if(!map.containsKey(chave)){
            map.put(chave, valorRequest);
            vLinha = Valor.newBuilder().setVersion(0).setTimeSt(0).setData(ByteString.copyFrom("".getBytes())).build();
        } else {
            //se ja existe verifica se o valorRequest eh igual
            if(valorRequest.getTimeSt() != map.get(chave).getTimeSt() && valorRequest.getData() != map.get(chave).getData()){
                Valor valorAtual = map.get(chave);
                Valor novoValor = Valor.newBuilder().setVersion(valorAtual.getVersion() + 1).setTimeSt(valorRequest.getTimeSt()).setData(valorRequest.getData()).build();
                map.put(chave, novoValor);
                vLinha = valorAtual;
            } else {
                erro = "ERROR";
                vLinha = map.get(chave);
            }
        }

        //Build e envio da resposta
        Saida resposta = Saida.newBuilder().setError(erro).setValue(vLinha).build();
        responseObserver.onNext(resposta);
        responseObserver.onCompleted();
    }

    @Override
    public void get(Chave chave, StreamObserver<Saida> responseObserver) {
        //Define a mensagem padrao de erro
        String erro = "ERROR";
        Valor valor;

        if(map.get(chave) != null){
            valor = map.get(chave);
            erro = "SUCCESS";
        } else {
            valor = Valor.newBuilder().setVersion(0).setTimeSt(0).setData(ByteString.copyFrom("".getBytes())).build();
        }

        //Build e envio da resposta
        Saida resposta = Saida.newBuilder().setError(erro).setValue(valor).build();
        responseObserver.onNext(resposta);
        responseObserver.onCompleted();
    }

    @Override
    public void delK(Chave k, StreamObserver<Saida> responseObserver) {

        //Define a mensagem padrao de erro
        String erro = "ERROR";
        Valor v;

        if(map.containsKey(k)){
            v = map.get(k);
            map.remove(k);
            erro = "SUCCESS";
        } else {
            v = Valor.newBuilder().setVersion(0).setTimeSt(0).setData(ByteString.copyFrom("".getBytes())).build();
        }

        //Build e envio da resposta
        Saida resposta = Saida.newBuilder().setError(erro).setValue(v).build();
        responseObserver.onNext(resposta);
        responseObserver.onCompleted();
    }

    @Override
    public void delKV(ChaveVersao kv, StreamObserver<Saida> responseObserver) {

        //Define a mensagem padrao de erro
        String erro = "ERROR_NE";
        Valor v;

        Chave k = kv.getKey();
        long vers = kv.getVersion();

        if(map.containsKey(k)){
            v = map.get(k);
            if(v.getVersion() == vers){
                //Existe uma entrada com a mesma versao
                map.remove(k);
                erro = "SUCCESS";
            } else {
                //Existe uma entrada com versao diferente
                erro = "ERROR_WV";
            }
        } else {
            //Nao existe entrada
            v = Valor.newBuilder().setVersion(0).setTimeSt(0).setData(ByteString.copyFrom("".getBytes())).build();
        }

        //Build e envio da resposta
        Saida resposta = Saida.newBuilder().setError(erro).setValue(v).build();
        responseObserver.onNext(resposta);
        responseObserver.onCompleted();
    }

    @Override
    public void testAndSet(ChaveValorVersao kvv, StreamObserver<Saida> responseObserver) {

        //Define a mensagem padrao de erro
        String erro = "ERROR_NE";
        Valor v;

        Chave k = kvv.getKey();
        Valor val = kvv.getValue();
        long vers = kvv.getVersion();

        if(map.containsKey(k)){
            v = map.get(k);
            if(v.getVersion() == vers){
                //Existe uma entrada com a mesma versao
                map.put(k, Valor.newBuilder().setVersion(val.getVersion() + 1).setTimeSt(val.getTimeSt()).setData(val.getData()).build());
                erro = "SUCCESS";
                v = map.get(k);
            } else {
                //Existe uma entrada com versao diferente
                erro = "ERROR_WV";
            }
        } else {
            //Nao existe entrada
            v = Valor.newBuilder().setVersion(0).setTimeSt(0).setData(ByteString.copyFrom("".getBytes())).build();
        }

        //Build e envio da resposta
        Saida resposta = Saida.newBuilder().setError(erro).setValue(v).build();
        responseObserver.onNext(resposta);
        responseObserver.onCompleted();
    }

    public void carregar() throws IOException {
        try (BufferedReader csvReader = new BufferedReader(new FileReader(ARQUIVO_DISCO))) {
            String linha;
            while ((linha = csvReader.readLine()) != null) {
                String[] valores = linha.split(",");
                Chave chave = Chave.newBuilder().setKey(Long.parseLong(valores[0])).build();
                long versao = Long.parseLong(valores[1]);
                long timestamp = Long.parseLong(valores[2]);
                ByteString dados = ByteString.copyFrom(valores[3].getBytes());

                Valor dado = Valor.newBuilder()
                        .setVersion(versao)
                        .setTimeSt(timestamp)
                        .setData(dados)
                        .build();

                map.put(chave, dado);
            }
        }catch(FileNotFoundException e){
            this.salvar();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void salvar() throws IOException {
        try (FileWriter csvWriter = new FileWriter(ARQUIVO_DISCO)) {
            Set<Map.Entry<Chave, Valor>> entradas = map.entrySet();
            for (Map.Entry<Chave, Valor> entrada : entradas) {
                String chave = String.valueOf(entrada.getKey().getKey());
                String versao = String.valueOf(entrada.getValue().getVersion());
                String timestamp = String.valueOf(new Date(entrada.getValue().getTimeSt()).getTime());
                String dado = entrada.getValue().getData().toStringUtf8();
                csvWriter.write(chave + "," + versao + "," + timestamp + "," + dado + "\n");
            }
            csvWriter.flush();
        }
    }
}