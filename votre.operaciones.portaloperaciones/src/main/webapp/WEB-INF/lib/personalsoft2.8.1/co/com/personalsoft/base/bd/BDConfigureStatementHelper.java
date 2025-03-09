package co.com.personalsoft.base.bd;

import co.com.personalsoft.base.beans.Parametro;
import co.com.personalsoft.base.beans.Sentencia;
import co.com.personalsoft.base.excepcion.PersonalsoftException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Clob;
import java.sql.PreparedStatement;
import java.sql.Ref;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import org.apache.log4j.Logger;

public class BDConfigureStatementHelper {
   private Logger log = Logger.getLogger(this.getClass());

   public PreparedStatement cargarParametrosPsmt(PreparedStatement psmt, Sentencia sentencia) throws SQLException {
      ArrayList parametros = sentencia.getParametros();
      Parametro param = null;
      PersonalsoftException personalsoftException = null;

      for(int i = 0; i < parametros.size(); ++i) {
         param = (Parametro)parametros.get(i);
         if (param.getValorParametro() == null) {
            param.setTipoParametro(0);
            param.setValorParametro(new Integer(param.getTipoParametro()));
         }

         switch(param.getTipoParametro()) {
         case 0:
            psmt.setNull(i + 1, (Integer)param.getValorParametro());
            break;
         case 2:
            psmt.setInt(i + 1, (Integer)param.getValorParametro());
            break;
         case 4:
            psmt.setInt(i + 1, (Integer)param.getValorParametro());
            break;
         case 6:
            psmt.setFloat(i + 1, (Float)param.getValorParametro());
            break;
         case 8:
            psmt.setDouble(i + 1, (Double)param.getValorParametro());
            break;
         case 12:
            psmt.setString(i + 1, param.getValorParametro().toString());
            break;
         case 16:
            psmt.setBoolean(i + 1, (Boolean)param.getValorParametro());
            break;
         case 91:
            if (param.getValorParametro() instanceof Date) {
               Date date = (Date)param.getValorParametro();
               psmt.setDate(i + 1, new java.sql.Date(date.getTime()));
            } else if (param.getValorParametro() instanceof java.sql.Date) {
               psmt.setDate(i + 1, (java.sql.Date)param.getValorParametro());
            } else {
               psmt.setString(i + 1, param.getValorParametro().toString());
            }
            break;
         case 92:
            psmt.setTimestamp(i + 1, (Timestamp)param.getValorParametro());
            break;
         case 93:
            psmt.setTime(i + 1, (Time)param.getValorParametro());
            break;
         case 2004:
            try {
               psmt.setBinaryStream(i + 1, (InputStream)param.getValorParametro(), ((InputStream)param.getValorParametro()).available());
            } catch (IOException var8) {
               personalsoftException = new PersonalsoftException(var8);
               this.log.error(personalsoftException.getTraza());
            }
            break;
         case 2005:
            try {
               psmt.setBinaryStream(i + 1, (InputStream)param.getValorParametro(), ((InputStream)param.getValorParametro()).available());
            } catch (IOException var9) {
               personalsoftException = new PersonalsoftException(var9);
               this.log.error(personalsoftException.getTraza());
            }
            break;
         case 2006:
            psmt.setRef(i + 1, (Ref)param.getValorParametro());
            break;
         default:
            psmt.setString(i + 1, param.getValorParametro().toString());
         }
      }

      return psmt;
   }

