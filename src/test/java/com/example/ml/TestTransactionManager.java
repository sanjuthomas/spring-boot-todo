package com.example.ml;

import org.junit.Assert;
import org.junit.Test;


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
		System.out.println(transactionId);
		MLTransactionManager.commit(transactionId);
	}

}
