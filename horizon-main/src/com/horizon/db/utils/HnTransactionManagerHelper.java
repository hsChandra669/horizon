package com.horizon.db.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionException;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

@Component
public class HnTransactionManagerHelper {

	Logger logger = LogManager.getLogger(HnTransactionManagerHelper.class);

	@Autowired
    private DataSourceTransactionManager transactionManager;

    public TransactionStatus getTrasaction() throws Exception{
        String methodName = "getTrasaction() - ";
        logger.entry(methodName);
        TransactionStatus txnStatus;
        try {
            TransactionDefinition txnDef = new DefaultTransactionDefinition();
            txnStatus = this.transactionManager.getTransaction(txnDef);
            logger.debug(methodName, "Transaction status ", txnStatus);

        } catch (TransactionException e) {
            logger.error(methodName, "Transaction Exception", e);
            throw e;
        }
        logger.exit(methodName  + txnStatus);
        return txnStatus;
    }

    public boolean rollback(TransactionStatus txnStatus) {
    	String methodName = "rollback()";
    	logger.entry(methodName);
    	boolean isTransactionRollBack = true;
    	if (txnStatus != null) {
    		try {
    			logger.debug(methodName, "Start of Transaction roll back - " + txnStatus);
    			this.transactionManager.rollback(txnStatus);
    			logger.debug(methodName, "Transaction rolled back ");
    		} catch (TransactionException e) {
    			isTransactionRollBack = false;
    			logger.error(methodName, "Transaction Exception", e);
    		}
    	}
        logger.exit(methodName);
        return isTransactionRollBack;
    }

    public boolean commit(TransactionStatus txnStatus) throws Exception{
        String methodName = "commit()";
        logger.entry(methodName);
        boolean isTransactionCommit = true;
        if (txnStatus != null) {
        	try {
        		logger.debug(methodName, "Start of Transaction commit - " + txnStatus);
        		this.transactionManager.commit(txnStatus);
        		logger.debug(methodName,"Transaction committed");
        	} catch (TransactionException e) {
        		isTransactionCommit = false;
        		logger.error(methodName, "Transaction Exception", e);
        		throw e;
        	}
        }

        logger.exit(methodName + isTransactionCommit);
        return isTransactionCommit;
    }

}
