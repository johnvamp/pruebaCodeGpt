package co.com.personalsoft.base.validator;

import java.util.HashMap;

public class Form {
   private String ruleId;
   private HashMap<String, Property> properties;

   public HashMap getProperties() {
      return this.properties;
   }

   public String getRuleId() {
      return this.ruleId;
   }

   public void setProperties(HashMap set) {
      this.properties = set;
   }

   public void setRuleId(String string) {
      this.ruleId = string;
   }

   public Property getProperty(String key) {
      return (Property)this.properties.get(key);
   }

   public void addProperty(String key, Property newProperty) {
      this.properties.put(key, newProperty);
   }

   public boolean isPropertyExist(String key) {
      return this.properties.containsKey(key);
   }
}
