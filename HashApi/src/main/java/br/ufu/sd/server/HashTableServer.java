package br.ufu.sd.server;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import org.apache.ratis.client.RaftClient;
import org.apache.ratis.conf.Parameters;
import org.apache.ratis.conf.RaftProperties;
import org.apache.ratis.grpc.GrpcConfigKeys;
import org.apache.ratis.grpc.GrpcFactory;
import org.apache.ratis.protocol.*;
import org.apache.ratis.server.RaftServer;
import org.apache.ratis.server.RaftServerConfigKeys;
import org.apache.ratis.thirdparty.com.google.protobuf.ByteString;
import org.apache.ratis.util.LifeCycle;

import java.io.File;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 * Server that manages startup/shutdown of a {@code API} server.
 */
public class HashTableServer {
    private static final Logger logger = Logger.getLogger(HashTableServer.class.getName());
    public static final String groupId = "raft_group____um";
    public static Map<String, InetSocketAddress> id2addr;
    public static List<RaftPeer> addresses;

    private Server server;

    public HashTableServer() {
        id2addr = new HashMap<>();
        id2addr.put("p1", new InetSocketAddress("127.0.0.1", 3000));
        id2addr.put("p2", new InetSocketAddress("127.0.0.1", 3500));
        id2addr.put("p3", new InetSocketAddress("127.0.0.1", 4000));

        addresses = id2addr.entrySet()
                .stream()
                .map(e -> new RaftPeer(RaftPeerId.valueOf(e.getKey()), e.getValue()))
                .collect(Collectors.toList());

    }

    // Server GRPC
    private void start(String serverId, RaftClient raftClient) throws IOException {
        int port = 50051;
        server = ServerBuilder.forPort(port)
                .addService(new ApiService(raftClient))
                .build()
                .start();
        logger.info("Servidor iniciado na porta " + port + ".");
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
        try {
            startRatis(serverId);
        } catch (InterruptedException e) {
            e.printStackTrace();
            try {
                stop();
            } catch (InterruptedException interruptedException) {
                System.out.println("Não foi possível finalizar a execução do servidor GRPC");
            }
            System.exit(1);
        }
    }

    // Server Ratis
    private void startRatis(String serverId) throws InterruptedException {
        //Setup for this node.
        RaftPeerId myId = RaftPeerId.valueOf(serverId);

        if (addresses.stream().noneMatch(p -> p.getId().equals(myId)))
        {
            System.out.println("Identificador " + serverId + " é inválido.");
            System.exit(1);
        }

        RaftProperties properties = new RaftProperties();
        properties.setInt(GrpcConfigKeys.OutputStream.RETRY_TIMES_KEY, Integer.MAX_VALUE);
        GrpcConfigKeys.Server.setPort(properties, id2addr.get(serverId).getPort());
        RaftServerConfigKeys.setStorageDir(properties, Collections.singletonList(new File("/tmp/" + myId)));


        //Join the group of processes.
        final RaftGroup raftGroup = RaftGroup.valueOf(RaftGroupId.valueOf(ByteString.copyFromUtf8(groupId)), addresses);
        RaftServer raftServer = null;
        try {
            raftServer = RaftServer.newBuilder()
                    .setServerId(myId)
                    .setStateMachine(new StateMachine()).setProperties(properties)
                    .setGroup(raftGroup)
                    .build();
            raftServer.start();
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }

        while(raftServer.getLifeCycleState() != LifeCycle.State.CLOSED) {
            TimeUnit.SECONDS.sleep(1);
        }
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
        RaftClient raftClient = HashTableServer.getRaftClient();
        final HashTableServer server = new HashTableServer();
        if (args.length == 1) {
            server.start(args[0], raftClient);
        } else {
            System.out.println("É necessário informar um identificador para o servidor");
            System.exit(1);
        }
        server.blockUntilShutdown();
    }

    public static RaftClient getRaftClient() {
        final RaftGroup raftGroup = RaftGroup.valueOf(RaftGroupId.valueOf(ByteString.copyFromUtf8(groupId)), addresses);
        RaftProperties raftProperties = new RaftProperties();

        return RaftClient.newBuilder()
                .setProperties(raftProperties)
                .setRaftGroup(raftGroup)
                .setClientRpc(new GrpcFactory(new Parameters())
                        .newRaftClientRpc(ClientId.randomId(), raftProperties))
                .build();
    }
}
