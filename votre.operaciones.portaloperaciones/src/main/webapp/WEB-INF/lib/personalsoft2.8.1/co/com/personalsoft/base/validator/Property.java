package co.com.personalsoft.base.validator;

import java.util.HashSet;

public class Property {
   private String name;
   private String label;
   private String format;
   private String maxValue;
   private String minValue;
   private int length;
   private String type;
   private boolean multiplicity;
   private String defaultValue;
   private String messageAlert;
   private HashSet actions;
   private boolean isNull;

   public HashSet getActions() {
      return this.actions;
   }

   public String getDefaultValue() {
      return this.defaultValue;
   }

   public String getFormat() {
      return this.format;
   }

   public int getLength() {
      return this.length;
   }

   public String getMaxValue() {
      return this.maxValue;
   }

   public String getMessageAlert() {
      return this.messageAlert;
   }

   public String getMinValue() {
      return this.minValue;
   }

   public String getName() {
      return this.name;
   }

   public void setActions(HashSet set) {
      this.actions = set;
   }

   public void setDefaultValue(String string) {
      this.defaultValue = string;
   }

   public void setFormat(String string) {
      this.format = string;
   }

   public void setLength(int i) {
      this.length = i;
   }

   public void setMaxValue(String string) {
      this.maxValue = string;
   }

   public void setMessageAlert(String string) {
      this.messageAlert = string;
   }

   public void setMinValue(String string) {
      this.minValue = string;
   }

   public void setName(String string) {
      this.name = string;
   }

   public boolean isMultiplicity() {
      return this.multiplicity;
   }

   public String getType() {
      return this.type;
   }

   public void setMultiplicity(boolean b) {
      this.multiplicity = b;
   }

   public void setType(String string) {
      this.type = string;
   }

   public String getGeneralType() {
      new String();
      String generalType;
      if (this.type.equalsIgnoreCase("Byte") | this.type.equalsIgnoreCase("Short") | this.type.equalsIgnoreCase("Integer") | this.type.equalsIgnoreCase("Long") | this.type.equalsIgnoreCase("Float") | this.type.equalsIgnoreCase("Double") | this.type.equalsIgnoreCase("BigInteger") | this.type.equalsIgnoreCase("BigDecimal")) {
         generalType = "number";
      } else if (this.type.equalsIgnoreCase("Date")) {
         generalType = "time";
      } else {
         generalType = "String";
      }

      return generalType;
   }

   public boolean isNull() {
      return this.isNull;
   }

   public void setNull(boolean b) {
      this.isNull = b;
   }

   public String getLabel() {
      return this.label;
   }

   public void setLabel(String string) {
      this.label = string;
   }
}
