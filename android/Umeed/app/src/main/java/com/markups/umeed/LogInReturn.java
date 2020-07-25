package com.markups.umeed;

public class LogInReturn {
    String worker_id,is_manager;

    public LogInReturn(String worker_id, String is_manager) {
        this.worker_id = worker_id;
        this.is_manager = is_manager;
    }

    public String getWorker_id() {
        return worker_id;
    }

    public String getIs_manager() {
        return is_manager;
    }
}
