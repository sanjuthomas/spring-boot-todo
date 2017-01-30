package com.example.ml;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;

/**
 * 
 * @author Sanju Thomas
 *
 */
public class MLTransactionManager {
	
	private static final ContentType DEFAULT_CONTENT_TYPE = ContentType.TEXT_PLAIN;
	
	public static String begin() throws Exception{
		
		String transactionId = null;
		final URIBuilder uriBuilder = MLConfiguration.getURIBuilder(MLEndpoints.TRANSACTION);
		final HttpPost request = new HttpPost(uriBuilder.build());
		final StringEntity params = new StringEntity("", "UTF-8");
		params.setContentType(DEFAULT_CONTENT_TYPE.toString());
		request.setEntity(params);
		
		final HttpResponse response = RequestProcessor.process(request);
		final HttpEntity entity = response.getEntity();
		
		if(null != entity){
			transactionId = IOUtils.toString(entity.getContent());
		}else{
			throw new Exception("Failed to create the transaction. Please check MarkLogic server error log for more details");
		}
		
		return transactionId;
	}

}
