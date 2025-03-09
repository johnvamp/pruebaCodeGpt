package co.com.personalsoft.base.config;

import javax.transaction.TransactionManager;
import org.jboss.cache.transaction.BatchModeTransactionManager;
import org.jboss.cache.transaction.TransactionManagerLookup;

public class ManejadorTransacciones implements TransactionManagerLookup {
   public TransactionManager getTransactionManager() throws Exception {
      return new BatchModeTransactionManager();
   }
}
