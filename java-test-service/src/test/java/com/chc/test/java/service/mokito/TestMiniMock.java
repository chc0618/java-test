package com.chc.test.java.service.mokito;

import org.junit.jupiter.api.Test;

public class TestMiniMock {

    @Test
    public void testMock() {
        Target1 target = MiniMock.mock(Target1.class);
        MiniMock.when(target.getA()).thenReturn("1000");
        MiniMock.when(target.getXXX("")).thenReturn("xxx");
        System.out.println(target.getA());
        System.out.println(target.getA());
        System.out.println(target.getA());
        System.out.println(target.getXXX("a"));
    }
}
