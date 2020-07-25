package com.markups.umeed;

public class LogInReturn {
    String worker_id,is_manager,name;

    public LogInReturn(String worker_id, String is_manager, String name) {
        this.worker_id = worker_id;
        this.is_manager = is_manager;
        this.name=name;
    }

    public String getWorker_id() {
        return worker_id;
    }

    public String getIs_manager() {
        return is_manager;
    }

    public String getName() {
        return name;
    }
}
