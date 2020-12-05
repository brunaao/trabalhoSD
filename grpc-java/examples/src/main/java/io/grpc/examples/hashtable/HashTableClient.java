
package io.grpc.examples.hashtable;

import io.grpc.Channel;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.google.protobuf.ByteString;

/**
 * A simple client that requests a API from the {@link HashMapServer}.
 */
public class HashTableClient {
  private static final Logger logger = Logger.getLogger(HashTableClient.class.getName());

  private final APIGrpc.APIBlockingStub blockingStub;

  /** Construct client for accessing HashMap server using the existing channel. */
  public HashTableClient(Channel channel) {
    // 'channel' here is a Channel, not a ManagedChannel, so it is not this code's responsibility to
    // shut it down.

    // Passing Channels to code makes code easier to test and makes it easier to reuse Channels.
    blockingStub = APIGrpc.newBlockingStub(channel);
  }
  
  public void set(long k, long t, ByteString d) {
    logger.info("Executando comando set...");
    Chave key = Chave.newBuilder().setKey(k).build();
    Valor val = Valor.newBuilder().setVersion(1).setTimeSt(t).setData(d).build();
    ChaveValor keyVal = ChaveValor.newBuilder().setKey(key).setValue(val).build();
    Saida resposta;
    try {
      resposta = blockingStub.set(keyVal);
    } catch (StatusRuntimeException e) {
      logger.log(Level.WARNING, "RPC failed: {0}", e.getStatus());
      return;
    }
    System.out.println("Resposta:\n" + resposta.getError() + "\n" + resposta.getValue());
  }
  
  public void get(long k) {
    logger.info("Executando comando get...");
    Chave key = Chave.newBuilder().setKey(k).build();
    Saida resposta;
    try {
      resposta = blockingStub.get(key);
    } catch (StatusRuntimeException e) {
      logger.log(Level.WARNING, "RPC failed: {0}", e.getStatus());
      return;
    }
    System.out.println("Resposta:\n" + resposta.getError() + "\n" + resposta.getValue());
  }
  
  public void delK(long k) {
  	logger.info("Executando comando del...");
    Chave key = Chave.newBuilder().setKey(k).build();
    Saida resposta;
    try {
      resposta = blockingStub.delK(key);
    } catch (StatusRuntimeException e) {
      logger.log(Level.WARNING, "RPC failed: {0}", e.getStatus());
      return;
    }
    System.out.println("Resposta:\n" + resposta.getError() + "\n" + resposta.getValue());
  }
  
  public void delKV(long k, long vers) {
  	logger.info("Executando comando del...");
  	Chave key = Chave.newBuilder().setKey(k).build();
    ChaveVersao keyVers = ChaveVersao.newBuilder().setKey(key).setVersion(vers).build();
    Saida resposta;
    try {
      resposta = blockingStub.delKV(keyVers);
    } catch (StatusRuntimeException e) {
      logger.log(Level.WARNING, "RPC failed: {0}", e.getStatus());
      return;
    }
    System.out.println("Resposta:\n" + resposta.getError() + "\n" + resposta.getValue());
  }
  
  public void testAndSet(long k, long t, ByteString d, long vers) {
  	logger.info("Executando comando del...");
  	Chave key = Chave.newBuilder().setKey(k).build();
  	Valor val = Valor.newBuilder().setVersion(vers).setTimeSt(t).setData(d).build();
    ChaveValorVersao keyValVers = ChaveValorVersao.newBuilder().setKey(key).setValue(val).setVersion(vers).build();
    Saida resposta;
    try {
      resposta = blockingStub.testAndSet(keyValVers);
    } catch (StatusRuntimeException e) {
      logger.log(Level.WARNING, "RPC failed: {0}", e.getStatus());
      return;
    }
    System.out.println("Resposta:\n" + resposta.getError() + "\n" + resposta.getValue());
  }

  /**
   * Greet server. If provided, the first element of {@code args} is the name to use in the
   * greeting. The second argument is the target server.
   */
  public static void main(String[] args) throws Exception {
    
    // Access a service running on the local machine on port 50051
    String target = "localhost:50051";
    
    if ("--help".equals(args[0])) {
    	System.err.println("Maneira de Usar: [comando [entrada]]");
      System.err.println("");
      System.err.println("  comando - O nome do comando da API (set, get, del, testAndSet)");
      System.err.println("  entrada - O(s) parametro(s) de entrada do comando");
      System.exit(1);
    }
    if (args.length < 2) {
      System.err.println("ERRO - Numero insuficiente de argumentos (veja --help)");
      System.exit(1);
    }
    
    String comando = args[0];

    // Create a communication channel to the server
    ManagedChannel channel = ManagedChannelBuilder.forTarget(target)
        // Channels are secure by default (via SSL/TLS). For the example we disable TLS to avoid
        // needing certificates.
        .usePlaintext()
        .build();
    try {
      HashTableClient client = new HashTableClient(channel);
      
      long k;
      long t;
      ByteString d;
      long vers;
      
      switch(comando){
      
    	case "set":
    		if(args.length != 4){
		  		System.err.println("ERRO - Numero errado de parametros (o comando set necessita de tres parametros)");
		    	System.exit(1);
      	}
    		k = Long.parseLong(args[1]);
    		if(k < 1){
    			System.err.println("ERRO - Sua chave deve ser um inteiro maior que 0)");
		    	System.exit(1);
    		}
    		t = Long.parseLong(args[2]);
    		d = ByteString.copyFrom(args[3].getBytes());
    		client.set(k, t, d);
    		break;
    		
    	case "get":
    		if(args.length != 2){
		  		System.err.println("ERRO - Numero errado de parametros (o comando get necessita de apenas um parametro)");
		    	System.exit(1);
      	}
    		k = Long.parseLong(args[1]);
    		client.get(k);
    		break;
    		
    	case "del":
    		if(args.length < 2 || args.length > 3){
    			System.err.println("ERRO - Numero errado de parametros (o comando del necessita de um (chave) ou dois (chave, versao) parametro(s))");
		    	System.exit(1);
    		}
    		k = Long.parseLong(args[1]);
    		//del(k)
    		if(args.length == 2){
    			client.delK(k);
    		} else {
    		//del(k,vers)
    			vers = Long.parseLong(args[2]);
    			client.delKV(k, vers);
    		}
    		break;
    		
    	case "testAndSet":
    		if(args.length != 5){
		  		System.err.println("ERRO - Numero errado de parametros (o comando testAndSet necessita de quatro parametros (chave, timestamp, dados, versao) )");
		    	System.exit(1);
      	}
      	k = Long.parseLong(args[1]);
      	t = Long.parseLong(args[2]);
    		d = ByteString.copyFrom(args[3].getBytes());
    		vers = Long.parseLong(args[4]);
    		client.testAndSet(k, t, d, vers);
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
