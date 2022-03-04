package com.rbiedrawa.app.services;

import com.rbiedrawa.app.controllers.models.NewTodo;
import com.rbiedrawa.app.controllers.models.Todo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

import static java.util.Map.of;

@Slf4j
@Service
public class TodoService {

    private final AtomicLong idGenerator = new AtomicLong(3);

    // Simulate database
    private static final Map<Long, Todo> TODO_DB = new ConcurrentHashMap<>(of(
            1L, new Todo(1L, "My Todo 1", ZonedDateTime.now()),
            2L, new Todo(2L, "My Todo 2", ZonedDateTime.now())
    )
    );

    public List<Todo> findAll() {
        return new ArrayList<>(TODO_DB.values());
    }

    public Todo add(NewTodo newTodo) {
        final var todo = new Todo(idGenerator.incrementAndGet(), newTodo.getTitle(), ZonedDateTime.now());
        TODO_DB.put(todo.getId(), todo);
        log.info("Todo created {}", todo);
        return todo;
    }

    public void remove(Long todoId) {
        TODO_DB.remove(todoId);
        log.info("Todo removed id: {}", todoId);
    }

    public Todo update(Long todoId, Todo todo) {
        TODO_DB.replace(todoId, todo);
        return todo;
    }

}