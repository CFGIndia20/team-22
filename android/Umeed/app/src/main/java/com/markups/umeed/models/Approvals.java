package com.markups.umeed.models;

public class Approvals {
    String task_id,worker_id,typeName,worker_uploaded_L0, end_date;

    public Approvals(String task_id, String worker_id, String typeName, String worker_uploaded_L0, String end_date) {
        this.task_id = task_id;
        this.worker_id = worker_id;
        this.typeName = typeName;
        this.worker_uploaded_L0 = worker_uploaded_L0;
        this.end_date=end_date;
    }

    public String getTask_id() {
        return task_id;
    }

    public void setTask_id(String task_id) {
        this.task_id = task_id;
    }

    public String getWorker_id() {
        return worker_id;
    }

    public void setWorker_id(String worker_id) {
        this.worker_id = worker_id;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getWorker_uploaded_L0() {
        return worker_uploaded_L0;
    }

    public void setWorker_uploaded_L0(String worker_uploaded_L0) {
        this.worker_uploaded_L0 = worker_uploaded_L0;
    }

    public String getEnd_date() {
        return end_date;
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }
}
