package com.markups.umeed;

public class LogInReturn {
    String worker_ID,is_Manager;

    public LogInReturn(String worker_ID, String is_Manager) {
        this.worker_ID = worker_ID;
        this.is_Manager = is_Manager;
    }

    public String getWorker_ID() {
        return worker_ID;
    }

    public String getIs_Manager() {
        return is_Manager;
    }
}
