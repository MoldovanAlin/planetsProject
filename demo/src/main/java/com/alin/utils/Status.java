package com.alin.utils;

public enum Status {

    OK("OK"),

    NOK("!NOK"),

    TODO("TODO"),

    EN_ROUTE("En route");

    private String status;

    Status(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


}
