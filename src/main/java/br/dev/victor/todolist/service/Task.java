package br.dev.victor.todolist.service;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Task {

    @Id
    private String id;

    private String name;

    public Task() {}

    public Task(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public Task(String name) {
        this(UUID.randomUUID().toString(), name);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
      this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
