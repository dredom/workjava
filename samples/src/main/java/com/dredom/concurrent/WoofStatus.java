package com.dredom.concurrent;

public class WoofStatus {

    String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "[status='" + status + "']";
    }

}
