package br.ufu.sd.grpc;

import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.29.0)",
    comments = "Source: hashtable.proto")
public final class APIGrpc {

  private APIGrpc() {}

  public static final String SERVICE_NAME = "hashtable.API";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<br.ufu.sd.grpc.ChaveValor,
      br.ufu.sd.grpc.Saida> getSetMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "set",
      requestType = br.ufu.sd.grpc.ChaveValor.class,
      responseType = br.ufu.sd.grpc.Saida.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<br.ufu.sd.grpc.ChaveValor,
      br.ufu.sd.grpc.Saida> getSetMethod() {
    io.grpc.MethodDescriptor<br.ufu.sd.grpc.ChaveValor, br.ufu.sd.grpc.Saida> getSetMethod;
    if ((getSetMethod = APIGrpc.getSetMethod) == null) {
      synchronized (APIGrpc.class) {
        if ((getSetMethod = APIGrpc.getSetMethod) == null) {
          APIGrpc.getSetMethod = getSetMethod =
              io.grpc.MethodDescriptor.<br.ufu.sd.grpc.ChaveValor, br.ufu.sd.grpc.Saida>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "set"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  br.ufu.sd.grpc.ChaveValor.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  br.ufu.sd.grpc.Saida.getDefaultInstance()))
              .setSchemaDescriptor(new APIMethodDescriptorSupplier("set"))
              .build();
        }
      }
    }
    return getSetMethod;
  }

  private static volatile io.grpc.MethodDescriptor<br.ufu.sd.grpc.Chave,
      br.ufu.sd.grpc.Saida> getGetMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "get",
      requestType = br.ufu.sd.grpc.Chave.class,
      responseType = br.ufu.sd.grpc.Saida.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<br.ufu.sd.grpc.Chave,
      br.ufu.sd.grpc.Saida> getGetMethod() {
    io.grpc.MethodDescriptor<br.ufu.sd.grpc.Chave, br.ufu.sd.grpc.Saida> getGetMethod;
    if ((getGetMethod = APIGrpc.getGetMethod) == null) {
      synchronized (APIGrpc.class) {
        if ((getGetMethod = APIGrpc.getGetMethod) == null) {
          APIGrpc.getGetMethod = getGetMethod =
              io.grpc.MethodDescriptor.<br.ufu.sd.grpc.Chave, br.ufu.sd.grpc.Saida>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "get"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  br.ufu.sd.grpc.Chave.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  br.ufu.sd.grpc.Saida.getDefaultInstance()))
              .setSchemaDescriptor(new APIMethodDescriptorSupplier("get"))
              .build();
        }
      }
    }
    return getGetMethod;
  }

  private static volatile io.grpc.MethodDescriptor<br.ufu.sd.grpc.Chave,
      br.ufu.sd.grpc.Saida> getDelKMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "delK",
      requestType = br.ufu.sd.grpc.Chave.class,
      responseType = br.ufu.sd.grpc.Saida.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<br.ufu.sd.grpc.Chave,
      br.ufu.sd.grpc.Saida> getDelKMethod() {
    io.grpc.MethodDescriptor<br.ufu.sd.grpc.Chave, br.ufu.sd.grpc.Saida> getDelKMethod;
    if ((getDelKMethod = APIGrpc.getDelKMethod) == null) {
      synchronized (APIGrpc.class) {
        if ((getDelKMethod = APIGrpc.getDelKMethod) == null) {
          APIGrpc.getDelKMethod = getDelKMethod =
              io.grpc.MethodDescriptor.<br.ufu.sd.grpc.Chave, br.ufu.sd.grpc.Saida>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "delK"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  br.ufu.sd.grpc.Chave.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  br.ufu.sd.grpc.Saida.getDefaultInstance()))
              .setSchemaDescriptor(new APIMethodDescriptorSupplier("delK"))
              .build();
        }
      }
    }
    return getDelKMethod;
  }

  private static volatile io.grpc.MethodDescriptor<br.ufu.sd.grpc.ChaveVersao,
      br.ufu.sd.grpc.Saida> getDelKVMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "delKV",
      requestType = br.ufu.sd.grpc.ChaveVersao.class,
      responseType = br.ufu.sd.grpc.Saida.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<br.ufu.sd.grpc.ChaveVersao,
      br.ufu.sd.grpc.Saida> getDelKVMethod() {
    io.grpc.MethodDescriptor<br.ufu.sd.grpc.ChaveVersao, br.ufu.sd.grpc.Saida> getDelKVMethod;
    if ((getDelKVMethod = APIGrpc.getDelKVMethod) == null) {
      synchronized (APIGrpc.class) {
        if ((getDelKVMethod = APIGrpc.getDelKVMethod) == null) {
          APIGrpc.getDelKVMethod = getDelKVMethod =
              io.grpc.MethodDescriptor.<br.ufu.sd.grpc.ChaveVersao, br.ufu.sd.grpc.Saida>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "delKV"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  br.ufu.sd.grpc.ChaveVersao.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  br.ufu.sd.grpc.Saida.getDefaultInstance()))
              .setSchemaDescriptor(new APIMethodDescriptorSupplier("delKV"))
              .build();
        }
      }
    }
    return getDelKVMethod;
  }

  private static volatile io.grpc.MethodDescriptor<br.ufu.sd.grpc.ChaveValorVersao,
      br.ufu.sd.grpc.Saida> getTestAndSetMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "testAndSet",
      requestType = br.ufu.sd.grpc.ChaveValorVersao.class,
      responseType = br.ufu.sd.grpc.Saida.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<br.ufu.sd.grpc.ChaveValorVersao,
      br.ufu.sd.grpc.Saida> getTestAndSetMethod() {
    io.grpc.MethodDescriptor<br.ufu.sd.grpc.ChaveValorVersao, br.ufu.sd.grpc.Saida> getTestAndSetMethod;
    if ((getTestAndSetMethod = APIGrpc.getTestAndSetMethod) == null) {
      synchronized (APIGrpc.class) {
        if ((getTestAndSetMethod = APIGrpc.getTestAndSetMethod) == null) {
          APIGrpc.getTestAndSetMethod = getTestAndSetMethod =
              io.grpc.MethodDescriptor.<br.ufu.sd.grpc.ChaveValorVersao, br.ufu.sd.grpc.Saida>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "testAndSet"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  br.ufu.sd.grpc.ChaveValorVersao.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  br.ufu.sd.grpc.Saida.getDefaultInstance()))
              .setSchemaDescriptor(new APIMethodDescriptorSupplier("testAndSet"))
              .build();
        }
      }
    }
    return getTestAndSetMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static APIStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<APIStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<APIStub>() {
        @java.lang.Override
        public APIStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new APIStub(channel, callOptions);
        }
      };
    return APIStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static APIBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<APIBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<APIBlockingStub>() {
        @java.lang.Override
        public APIBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new APIBlockingStub(channel, callOptions);
        }
      };
    return APIBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static APIFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<APIFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<APIFutureStub>() {
        @java.lang.Override
        public APIFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new APIFutureStub(channel, callOptions);
        }
      };
    return APIFutureStub.newStub(factory, channel);
  }

  /**
   */
  public static abstract class APIImplBase implements io.grpc.BindableService {

    /**
     */
    public void set(br.ufu.sd.grpc.ChaveValor request,
        io.grpc.stub.StreamObserver<br.ufu.sd.grpc.Saida> responseObserver) {
      asyncUnimplementedUnaryCall(getSetMethod(), responseObserver);
    }

    /**
     */
    public void get(br.ufu.sd.grpc.Chave request,
        io.grpc.stub.StreamObserver<br.ufu.sd.grpc.Saida> responseObserver) {
      asyncUnimplementedUnaryCall(getGetMethod(), responseObserver);
    }

    /**
     */
    public void delK(br.ufu.sd.grpc.Chave request,
        io.grpc.stub.StreamObserver<br.ufu.sd.grpc.Saida> responseObserver) {
      asyncUnimplementedUnaryCall(getDelKMethod(), responseObserver);
    }

    /**
     */
    public void delKV(br.ufu.sd.grpc.ChaveVersao request,
        io.grpc.stub.StreamObserver<br.ufu.sd.grpc.Saida> responseObserver) {
      asyncUnimplementedUnaryCall(getDelKVMethod(), responseObserver);
    }

    /**
     */
    public void testAndSet(br.ufu.sd.grpc.ChaveValorVersao request,
        io.grpc.stub.StreamObserver<br.ufu.sd.grpc.Saida> responseObserver) {
      asyncUnimplementedUnaryCall(getTestAndSetMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getSetMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                br.ufu.sd.grpc.ChaveValor,
                br.ufu.sd.grpc.Saida>(
                  this, METHODID_SET)))
          .addMethod(
            getGetMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                br.ufu.sd.grpc.Chave,
                br.ufu.sd.grpc.Saida>(
                  this, METHODID_GET)))
          .addMethod(
            getDelKMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                br.ufu.sd.grpc.Chave,
                br.ufu.sd.grpc.Saida>(
                  this, METHODID_DEL_K)))
          .addMethod(
            getDelKVMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                br.ufu.sd.grpc.ChaveVersao,
                br.ufu.sd.grpc.Saida>(
                  this, METHODID_DEL_KV)))
          .addMethod(
            getTestAndSetMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                br.ufu.sd.grpc.ChaveValorVersao,
                br.ufu.sd.grpc.Saida>(
                  this, METHODID_TEST_AND_SET)))
          .build();
    }
  }

  /**
   */
  public static final class APIStub extends io.grpc.stub.AbstractAsyncStub<APIStub> {
    private APIStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected APIStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new APIStub(channel, callOptions);
    }

    /**
     */
    public void set(br.ufu.sd.grpc.ChaveValor request,
        io.grpc.stub.StreamObserver<br.ufu.sd.grpc.Saida> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getSetMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void get(br.ufu.sd.grpc.Chave request,
        io.grpc.stub.StreamObserver<br.ufu.sd.grpc.Saida> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void delK(br.ufu.sd.grpc.Chave request,
        io.grpc.stub.StreamObserver<br.ufu.sd.grpc.Saida> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getDelKMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void delKV(br.ufu.sd.grpc.ChaveVersao request,
        io.grpc.stub.StreamObserver<br.ufu.sd.grpc.Saida> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getDelKVMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void testAndSet(br.ufu.sd.grpc.ChaveValorVersao request,
        io.grpc.stub.StreamObserver<br.ufu.sd.grpc.Saida> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getTestAndSetMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class APIBlockingStub extends io.grpc.stub.AbstractBlockingStub<APIBlockingStub> {
    private APIBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected APIBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new APIBlockingStub(channel, callOptions);
    }

    /**
     */
    public br.ufu.sd.grpc.Saida set(br.ufu.sd.grpc.ChaveValor request) {
      return blockingUnaryCall(
          getChannel(), getSetMethod(), getCallOptions(), request);
    }

    /**
     */
    public br.ufu.sd.grpc.Saida get(br.ufu.sd.grpc.Chave request) {
      return blockingUnaryCall(
          getChannel(), getGetMethod(), getCallOptions(), request);
    }

    /**
     */
    public br.ufu.sd.grpc.Saida delK(br.ufu.sd.grpc.Chave request) {
      return blockingUnaryCall(
          getChannel(), getDelKMethod(), getCallOptions(), request);
    }

    /**
     */
    public br.ufu.sd.grpc.Saida delKV(br.ufu.sd.grpc.ChaveVersao request) {
      return blockingUnaryCall(
          getChannel(), getDelKVMethod(), getCallOptions(), request);
    }

    /**
     */
    public br.ufu.sd.grpc.Saida testAndSet(br.ufu.sd.grpc.ChaveValorVersao request) {
      return blockingUnaryCall(
          getChannel(), getTestAndSetMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class APIFutureStub extends io.grpc.stub.AbstractFutureStub<APIFutureStub> {
    private APIFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected APIFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new APIFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<br.ufu.sd.grpc.Saida> set(
        br.ufu.sd.grpc.ChaveValor request) {
      return futureUnaryCall(
          getChannel().newCall(getSetMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<br.ufu.sd.grpc.Saida> get(
        br.ufu.sd.grpc.Chave request) {
      return futureUnaryCall(
          getChannel().newCall(getGetMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<br.ufu.sd.grpc.Saida> delK(
        br.ufu.sd.grpc.Chave request) {
      return futureUnaryCall(
          getChannel().newCall(getDelKMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<br.ufu.sd.grpc.Saida> delKV(
        br.ufu.sd.grpc.ChaveVersao request) {
      return futureUnaryCall(
          getChannel().newCall(getDelKVMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<br.ufu.sd.grpc.Saida> testAndSet(
        br.ufu.sd.grpc.ChaveValorVersao request) {
      return futureUnaryCall(
          getChannel().newCall(getTestAndSetMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_SET = 0;
  private static final int METHODID_GET = 1;
  private static final int METHODID_DEL_K = 2;
  private static final int METHODID_DEL_KV = 3;
  private static final int METHODID_TEST_AND_SET = 4;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final APIImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(APIImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_SET:
          serviceImpl.set((br.ufu.sd.grpc.ChaveValor) request,
              (io.grpc.stub.StreamObserver<br.ufu.sd.grpc.Saida>) responseObserver);
          break;
        case METHODID_GET:
          serviceImpl.get((br.ufu.sd.grpc.Chave) request,
              (io.grpc.stub.StreamObserver<br.ufu.sd.grpc.Saida>) responseObserver);
          break;
        case METHODID_DEL_K:
          serviceImpl.delK((br.ufu.sd.grpc.Chave) request,
              (io.grpc.stub.StreamObserver<br.ufu.sd.grpc.Saida>) responseObserver);
          break;
        case METHODID_DEL_KV:
          serviceImpl.delKV((br.ufu.sd.grpc.ChaveVersao) request,
              (io.grpc.stub.StreamObserver<br.ufu.sd.grpc.Saida>) responseObserver);
          break;
        case METHODID_TEST_AND_SET:
          serviceImpl.testAndSet((br.ufu.sd.grpc.ChaveValorVersao) request,
              (io.grpc.stub.StreamObserver<br.ufu.sd.grpc.Saida>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class APIBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    APIBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return br.ufu.sd.grpc.HashTableProto.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("API");
    }
  }

  private static final class APIFileDescriptorSupplier
      extends APIBaseDescriptorSupplier {
    APIFileDescriptorSupplier() {}
  }

  private static final class APIMethodDescriptorSupplier
      extends APIBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    APIMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (APIGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new APIFileDescriptorSupplier())
              .addMethod(getSetMethod())
              .addMethod(getGetMethod())
              .addMethod(getDelKMethod())
              .addMethod(getDelKVMethod())
              .addMethod(getTestAndSetMethod())
              .build();
        }
      }
    }
    return result;
  }
}
