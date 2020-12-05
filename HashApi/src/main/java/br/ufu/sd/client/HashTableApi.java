package br.ufu.sd.client;

import br.ufu.sd.grpc.*;
import br.ufu.sd.server.HashTableServer;
import com.google.protobuf.ByteString;
import io.grpc.Channel;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;

import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * A simple client that requests a API from the {@link HashTableServer}.
 */
public class HashTableApi {
    private static final Logger logger = Logger.getLogger(HashTableApi.class.getName());

    private final APIGrpc.APIBlockingStub blockingStub;

    /** Construct client for accessing HashMap server using the existing channel. */
    public HashTableApi(Channel channel) {
        blockingStub = APIGrpc.newBlockingStub(channel);
    }

    public Saida set(long k, long t, ByteString d) {
        Chave chave = Chave.newBuilder().setKey(k).build();
        Valor valor = Valor.newBuilder().setVersion(1).setTimeSt(t).setData(d).build();
        ChaveValor keyVal = ChaveValor.newBuilder().setKey(chave).setValue(valor).build();
        Saida resposta;
        try {
            resposta = blockingStub.set(keyVal);
            return resposta;
        } catch (StatusRuntimeException e) {
            logger.log(Level.WARNING, "RPC failed: {0}", e.getStatus());
            return null;
        }
    }

    public Saida get(long k) {
        Chave key = Chave.newBuilder().setKey(k).build();
        Saida resposta;
        try {
            resposta = blockingStub.get(key);
            return resposta;
        } catch (StatusRuntimeException e) {
            logger.log(Level.WARNING, "RPC failed: {0}", e.getStatus());
            return null;
        }
    }

    public Saida delK(long k) {
        Chave key = Chave.newBuilder().setKey(k).build();
        Saida resposta;
        try {
            resposta = blockingStub.delK(key);
            return resposta;
        } catch (StatusRuntimeException e) {
            logger.log(Level.WARNING, "RPC failed: {0}", e.getStatus());
            return null;
        }
    }

    public Saida delKV(long k, long vers) {
        Chave key = Chave.newBuilder().setKey(k).build();
        ChaveVersao keyVers = ChaveVersao.newBuilder().setKey(key).setVersion(vers).build();
        Saida resposta;
        try {
            resposta = blockingStub.delKV(keyVers);
            return resposta;
        } catch (StatusRuntimeException e) {
            logger.log(Level.WARNING, "RPC failed: {0}", e.getStatus());
            return null;
        }
    }

    public Saida testAndSet(long k, long t, ByteString d, long vers) {
        logger.info("Executando comando del...");
        Chave key = Chave.newBuilder().setKey(k).build();
        Valor val = Valor.newBuilder().setVersion(vers).setTimeSt(t).setData(d).build();
        ChaveValorVersao keyValVers = ChaveValorVersao.newBuilder().setKey(key).setValue(val).setVersion(vers).build();
        Saida resposta;
        try {
            resposta = blockingStub.testAndSet(keyValVers);
            return resposta;
        } catch (StatusRuntimeException e) {
            logger.log(Level.WARNING, "RPC failed: {0}", e.getStatus());
            return null;
        }
    }

    /**
     * Greet server. If provided, the first element of {@code args} is the name to use in the
     * greeting. The second argument is the target server.
     */
    public static void main(String[] args) throws Exception {
        String target = "localhost:50051";

        if (args.length < 2) {
            System.err.println("ERRO - Numero insuficiente de argumentos (veja --help)");
            System.exit(1);
        }

        if ("--help".equals(args[0])) {
            System.err.println("Maneira de Usar: aplicacao comando [entrada]\n");
            System.err.println("\tcomando - O nome do comando da API (set, get, del, testAndSet)");
            System.err.println("\tentrada - O(s) parametro(s) de entrada do comando");
            System.exit(1);
        }

        String comando = args[0];
        ManagedChannel channel = ManagedChannelBuilder.forTarget(target)
                .usePlaintext()
                .build();
        try {
            HashTableApi client = new HashTableApi(channel);

            long key;
            long timestamp;
            ByteString data;
            long versao;

            switch(comando){

                case "set":
                    if(args.length != 3){
                        System.err.println("ERRO - Numero errado de parametros (o comando set necessita de dois parametros)");
                        System.exit(1);
                    }
                    key = Long.parseLong(args[1]);
                    if(key < 1){
                        System.err.println("ERRO - Sua chave deve ser um inteiro maior que 0)");
                        System.exit(1);
                    }
//                    timestamp = Long.parseLong(args[2]);
                    timestamp = new Date().getTime();
                    data = ByteString.copyFrom(args[2].getBytes());
                    client.set(key, timestamp, data);
                    break;

                case "get":
                    if(args.length != 2){
                        System.err.println("ERRO - Numero errado de parametros (o comando get necessita de apenas um parametro)");
                        System.exit(1);
                    }
                    key = Long.parseLong(args[1]);
                    client.get(key);
                    break;

                case "del":
                    if(args.length > 3){
                        System.err.println("ERRO - Numero errado de parametros (o comando del necessita de um (chave) ou dois (chave, versao) parametro(s))");
                        System.exit(1);
                    }
                    key = Long.parseLong(args[1]);
                    //del(key)
                    if(args.length == 2){
                        client.delK(key);
                    } else {
                        //del(key,versao)
                        versao = Long.parseLong(args[2]);
                        client.delKV(key, versao);
                    }
                    break;

                case "testAndSet":
                    if(args.length != 5){
                        System.err.println("ERRO - Numero errado de parametros (o comando testAndSet necessita de quatro parametros (chave, timestamp, dados, versao) )");
                        System.exit(1);
                    }
                    key = Long.parseLong(args[1]);
                    timestamp = Long.parseLong(args[2]);
                    data = ByteString.copyFrom(args[3].getBytes());
                    versao = Long.parseLong(args[4]);
                    client.testAndSet(key, timestamp, data, versao);
                    break;
                default:
                    System.err.println("ERRO - Comando nao reconhecido (veja --help)");
                    System.exit(1);
            }

        } finally {
            // ManagedChannels use resources like threads and TCP connections. To prevent leaking these
            // resources the channel should be shut down when it will no longer be used. If it may be used
            // again leave it running.
            channel.shutdownNow().awaitTermination(5, TimeUnit.SECONDS);
        }
    }
}