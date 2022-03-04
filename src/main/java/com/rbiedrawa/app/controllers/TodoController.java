package com.rbiedrawa.app.controllers;

import com.rbiedrawa.app.controllers.models.NewTodo;
import com.rbiedrawa.app.controllers.models.Todo;
import com.rbiedrawa.app.services.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TodoController implements TodosApi {

    private final TodoService todoService;

    @Override
    public ResponseEntity<Todo> createTodo(NewTodo newTodo) {
        return ResponseEntity.ok(todoService.add(newTodo));
    }

    @Override
    public ResponseEntity<Void> deleteTodo(Long todoId) {
        todoService.remove(todoId);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<List<Todo>> listTodos() {
        return ResponseEntity.ok(todoService.findAll());
    }

    @Override
    public ResponseEntity<Todo> updateTodo(Long todoId, Todo todo) {
        return ResponseEntity.ok(todoService.update(todoId, todo));
    }
}
