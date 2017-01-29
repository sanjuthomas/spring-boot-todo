package com.example.ml;

import org.apache.http.entity.ContentType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.Todo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 *
 * @author Sanju Thomas
 *
 */
public class Payload<T>{

	private static final Logger logger = LoggerFactory.getLogger(Payload.class);
	private static final ObjectMapper MAPPER = new ObjectMapper();
	private Todo entity;
	private final ContentType DEFAULT_CONTENT_TYPE = ContentType.APPLICATION_JSON;

	public Payload(final Todo t){
		this.entity = t;
	}

	public String getUri(){
		return entity.uri();
	}

	public Todo getEntity() {
		return this.entity;
	}

	public void setEntity(final Todo entity) {
		this.entity = entity;
	}

	public ContentType getContentType() {
		return this.DEFAULT_CONTENT_TYPE;
	}

	public String json(){
		try {
			return MAPPER.writeValueAsString(this.entity);
		} catch (final JsonProcessingException e) {
			logger.error("JSON conversion failed", e);
		}
		return null;
	}

}