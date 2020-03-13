package com.chc.test.java.service;

public class Compute {
    public long compute(){
        long a = 1L;
        long b = 2L;
        long c = a + b;
        return c;
    }

    public static void Main(String[] args){
        Compute c = new Compute();
        long compute = c.compute();
        System.out.println(compute);

    }
}
