package com.example.ml;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;

/**
 * 
 * @author Sanju Thomas
 *
 */
public class MLTransactionManager {
	
	private static final ContentType DEFAULT_CONTENT_TYPE = ContentType.TEXT_PLAIN;
	
	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	public static String begin() throws Exception{
		
		final HttpPost request = buildRequest(null, new ArrayList<>());
		request.addHeader(new BasicHeader("Accept", "application/json"));
		final HttpResponse response = RequestProcessor.process(request);
		final HttpEntity entity = response.getEntity();
		if(null != entity){
			return IOUtils.toString(entity.getContent());
		}else{
			throw new Exception("Failed to create the transaction. Please check MarkLogic server error log for more details.");
		}
	}

	private static HttpPost buildRequest(final String path, final List<NameValuePair> nameValuePairs) throws URISyntaxException {
		
		final URIBuilder uriBuilder = MLConfiguration.getURIBuilder(MLEndpoints.TRANSACTION);
		uriBuilder.addParameters(nameValuePairs);
		if(null != path){
			uriBuilder.setPath("/" + path);
		}
		final HttpPost request = new HttpPost(uriBuilder.build());
		final StringEntity params = new StringEntity("", "UTF-8");
		params.setContentType(DEFAULT_CONTENT_TYPE.toString());
		request.setEntity(params);
		return request;
	}
	
	/**
	 * 
	 * @param transactionId
	 * @throws Exception 
	 */
	public static void commit(final String transactionId) throws Exception{
		
		final List<NameValuePair> params = buildParams("commit");
		final HttpPost request = buildRequest(transactionId, params);
		final HttpResponse response = RequestProcessor.process(request);
		if(204 != response.getStatusLine().getStatusCode()){
			throw new Exception("Failed to commit the transcation. Please check MarkLogic server error log for more details.");
		}
	}

	private static List<NameValuePair> buildParams(final String value) {
		
		final List<NameValuePair> params = new ArrayList<NameValuePair>();
		final NameValuePair result = new BasicNameValuePair("result", value);
		params.add(result);
		return params;
	}
	
	/**
	 * 
	 * @param transactionId
	 * @throws Exception 
	 */
	public static void rollback(final String transactionId) throws Exception{
		
		final List<NameValuePair> params = buildParams("rollback");
		final HttpPost request = buildRequest(transactionId, params);
		final HttpResponse response = RequestProcessor.process(request);
		if(204 != response.getStatusLine().getStatusCode()){
			throw new Exception("Failed to commit the transcation. Please check MarkLogic server error log for more details.");
		}
	}

}