   public CallableStatement cargarParametrosCsmt(CallableStatement csmt, Sentencia sentencia) throws SQLException {
      ArrayList parametros = sentencia.getParametros();
      Parametro param = null;
      PersonalsoftException personalsoftException = null;

      for(int i = 0; i < parametros.size(); ++i) {
         param = (Parametro)parametros.get(i);
         if (param.getValorParametro() == null) {
            param.setTipoParametro(0);
            param.setValorParametro(new Integer(param.getTipoParametro()));
         }

         Date date;
         if (param.getTipoES() == 1) {
            switch(param.getTipoParametro()) {
            case 0:
               csmt.setNull(i + 1, (Integer)param.getValorParametro());
               break;
            case 2:
               csmt.setInt(i + 1, (Integer)param.getValorParametro());
               break;
            case 4:
               csmt.setInt(i + 1, (Integer)param.getValorParametro());
               break;
            case 6:
               csmt.setFloat(i + 1, (Float)param.getValorParametro());
               break;
            case 8:
               csmt.setDouble(i + 1, (Double)param.getValorParametro());
               break;
            case 12:
               csmt.setString(i + 1, param.getValorParametro().toString());
               break;
            case 16:
               csmt.setBoolean(i + 1, (Boolean)param.getValorParametro());
               break;
            case 91:
               if (param.getValorParametro() instanceof Date) {
                  date = (Date)param.getValorParametro();
                  csmt.setDate(i + 1, new java.sql.Date(date.getTime()));
               } else if (param.getValorParametro() instanceof java.sql.Date) {
                  csmt.setDate(i + 1, (java.sql.Date)param.getValorParametro());
               } else {
                  csmt.setString(i + 1, param.getValorParametro().toString());
               }
               break;
            case 92:
               csmt.setTimestamp(i + 1, (Timestamp)param.getValorParametro());
               break;
            case 93:
               csmt.setTime(i + 1, (Time)param.getValorParametro());
               break;
            case 2004:
               try {
                  csmt.setBinaryStream(i + 1, (InputStream)param.getValorParametro(), ((InputStream)param.getValorParametro()).available());
               } catch (IOException var8) {
                  personalsoftException = new PersonalsoftException(var8);
                  this.log.error(personalsoftException.getTraza());
               }
               break;
            case 2005:
               try {
                  csmt.setBinaryStream(i + 1, (InputStream)param.getValorParametro(), ((InputStream)param.getValorParametro()).available());
               } catch (IOException var9) {
                  personalsoftException = new PersonalsoftException(var9);
                  this.log.error(personalsoftException.getTraza());
               }
               break;
            case 2006:
               csmt.setRef(i + 1, (Ref)param.getValorParametro());
               break;
            default:
               csmt.setString(i + 1, param.getValorParametro().toString());
            }
         } else if (param.getTipoES() == 3) {
            switch(param.getTipoParametro()) {
            case 0:
               csmt.setNull(i + 1, (Integer)param.getValorParametro());
               csmt.registerOutParameter(i + 1, 0);
               break;
            case 2:
               csmt.setInt(i + 1, (Integer)param.getValorParametro());
               csmt.registerOutParameter(i + 1, 2);
               break;
            case 4:
               csmt.setInt(i + 1, (Integer)param.getValorParametro());
               csmt.registerOutParameter(i + 1, 4);
               break;
            case 6:
               csmt.setFloat(i + 1, (Float)param.getValorParametro());
               csmt.registerOutParameter(i + 1, 6);
               break;
            case 8:
               csmt.setDouble(i + 1, (Double)param.getValorParametro());
               csmt.registerOutParameter(i + 1, 8);
               break;
            case 12:
               csmt.setString(i + 1, param.getValorParametro().toString());
               csmt.registerOutParameter(i + 1, 12);
               break;
            case 16:
               csmt.setBoolean(i + 1, (Boolean)param.getValorParametro());
               csmt.registerOutParameter(i + 1, 16);
               break;
            case 91:
               if (param.getValorParametro() instanceof Date) {
                  date = (Date)param.getValorParametro();
                  csmt.setDate(i + 1, new java.sql.Date(date.getTime()));
                  csmt.registerOutParameter(i + 1, 91);
               } else if (param.getValorParametro() instanceof java.sql.Date) {
                  csmt.setDate(i + 1, (java.sql.Date)param.getValorParametro());
                  csmt.registerOutParameter(i + 1, 91);
               } else {
                  csmt.setString(i + 1, param.getValorParametro().toString());
                  csmt.registerOutParameter(i + 1, 12);
               }
               break;
            case 92:
               csmt.setTimestamp(i + 1, (Timestamp)param.getValorParametro());
               csmt.registerOutParameter(i + 1, 92);
               break;
            case 93:
               csmt.setTime(i + 1, (Time)param.getValorParametro());
               csmt.registerOutParameter(i + 1, 93);
               break;
            case 2004:
               csmt.setBlob(i + 1, (Blob)param.getValorParametro());
               csmt.registerOutParameter(i + 1, 2004);
               break;
            case 2005:
               csmt.setClob(i + 1, (Clob)param.getValorParametro());
               csmt.registerOutParameter(i + 1, 2005);
               break;
            case 2006:
               csmt.setRef(i + 1, (Ref)param.getValorParametro());
               csmt.registerOutParameter(i + 1, 2006);
               break;
            default:
               csmt.setString(i + 1, param.getValorParametro().toString());
               csmt.registerOutParameter(i + 1, 12);
            }
         } else if (param.getTipoES() == 2) {
            switch(param.getTipoParametro()) {
            case 0:
               csmt.registerOutParameter(i + 1, 0);
               break;
            case 2:
               csmt.registerOutParameter(i + 1, 2);
               break;
            case 4:
               csmt.registerOutParameter(i + 1, 4);
               break;
            case 6:
               csmt.registerOutParameter(i + 1, 6);
               break;
            case 8:
               csmt.registerOutParameter(i + 1, 8);
               break;
            case 12:
               csmt.registerOutParameter(i + 1, 12);
               break;
            case 16:
               csmt.registerOutParameter(i + 1, 16);
               break;
            case 91:
               if (param.getValorParametro() instanceof Date) {
                  csmt.registerOutParameter(i + 1, 91);
               } else if (param.getValorParametro() instanceof java.sql.Date) {
                  csmt.registerOutParameter(i + 1, 91);
               } else {
                  csmt.registerOutParameter(i + 1, 12);
               }
               break;
            case 92:
               csmt.registerOutParameter(i + 1, 92);
               break;
            case 93:
               csmt.registerOutParameter(i + 1, 93);
               break;
            case 2004:
               csmt.registerOutParameter(i + 1, 2004);
               break;
            case 2005:
               csmt.registerOutParameter(i + 1, 2005);
               break;
            case 2006:
               csmt.registerOutParameter(i + 1, 2006);
               break;
            default:
               csmt.registerOutParameter(i + 1, 12);
            }
         }
      }

      return csmt;
   }
}
