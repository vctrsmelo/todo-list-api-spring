package br.dev.victor.todolist.service;

import java.util.List;

import org.springframework.stereotype.Component;

import br.dev.victor.todolist.dataAccess.TaskRepository;
import jakarta.annotation.PostConstruct;

@Component
public class DataLoader {
  private final TaskRepository taskRepository;

  public DataLoader(TaskRepository taskRepository) {
    this.taskRepository = taskRepository;
  }

  @PostConstruct
  private void loadData() {
    this.taskRepository.saveAll(List.of(
        new Task("First Task"),
        new Task("Second Task"),
        new Task("Third Task")
    ));
  }
}
