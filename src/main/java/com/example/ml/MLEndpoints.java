package com.example.ml;

/**
 * 
 * @author Sanju Thomas
 *
 */
public enum MLEndpoints {
	
	CONFIG("document-path"),
	DOCUMENT("options-path");
	
	private String property;
	
	private MLEndpoints(String endpoint){
		this.property = endpoint;
	}
	
	public String property(){
		return property;
	}
}
