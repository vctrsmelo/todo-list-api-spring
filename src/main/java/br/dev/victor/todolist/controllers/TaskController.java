package br.dev.victor.todolist.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.dev.victor.todolist.models.Task;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    
    private List<Task> tasks = new ArrayList<>();

    public TaskController() {
        tasks.addAll(List.of(
            new Task("First Task"),
            new Task("Second Task"),
            new Task("Third Task")
        ));
    }

    @GetMapping
    Iterable<Task> getTasks() {
        return tasks;
    }

    @GetMapping("/{id}")
    Optional<Task> getTask(@PathVariable String id) {
        return tasks.stream()
                    .filter(task -> task.getId().equals(id))
                    .findFirst();
    }

    @PostMapping
    Task postTask(@RequestBody Task task) {
        tasks.add(task);
        return task;
    }

    @PutMapping("{id}")
    Task putTask(@PathVariable String id, @RequestBody Task task) {
        int index = -1;
        for (Task t : tasks) {
            if (t.getId().equals(id)) {
                index = tasks.indexOf(t);
                tasks.set(index, task);
                break;
            }
        }

        return (index == -1) ? postTask(task) : task;
    }

    @DeleteMapping("{id}")
    ResponseEntity<String> deleteTask(@PathVariable String id) {
        Boolean isDeleted = tasks.removeIf(t -> t.getId().equals(id));
        return isDeleted ? ResponseEntity.ok("Deleted with success") : ResponseEntity.badRequest().body("Did not find the task with ID "+id);
    }
}
