package com.example.ml;

import org.apache.http.entity.ContentType;


/**
 * 
 * @author Sanju Thomas
 *
 */
public class QueryOptionsPayload {
	
	private final ContentType DEFAULT_CONTENT_TYPE = ContentType.APPLICATION_XML;
	private String name;
	private String xml;
	
	public QueryOptionsPayload(String name, String xml){
		this.name = name;
		this.xml = xml;
	}
	
	public ContentType getContentType() {
		return this.DEFAULT_CONTENT_TYPE;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getXml() {
		return xml;
	}
	public void setXml(String xml) {
		this.xml = xml;
	}

}
