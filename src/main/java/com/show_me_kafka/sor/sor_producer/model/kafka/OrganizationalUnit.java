/**
 * Autogenerated by Avro
 *
 * DO NOT EDIT DIRECTLY
 */
package com.show_me_kafka.sor.sor_producer.model.kafka;

import org.apache.avro.specific.SpecificData;
import org.apache.avro.message.BinaryMessageEncoder;
import org.apache.avro.message.BinaryMessageDecoder;
import org.apache.avro.message.SchemaStore;

@SuppressWarnings("all")
@org.apache.avro.specific.AvroGenerated
public class OrganizationalUnit extends org.apache.avro.specific.SpecificRecordBase implements org.apache.avro.specific.SpecificRecord {
  private static final long serialVersionUID = -1441692836654601792L;
  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"record\",\"name\":\"OrganizationalUnit\",\"namespace\":\"com.show_me_kafka.sor.sor_producer.model.kafka\",\"fields\":[{\"name\":\"SorIdentifier\",\"type\":\"string\"},{\"name\":\"EntityName\",\"type\":\"string\"},{\"name\":\"EntityTypeIdentifier\",\"type\":\"string\"},{\"name\":\"ParentSorIdentifier\",\"type\":\"string\"}]}");
  public static org.apache.avro.Schema getClassSchema() { return SCHEMA$; }

  private static SpecificData MODEL$ = new SpecificData();

  private static final BinaryMessageEncoder<OrganizationalUnit> ENCODER =
      new BinaryMessageEncoder<OrganizationalUnit>(MODEL$, SCHEMA$);

  private static final BinaryMessageDecoder<OrganizationalUnit> DECODER =
      new BinaryMessageDecoder<OrganizationalUnit>(MODEL$, SCHEMA$);

  /**
   * Return the BinaryMessageDecoder instance used by this class.
   */
  public static BinaryMessageDecoder<OrganizationalUnit> getDecoder() {
    return DECODER;
  }

  /**
   * Create a new BinaryMessageDecoder instance for this class that uses the specified {@link SchemaStore}.
   * @param resolver a {@link SchemaStore} used to find schemas by fingerprint
   */
  public static BinaryMessageDecoder<OrganizationalUnit> createDecoder(SchemaStore resolver) {
    return new BinaryMessageDecoder<OrganizationalUnit>(MODEL$, SCHEMA$, resolver);
  }

  /** Serializes this OrganizationalUnit to a ByteBuffer. */
  public java.nio.ByteBuffer toByteBuffer() throws java.io.IOException {
    return ENCODER.encode(this);
  }

  /** Deserializes a OrganizationalUnit from a ByteBuffer. */
  public static OrganizationalUnit fromByteBuffer(
      java.nio.ByteBuffer b) throws java.io.IOException {
    return DECODER.decode(b);
  }

  @Deprecated public java.lang.CharSequence SorIdentifier;
  @Deprecated public java.lang.CharSequence EntityName;
  @Deprecated public java.lang.CharSequence EntityTypeIdentifier;
  @Deprecated public java.lang.CharSequence ParentSorIdentifier;

  /**
   * Default constructor.  Note that this does not initialize fields
   * to their default values from the schema.  If that is desired then
   * one should use <code>newBuilder()</code>.
   */
  public OrganizationalUnit() {}

  /**
   * All-args constructor.
   * @param SorIdentifier The new value for SorIdentifier
   * @param EntityName The new value for EntityName
   * @param EntityTypeIdentifier The new value for EntityTypeIdentifier
   * @param ParentSorIdentifier The new value for ParentSorIdentifier
   */
  public OrganizationalUnit(java.lang.CharSequence SorIdentifier, java.lang.CharSequence EntityName, java.lang.CharSequence EntityTypeIdentifier, java.lang.CharSequence ParentSorIdentifier) {
    this.SorIdentifier = SorIdentifier;
    this.EntityName = EntityName;
    this.EntityTypeIdentifier = EntityTypeIdentifier;
    this.ParentSorIdentifier = ParentSorIdentifier;
  }

  public org.apache.avro.Schema getSchema() { return SCHEMA$; }
  // Used by DatumWriter.  Applications should not call.
  public java.lang.Object get(int field$) {
    switch (field$) {
    case 0: return SorIdentifier;
    case 1: return EntityName;
    case 2: return EntityTypeIdentifier;
    case 3: return ParentSorIdentifier;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }

  // Used by DatumReader.  Applications should not call.
  @SuppressWarnings(value="unchecked")
  public void put(int field$, java.lang.Object value$) {
    switch (field$) {
    case 0: SorIdentifier = (java.lang.CharSequence)value$; break;
    case 1: EntityName = (java.lang.CharSequence)value$; break;
    case 2: EntityTypeIdentifier = (java.lang.CharSequence)value$; break;
    case 3: ParentSorIdentifier = (java.lang.CharSequence)value$; break;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }

  /**
   * Gets the value of the 'SorIdentifier' field.
   * @return The value of the 'SorIdentifier' field.
   */
  public java.lang.CharSequence getSorIdentifier() {
    return SorIdentifier;
  }

  /**
   * Sets the value of the 'SorIdentifier' field.
   * @param value the value to set.
   */
  public void setSorIdentifier(java.lang.CharSequence value) {
    this.SorIdentifier = value;
  }

  /**
   * Gets the value of the 'EntityName' field.
   * @return The value of the 'EntityName' field.
   */
  public java.lang.CharSequence getEntityName() {
    return EntityName;
  }

  /**
   * Sets the value of the 'EntityName' field.
   * @param value the value to set.
   */
  public void setEntityName(java.lang.CharSequence value) {
    this.EntityName = value;
  }

  /**
   * Gets the value of the 'EntityTypeIdentifier' field.
   * @return The value of the 'EntityTypeIdentifier' field.
   */
  public java.lang.CharSequence getEntityTypeIdentifier() {
    return EntityTypeIdentifier;
  }

  /**
   * Sets the value of the 'EntityTypeIdentifier' field.
   * @param value the value to set.
   */
  public void setEntityTypeIdentifier(java.lang.CharSequence value) {
    this.EntityTypeIdentifier = value;
  }

  /**
   * Gets the value of the 'ParentSorIdentifier' field.
   * @return The value of the 'ParentSorIdentifier' field.
   */
  public java.lang.CharSequence getParentSorIdentifier() {
    return ParentSorIdentifier;
  }

  /**
   * Sets the value of the 'ParentSorIdentifier' field.
   * @param value the value to set.
   */
  public void setParentSorIdentifier(java.lang.CharSequence value) {
    this.ParentSorIdentifier = value;
  }

  /**
   * Creates a new OrganizationalUnit RecordBuilder.
   * @return A new OrganizationalUnit RecordBuilder
   */
  public static com.show_me_kafka.sor.sor_producer.model.kafka.OrganizationalUnit.Builder newBuilder() {
    return new com.show_me_kafka.sor.sor_producer.model.kafka.OrganizationalUnit.Builder();
  }

  /**
   * Creates a new OrganizationalUnit RecordBuilder by copying an existing Builder.
   * @param other The existing builder to copy.
   * @return A new OrganizationalUnit RecordBuilder
   */
  public static com.show_me_kafka.sor.sor_producer.model.kafka.OrganizationalUnit.Builder newBuilder(com.show_me_kafka.sor.sor_producer.model.kafka.OrganizationalUnit.Builder other) {
    return new com.show_me_kafka.sor.sor_producer.model.kafka.OrganizationalUnit.Builder(other);
  }

  /**
   * Creates a new OrganizationalUnit RecordBuilder by copying an existing OrganizationalUnit instance.
   * @param other The existing instance to copy.
   * @return A new OrganizationalUnit RecordBuilder
   */
  public static com.show_me_kafka.sor.sor_producer.model.kafka.OrganizationalUnit.Builder newBuilder(com.show_me_kafka.sor.sor_producer.model.kafka.OrganizationalUnit other) {
    return new com.show_me_kafka.sor.sor_producer.model.kafka.OrganizationalUnit.Builder(other);
  }

  /**
   * RecordBuilder for OrganizationalUnit instances.
   */
  public static class Builder extends org.apache.avro.specific.SpecificRecordBuilderBase<OrganizationalUnit>
    implements org.apache.avro.data.RecordBuilder<OrganizationalUnit> {

    private java.lang.CharSequence SorIdentifier;
    private java.lang.CharSequence EntityName;
    private java.lang.CharSequence EntityTypeIdentifier;
    private java.lang.CharSequence ParentSorIdentifier;

    /** Creates a new Builder */
    private Builder() {
      super(SCHEMA$);
    }

    /**
     * Creates a Builder by copying an existing Builder.
     * @param other The existing Builder to copy.
     */
    private Builder(com.show_me_kafka.sor.sor_producer.model.kafka.OrganizationalUnit.Builder other) {
      super(other);
      if (isValidValue(fields()[0], other.SorIdentifier)) {
        this.SorIdentifier = data().deepCopy(fields()[0].schema(), other.SorIdentifier);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.EntityName)) {
        this.EntityName = data().deepCopy(fields()[1].schema(), other.EntityName);
        fieldSetFlags()[1] = true;
      }
      if (isValidValue(fields()[2], other.EntityTypeIdentifier)) {
        this.EntityTypeIdentifier = data().deepCopy(fields()[2].schema(), other.EntityTypeIdentifier);
        fieldSetFlags()[2] = true;
      }
      if (isValidValue(fields()[3], other.ParentSorIdentifier)) {
        this.ParentSorIdentifier = data().deepCopy(fields()[3].schema(), other.ParentSorIdentifier);
        fieldSetFlags()[3] = true;
      }
    }

    /**
     * Creates a Builder by copying an existing OrganizationalUnit instance
     * @param other The existing instance to copy.
     */
    private Builder(com.show_me_kafka.sor.sor_producer.model.kafka.OrganizationalUnit other) {
            super(SCHEMA$);
      if (isValidValue(fields()[0], other.SorIdentifier)) {
        this.SorIdentifier = data().deepCopy(fields()[0].schema(), other.SorIdentifier);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.EntityName)) {
        this.EntityName = data().deepCopy(fields()[1].schema(), other.EntityName);
        fieldSetFlags()[1] = true;
      }
      if (isValidValue(fields()[2], other.EntityTypeIdentifier)) {
        this.EntityTypeIdentifier = data().deepCopy(fields()[2].schema(), other.EntityTypeIdentifier);
        fieldSetFlags()[2] = true;
      }
      if (isValidValue(fields()[3], other.ParentSorIdentifier)) {
        this.ParentSorIdentifier = data().deepCopy(fields()[3].schema(), other.ParentSorIdentifier);
        fieldSetFlags()[3] = true;
      }
    }

    /**
      * Gets the value of the 'SorIdentifier' field.
      * @return The value.
      */
    public java.lang.CharSequence getSorIdentifier() {
      return SorIdentifier;
    }

    /**
      * Sets the value of the 'SorIdentifier' field.
      * @param value The value of 'SorIdentifier'.
      * @return This builder.
      */
    public com.show_me_kafka.sor.sor_producer.model.kafka.OrganizationalUnit.Builder setSorIdentifier(java.lang.CharSequence value) {
      validate(fields()[0], value);
      this.SorIdentifier = value;
      fieldSetFlags()[0] = true;
      return this;
    }

    /**
      * Checks whether the 'SorIdentifier' field has been set.
      * @return True if the 'SorIdentifier' field has been set, false otherwise.
      */
    public boolean hasSorIdentifier() {
      return fieldSetFlags()[0];
    }


    /**
      * Clears the value of the 'SorIdentifier' field.
      * @return This builder.
      */
    public com.show_me_kafka.sor.sor_producer.model.kafka.OrganizationalUnit.Builder clearSorIdentifier() {
      SorIdentifier = null;
      fieldSetFlags()[0] = false;
      return this;
    }

    /**
      * Gets the value of the 'EntityName' field.
      * @return The value.
      */
    public java.lang.CharSequence getEntityName() {
      return EntityName;
    }

    /**
      * Sets the value of the 'EntityName' field.
      * @param value The value of 'EntityName'.
      * @return This builder.
      */
    public com.show_me_kafka.sor.sor_producer.model.kafka.OrganizationalUnit.Builder setEntityName(java.lang.CharSequence value) {
      validate(fields()[1], value);
      this.EntityName = value;
      fieldSetFlags()[1] = true;
      return this;
    }

    /**
      * Checks whether the 'EntityName' field has been set.
      * @return True if the 'EntityName' field has been set, false otherwise.
      */
    public boolean hasEntityName() {
      return fieldSetFlags()[1];
    }


    /**
      * Clears the value of the 'EntityName' field.
      * @return This builder.
      */
    public com.show_me_kafka.sor.sor_producer.model.kafka.OrganizationalUnit.Builder clearEntityName() {
      EntityName = null;
      fieldSetFlags()[1] = false;
      return this;
    }

    /**
      * Gets the value of the 'EntityTypeIdentifier' field.
      * @return The value.
      */
    public java.lang.CharSequence getEntityTypeIdentifier() {
      return EntityTypeIdentifier;
    }

    /**
      * Sets the value of the 'EntityTypeIdentifier' field.
      * @param value The value of 'EntityTypeIdentifier'.
      * @return This builder.
      */
    public com.show_me_kafka.sor.sor_producer.model.kafka.OrganizationalUnit.Builder setEntityTypeIdentifier(java.lang.CharSequence value) {
      validate(fields()[2], value);
      this.EntityTypeIdentifier = value;
      fieldSetFlags()[2] = true;
      return this;
    }

    /**
      * Checks whether the 'EntityTypeIdentifier' field has been set.
      * @return True if the 'EntityTypeIdentifier' field has been set, false otherwise.
      */
    public boolean hasEntityTypeIdentifier() {
      return fieldSetFlags()[2];
    }


    /**
      * Clears the value of the 'EntityTypeIdentifier' field.
      * @return This builder.
      */
    public com.show_me_kafka.sor.sor_producer.model.kafka.OrganizationalUnit.Builder clearEntityTypeIdentifier() {
      EntityTypeIdentifier = null;
      fieldSetFlags()[2] = false;
      return this;
    }

    /**
      * Gets the value of the 'ParentSorIdentifier' field.
      * @return The value.
      */
    public java.lang.CharSequence getParentSorIdentifier() {
      return ParentSorIdentifier;
    }

    /**
      * Sets the value of the 'ParentSorIdentifier' field.
      * @param value The value of 'ParentSorIdentifier'.
      * @return This builder.
      */
    public com.show_me_kafka.sor.sor_producer.model.kafka.OrganizationalUnit.Builder setParentSorIdentifier(java.lang.CharSequence value) {
      validate(fields()[3], value);
      this.ParentSorIdentifier = value;
      fieldSetFlags()[3] = true;
      return this;
    }

    /**
      * Checks whether the 'ParentSorIdentifier' field has been set.
      * @return True if the 'ParentSorIdentifier' field has been set, false otherwise.
      */
    public boolean hasParentSorIdentifier() {
      return fieldSetFlags()[3];
    }


    /**
      * Clears the value of the 'ParentSorIdentifier' field.
      * @return This builder.
      */
    public com.show_me_kafka.sor.sor_producer.model.kafka.OrganizationalUnit.Builder clearParentSorIdentifier() {
      ParentSorIdentifier = null;
      fieldSetFlags()[3] = false;
      return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public OrganizationalUnit build() {
      try {
        OrganizationalUnit record = new OrganizationalUnit();
        record.SorIdentifier = fieldSetFlags()[0] ? this.SorIdentifier : (java.lang.CharSequence) defaultValue(fields()[0]);
        record.EntityName = fieldSetFlags()[1] ? this.EntityName : (java.lang.CharSequence) defaultValue(fields()[1]);
        record.EntityTypeIdentifier = fieldSetFlags()[2] ? this.EntityTypeIdentifier : (java.lang.CharSequence) defaultValue(fields()[2]);
        record.ParentSorIdentifier = fieldSetFlags()[3] ? this.ParentSorIdentifier : (java.lang.CharSequence) defaultValue(fields()[3]);
        return record;
      } catch (java.lang.Exception e) {
        throw new org.apache.avro.AvroRuntimeException(e);
      }
    }
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumWriter<OrganizationalUnit>
    WRITER$ = (org.apache.avro.io.DatumWriter<OrganizationalUnit>)MODEL$.createDatumWriter(SCHEMA$);

  @Override public void writeExternal(java.io.ObjectOutput out)
    throws java.io.IOException {
    WRITER$.write(this, SpecificData.getEncoder(out));
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumReader<OrganizationalUnit>
    READER$ = (org.apache.avro.io.DatumReader<OrganizationalUnit>)MODEL$.createDatumReader(SCHEMA$);

  @Override public void readExternal(java.io.ObjectInput in)
    throws java.io.IOException {
    READER$.read(this, SpecificData.getDecoder(in));
  }

}
