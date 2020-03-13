package com.chc.test.java.service.mokito;

import lombok.Data;

@Data
public class Target implements TargetInterface{
    private String name;

    @Override
    public String doSomeThing(String s) {
        return name + s;
    }
}