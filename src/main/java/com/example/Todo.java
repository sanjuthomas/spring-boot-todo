package com.example;

import com.example.ml.MLDocumentMetatdata;

/**
 * 
 * @author Sanju Thomas
 *
 */
public class Todo {

    private String id;
    private String text;
    private boolean done;
    
    public Todo(){}

    public Todo(String id, String text, boolean done) {
    	this.id = id;
        this.text = text;
        this.done = done;
    }
    
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public void toggleDone() {
        setDone(!isDone());
    }
    
    public String uri(){
    	return MLDocumentMetatdata.TODO.getDirectory() + id;
    }

	@Override
	public String toString() {
		return "Todo [id=" + id + ", text=" + text + ", done=" + done + "]";
	}
    
}
