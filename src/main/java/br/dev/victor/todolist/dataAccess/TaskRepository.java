package br.dev.victor.todolist.dataAccess;

import org.springframework.data.repository.CrudRepository;

import br.dev.victor.todolist.service.Task;

public interface TaskRepository extends CrudRepository<Task, String> {}
