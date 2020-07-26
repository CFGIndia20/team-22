package com.markups.umeed.models;

public class WorkersAssigned {
    String task_id,typeName,quantity,worker_id,manager_id;

    public WorkersAssigned(String task_id, String typeName, String quantity, String worker_id, String manager_id) {
        this.task_id = task_id;
        this.typeName = typeName;
        this.quantity = quantity;
        this.worker_id = worker_id;
        this.manager_id = manager_id;
    }

    public String getTask_id() {
        return task_id;
    }

    public void setTask_id(String task_id) {
        this.task_id = task_id;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getWorker_id() {
        return worker_id;
    }

    public void setWorker_id(String worker_id) {
        this.worker_id = worker_id;
    }

    public String getManager_id() {
        return manager_id;
    }

    public void setManager_id(String manager_id) {
        this.manager_id = manager_id;
    }
}
