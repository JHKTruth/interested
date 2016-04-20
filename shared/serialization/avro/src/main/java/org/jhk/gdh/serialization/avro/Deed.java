/**
 * Autogenerated by Avro
 * 
 * DO NOT EDIT DIRECTLY
 */
package org.jhk.gdh.serialization.avro;  
@SuppressWarnings("all")
@org.apache.avro.specific.AvroGenerated
public class Deed extends org.apache.avro.specific.SpecificRecordBase implements org.apache.avro.specific.SpecificRecord {
  private static final long serialVersionUID = 664475382411038031L;
  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"record\",\"name\":\"Deed\",\"namespace\":\"org.jhk.gdh.serialization.avro\",\"fields\":[{\"name\":\"deedTime\",\"type\":\"long\"},{\"name\":\"description\",\"type\":\"string\"},{\"name\":\"id\",\"type\":\"long\"},{\"name\":\"pluses\",\"type\":\"int\"},{\"name\":\"deederId\",\"type\":\"long\"},{\"name\":\"title\",\"type\":\"string\"},{\"name\":\"validated\",\"type\":\"boolean\"}]}");
  public static org.apache.avro.Schema getClassSchema() { return SCHEMA$; }
  @Deprecated public long deedTime;
  @Deprecated public java.lang.CharSequence description;
  @Deprecated public long id;
  @Deprecated public int pluses;
  @Deprecated public long deederId;
  @Deprecated public java.lang.CharSequence title;
  @Deprecated public boolean validated;

  /**
   * Default constructor.  Note that this does not initialize fields
   * to their default values from the schema.  If that is desired then
   * one should use <code>newBuilder()</code>. 
   */
  public Deed() {}

  /**
   * All-args constructor.
   */
  public Deed(java.lang.Long deedTime, java.lang.CharSequence description, java.lang.Long id, java.lang.Integer pluses, java.lang.Long deederId, java.lang.CharSequence title, java.lang.Boolean validated) {
    this.deedTime = deedTime;
    this.description = description;
    this.id = id;
    this.pluses = pluses;
    this.deederId = deederId;
    this.title = title;
    this.validated = validated;
  }

  public org.apache.avro.Schema getSchema() { return SCHEMA$; }
  // Used by DatumWriter.  Applications should not call. 
  public java.lang.Object get(int field$) {
    switch (field$) {
    case 0: return deedTime;
    case 1: return description;
    case 2: return id;
    case 3: return pluses;
    case 4: return deederId;
    case 5: return title;
    case 6: return validated;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }
  // Used by DatumReader.  Applications should not call. 
  @SuppressWarnings(value="unchecked")
  public void put(int field$, java.lang.Object value$) {
    switch (field$) {
    case 0: deedTime = (java.lang.Long)value$; break;
    case 1: description = (java.lang.CharSequence)value$; break;
    case 2: id = (java.lang.Long)value$; break;
    case 3: pluses = (java.lang.Integer)value$; break;
    case 4: deederId = (java.lang.Long)value$; break;
    case 5: title = (java.lang.CharSequence)value$; break;
    case 6: validated = (java.lang.Boolean)value$; break;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }

  /**
   * Gets the value of the 'deedTime' field.
   */
  public java.lang.Long getDeedTime() {
    return deedTime;
  }

  /**
   * Sets the value of the 'deedTime' field.
   * @param value the value to set.
   */
  public void setDeedTime(java.lang.Long value) {
    this.deedTime = value;
  }

  /**
   * Gets the value of the 'description' field.
   */
  public java.lang.CharSequence getDescription() {
    return description;
  }

  /**
   * Sets the value of the 'description' field.
   * @param value the value to set.
   */
  public void setDescription(java.lang.CharSequence value) {
    this.description = value;
  }

  /**
   * Gets the value of the 'id' field.
   */
  public java.lang.Long getId() {
    return id;
  }

  /**
   * Sets the value of the 'id' field.
   * @param value the value to set.
   */
  public void setId(java.lang.Long value) {
    this.id = value;
  }

  /**
   * Gets the value of the 'pluses' field.
   */
  public java.lang.Integer getPluses() {
    return pluses;
  }

  /**
   * Sets the value of the 'pluses' field.
   * @param value the value to set.
   */
  public void setPluses(java.lang.Integer value) {
    this.pluses = value;
  }

  /**
   * Gets the value of the 'deederId' field.
   */
  public java.lang.Long getDeederId() {
    return deederId;
  }

  /**
   * Sets the value of the 'deederId' field.
   * @param value the value to set.
   */
  public void setDeederId(java.lang.Long value) {
    this.deederId = value;
  }

  /**
   * Gets the value of the 'title' field.
   */
  public java.lang.CharSequence getTitle() {
    return title;
  }

  /**
   * Sets the value of the 'title' field.
   * @param value the value to set.
   */
  public void setTitle(java.lang.CharSequence value) {
    this.title = value;
  }

  /**
   * Gets the value of the 'validated' field.
   */
  public java.lang.Boolean getValidated() {
    return validated;
  }

  /**
   * Sets the value of the 'validated' field.
   * @param value the value to set.
   */
  public void setValidated(java.lang.Boolean value) {
    this.validated = value;
  }

  /**
   * Creates a new Deed RecordBuilder.
   * @return A new Deed RecordBuilder
   */
  public static org.jhk.gdh.serialization.avro.Deed.Builder newBuilder() {
    return new org.jhk.gdh.serialization.avro.Deed.Builder();
  }
  
  /**
   * Creates a new Deed RecordBuilder by copying an existing Builder.
   * @param other The existing builder to copy.
   * @return A new Deed RecordBuilder
   */
  public static org.jhk.gdh.serialization.avro.Deed.Builder newBuilder(org.jhk.gdh.serialization.avro.Deed.Builder other) {
    return new org.jhk.gdh.serialization.avro.Deed.Builder(other);
  }
  
  /**
   * Creates a new Deed RecordBuilder by copying an existing Deed instance.
   * @param other The existing instance to copy.
   * @return A new Deed RecordBuilder
   */
  public static org.jhk.gdh.serialization.avro.Deed.Builder newBuilder(org.jhk.gdh.serialization.avro.Deed other) {
    return new org.jhk.gdh.serialization.avro.Deed.Builder(other);
  }
  
  /**
   * RecordBuilder for Deed instances.
   */
  public static class Builder extends org.apache.avro.specific.SpecificRecordBuilderBase<Deed>
    implements org.apache.avro.data.RecordBuilder<Deed> {

    private long deedTime;
    private java.lang.CharSequence description;
    private long id;
    private int pluses;
    private long deederId;
    private java.lang.CharSequence title;
    private boolean validated;

    /** Creates a new Builder */
    private Builder() {
      super(org.jhk.gdh.serialization.avro.Deed.SCHEMA$);
    }
    
    /**
     * Creates a Builder by copying an existing Builder.
     * @param other The existing Builder to copy.
     */
    private Builder(org.jhk.gdh.serialization.avro.Deed.Builder other) {
      super(other);
      if (isValidValue(fields()[0], other.deedTime)) {
        this.deedTime = data().deepCopy(fields()[0].schema(), other.deedTime);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.description)) {
        this.description = data().deepCopy(fields()[1].schema(), other.description);
        fieldSetFlags()[1] = true;
      }
      if (isValidValue(fields()[2], other.id)) {
        this.id = data().deepCopy(fields()[2].schema(), other.id);
        fieldSetFlags()[2] = true;
      }
      if (isValidValue(fields()[3], other.pluses)) {
        this.pluses = data().deepCopy(fields()[3].schema(), other.pluses);
        fieldSetFlags()[3] = true;
      }
      if (isValidValue(fields()[4], other.deederId)) {
        this.deederId = data().deepCopy(fields()[4].schema(), other.deederId);
        fieldSetFlags()[4] = true;
      }
      if (isValidValue(fields()[5], other.title)) {
        this.title = data().deepCopy(fields()[5].schema(), other.title);
        fieldSetFlags()[5] = true;
      }
      if (isValidValue(fields()[6], other.validated)) {
        this.validated = data().deepCopy(fields()[6].schema(), other.validated);
        fieldSetFlags()[6] = true;
      }
    }
    
    /**
     * Creates a Builder by copying an existing Deed instance
     * @param other The existing instance to copy.
     */
    private Builder(org.jhk.gdh.serialization.avro.Deed other) {
            super(org.jhk.gdh.serialization.avro.Deed.SCHEMA$);
      if (isValidValue(fields()[0], other.deedTime)) {
        this.deedTime = data().deepCopy(fields()[0].schema(), other.deedTime);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.description)) {
        this.description = data().deepCopy(fields()[1].schema(), other.description);
        fieldSetFlags()[1] = true;
      }
      if (isValidValue(fields()[2], other.id)) {
        this.id = data().deepCopy(fields()[2].schema(), other.id);
        fieldSetFlags()[2] = true;
      }
      if (isValidValue(fields()[3], other.pluses)) {
        this.pluses = data().deepCopy(fields()[3].schema(), other.pluses);
        fieldSetFlags()[3] = true;
      }
      if (isValidValue(fields()[4], other.deederId)) {
        this.deederId = data().deepCopy(fields()[4].schema(), other.deederId);
        fieldSetFlags()[4] = true;
      }
      if (isValidValue(fields()[5], other.title)) {
        this.title = data().deepCopy(fields()[5].schema(), other.title);
        fieldSetFlags()[5] = true;
      }
      if (isValidValue(fields()[6], other.validated)) {
        this.validated = data().deepCopy(fields()[6].schema(), other.validated);
        fieldSetFlags()[6] = true;
      }
    }

    /**
      * Gets the value of the 'deedTime' field.
      * @return The value.
      */
    public java.lang.Long getDeedTime() {
      return deedTime;
    }

    /**
      * Sets the value of the 'deedTime' field.
      * @param value The value of 'deedTime'.
      * @return This builder.
      */
    public org.jhk.gdh.serialization.avro.Deed.Builder setDeedTime(long value) {
      validate(fields()[0], value);
      this.deedTime = value;
      fieldSetFlags()[0] = true;
      return this; 
    }

    /**
      * Checks whether the 'deedTime' field has been set.
      * @return True if the 'deedTime' field has been set, false otherwise.
      */
    public boolean hasDeedTime() {
      return fieldSetFlags()[0];
    }


    /**
      * Clears the value of the 'deedTime' field.
      * @return This builder.
      */
    public org.jhk.gdh.serialization.avro.Deed.Builder clearDeedTime() {
      fieldSetFlags()[0] = false;
      return this;
    }

    /**
      * Gets the value of the 'description' field.
      * @return The value.
      */
    public java.lang.CharSequence getDescription() {
      return description;
    }

    /**
      * Sets the value of the 'description' field.
      * @param value The value of 'description'.
      * @return This builder.
      */
    public org.jhk.gdh.serialization.avro.Deed.Builder setDescription(java.lang.CharSequence value) {
      validate(fields()[1], value);
      this.description = value;
      fieldSetFlags()[1] = true;
      return this; 
    }

    /**
      * Checks whether the 'description' field has been set.
      * @return True if the 'description' field has been set, false otherwise.
      */
    public boolean hasDescription() {
      return fieldSetFlags()[1];
    }


    /**
      * Clears the value of the 'description' field.
      * @return This builder.
      */
    public org.jhk.gdh.serialization.avro.Deed.Builder clearDescription() {
      description = null;
      fieldSetFlags()[1] = false;
      return this;
    }

    /**
      * Gets the value of the 'id' field.
      * @return The value.
      */
    public java.lang.Long getId() {
      return id;
    }

    /**
      * Sets the value of the 'id' field.
      * @param value The value of 'id'.
      * @return This builder.
      */
    public org.jhk.gdh.serialization.avro.Deed.Builder setId(long value) {
      validate(fields()[2], value);
      this.id = value;
      fieldSetFlags()[2] = true;
      return this; 
    }

    /**
      * Checks whether the 'id' field has been set.
      * @return True if the 'id' field has been set, false otherwise.
      */
    public boolean hasId() {
      return fieldSetFlags()[2];
    }


    /**
      * Clears the value of the 'id' field.
      * @return This builder.
      */
    public org.jhk.gdh.serialization.avro.Deed.Builder clearId() {
      fieldSetFlags()[2] = false;
      return this;
    }

    /**
      * Gets the value of the 'pluses' field.
      * @return The value.
      */
    public java.lang.Integer getPluses() {
      return pluses;
    }

    /**
      * Sets the value of the 'pluses' field.
      * @param value The value of 'pluses'.
      * @return This builder.
      */
    public org.jhk.gdh.serialization.avro.Deed.Builder setPluses(int value) {
      validate(fields()[3], value);
      this.pluses = value;
      fieldSetFlags()[3] = true;
      return this; 
    }

    /**
      * Checks whether the 'pluses' field has been set.
      * @return True if the 'pluses' field has been set, false otherwise.
      */
    public boolean hasPluses() {
      return fieldSetFlags()[3];
    }


    /**
      * Clears the value of the 'pluses' field.
      * @return This builder.
      */
    public org.jhk.gdh.serialization.avro.Deed.Builder clearPluses() {
      fieldSetFlags()[3] = false;
      return this;
    }

    /**
      * Gets the value of the 'deederId' field.
      * @return The value.
      */
    public java.lang.Long getDeederId() {
      return deederId;
    }

    /**
      * Sets the value of the 'deederId' field.
      * @param value The value of 'deederId'.
      * @return This builder.
      */
    public org.jhk.gdh.serialization.avro.Deed.Builder setDeederId(long value) {
      validate(fields()[4], value);
      this.deederId = value;
      fieldSetFlags()[4] = true;
      return this; 
    }

    /**
      * Checks whether the 'deederId' field has been set.
      * @return True if the 'deederId' field has been set, false otherwise.
      */
    public boolean hasDeederId() {
      return fieldSetFlags()[4];
    }


    /**
      * Clears the value of the 'deederId' field.
      * @return This builder.
      */
    public org.jhk.gdh.serialization.avro.Deed.Builder clearDeederId() {
      fieldSetFlags()[4] = false;
      return this;
    }

    /**
      * Gets the value of the 'title' field.
      * @return The value.
      */
    public java.lang.CharSequence getTitle() {
      return title;
    }

    /**
      * Sets the value of the 'title' field.
      * @param value The value of 'title'.
      * @return This builder.
      */
    public org.jhk.gdh.serialization.avro.Deed.Builder setTitle(java.lang.CharSequence value) {
      validate(fields()[5], value);
      this.title = value;
      fieldSetFlags()[5] = true;
      return this; 
    }

    /**
      * Checks whether the 'title' field has been set.
      * @return True if the 'title' field has been set, false otherwise.
      */
    public boolean hasTitle() {
      return fieldSetFlags()[5];
    }


    /**
      * Clears the value of the 'title' field.
      * @return This builder.
      */
    public org.jhk.gdh.serialization.avro.Deed.Builder clearTitle() {
      title = null;
      fieldSetFlags()[5] = false;
      return this;
    }

    /**
      * Gets the value of the 'validated' field.
      * @return The value.
      */
    public java.lang.Boolean getValidated() {
      return validated;
    }

    /**
      * Sets the value of the 'validated' field.
      * @param value The value of 'validated'.
      * @return This builder.
      */
    public org.jhk.gdh.serialization.avro.Deed.Builder setValidated(boolean value) {
      validate(fields()[6], value);
      this.validated = value;
      fieldSetFlags()[6] = true;
      return this; 
    }

    /**
      * Checks whether the 'validated' field has been set.
      * @return True if the 'validated' field has been set, false otherwise.
      */
    public boolean hasValidated() {
      return fieldSetFlags()[6];
    }


    /**
      * Clears the value of the 'validated' field.
      * @return This builder.
      */
    public org.jhk.gdh.serialization.avro.Deed.Builder clearValidated() {
      fieldSetFlags()[6] = false;
      return this;
    }

    @Override
    public Deed build() {
      try {
        Deed record = new Deed();
        record.deedTime = fieldSetFlags()[0] ? this.deedTime : (java.lang.Long) defaultValue(fields()[0]);
        record.description = fieldSetFlags()[1] ? this.description : (java.lang.CharSequence) defaultValue(fields()[1]);
        record.id = fieldSetFlags()[2] ? this.id : (java.lang.Long) defaultValue(fields()[2]);
        record.pluses = fieldSetFlags()[3] ? this.pluses : (java.lang.Integer) defaultValue(fields()[3]);
        record.deederId = fieldSetFlags()[4] ? this.deederId : (java.lang.Long) defaultValue(fields()[4]);
        record.title = fieldSetFlags()[5] ? this.title : (java.lang.CharSequence) defaultValue(fields()[5]);
        record.validated = fieldSetFlags()[6] ? this.validated : (java.lang.Boolean) defaultValue(fields()[6]);
        return record;
      } catch (Exception e) {
        throw new org.apache.avro.AvroRuntimeException(e);
      }
    }
  }

  private static final org.apache.avro.io.DatumWriter
    WRITER$ = new org.apache.avro.specific.SpecificDatumWriter(SCHEMA$);  

  @Override public void writeExternal(java.io.ObjectOutput out)
    throws java.io.IOException {
    WRITER$.write(this, org.apache.avro.specific.SpecificData.getEncoder(out));
  }

  private static final org.apache.avro.io.DatumReader
    READER$ = new org.apache.avro.specific.SpecificDatumReader(SCHEMA$);  

  @Override public void readExternal(java.io.ObjectInput in)
    throws java.io.IOException {
    READER$.read(this, org.apache.avro.specific.SpecificData.getDecoder(in));
  }

}
