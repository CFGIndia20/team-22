package com.markups.umeed.models;

public class Result {
    String worker_id;
    Integer flag;

    public Result(String worker_id, Integer flag) {
        this.worker_id = worker_id;
        this.flag = flag;
    }

    public String getWorker_id() {
        return worker_id;
    }

    public void setWorker_id(String worker_id) {
        this.worker_id = worker_id;
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }
}
