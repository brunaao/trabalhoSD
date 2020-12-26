package br.ufu.sd.server;

import br.ufu.sd.grpc.APIGrpc.APIImplBase;
import br.ufu.sd.grpc.*;
import com.google.protobuf.ByteString;
import io.grpc.stub.StreamObserver;
import org.apache.ratis.client.RaftClient;
import org.apache.ratis.protocol.Message;
import org.apache.ratis.protocol.RaftClientReply;

import java.io.IOException;
import java.nio.charset.Charset;

// Métodos do server GRPC
// Os métodos irão chamar os métodos do servidor Ratis
public class ApiService extends APIImplBase {
    private final RaftClient raftClient;

    public ApiService(RaftClient client) {
        this.raftClient = client;
    }

    @Override
    public void set(ChaveValor kv, StreamObserver<Saida> responseObserver) {
        Chave chave = kv.getKey();
        Valor valorRequest = kv.getValue();
        long versao = valorRequest.getVersion();
        long timeSt = valorRequest.getTimeSt();
        ByteString dados = valorRequest.getData();  
        Valor vLinha;

        RaftClientReply getValue;
        try {
            //getValue = raftClient.send(Message.valueOf("SET " + chave.getKey() + " " + versao + " " + timeSt + " " + dados));
            getValue = raftClient.sendReadOnly(Message.valueOf("SET "));
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        String response = getValue.getMessage().getContent().toString(Charset.defaultCharset());
        System.out.println("Resposta:" + response);

        //Define a mensagem padrao de erro
        //String erro = "SUCCESS";

        //Se o valorRequest nao existe no map
        // if(!map.containsKey(chave)){
        //     map.put(chave, valorRequest);
        //     vLinha = Valor.newBuilder().setVersion(0).setTimeSt(0).setData(ByteString.copyFrom("".getBytes())).build();
        // } else {
        //     //se ja existe verifica se o valorRequest eh igual
        //     if(valorRequest.getTimeSt() != map.get(chave).getTimeSt() && valorRequest.getData() != map.get(chave).getData()){
        //         Valor valorAtual = map.get(chave);
        //         Valor novoValor = Valor.newBuilder().setVersion(valorAtual.getVersion() + 1).setTimeSt(valorRequest.getTimeSt()).setData(valorRequest.getData()).build();
        //         map.put(chave, novoValor);
        //         vLinha = valorAtual;
        //     } else {
        //         erro = "ERROR";
        //         vLinha = map.get(chave);
        //     }
        // }

        //Build e envio da resposta
        //Saida resposta = Saida.newBuilder().setError(erro).setValue(vLinha).build();
        //responseObserver.onNext(resposta);
        //responseObserver.onCompleted();
    }

    @Override
    public void get(Chave chave, StreamObserver<Saida> responseObserver) {
        //Define a mensagem padrao de erro
        String erro = "ERROR";
        Valor valor;

        RaftClientReply getValue;
        try {
            getValue = raftClient.sendReadOnly(Message.valueOf("GET " + chave));
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        String response = getValue.getMessage().getContent().toString(Charset.defaultCharset());
        System.out.println("Resposta:" + response);

        // if(map.get(chave) != null){
        //     valor = map.get(chave);
        //     erro = "SUCCESS";
        // } else {
        //     valor = Valor.newBuilder().setVersion(0).setTimeSt(0).setData(ByteString.copyFrom("".getBytes())).build();
        // }

        // //Build e envio da resposta
        // Saida resposta = Saida.newBuilder().setError(erro).setValue(valor).build();
        // responseObserver.onNext(resposta);
        // responseObserver.onCompleted();
    }

    @Override
    public void delK(Chave chave, StreamObserver<Saida> responseObserver) {

        //Define a mensagem padrao de erro
        String erro = "ERROR";
        Valor v;

        RaftClientReply getValue;
        try {
            getValue = raftClient.send(Message.valueOf("DELK " + chave));
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        String response = getValue.getMessage().getContent().toString(Charset.defaultCharset());
        System.out.println("Resposta:" + response);

        // if(map.containsKey(k)){
        //     v = map.get(k);
        //     map.remove(k);
        //     erro = "SUCCESS";
        // } else {
        //     v = Valor.newBuilder().setVersion(0).setTimeSt(0).setData(ByteString.copyFrom("".getBytes())).build();
        // }

        // //Build e envio da resposta
        // Saida resposta = Saida.newBuilder().setError(erro).setValue(v).build();
        // responseObserver.onNext(resposta);
        // responseObserver.onCompleted();
    }

    @Override
    public void delKV(ChaveVersao kv, StreamObserver<Saida> responseObserver) {

        //Define a mensagem padrao de erro
        String erro = "ERROR_NE";
        Valor v;

        Chave chave = kv.getKey();
        long versao = kv.getVersion();

        RaftClientReply getValue;
        try {
            getValue = raftClient.send(Message.valueOf("DELKV " + chave + " " + versao));
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        String response = getValue.getMessage().getContent().toString(Charset.defaultCharset());
        System.out.println("Resposta:" + response);

        // if(map.containsKey(k)){
        //     v = map.get(k);
        //     if(v.getVersion() == vers){
        //         //Existe uma entrada com a mesma versao
        //         map.remove(k);
        //         erro = "SUCCESS";
        //     } else {
        //         //Existe uma entrada com versao diferente
        //         erro = "ERROR_WV";
        //     }
        // } else {
        //     //Nao existe entrada
        //     v = Valor.newBuilder().setVersion(0).setTimeSt(0).setData(ByteString.copyFrom("".getBytes())).build();
        // }

        // //Build e envio da resposta
        // Saida resposta = Saida.newBuilder().setError(erro).setValue(v).build();
        // responseObserver.onNext(resposta);
        // responseObserver.onCompleted();
    }

    @Override
    public void testAndSet(ChaveValorVersao kvv, StreamObserver<Saida> responseObserver) {

        //Define a mensagem padrao de erro
        String erro = "ERROR_NE";
        Valor v;

        Chave chave = kvv.getKey();
        Valor valor = kvv.getValue();
        long versao = valor.getVersion();
        long timeSt = valor.getTimeSt();
        ByteString dados = valor.getData(); 
        long versaoVerificacao = kvv.getVersion();

        RaftClientReply getValue;
        try {
            getValue = raftClient.send(Message.valueOf("TAS " + chave + " " + versao + " " + timeSt + " " + dados + " "  + versaoVerificacao));
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        String response = getValue.getMessage().getContent().toString(Charset.defaultCharset());
        System.out.println("Resposta:" + response);

        // if(map.containsKey(k)){
        //     v = map.get(k);
        //     if(v.getVersion() == vers){
        //         //Existe uma entrada com a mesma versao
        //         map.put(k, Valor.newBuilder().setVersion(val.getVersion() + 1).setTimeSt(val.getTimeSt()).setData(val.getData()).build());
        //         erro = "SUCCESS";
        //         v = map.get(k);
        //     } else {
        //         //Existe uma entrada com versao diferente
        //         erro = "ERROR_WV";
        //     }
        // } else {
        //     //Nao existe entrada
        //     v = Valor.newBuilder().setVersion(0).setTimeSt(0).setData(ByteString.copyFrom("".getBytes())).build();
        // }

        // //Build e envio da resposta
        // Saida resposta = Saida.newBuilder().setError(erro).setValue(v).build();
        // responseObserver.onNext(resposta);
        // responseObserver.onCompleted();
    }
}