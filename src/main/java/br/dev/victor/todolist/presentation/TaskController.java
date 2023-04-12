package br.dev.victor.todolist.presentation;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.dev.victor.todolist.dataAccess.TaskRepository;
import br.dev.victor.todolist.service.Task;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    
    private final TaskRepository taskRepository;

    public TaskController(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;

        this.taskRepository.saveAll(List.of(
            new Task("First Task"),
            new Task("Second Task"),
            new Task("Third Task")
        ));
    }

    @GetMapping
    Iterable<Task> getTasks() {
        return taskRepository.findAll();
    }

    @GetMapping("/{id}")
    Optional<Task> getTask(@PathVariable String id) {
        return taskRepository.findById(id);
    }

    @PostMapping
    Task postTask(@RequestBody Task task) {
        return taskRepository.save(task);
    }

    @PutMapping("{id}")
    ResponseEntity<Task> putTask(@PathVariable String id, @RequestBody Task task) {
        HttpStatus status = taskRepository.existsById(id) ? HttpStatus.OK : HttpStatus.CREATED;
        return new ResponseEntity<>(taskRepository.save(task), status);
    }

    @DeleteMapping("{id}")
    void deleteTask(@PathVariable String id) {
        taskRepository.deleteById(id);
    }
}
