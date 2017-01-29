package com.example.ml;

/**
 * 
 * @author Sanju Thomas
 *
 */
public enum MLEndpoints {
	
	CONFIG("/v1/documents"),
	DOCUMENT("/v1/config/query");
	
	private String endpoint;
	
	private MLEndpoints(String endpoint){
		this.endpoint = endpoint;
	}
	
	public String endpoint(){
		return endpoint;
	}
}
