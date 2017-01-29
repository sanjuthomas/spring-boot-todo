package com.example.ml;

/**
 * 
 * @author Sanju Thomas
 *
 */
public enum MLDocumentMetatdata {
	
	TODO("todo", "/todo/");
	
	private String collection;
	private String directory;
	
	/**
	 * 
	 * @param collection
	 * @param directory
	 */
	MLDocumentMetatdata(String collection, String directory){
		
		this.collection = collection;
		this.directory = directory;
	}

	public String getCollection() {
		return collection;
	}

	public void setCollection(String collection) {
		this.collection = collection;
	}

	public String getDirectory() {
		return directory;
	}

	public void setDirectory(String directory) {
		this.directory = directory;
	}

}
