package br.ufu.sd.client;

import br.ufu.sd.grpc.*;
import br.ufu.sd.server.HashTableServer;
import com.google.protobuf.ByteString;
import io.grpc.Channel;
import io.grpc.StatusRuntimeException;

import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Cliente final
 * A simple client that requests a API from the {@link HashTableServer}.
 */
public class HashTableApi {
    private static final Logger logger = Logger.getLogger(HashTableApi.class.getName());

    private final APIGrpc.APIBlockingStub blockingStub;

    /** Construct client for accessing HashMap server using the existing channel. */
    public HashTableApi(Channel channel) {
        blockingStub = APIGrpc.newBlockingStub(channel);
    }

    public Saida set(long k, ByteString d) {
        long timestamp = new Date().getTime();
        Chave chave = Chave.newBuilder().setKey(k).build();
        Valor valor = Valor.newBuilder().setVersion(1).setTimeSt(timestamp).setData(d).build();
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

    public Saida testAndSet(long k, long vers, ByteString d) {
        long timestamp = new Date().getTime();
        Chave key = Chave.newBuilder().setKey(k).build();
        Valor val = Valor.newBuilder().setVersion(vers).setTimeSt(timestamp).setData(d).build();
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
}