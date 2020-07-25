package com.markups.umeed;

public class TasksAssigned {
    String task_id,quantity,product_id,end_date;

    public TasksAssigned(String task_id, String quantity, String product_id, String end_date) {
        this.task_id = task_id;
        this.quantity = quantity;
        this.product_id = product_id;
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

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public String getEnd_date() {
        return end_date;
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }
}
