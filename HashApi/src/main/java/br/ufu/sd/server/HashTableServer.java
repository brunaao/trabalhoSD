package br.ufu.sd.server;

import br.ufu.sd.grpc.*;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import com.google.protobuf.ByteString;

/**
 * Server that manages startup/shutdown of a {@code API} server.
 */
public class HashTableServer {
    private static final Logger logger = Logger.getLogger(HashTableServer.class.getName());

    private Server server;

    private void start() throws IOException {
        int port = 50051;
        server = ServerBuilder.forPort(port)
                .addService(new APIService())
                .build()
                .start();
        logger.info("Server started, listening on " + port);
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                // Use stderr here since the logger may have been reset by its JVM shutdown hook.
                System.err.println("*** shutting down gRPC server since JVM is shutting down");
                try {
                    HashTableServer.this.stop();
                } catch (InterruptedException e) {
                    e.printStackTrace(System.err);
                }
                System.err.println("*** server shut down");
            }
        });
    }

    private void stop() throws InterruptedException {
        if (server != null) {
            server.shutdown().awaitTermination(30, TimeUnit.SECONDS);
        }
    }

    /**
     * Await termination on the main thread since the grpc library uses daemon threads.
     */
    private void blockUntilShutdown() throws InterruptedException {
        if (server != null) {
            server.awaitTermination();
        }
    }

    /**
     * Main launches the server from the command line.
     */
    public static void main(String[] args) throws IOException, InterruptedException {
        final HashTableServer server = new HashTableServer();
        server.start();
        server.blockUntilShutdown();
    }

    static class APIService extends APIGrpc.APIImplBase {

        private ConcurrentMap<Chave, Valor> map = new ConcurrentHashMap<Chave, Valor>();

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
    }
}