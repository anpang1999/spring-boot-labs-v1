package com.example.ch2labs.labs04;

public class Result {
    private String user;
    private String server;
    private String result;

    public Result(String user, String server, String result) {
        this.user = user;
        this.server = server;
        this.result = result;
    }

    // Getters (필요시 Setters도 추가 가능)
    public String getUser() {
        return user;
    }

    public String getServer() {
        return server;
    }

    public String getResult() {
        return result;
    }
}
