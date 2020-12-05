// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: hashtable.proto

package br.ufu.sd.grpc;

/**
 * Protobuf type {@code hashtable.ChaveVersao}
 */
public final class ChaveVersao extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:hashtable.ChaveVersao)
    ChaveVersaoOrBuilder {
private static final long serialVersionUID = 0L;
  // Use ChaveVersao.newBuilder() to construct.
  private ChaveVersao(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private ChaveVersao() {
  }

  @java.lang.Override
  @SuppressWarnings({"unused"})
  protected java.lang.Object newInstance(
      UnusedPrivateParameter unused) {
    return new ChaveVersao();
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private ChaveVersao(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    this();
    if (extensionRegistry == null) {
      throw new java.lang.NullPointerException();
    }
    com.google.protobuf.UnknownFieldSet.Builder unknownFields =
        com.google.protobuf.UnknownFieldSet.newBuilder();
    try {
      boolean done = false;
      while (!done) {
        int tag = input.readTag();
        switch (tag) {
          case 0:
            done = true;
            break;
          case 10: {
            br.ufu.sd.grpc.Chave.Builder subBuilder = null;
            if (key_ != null) {
              subBuilder = key_.toBuilder();
            }
            key_ = input.readMessage(br.ufu.sd.grpc.Chave.parser(), extensionRegistry);
            if (subBuilder != null) {
              subBuilder.mergeFrom(key_);
              key_ = subBuilder.buildPartial();
            }

            break;
          }
          case 16: {

            version_ = input.readInt64();
            break;
          }
          default: {
            if (!parseUnknownField(
                input, unknownFields, extensionRegistry, tag)) {
              done = true;
            }
            break;
          }
        }
      }
    } catch (com.google.protobuf.InvalidProtocolBufferException e) {
      throw e.setUnfinishedMessage(this);
    } catch (java.io.IOException e) {
      throw new com.google.protobuf.InvalidProtocolBufferException(
          e).setUnfinishedMessage(this);
    } finally {
      this.unknownFields = unknownFields.build();
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return br.ufu.sd.grpc.HashTableProto.internal_static_hashtable_ChaveVersao_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return br.ufu.sd.grpc.HashTableProto.internal_static_hashtable_ChaveVersao_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            br.ufu.sd.grpc.ChaveVersao.class, br.ufu.sd.grpc.ChaveVersao.Builder.class);
  }

  public static final int KEY_FIELD_NUMBER = 1;
  private br.ufu.sd.grpc.Chave key_;
  /**
   * <code>.hashtable.Chave Key = 1;</code>
   * @return Whether the key field is set.
   */
  @java.lang.Override
  public boolean hasKey() {
    return key_ != null;
  }
  /**
   * <code>.hashtable.Chave Key = 1;</code>
   * @return The key.
   */
  @java.lang.Override
  public br.ufu.sd.grpc.Chave getKey() {
    return key_ == null ? br.ufu.sd.grpc.Chave.getDefaultInstance() : key_;
  }
  /**
   * <code>.hashtable.Chave Key = 1;</code>
   */
  @java.lang.Override
  public br.ufu.sd.grpc.ChaveOrBuilder getKeyOrBuilder() {
    return getKey();
  }

  public static final int VERSION_FIELD_NUMBER = 2;
  private long version_;
  /**
   * <code>int64 Version = 2;</code>
   * @return The version.
   */
  @java.lang.Override
  public long getVersion() {
    return version_;
  }

  private byte memoizedIsInitialized = -1;
  @java.lang.Override
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    memoizedIsInitialized = 1;
    return true;
  }

  @java.lang.Override
  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    if (key_ != null) {
      output.writeMessage(1, getKey());
    }
    if (version_ != 0L) {
      output.writeInt64(2, version_);
    }
    unknownFields.writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (key_ != null) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(1, getKey());
    }
    if (version_ != 0L) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt64Size(2, version_);
    }
    size += unknownFields.getSerializedSize();
    memoizedSize = size;
    return size;
  }

  @java.lang.Override
  public boolean equals(final java.lang.Object obj) {
    if (obj == this) {
     return true;
    }
    if (!(obj instanceof br.ufu.sd.grpc.ChaveVersao)) {
      return super.equals(obj);
    }
    br.ufu.sd.grpc.ChaveVersao other = (br.ufu.sd.grpc.ChaveVersao) obj;

    if (hasKey() != other.hasKey()) return false;
    if (hasKey()) {
      if (!getKey()
          .equals(other.getKey())) return false;
    }
    if (getVersion()
        != other.getVersion()) return false;
    if (!unknownFields.equals(other.unknownFields)) return false;
    return true;
  }

  @java.lang.Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptor().hashCode();
    if (hasKey()) {
      hash = (37 * hash) + KEY_FIELD_NUMBER;
      hash = (53 * hash) + getKey().hashCode();
    }
    hash = (37 * hash) + VERSION_FIELD_NUMBER;
    hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
        getVersion());
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static br.ufu.sd.grpc.ChaveVersao parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static br.ufu.sd.grpc.ChaveVersao parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static br.ufu.sd.grpc.ChaveVersao parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static br.ufu.sd.grpc.ChaveVersao parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static br.ufu.sd.grpc.ChaveVersao parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static br.ufu.sd.grpc.ChaveVersao parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static br.ufu.sd.grpc.ChaveVersao parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static br.ufu.sd.grpc.ChaveVersao parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static br.ufu.sd.grpc.ChaveVersao parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static br.ufu.sd.grpc.ChaveVersao parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static br.ufu.sd.grpc.ChaveVersao parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static br.ufu.sd.grpc.ChaveVersao parseFrom(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  @java.lang.Override
  public Builder newBuilderForType() { return newBuilder(); }
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  public static Builder newBuilder(br.ufu.sd.grpc.ChaveVersao prototype) {
    return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
  }
  @java.lang.Override
  public Builder toBuilder() {
    return this == DEFAULT_INSTANCE
        ? new Builder() : new Builder().mergeFrom(this);
  }

  @java.lang.Override
  protected Builder newBuilderForType(
      com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
    Builder builder = new Builder(parent);
    return builder;
  }
  /**
   * Protobuf type {@code hashtable.ChaveVersao}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:hashtable.ChaveVersao)
      br.ufu.sd.grpc.ChaveVersaoOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return br.ufu.sd.grpc.HashTableProto.internal_static_hashtable_ChaveVersao_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return br.ufu.sd.grpc.HashTableProto.internal_static_hashtable_ChaveVersao_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              br.ufu.sd.grpc.ChaveVersao.class, br.ufu.sd.grpc.ChaveVersao.Builder.class);
    }

    // Construct using br.ufu.sd.grpc.ChaveVersao.newBuilder()
    private Builder() {
      maybeForceBuilderInitialization();
    }

    private Builder(
        com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
      super(parent);
      maybeForceBuilderInitialization();
    }
    private void maybeForceBuilderInitialization() {
      if (com.google.protobuf.GeneratedMessageV3
              .alwaysUseFieldBuilders) {
      }
    }
    @java.lang.Override
    public Builder clear() {
      super.clear();
      if (keyBuilder_ == null) {
        key_ = null;
      } else {
        key_ = null;
        keyBuilder_ = null;
      }
      version_ = 0L;

      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return br.ufu.sd.grpc.HashTableProto.internal_static_hashtable_ChaveVersao_descriptor;
    }

    @java.lang.Override
    public br.ufu.sd.grpc.ChaveVersao getDefaultInstanceForType() {
      return br.ufu.sd.grpc.ChaveVersao.getDefaultInstance();
    }

    @java.lang.Override
    public br.ufu.sd.grpc.ChaveVersao build() {
      br.ufu.sd.grpc.ChaveVersao result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public br.ufu.sd.grpc.ChaveVersao buildPartial() {
      br.ufu.sd.grpc.ChaveVersao result = new br.ufu.sd.grpc.ChaveVersao(this);
      if (keyBuilder_ == null) {
        result.key_ = key_;
      } else {
        result.key_ = keyBuilder_.build();
      }
      result.version_ = version_;
      onBuilt();
      return result;
    }

    @java.lang.Override
    public Builder clone() {
      return super.clone();
    }
    @java.lang.Override
    public Builder setField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return super.setField(field, value);
    }
    @java.lang.Override
    public Builder clearField(
        com.google.protobuf.Descriptors.FieldDescriptor field) {
      return super.clearField(field);
    }
    @java.lang.Override
    public Builder clearOneof(
        com.google.protobuf.Descriptors.OneofDescriptor oneof) {
      return super.clearOneof(oneof);
    }
    @java.lang.Override
    public Builder setRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        int index, java.lang.Object value) {
      return super.setRepeatedField(field, index, value);
    }
    @java.lang.Override
    public Builder addRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return super.addRepeatedField(field, value);
    }
    @java.lang.Override
    public Builder mergeFrom(com.google.protobuf.Message other) {
      if (other instanceof br.ufu.sd.grpc.ChaveVersao) {
        return mergeFrom((br.ufu.sd.grpc.ChaveVersao)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(br.ufu.sd.grpc.ChaveVersao other) {
      if (other == br.ufu.sd.grpc.ChaveVersao.getDefaultInstance()) return this;
      if (other.hasKey()) {
        mergeKey(other.getKey());
      }
      if (other.getVersion() != 0L) {
        setVersion(other.getVersion());
      }
      this.mergeUnknownFields(other.unknownFields);
      onChanged();
      return this;
    }

    @java.lang.Override
    public final boolean isInitialized() {
      return true;
    }

    @java.lang.Override
    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      br.ufu.sd.grpc.ChaveVersao parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (br.ufu.sd.grpc.ChaveVersao) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }

    private br.ufu.sd.grpc.Chave key_;
    private com.google.protobuf.SingleFieldBuilderV3<
        br.ufu.sd.grpc.Chave, br.ufu.sd.grpc.Chave.Builder, br.ufu.sd.grpc.ChaveOrBuilder> keyBuilder_;
    /**
     * <code>.hashtable.Chave Key = 1;</code>
     * @return Whether the key field is set.
     */
    public boolean hasKey() {
      return keyBuilder_ != null || key_ != null;
    }
    /**
     * <code>.hashtable.Chave Key = 1;</code>
     * @return The key.
     */
    public br.ufu.sd.grpc.Chave getKey() {
      if (keyBuilder_ == null) {
        return key_ == null ? br.ufu.sd.grpc.Chave.getDefaultInstance() : key_;
      } else {
        return keyBuilder_.getMessage();
      }
    }
    /**
     * <code>.hashtable.Chave Key = 1;</code>
     */
    public Builder setKey(br.ufu.sd.grpc.Chave value) {
      if (keyBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        key_ = value;
        onChanged();
      } else {
        keyBuilder_.setMessage(value);
      }

      return this;
    }
    /**
     * <code>.hashtable.Chave Key = 1;</code>
     */
    public Builder setKey(
        br.ufu.sd.grpc.Chave.Builder builderForValue) {
      if (keyBuilder_ == null) {
        key_ = builderForValue.build();
        onChanged();
      } else {
        keyBuilder_.setMessage(builderForValue.build());
      }

      return this;
    }
    /**
     * <code>.hashtable.Chave Key = 1;</code>
     */
    public Builder mergeKey(br.ufu.sd.grpc.Chave value) {
      if (keyBuilder_ == null) {
        if (key_ != null) {
          key_ =
            br.ufu.sd.grpc.Chave.newBuilder(key_).mergeFrom(value).buildPartial();
        } else {
          key_ = value;
        }
        onChanged();
      } else {
        keyBuilder_.mergeFrom(value);
      }

      return this;
    }
    /**
     * <code>.hashtable.Chave Key = 1;</code>
     */
    public Builder clearKey() {
      if (keyBuilder_ == null) {
        key_ = null;
        onChanged();
      } else {
        key_ = null;
        keyBuilder_ = null;
      }

      return this;
    }
    /**
     * <code>.hashtable.Chave Key = 1;</code>
     */
    public br.ufu.sd.grpc.Chave.Builder getKeyBuilder() {
      
      onChanged();
      return getKeyFieldBuilder().getBuilder();
    }
    /**
     * <code>.hashtable.Chave Key = 1;</code>
     */
    public br.ufu.sd.grpc.ChaveOrBuilder getKeyOrBuilder() {
      if (keyBuilder_ != null) {
        return keyBuilder_.getMessageOrBuilder();
      } else {
        return key_ == null ?
            br.ufu.sd.grpc.Chave.getDefaultInstance() : key_;
      }
    }
    /**
     * <code>.hashtable.Chave Key = 1;</code>
     */
    private com.google.protobuf.SingleFieldBuilderV3<
        br.ufu.sd.grpc.Chave, br.ufu.sd.grpc.Chave.Builder, br.ufu.sd.grpc.ChaveOrBuilder> 
        getKeyFieldBuilder() {
      if (keyBuilder_ == null) {
        keyBuilder_ = new com.google.protobuf.SingleFieldBuilderV3<
            br.ufu.sd.grpc.Chave, br.ufu.sd.grpc.Chave.Builder, br.ufu.sd.grpc.ChaveOrBuilder>(
                getKey(),
                getParentForChildren(),
                isClean());
        key_ = null;
      }
      return keyBuilder_;
    }

    private long version_ ;
    /**
     * <code>int64 Version = 2;</code>
     * @return The version.
     */
    @java.lang.Override
    public long getVersion() {
      return version_;
    }
    /**
     * <code>int64 Version = 2;</code>
     * @param value The version to set.
     * @return This builder for chaining.
     */
    public Builder setVersion(long value) {
      
      version_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>int64 Version = 2;</code>
     * @return This builder for chaining.
     */
    public Builder clearVersion() {
      
      version_ = 0L;
      onChanged();
      return this;
    }
    @java.lang.Override
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.setUnknownFields(unknownFields);
    }

    @java.lang.Override
    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.mergeUnknownFields(unknownFields);
    }


    // @@protoc_insertion_point(builder_scope:hashtable.ChaveVersao)
  }

  // @@protoc_insertion_point(class_scope:hashtable.ChaveVersao)
  private static final br.ufu.sd.grpc.ChaveVersao DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new br.ufu.sd.grpc.ChaveVersao();
  }

  public static br.ufu.sd.grpc.ChaveVersao getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<ChaveVersao>
      PARSER = new com.google.protobuf.AbstractParser<ChaveVersao>() {
    @java.lang.Override
    public ChaveVersao parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new ChaveVersao(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<ChaveVersao> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<ChaveVersao> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public br.ufu.sd.grpc.ChaveVersao getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

