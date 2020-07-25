package com.markups.umeed;

public class Worker {
    String name,worker_id;

    public Worker(String name, String worker_id) {
        this.name = name;
        this.worker_id = worker_id;
    }

    public String getName() {
        return name;
    }

    public String getWorker_id() {
        return worker_id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setWorker_id(String worker_id) {
        this.worker_id = worker_id;
    }
}
