package com.example.ml;

/**
 *
 * @author Sanju Thomas
 *
 */
public enum MLEndpoints {

	CONFIG("options-path"),
	DOCUMENT("document-path"),
	SEARCH("search-path");
	
	private String property;

	private MLEndpoints(final String endpoint){
		this.property = endpoint;
	}

	public String property(){
		return this.property;
	}
}
