package com.chc.test.java.service.mokito;

public class Target1 {
    private String a = "default";

    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }

    public String getXXX(String x) {
        return x + x + x;
    }
}
