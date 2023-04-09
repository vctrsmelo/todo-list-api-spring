package br.dev.victor.todolist.models;

import java.util.UUID;

public class Task {
    private final String id;
    private String name;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
