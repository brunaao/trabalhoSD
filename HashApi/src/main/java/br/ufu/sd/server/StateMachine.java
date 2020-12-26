package br.ufu.sd.server;

import org.apache.ratis.proto.RaftProtos;
import org.apache.ratis.protocol.Message;
import org.apache.ratis.statemachine.TransactionContext;
import org.apache.ratis.statemachine.impl.BaseStateMachine;

import java.nio.charset.Charset;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;


// Servidor Ratis
// Métodos de alteração de estado estão aqui
public class StateMachine extends BaseStateMachine
{
    private final Map<String, String> key2values = new ConcurrentHashMap<>();

    @Override
    public CompletableFuture<Message> query(Message request) {

        // GET CHAVE
        final String[] opKey = request.getContent().toString(Charset.defaultCharset()).split(" ");

        Chave chave = Valor.newBuilder().setKey(Long.parseLong(opKey[1], 10)).build();
        Valor valor = key2values.get(chave);
        String erro = "ERROR";

        if(valor != null){
            erro = "SUCCESS";
        } else {
            valor = Valor.newBuilder().setVersion(0).setTimeSt(0).setData(ByteString.copyFrom("".getBytes())).build();
        }

        Saida resposta = Saida.newBuilder().setError(erro).setValue(response).build();

        return CompletableFuture.completedFuture(resposta);

    }


    @Override
    public CompletableFuture<Message> applyTransaction(TransactionContext trx) {
        // METODO CHAVE [PARAM]
        final RaftProtos.LogEntryProto entry = trx.getLogEntry();
        final String[] opKeyValue = entry.getStateMachineLogEntry().getLogData().toString(Charset.defaultCharset()).split(":");

        switch (opKeyValue[0]){
            case "SET":
                // SET CHAVE VERSAO TIMEST DADOS

                Chave chave = Chave.newBuilder().setKey(Long.parseLong(opKeyValue[1], 10)).build();
                Valor valor = Valor.newBuilder().setVersion(Long.parseLong(opKeyValue[2], 10)).setTimeSt(Long.parseLong(opKeyValue[3], 10)).setData(ByteString.copyFrom(opKeyValue[4].getBytes())).build();
                Valor vLinha;
                String erro = "SUCCESS";

                if(!key2values.containsKey(chave)){
                    key2values.put(chave, valor);
                    vLinha = Valor.newBuilder().setVersion(0).setTimeSt(0).setData(ByteString.copyFrom("".getBytes())).build();
                } else {
                    //se ja existe verifica se o valorRequest eh igual
                    if(valor.getTimeSt() != key2values.get(chave).getTimeSt() && valor.getData() != key2values.get(chave).getData()){
                        Valor valorAtual = key2values.get(chave);
                        Valor novoValor = Valor.newBuilder().setVersion(valorAtual.getVersion() + 1).setTimeSt(valor.getTimeSt()).setData(valor.getData()).build();
                        key2values.put(chave, novoValor);
                        vLinha = valorAtual;
                    } else {
                        vLinha = key2values.get(chave);
                        erro = "ERROR";
                    }
                }

                Saida resposta = Saida.newBuilder().setError(erro).setValue(vLinha).build();

                return CompletableFuture.completedFuture(resposta);

            case "DELK":
                // DELK CHAVE

                Chave chave = Chave.newBuilder().setKey(Long.parseLong(opKeyValue[1], 10)).build();
                Valor vLinha;
                String erro = "ERROR";

                if(key2values.containsKey(chave)){
                    vLinha = key2values.get(chave);
                    key2values.remove(chave);
                    erro = "SUCCESS";
                } else {
                    vLinha = Valor.newBuilder().setVersion(0).setTimeSt(0).setData(ByteString.copyFrom("".getBytes())).build();
                }

                Saida resposta = Saida.newBuilder().setError(erro).setValue(vLinha).build();

                return CompletableFuture.completedFuture(resposta);
                
            case "DELKV":
                // DELKV CHAVE VERSAO
                Chave chave = Chave.newBuilder().setKey(Long.parseLong(opKeyValue[1], 10)).build();
                long versao = Long.parseLong(opKeyValue[2], 10);
                String erro = "ERROR_NE";
                Valor vLinha;

                if(key2values.containsKey(chave)){
                    vLinha = key2values.get(chave);
                    if(vLinha.getVersion() == versao){
                        //Existe uma entrada com a mesma versao
                        key2values.remove(chave);
                        erro = "SUCCESS";
                    } else {
                        //Existe uma entrada com versao diferente
                        erro = "ERROR_WV";
                    }
                } else {
                    //Nao existe entrada
                    vLinha = Valor.newBuilder().setVersion(0).setTimeSt(0).setData(ByteString.copyFrom("".getBytes())).build();
                }

                Saida resposta = Saida.newBuilder().setError(erro).setValue(vLinha).build();

                return CompletableFuture.completedFuture(resposta);

            case "TAS":
                // TAS CHAVE VERSAO TIMEST DADOS VERSAOVERIFICACAO
                Chave chave = Chave.newBuilder().setKey(Long.parseLong(opKeyValue[1], 10)).build();
                Valor valor = Valor.newBuilder().setVersion(Long.parseLong(opKeyValue[2], 10)).setTimeSt(Long.parseLong(opKeyValue[3], 10)).setData(ByteString.copyFrom(opKeyValue[4].getBytes())).build();
                long versao = Long.parseLong(opKeyValue[5], 10);
                String erro = "ERROR_NE";
                Valor vLinha;

                if(key2values.containsKey(chave)){
                    vLinha = key2values.get(chave);
                    if(vLinha.getVersion() == versao){
                        //Existe uma entrada com a mesma versao
                        key2values.put(chave, Valor.newBuilder().setVersion(valor.getVersion() + 1).setTimeSt(valor.getTimeSt()).setData(valor.getData()).build());
                        erro = "SUCCESS";
                        vLinha = key2values.get(chave);
                    } else {
                        //Existe uma entrada com versao diferente
                        erro = "ERROR_WV";
                    }
                } else {
                    //Nao existe entrada
                    vLinha = Valor.newBuilder().setVersion(0).setTimeSt(0).setData(ByteString.copyFrom("".getBytes())).build();
                }

                Saida resposta = Saida.newBuilder().setError(erro).setValue(vLinha).build();

                return CompletableFuture.completedFuture(resposta);

            default:
                break;
        }

        final String result = opKeyValue[0]+ ":"+ key2values.put(opKeyValue[1], opKeyValue[2]);
        
        final CompletableFuture<Message> f = CompletableFuture.completedFuture(Message.valueOf(result));

        final RaftProtos.RaftPeerRole role = trx.getServerRole();
        System.out.println(role + ":" + getId() + " " + opKeyValue[0] + "=" + opKeyValue[1] + " " + opKeyValue[2]);

        if (LOG.isTraceEnabled()) {
            System.out.println(getId() + ":key/values=" + key2values);
        }
        return f;
    }
}
