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
<<<<<<< HEAD
    final private RaftClient raftClient;
=======
    private final RaftClient raftClient;
>>>>>>> 0b86311aef750916236d8271a290e5e425793239

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

        RaftClientReply getValue = null;
        try {
            getValue = raftClient.send(Message.valueOf("SET " + chave.getKey() + " " + versao + " " + timeSt + " " + dados));
        } catch (IOException e) {
            e.printStackTrace();
        }
        String[] stringRetorno = getValue.getMessage().getContent().toString(Charset.defaultCharset()).split(" ");
        versao = Long.parseLong(stringRetorno[1]);
        long timeStamp = Long.parseLong(stringRetorno[2]);
        dados = ByteString.copyFrom(stringRetorno[3].getBytes());
        Valor valorRetorno = Valor.newBuilder().setVersion(versao).setTimeSt(timeStamp).setData(dados).build();
        Saida response = Saida.newBuilder().setError(stringRetorno[0]).setValue(valorRetorno).build();

        //Envio da resposta
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void get(Chave chave, StreamObserver<Saida> responseObserver) {
<<<<<<< HEAD

        RaftClientReply getValue = null;
        try {
            getValue = raftClient.sendReadOnly(Message.valueOf("GET " + chave.getKey()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        String[] stringRetorno = getValue.getMessage().getContent().toString(Charset.defaultCharset()).split(" ");
        long versao = Long.parseLong(stringRetorno[1]);
        long timeStamp = Long.parseLong(stringRetorno[2]);
        ByteString dados = ByteString.copyFrom(stringRetorno[3].getBytes());
        Valor vLinha = Valor.newBuilder().setVersion(versao).setTimeSt(timeStamp).setData(dados).build();
        Saida response = Saida.newBuilder().setError(stringRetorno[0]).setValue(vLinha).build();
=======
        RaftClientReply getValue = raftClient.sendReadOnly(Message.valueOf("GET " + chave.getKey()));
        Saida response = getValue.getMessage().getContent();
>>>>>>> 0b86311aef750916236d8271a290e5e425793239

        // Envio da resposta
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void delK(Chave chave, StreamObserver<Saida> responseObserver) {

        RaftClientReply getValue = null;
        try {
            getValue = raftClient.send(Message.valueOf("DELK " + chave));
        } catch (IOException e) {
            e.printStackTrace();
        }
        String[] stringRetorno = getValue.getMessage().getContent().toString(Charset.defaultCharset()).split(" ");
        long versao = Long.parseLong(stringRetorno[1]);
        long timeStamp = Long.parseLong(stringRetorno[2]);
        ByteString dados = ByteString.copyFrom(stringRetorno[3].getBytes());
        Valor vLinha = Valor.newBuilder().setVersion(versao).setTimeSt(timeStamp).setData(dados).build();
        Saida response = Saida.newBuilder().setError(stringRetorno[0]).setValue(vLinha).build();
        
        // Envio da resposta
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void delKV(ChaveVersao kv, StreamObserver<Saida> responseObserver) {

        Chave chave = kv.getKey();
        long versao = kv.getVersion();

        RaftClientReply getValue = null;
        try {
            getValue = raftClient.send(Message.valueOf("DELKV " + chave.getKey() + " " + versao));
        } catch (IOException e) {
            e.printStackTrace();
        }
        String[] stringRetorno = getValue.getMessage().getContent().toString(Charset.defaultCharset()).split(" ");
        versao = Long.parseLong(stringRetorno[1]);
        long timeStamp = Long.parseLong(stringRetorno[2]);
        ByteString dados = ByteString.copyFrom(stringRetorno[3].getBytes());
        Valor vLinha = Valor.newBuilder().setVersion(versao).setTimeSt(timeStamp).setData(dados).build();
        Saida response = Saida.newBuilder().setError(stringRetorno[0]).setValue(vLinha).build();

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

        RaftClientReply getValue = null;
        try {
            getValue = raftClient.send(Message.valueOf("TAS " + chave.getKey() + " " + versao + " " + timeSt + " " + dados + " "  + versaoVerificacao));
        } catch (IOException e) {
            e.printStackTrace();
        }
        String[] stringRetorno = getValue.getMessage().getContent().toString(Charset.defaultCharset()).split(" ");
        versao = Long.parseLong(stringRetorno[1]);
        long timeStamp = Long.parseLong(stringRetorno[2]);
        dados = ByteString.copyFrom(stringRetorno[3].getBytes());
        Valor vLinha = Valor.newBuilder().setVersion(versao).setTimeSt(timeStamp).setData(dados).build();
        Saida response = Saida.newBuilder().setError(stringRetorno[0]).setValue(vLinha).build();

        // Envio da resposta
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}