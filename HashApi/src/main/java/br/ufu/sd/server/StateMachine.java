package br.ufu.sd.server;

import br.ufu.sd.grpc.Chave;
import br.ufu.sd.grpc.Saida;
import br.ufu.sd.grpc.Valor;
import com.google.protobuf.ByteString;
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
    private final Map<Chave, Valor> key2values = new ConcurrentHashMap<>();

    @Override
    public CompletableFuture<Message> query(Message request) {

        // GET CHAVE
        final String[] opKey = request.getContent().toString(Charset.defaultCharset()).split(" ");
        String stringResposta;

        Chave chave = Chave.newBuilder().setKey(Long.parseLong(opKey[1], 10)).build();
        Valor valor = key2values.get(chave);
        String erro = "ERROR";

        if(valor != null){
            erro = "SUCCESS";
        } else {
            valor = Valor.newBuilder().setVersion(0).setTimeSt(0).setData(ByteString.copyFrom("".getBytes())).build();
        }

        Saida resposta = Saida.newBuilder().setError(erro).setValue(valor).build();
        stringResposta = resposta.getError() + " " + resposta.getValue().getVersion() + " " +  resposta.getValue().getTimeSt() + " " +  resposta.getValue().getData().toStringUtf8();
        return CompletableFuture.completedFuture(Message.valueOf(stringResposta));

    }


    @Override
    public CompletableFuture<Message> applyTransaction(TransactionContext trx) {
        // METODO CHAVE [PARAM]
        final RaftProtos.LogEntryProto entry = trx.getLogEntry();

        final String[] opKeyValue = entry.getStateMachineLogEntry().getLogData().toString(Charset.defaultCharset()).split(" ");
        String result = "";
        Chave chave;
        Valor valor;
        String erro;
        Valor vLinha;
        long versao;
        ByteString stringData;
        Saida resposta;
        String stringResposta;

        switch (opKeyValue[0]){
            case "SET":
                // SET CHAVE VERSAO TIMEST DADOS
                //Retorno: ERRO VERSAO TIMEST DADOS
                chave = Chave.newBuilder().setKey(Long.parseLong(opKeyValue[1], 10)).build();
                valor = Valor.newBuilder().setVersion(Long.parseLong(opKeyValue[2], 10)).setTimeSt(Long.parseLong(opKeyValue[3], 10)).setData(ByteString.copyFrom(opKeyValue[4].getBytes())).build();
                erro = "SUCCESS";

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
                resposta = Saida.newBuilder().setError(erro).setValue(vLinha).build();
                stringResposta = resposta.getError() + " " + resposta.getValue().getVersion() + " " +  resposta.getValue().getTimeSt() + " " +  resposta.getValue().getData().toStringUtf8();
                return CompletableFuture.completedFuture(Message.valueOf(stringResposta));

            case "DELK":
                // DELK CHAVE
                chave = Chave.newBuilder().setKey(Long.parseLong(opKeyValue[1], 10)).build();
                erro = "SUCCESS";

                if(key2values.containsKey(chave)){
                    vLinha = key2values.get(chave);
                    key2values.remove(chave);
                } else {
                    vLinha = Valor.newBuilder().setVersion(0).setTimeSt(0).setData(ByteString.copyFrom("".getBytes())).build();
                    erro = "ERROR";
                }

                resposta = Saida.newBuilder().setError(erro).setValue(vLinha).build();
                stringResposta = resposta.getError() + " " + resposta.getValue().getVersion() + " " +  resposta.getValue().getTimeSt() + " " +  resposta.getValue().getData().toStringUtf8();
                return CompletableFuture.completedFuture(Message.valueOf(stringResposta));
                
            case "DELKV":
                // DELKV CHAVE VERSAO
                chave = Chave.newBuilder().setKey(Long.parseLong(opKeyValue[1], 10)).build();
                versao = Long.parseLong(opKeyValue[2], 10);
                erro = "ERROR_NE";

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

                resposta = Saida.newBuilder().setError(erro).setValue(vLinha).build();
                stringResposta = resposta.getError() + " " + resposta.getValue().getVersion() + " " +  resposta.getValue().getTimeSt() + " " +  resposta.getValue().getData().toStringUtf8();
                return CompletableFuture.completedFuture(Message.valueOf(stringResposta));

            case "TAS":
                // TAS CHAVE VERSAO TIMEST DADOS VERSAOVERIFICACAO
                chave = Chave.newBuilder().setKey(Long.parseLong(opKeyValue[1], 10)).build();
                valor = Valor.newBuilder().setVersion(Long.parseLong(opKeyValue[2], 10)).setTimeSt(Long.parseLong(opKeyValue[3], 10)).setData(ByteString.copyFrom(opKeyValue[4].getBytes())).build();
                versao = Long.parseLong(opKeyValue[5], 10);
                erro = "ERROR_NE";

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

                resposta = Saida.newBuilder().setError(erro).setValue(vLinha).build();
                stringResposta = resposta.getError() + " " + resposta.getValue().getVersion() + " " +  resposta.getValue().getTimeSt() + " " +  resposta.getValue().getData().toStringUtf8();
                return CompletableFuture.completedFuture(Message.valueOf(stringResposta));

            default:
                break;
        }
        
        final CompletableFuture<Message> f = CompletableFuture.completedFuture(Message.valueOf(result));

        final RaftProtos.RaftPeerRole role = trx.getServerRole();
        System.out.println(role + ":" + getId() + " " + opKeyValue[0] + "=" + opKeyValue[1] + " " + opKeyValue[2]);

        if (LOG.isTraceEnabled()) {
            System.out.println(getId() + ":key/values=" + key2values);
        }
        return f;
    }
}
