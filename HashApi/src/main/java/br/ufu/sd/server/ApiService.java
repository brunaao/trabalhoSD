package br.ufu.sd.server;

import br.ufu.sd.grpc.APIGrpc.APIImplBase;
import br.ufu.sd.grpc.*;
import com.google.protobuf.ByteString;
import io.grpc.stub.StreamObserver;
import org.apache.ratis.client.RaftClient;
import org.apache.ratis.protocol.Message;
import org.apache.ratis.protocol.RaftClientReply;

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

        RaftClientReply getValue = raftClient.send(Message.valueOf("SET " + chave.getKey() + " " + versao + " " + timeSt + " " + dados));
        Saida response = getValue.getMessage().getContent();

        //Envio da resposta
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void get(Chave chave, StreamObserver<Saida> responseObserver) {
        RaftClientReply getValue = raftClient.sendReadOnly(Message.valueOf("GET " + chave.getKey()));
        Saida response = getValue.getMessage().getContent();

        // Envio da resposta
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void delK(Chave chave, StreamObserver<Saida> responseObserver) {

        RaftClientReply getValue = raftClient.send(Message.valueOf("DELK " + chave));
        Saida response = getValue.getMessage().getContent();
        
        // Envio da resposta
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void delKV(ChaveVersao kv, StreamObserver<Saida> responseObserver) {

        Chave chave = kv.getKey();
        long versao = kv.getVersion();

        RaftClientReply getValue = raftClient.send(Message.valueOf("DELKV " + chave.getKey() + " " + versao));
        Saida response = getValue.getMessage().getContent();

        // Envio da resposta
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void testAndSet(ChaveValorVersao kvv, StreamObserver<Saida> responseObserver) {

        Chave chave = kvv.getKey();
        Valor valor = kvv.getValue();
        long versao = valor.getVersion();
        long timeSt = valor.getTimeSt();
        ByteString dados = valor.getData(); 
        long versaoVerificacao = kvv.getVersion();

        RaftClientReply getValue = raftClient.send(Message.valueOf("TAS " + chave.getKey() + " " + versao + " " + timeSt + " " + dados + " "  + versaoVerificacao));
        Saida response = getValue.getMessage().getContent();

        // Envio da resposta
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}