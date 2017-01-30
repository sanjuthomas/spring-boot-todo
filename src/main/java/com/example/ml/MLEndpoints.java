package com.example.ml;

/**
 * 
 * @author Sanju Thomas
 *
 */
public enum MLEndpoints {
	
	CONFIG("options-path"),
	DOCUMENT("document-path"),
	QBE("qbe-path"),
	TRANSACTION("transaction-path");
	
	private String property;
	
	private MLEndpoints(String endpoint){
		this.property = endpoint;
	}
	
	public String property(){
		return property;
	}
}
