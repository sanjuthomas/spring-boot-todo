package com.example.ml;

import org.junit.Assert;
import org.junit.Test;

import com.example.ml.MLTransactionManager.TransactionStatus;


/**
 *
 * @author Sanju Thomas
 *
 */
public class TestTransactionManager {

	@Test
	public void shouldGetTransactionId() throws Exception{

		Assert.assertNotNull(MLTransactionManager.begin());
	}

	@Test
	public void shouldCommit() throws Exception{

		final String transactionId = MLTransactionManager.begin();
		Assert.assertEquals(TransactionStatus.ACTIVE, MLTransactionManager.status(transactionId));
		MLTransactionManager.commit(transactionId);
		Assert.assertEquals(TransactionStatus.ENDED, MLTransactionManager.status(transactionId));
	}

	@Test
	public void shouldRollback() throws Exception{

		final String transactionId = MLTransactionManager.begin();
		Assert.assertEquals(TransactionStatus.ACTIVE, MLTransactionManager.status(transactionId));
		MLTransactionManager.rollback(transactionId);
		Assert.assertEquals(TransactionStatus.ENDED, MLTransactionManager.status(transactionId));
	}

}
