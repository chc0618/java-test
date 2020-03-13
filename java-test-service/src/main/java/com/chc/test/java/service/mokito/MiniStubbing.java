package com.chc.test.java.service.mokito;

public class MiniStubbing {
    public MiniStubbing thenReturn(Object result) {
        if (RetValueContainer.getKey() != null) {
            RetValueContainer.add(RetValueContainer.getKey(), result);
        } else {
            throw new UnsupportedOperationException();
        }
        return this;
    }
}
