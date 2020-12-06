package br.ufu.sd.server;

import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

/**
 * Server that manages startup/shutdown of a {@code API} server.
 */
public class HashTableServer {
    private static final Logger logger = Logger.getLogger(HashTableServer.class.getName());

    private Server server;

    private void start(int intervalo) throws IOException {
        int port = 50051;
        server = ServerBuilder.forPort(port)
                .addService(new ApiService(intervalo))
                .build()
                .start();
        logger.info("Servidor iniciado na porta " + port + ". Salvamento em disco com periodicidade de " + intervalo + " segundos");
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
        int periodicidade = 2;
        if(args.length > 0){
        	periodicidade = Integer.parseInt(args[0]);
        }
        server.start(periodicidade);
        server.blockUntilShutdown();
    }
}
