package com.example;

import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import com.example.ml.TodoRepository;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.VerticalLayout;

@UIScope
@SpringComponent
class TodoList extends VerticalLayout implements TodoChangeListener {
	
	private static final long serialVersionUID = 1L;
    private List<Todo> todos;

    @PostConstruct
    void init() {
        setWidth("80%");
        setSpacing(true);

        update();
    }

    private void update() {
        setTodos(TodoRepository.findAll());
    }

    private void setTodos(List<Todo> todos) {
        this.todos = todos;
        removeAllComponents();
        todos.forEach(todo -> addComponent(new TodoLayout(todo, this)));
    }

     void addTodo(Todo todo) {
    	 TodoRepository.save(todo);
        update();
    }

    @Override
    public void todoChanged(Todo todo) {
        addTodo(todo);
    }

    public void deleteCompleted() {
    	TodoRepository.deleteInBatch(
                todos.stream().filter(Todo::isDone).collect(Collectors.toList())
        );
        update();
    }
}
