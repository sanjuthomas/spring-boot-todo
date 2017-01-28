package com.example;

public class Todo {

    private String id;

    private String text;

    private boolean done;

    public Todo(String id, String text, boolean done) {
    	this.id = id;
        this.text = text;
        this.done = done;
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
    
    @Override
  	public String toString() {
  		return "/todo/"+id;
  	}

}
