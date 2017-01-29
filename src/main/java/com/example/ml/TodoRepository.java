package com.example.ml;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.Todo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 
 * @author Sanju Thomas
 *
 * @param <T>
 */
public class TodoRepository<T>  {
	
	private static final Logger logger = LoggerFactory.getLogger(TodoRepository.class);
	private static final ObjectMapper MAPPER = new ObjectMapper();
	
	public static List<Todo> findAll() {
		
		final List<Todo> openTodos = new ArrayList<Todo>();
		try {
			final List<NameValuePair> params = new ArrayList<NameValuePair>();
			final NameValuePair options = new BasicNameValuePair("options", "todo-options");
			final NameValuePair query = new BasicNameValuePair("query", "{ '$query': {'done': false} }");
			params.add(options);
			params.add(query);
			final HttpResponse response = RequestProcessor.process(RequestBuilder.get(params, MLEndpoints.QBE));
			openTodos.addAll(extractDocuments(response));
		} catch (IOException | URISyntaxException e) {
			logger.error(e.getMessage(), e);
		}
		
		return openTodos;
	}
	
	private static List<Todo> extractDocuments(HttpResponse response) throws JsonProcessingException, UnsupportedOperationException, IOException{
		
		final List<Todo> todos = new ArrayList<Todo>();
		final HttpEntity entity = response.getEntity();
		if(null != entity){
			final JsonNode searchResult = MAPPER.readTree(entity.getContent());
			final JsonNode results = searchResult.get("results");
			final Iterator<JsonNode> resultNodes = results.iterator();
			while(resultNodes.hasNext()){
				final JsonNode resultNode = resultNodes.next();
				final JsonNode content = resultNode.get("content");
				todos.add(MAPPER.treeToValue(content, Todo.class));
			}
		}
		return todos;
	}
	

	public static void save(Todo t) {
		try {
			RequestProcessor.process(RequestBuilder.put(new Payload<Todo>(t), MLDocumentMetatdata.TODO.getCollection()));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	public static void deleteInBatch(List<Todo> t) {
		
	}

}
