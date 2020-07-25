package com.markups.umeed;

public class TasksAssigned {
    String task_id,quantity, typeName,end_date;

    public TasksAssigned(String task_id, String quantity, String typeName, String end_date) {
        this.task_id = task_id;
        this.quantity = quantity;
        this.typeName = typeName;
        this.end_date = end_date;
    }

    public String getTask_id() {
        return task_id;
    }

    public void setTask_id(String task_id) {
        this.task_id = task_id;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getEnd_date() {
        return end_date;
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }
}
