package co.com.personalsoft.base.validator;

import co.com.personalsoft.base.error.MensajesUsr;
import co.com.personalsoft.base.excepcion.PersonalsoftException;
import java.io.File;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.util.jcache.CacheAccessFactory;
import javax.util.jcache.CacheException;
import javax.util.jcache.CacheMap;
import org.apache.commons.validator.CreditCardValidator;
import org.apache.commons.validator.EmailValidator;
import org.apache.commons.validator.ISBNValidator;
import org.apache.commons.validator.UrlValidator;
import org.apache.commons.validator.routines.BigDecimalValidator;
import org.apache.commons.validator.routines.BigIntegerValidator;
import org.apache.commons.validator.routines.ByteValidator;
import org.apache.commons.validator.routines.DateValidator;
import org.apache.commons.validator.routines.DoubleValidator;
import org.apache.commons.validator.routines.FloatValidator;
import org.apache.commons.validator.routines.IntegerValidator;
import org.apache.commons.validator.routines.LongValidator;
import org.apache.commons.validator.routines.PercentValidator;
import org.apache.commons.validator.routines.ShortValidator;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class Validador {
   private boolean isValid = true;
   // $FF: synthetic field
   private static int[] $SWITCH_TABLE$co$com$personalsoft$base$validator$Validador$ValidarTipo;

   public void initValidator(String validationFilePath) throws PersonalsoftException {
      SAXReader reader = new SAXReader();

      try {
         File validationFile = new File(validationFilePath);
         if (validationFile.exists()) {
            Document document = reader.read(validationFile);
            this.build(document);
         } else {
            throw new PersonalsoftException("El archivo de validación de reglas no existe");
         }
      } catch (Exception var5) {
         var5.printStackTrace();
         throw new PersonalsoftException(var5.getMessage());
      }
   }

   public void build(Document document) throws PersonalsoftException {
      CacheAccessFactory factory = CacheAccessFactory.getInstance();
      CacheMap cache = null;

      try {
         cache = factory.getMapAccess();
         Element root = document.getRootElement();
         Iterator i = root.elementIterator("form");

         while(i.hasNext()) {
            Element formElement = (Element)i.next();
            Form form = this.buildForm(formElement);
            cache.put(form.getRuleId(), form);
         }

      } catch (CacheException var8) {
         var8.printStackTrace();
         throw new PersonalsoftException("Error cargando las reglas de validación en el cache");
      } catch (Exception var9) {
         var9.printStackTrace();
         throw new PersonalsoftException("Error cargando los recursos del usuario en el cache");
      }
   }

   private Form buildForm(Element formElement) {
      Form form = new Form();
      form.setRuleId(formElement.attributeValue("idrule"));
      HashMap<String, Property> properties = this.buildProperties(formElement);
      form.setProperties(properties);
      return form;
   }

   private HashMap<String, Property> buildProperties(Element formElement) {
      HashMap<String, Property> properties = new HashMap();
      Iterator i = formElement.elementIterator("property");

      while(i.hasNext()) {
         Element propertyElement = (Element)i.next();
         Property property = new Property();
         property.setName(propertyElement.attributeValue("name"));
         property.setLabel(propertyElement.attributeValue("label"));
         property.setType(propertyElement.attributeValue("type"));
         if (property.getGeneralType().equalsIgnoreCase("number")) {
            property.setMaxValue(propertyElement.attributeValue("maxValue"));
            property.setMinValue(propertyElement.attributeValue("minValue"));
         }

         if (property.getGeneralType().equalsIgnoreCase("String") && propertyElement.attributeValue("maxValue") != null && propertyElement.attributeValue("maxValue").trim().length() > 0) {
            property.setLength(Integer.parseInt(propertyElement.attributeValue("maxValue")));
         }

         if (propertyElement.attributeValue("format") != null && propertyElement.attributeValue("maxValue").trim().length() > 0) {
            property.setFormat(propertyElement.attributeValue("format"));
         }

         if (propertyElement.attributeValue("multiplicity") != null && propertyElement.attributeValue("multiplicity").trim().length() > 0) {
            property.setMultiplicity(Boolean.valueOf(propertyElement.attributeValue("multiplicity")));
         }

         if (propertyElement.attributeValue("isBeNull") != null && propertyElement.attributeValue("isBeNull").trim().length() > 0) {
            property.setNull(Boolean.valueOf(propertyElement.attributeValue("isBeNull")));
         }

         Element requiredActionElement = propertyElement.element("required-in-actions");
         HashSet<String> actionSet = new HashSet();
         Iterator j = requiredActionElement.elementIterator("action");

         while(j.hasNext()) {
            Element actionElement = (Element)j.next();
            actionSet.add(actionElement.attributeValue("name"));
         }

         property.setActions(actionSet);
         Element messajeElement = propertyElement.element("message-alert");
         property.setMessageAlert(messajeElement.getText());
         properties.put(property.getName(), property);
      }

      return properties;
   }

   public MensajesUsr validate(String idRule, HttpServletRequest request) throws PersonalsoftException {
      CacheAccessFactory factory = CacheAccessFactory.getInstance();
      Map<String, Form> cache = null;
      MensajesUsr mensajesUsr = null;

      try {
         cache = factory.getMapAccess();
         Object obj = cache.get(idRule);
         if (obj != null) {
            mensajesUsr = this.validateForm((Form)obj, request);
         }

         return mensajesUsr;
      } catch (CacheException var7) {
         throw new PersonalsoftException("Error cargando los recursos del usuario en el cache");
      }
   }

   private MensajesUsr validateForm(Form form, HttpServletRequest request) throws PersonalsoftException {
      MensajesUsr mensajesUsr = (MensajesUsr)request.getAttribute("mensajesUsr");
      HashMap properties = form.getProperties();
      if (properties != null) {
         Iterator iterator = properties.keySet().iterator();

         while(true) {
            while(iterator.hasNext()) {
               Object key = iterator.next();
               Property property = (Property)properties.get(key);
               String field = request.getParameter(property.getName());
               if (field != null && field.trim().length() > 0) {
                  switch($SWITCH_TABLE$co$com$personalsoft$base$validator$Validador$ValidarTipo()[this.getTipo(property).ordinal()]) {
                  case 1:
                     mensajesUsr = this.validarByte(mensajesUsr, property, field);
                     break;
                  case 2:
                     mensajesUsr = this.validarDate(mensajesUsr, property, field);
                     break;
                  case 3:
                     mensajesUsr = this.validarShort(mensajesUsr, property, field);
                     break;
                  case 4:
                     mensajesUsr = this.validarInteger(mensajesUsr, property, field);
                     break;
                  case 5:
                     mensajesUsr = this.validarLong(mensajesUsr, property, field);
                     break;
                  case 6:
                     mensajesUsr = this.validarFloat(mensajesUsr, property, field);
                     break;
                  case 7:
                     mensajesUsr = this.validarDouble(mensajesUsr, property, field);
                     break;
                  case 8:
                     mensajesUsr = this.validarBigInteger(mensajesUsr, property, field);
                     break;
                  case 9:
                     mensajesUsr = this.validarBigDecimal(mensajesUsr, property, field);
                     break;
                  case 10:
                     mensajesUsr = this.validarEmail(mensajesUsr, property, field);
                     break;
                  case 11:
                     mensajesUsr = this.validarISBN(mensajesUsr, property, field);
                     break;
                  case 12:
                     mensajesUsr = this.validarCreditCard(mensajesUsr, property, field);
                     break;
                  case 13:
                     mensajesUsr = this.validarPercent(mensajesUsr, property, field);
                     break;
                  case 14:
                     mensajesUsr = this.validarURL(mensajesUsr, property, field);
                     break;
                  default:
                     System.err.println("Llego un tipo de parametro no esperado por el validador: " + property.getType());
                  }
               } else if (!property.isNull()) {
                  this.isValid = false;
                  if (property.getMessageAlert() != null && property.getMessageAlert().trim().length() > 0) {
                     mensajesUsr.addAlert(property.getMessageAlert());
                  } else {
                     mensajesUsr.addAlert("El campo " + property.getLabel() + " no puede ser nulo.");
                  }
               } else {
                  String event = request.getParameter("execute");
                  if (event == null || event.trim().length() <= 0) {
                     event = "execute";
                  }

                  HashSet actions = property.getActions();
                  if (actions != null) {
                     if (event != null && event.trim().length() > 0) {
                        if (actions.contains(event)) {
                           this.isValid = false;
                           if (property.getMessageAlert() != null && property.getMessageAlert().trim().length() > 0) {
                              mensajesUsr.addAlert(property.getMessageAlert());
                           } else {
                              mensajesUsr.addAlert("El campo " + property.getLabel() + " es requerido para este acción.");
                           }
                        }
                     } else if (actions.contains("execute")) {
                        this.isValid = false;
                        if (property.getMessageAlert() != null && property.getMessageAlert().trim().length() > 0 && property.getMessageAlert().trim().length() > 0) {
                           mensajesUsr.addAlert(property.getMessageAlert());
                        } else {
                           mensajesUsr.addAlert("El campo " + property.getLabel() + " es requerido para este acción.");
                        }
                     }
                  }
               }
            }

            return mensajesUsr;
         }
      } else {
         return mensajesUsr;
      }
   }

   private MensajesUsr validarURL(MensajesUsr mensajesUsr, Property property, String field) {
      UrlValidator urlValidator = new UrlValidator();
      if (!urlValidator.isValid(field)) {
         this.isValid = false;
         if (property.getMessageAlert() != null) {
            mensajesUsr.addAlert(property.getMessageAlert());
         } else {
            mensajesUsr.addAlert("El campo " + property.getLabel() + " no es valido.");
         }
      }

      return mensajesUsr;
   }

   private MensajesUsr validarPercent(MensajesUsr mensajesUsr, Property property, String field) {
      PercentValidator percentValidator = new PercentValidator();
      if (!percentValidator.isValid(field, property.getFormat())) {
         this.isValid = false;
         if (property.getMessageAlert() != null && property.getMessageAlert().trim().length() > 0) {
            mensajesUsr.addAlert(property.getMessageAlert());
         } else {
            mensajesUsr.addAlert("El campo " + property.getLabel() + " no es valido.");
         }
      } else {
         if (!percentValidator.maxValue(new BigDecimal(field), Double.parseDouble(property.getMaxValue()))) {
            this.isValid = false;
            mensajesUsr.addAlert("El campo " + property.getLabel() + " sobrepasa el tamaño máximo.");
         }

         if (!percentValidator.minValue(new BigDecimal(field), Double.parseDouble(property.getMinValue()))) {
            this.isValid = false;
            mensajesUsr.addAlert("El campo " + property.getLabel() + " no sobrepasa el tamaño mínimo.");
         }
      }

      return mensajesUsr;
   }

   private MensajesUsr validarCreditCard(MensajesUsr mensajesUsr, Property property, String field) {
      CreditCardValidator creditCardValidator = new CreditCardValidator();
      if (!creditCardValidator.isValid(field)) {
         this.isValid = false;
         if (property.getMessageAlert() != null && property.getMessageAlert().trim().length() > 0) {
            mensajesUsr.addAlert(property.getMessageAlert());
         } else {
            mensajesUsr.addAlert("El campo " + property.getLabel() + " no es valido.");
         }
      }

      return mensajesUsr;
   }

   private MensajesUsr validarISBN(MensajesUsr mensajesUsr, Property property, String field) {
      ISBNValidator validator = new ISBNValidator();
      if (!validator.isValid(field)) {
         this.isValid = false;
         if (property.getMessageAlert() != null && property.getMessageAlert().trim().length() > 0) {
            mensajesUsr.addAlert(property.getMessageAlert());
         } else {
            mensajesUsr.addAlert("El campo " + property.getLabel() + " no es valido.");
         }
      }

      return mensajesUsr;
   }

   private MensajesUsr validarEmail(MensajesUsr mensajesUsr, Property property, String field) {
      if (!EmailValidator.getInstance().isValid(field)) {
         this.isValid = false;
         if (property.getMessageAlert() != null && property.getMessageAlert().trim().length() > 0) {
            mensajesUsr.addAlert(property.getMessageAlert());
         } else {
            mensajesUsr.addAlert("El campo " + property.getLabel() + " no es valido.");
         }
      }

      return mensajesUsr;
   }

   private MensajesUsr validarBigDecimal(MensajesUsr mensajesUsr, Property property, String field) {
      BigDecimalValidator bigDecimalValidator = new BigDecimalValidator();
      if (!bigDecimalValidator.isValid(field, property.getFormat())) {
         this.isValid = false;
         if (property.getMessageAlert() != null && property.getMessageAlert().trim().length() > 0) {
            mensajesUsr.addAlert(property.getMessageAlert());
         } else {
            mensajesUsr.addAlert("El campo " + property.getLabel() + " no es valido.");
         }
      } else {
         if (!bigDecimalValidator.maxValue(new BigDecimal(field), Double.parseDouble(property.getMaxValue()))) {
            this.isValid = false;
            mensajesUsr.addAlert("El campo " + property.getLabel() + " sobrepasa el tamaño máximo.");
         }

         if (!bigDecimalValidator.minValue(new BigDecimal(field), Double.parseDouble(property.getMinValue()))) {
            this.isValid = false;
            mensajesUsr.addAlert("El campo " + property.getLabel() + " no sobrepasa el tamaño mínimo.");
         }
      }

      return mensajesUsr;
   }

   private MensajesUsr validarBigInteger(MensajesUsr mensajesUsr, Property property, String field) {
      BigIntegerValidator bigIntegerValidator = new BigIntegerValidator();
      if (!bigIntegerValidator.isValid(field, property.getFormat())) {
         this.isValid = false;
         if (property.getMessageAlert() != null && property.getMessageAlert().trim().length() > 0) {
            mensajesUsr.addAlert(property.getMessageAlert());
         } else {
            mensajesUsr.addAlert("El campo " + property.getLabel() + " no es valido.");
         }
      } else {
         if (!bigIntegerValidator.maxValue(new BigInteger(field), Long.parseLong(property.getMaxValue()))) {
            this.isValid = false;
            mensajesUsr.addAlert("El campo " + property.getLabel() + " sobrepasa el tamaño máximo.");
         }

         if (!bigIntegerValidator.minValue(new BigInteger(field), Long.parseLong(property.getMinValue()))) {
            this.isValid = false;
            mensajesUsr.addAlert("El campo " + property.getLabel() + " no sobrepasa el tamaño mínimo.");
         }
      }

      return mensajesUsr;
   }

   private MensajesUsr validarDouble(MensajesUsr mensajesUsr, Property property, String field) {
      DoubleValidator doubleValidator = new DoubleValidator();
      if (!doubleValidator.isValid(field, property.getFormat())) {
         this.isValid = false;
         if (property.getMessageAlert() != null && property.getMessageAlert().trim().length() > 0) {
            mensajesUsr.addAlert(property.getMessageAlert());
         } else {
            mensajesUsr.addAlert("El campo " + property.getLabel() + " no es valido.");
         }
      } else {
         if (!doubleValidator.maxValue(Double.parseDouble(field), Double.parseDouble(property.getMaxValue()))) {
            this.isValid = false;
            mensajesUsr.addAlert("El campo " + property.getLabel() + " sobrepasa el tamaño máximo.");
         }

         if (!doubleValidator.minValue(Double.parseDouble(field), Double.parseDouble(property.getMinValue()))) {
            this.isValid = false;
            mensajesUsr.addAlert("El campo " + property.getLabel() + " no sobrepasa el tamaño mínimo.");
         }
      }

      return mensajesUsr;
   }

   private MensajesUsr validarFloat(MensajesUsr mensajesUsr, Property property, String field) {
      FloatValidator floatValidator = new FloatValidator();
      if (!floatValidator.isValid(field, property.getFormat())) {
         this.isValid = false;
         if (property.getMessageAlert() != null && property.getMessageAlert().trim().length() > 0) {
            mensajesUsr.addAlert(property.getMessageAlert());
         } else {
            mensajesUsr.addAlert("El campo " + property.getLabel() + " no es valido.");
         }
      } else {
         if (!floatValidator.maxValue(Float.parseFloat(field), Float.parseFloat(property.getMaxValue()))) {
            this.isValid = false;
            mensajesUsr.addAlert("El campo " + property.getLabel() + " sobrepasa el tamaño máximo.");
         }

         if (!floatValidator.minValue(Float.parseFloat(field), Float.parseFloat(property.getMinValue()))) {
            this.isValid = false;
            mensajesUsr.addAlert("El campo " + property.getLabel() + " no sobrepasa el tamaño mínimo.");
         }
      }

      return mensajesUsr;
   }

   private MensajesUsr validarLong(MensajesUsr mensajesUsr, Property property, String field) {
      LongValidator longValidator = new LongValidator();
      if (!longValidator.isValid(field, property.getFormat())) {
         this.isValid = false;
         if (property.getMessageAlert() != null && property.getMessageAlert().trim().length() > 0) {
            mensajesUsr.addAlert(property.getMessageAlert());
         } else {
            mensajesUsr.addAlert("El campo " + property.getLabel() + " no es valido.");
         }
      } else {
         if (!longValidator.maxValue(Long.parseLong(field), Long.parseLong(property.getMaxValue()))) {
            this.isValid = false;
            mensajesUsr.addAlert("El campo " + property.getLabel() + " sobrepasa el tamaño máximo.");
         }

         if (!longValidator.minValue(Long.parseLong(field), Long.parseLong(property.getMinValue()))) {
            this.isValid = false;
            mensajesUsr.addAlert("El campo " + property.getLabel() + " no sobrepasa el tamaño mínimo.");
         }
      }

      return mensajesUsr;
   }

   private MensajesUsr validarInteger(MensajesUsr mensajesUsr, Property property, String field) {
      IntegerValidator integerValidator = new IntegerValidator();
      if (!integerValidator.isValid(field, property.getFormat())) {
         this.isValid = false;
         if (property.getMessageAlert() != null && property.getMessageAlert().trim().length() > 0) {
            mensajesUsr.addAlert(property.getMessageAlert());
         } else {
            mensajesUsr.addAlert("El campo " + property.getLabel() + " no es valido.");
         }
      } else {
         if (!integerValidator.maxValue(Integer.parseInt(field), Integer.parseInt(property.getMaxValue()))) {
            this.isValid = false;
            mensajesUsr.addAlert("El campo " + property.getLabel() + " sobrepasa el tamaño máximo.");
         }

         if (!integerValidator.minValue(Integer.parseInt(field), Integer.parseInt(property.getMinValue()))) {
            this.isValid = false;
            mensajesUsr.addAlert("El campo " + property.getLabel() + " no sobrepasa el tamaño mínimo.");
         }
      }

      return mensajesUsr;
   }

   private MensajesUsr validarShort(MensajesUsr mensajesUsr, Property property, String field) {
      ShortValidator shortValidator = new ShortValidator();
      if (!shortValidator.isValid(field, property.getFormat())) {
         this.isValid = false;
         if (property.getMessageAlert() != null && property.getMessageAlert().trim().length() > 0) {
            mensajesUsr.addAlert(property.getMessageAlert());
         } else {
            mensajesUsr.addAlert("El campo " + property.getLabel() + " no es valido.");
         }
      } else {
         if (!shortValidator.maxValue(Short.parseShort(field), Short.parseShort(property.getMaxValue()))) {
            this.isValid = false;
            mensajesUsr.addAlert("El campo " + property.getLabel() + " sobrepasa el tamaño máximo.");
         }

         if (!shortValidator.minValue(Short.parseShort(field), Short.parseShort(property.getMinValue()))) {
            this.isValid = false;
            mensajesUsr.addAlert("El campo " + property.getLabel() + " no sobrepasa el tamaño mínimo.");
         }
      }

      return mensajesUsr;
   }

   private MensajesUsr validarDate(MensajesUsr mensajesUsr, Property property, String field) {
      DateValidator dateValidator = DateValidator.getInstance();
      Date _date = dateValidator.validate(field, property.getFormat());
      if (_date == null) {
         this.isValid = false;
         if (property.getMessageAlert() != null && property.getMessageAlert().trim().length() > 0) {
            mensajesUsr.addAlert(property.getMessageAlert());
         } else {
            mensajesUsr.addAlert("El campo " + property.getLabel() + " no es valido.");
         }
      }

      return mensajesUsr;
   }

   private MensajesUsr validarByte(MensajesUsr mensajesUsr, Property property, String field) {
      ByteValidator byteValidator = new ByteValidator();
      if (!byteValidator.isValid(field, property.getFormat())) {
         this.isValid = false;
         if (property.getMessageAlert() != null && property.getMessageAlert().trim().length() > 0) {
            mensajesUsr.addAlert(property.getMessageAlert());
         } else {
            mensajesUsr.addAlert("El campo " + property.getLabel() + " no es valido.");
         }
      } else {
         if (!byteValidator.maxValue(Byte.parseByte(field), Byte.parseByte(property.getMaxValue()))) {
            this.isValid = false;
            mensajesUsr.addAlert("El campo " + property.getLabel() + " sobrepasa el tamaño máximo.");
         }

         if (!byteValidator.minValue(Byte.parseByte(field), Byte.parseByte(property.getMinValue()))) {
            this.isValid = false;
            mensajesUsr.addAlert("El campo " + property.getLabel() + " no sobrepasa el tamaño mínimo.");
         }
      }

      return mensajesUsr;
   }

   private Validador.ValidarTipo getTipo(Property property) {
      if (property.getType().equalsIgnoreCase("Byte")) {
         return Validador.ValidarTipo.BYTE;
      } else if (property.getType().equalsIgnoreCase("Date")) {
         return Validador.ValidarTipo.DATE;
      } else if (property.getType().equalsIgnoreCase("Short")) {
         return Validador.ValidarTipo.SHORT;
      } else if (property.getType().equalsIgnoreCase("Integer")) {
         return Validador.ValidarTipo.INTEGER;
      } else if (property.getType().equalsIgnoreCase("Long")) {
         return Validador.ValidarTipo.LONG;
      } else if (property.getType().equalsIgnoreCase("Float")) {
         return Validador.ValidarTipo.FLOAT;
      } else if (property.getType().equalsIgnoreCase("Double")) {
         return Validador.ValidarTipo.DOUBLE;
      } else if (property.getType().equalsIgnoreCase("BigInteger")) {
         return Validador.ValidarTipo.BIG_INTEGER;
      } else if (property.getType().equalsIgnoreCase("BigDecimal")) {
         return Validador.ValidarTipo.BIG_DECIMAL;
      } else if (property.getType().equalsIgnoreCase("email")) {
         return Validador.ValidarTipo.EMAIL;
      } else if (property.getType().equalsIgnoreCase("ISBN")) {
         return Validador.ValidarTipo.ISBN;
      } else if (property.getType().equalsIgnoreCase("CreditCard")) {
         return Validador.ValidarTipo.CREDIT_CART;
      } else if (property.getType().equalsIgnoreCase("Percent")) {
         return Validador.ValidarTipo.PERCENT;
      } else {
         return property.getType().equalsIgnoreCase("URL") ? Validador.ValidarTipo.URL : Validador.ValidarTipo.INDEFINIDO;
      }
   }

   public boolean isValid() {
      return this.isValid;
   }

   public void setValid(boolean b) {
      this.isValid = b;
   }

   // $FF: synthetic method
   static int[] $SWITCH_TABLE$co$com$personalsoft$base$validator$Validador$ValidarTipo() {
      int[] var10000 = $SWITCH_TABLE$co$com$personalsoft$base$validator$Validador$ValidarTipo;
      if (var10000 != null) {
         return var10000;
      } else {
         int[] var0 = new int[Validador.ValidarTipo.values().length];

         try {
            var0[Validador.ValidarTipo.BIG_DECIMAL.ordinal()] = 9;
         } catch (NoSuchFieldError var15) {
         }

         try {
            var0[Validador.ValidarTipo.BIG_INTEGER.ordinal()] = 8;
         } catch (NoSuchFieldError var14) {
         }

         try {
            var0[Validador.ValidarTipo.BYTE.ordinal()] = 1;
         } catch (NoSuchFieldError var13) {
         }

         try {
            var0[Validador.ValidarTipo.CREDIT_CART.ordinal()] = 12;
         } catch (NoSuchFieldError var12) {
         }

         try {
            var0[Validador.ValidarTipo.DATE.ordinal()] = 2;
         } catch (NoSuchFieldError var11) {
         }

         try {
            var0[Validador.ValidarTipo.DOUBLE.ordinal()] = 7;
         } catch (NoSuchFieldError var10) {
         }

         try {
            var0[Validador.ValidarTipo.EMAIL.ordinal()] = 10;
         } catch (NoSuchFieldError var9) {
         }

         try {
            var0[Validador.ValidarTipo.FLOAT.ordinal()] = 6;
         } catch (NoSuchFieldError var8) {
         }

         try {
            var0[Validador.ValidarTipo.INDEFINIDO.ordinal()] = 15;
         } catch (NoSuchFieldError var7) {
         }

         try {
            var0[Validador.ValidarTipo.INTEGER.ordinal()] = 4;
         } catch (NoSuchFieldError var6) {
         }

         try {
            var0[Validador.ValidarTipo.ISBN.ordinal()] = 11;
         } catch (NoSuchFieldError var5) {
         }

         try {
            var0[Validador.ValidarTipo.LONG.ordinal()] = 5;
         } catch (NoSuchFieldError var4) {
         }

         try {
            var0[Validador.ValidarTipo.PERCENT.ordinal()] = 13;
         } catch (NoSuchFieldError var3) {
         }

         try {
            var0[Validador.ValidarTipo.SHORT.ordinal()] = 3;
         } catch (NoSuchFieldError var2) {
         }

         try {
            var0[Validador.ValidarTipo.URL.ordinal()] = 14;
         } catch (NoSuchFieldError var1) {
         }

         $SWITCH_TABLE$co$com$personalsoft$base$validator$Validador$ValidarTipo = var0;
         return var0;
      }
   }

   static enum ValidarTipo {
      BYTE,
      DATE,
      SHORT,
      INTEGER,
      LONG,
      FLOAT,
      DOUBLE,
      BIG_INTEGER,
      BIG_DECIMAL,
      EMAIL,
      ISBN,
      CREDIT_CART,
      PERCENT,
      URL,
      INDEFINIDO;
   }
}
