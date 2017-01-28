package com.example;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Sanju Thomas
 *
 * @param <T>
 */
public class TodoRepository<T>  {
	
	private static final TodoRepository<Todo> repo = new TodoRepository<Todo>();
	
	public static TodoRepository<Todo> getInstance(){
		return repo;
	}

	public List<T> findAll() {
		return new ArrayList<>();
	}

	public void save(T t) {
		try {
			RequestProcessor.process(RequestBuilder.put(new Payload<T>(t), "todo"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void deleteInBatch(List<T> t) {
		
	}

}
