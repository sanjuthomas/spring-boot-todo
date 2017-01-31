package com.example.ml;

import java.net.URISyntaxException;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;

/**
 *
 * @author Sanju Thomas
 *
 */
public class RequestBuilder {


	public static HttpPost post(final QueryOptionsPayload payload) throws URISyntaxException{

		final URIBuilder uriBuilder = MLConfiguration.getURIBuilder(MLEndpoints.CONFIG);
		uriBuilder.setPath(uriBuilder.getPath() +"/" + payload.getName());
		final HttpPost request = new HttpPost(uriBuilder.build());
		final StringEntity params = new StringEntity(payload.getXml(), "UTF-8");
		params.setContentType(payload.getContentType().toString());
		request.setEntity(params);

		return request;
	}

	/**
	 *
	 * @param payload
	 * @return
	 * @throws URISyntaxException
	 */
	public static HttpPut put(final Payload<?> payload, final String... collections) throws URISyntaxException {

		final URIBuilder uriBuilder = MLConfiguration.getURIBuilder(MLEndpoints.DOCUMENT);
		uriBuilder.addParameter("uri", payload.getUri());
		for(final String collection : collections){
			uriBuilder.addParameter("collection", collection);
		}
		final HttpPut request = new HttpPut(uriBuilder.build());
		final StringEntity params = new StringEntity(payload.json(), "UTF-8");
		params.setContentType(payload.getContentType().toString());
		request.setEntity(params);

		return request;
	}

	/**
	 *
	 * @param uri
	 * @return
	 * @throws URISyntaxException
	 */
	public static HttpGet get(final List<NameValuePair> nameValuePairs, final MLEndpoints endpoint) throws URISyntaxException{

		final URIBuilder uriBuilder = MLConfiguration.getURIBuilder(endpoint);
		uriBuilder.addParameters(nameValuePairs);
		return  new HttpGet(uriBuilder.build());
	}

	/**
	 *
	 * @param uri
	 * @return
	 * @throws URISyntaxException
	 */
	public static HttpDelete delete(final String... uris) throws URISyntaxException{

		final URIBuilder uriBuilder = MLConfiguration.getURIBuilder(MLEndpoints.DOCUMENT);
		for(final String uri : uris){
			uriBuilder.addParameter("uri", uri);
		}
		return new HttpDelete(uriBuilder.build());
	}
}
