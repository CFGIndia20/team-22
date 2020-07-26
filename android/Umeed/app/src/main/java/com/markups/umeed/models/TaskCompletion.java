package com.markups.umeed.models;

public class TaskCompletion {
    String task_id,typeName,date,description,worker_id;
    Integer quantity;

    public TaskCompletion(String task_id, String worker_id, Integer quantity, String typeName, String date,String description) {
        this.task_id = task_id;
        this.quantity = quantity;
        this.typeName = typeName;
        this.date = date;
        this.description=description;
        this.worker_id=worker_id;
    }

    public String getTask_id() {
        return task_id;
    }

    public void setTask_id(String task_id) {
        this.task_id = task_id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getWorker_id() {
        return worker_id;
    }

    public void setWorker_id(String worker_id) {
        this.worker_id = worker_id;
    }
}
