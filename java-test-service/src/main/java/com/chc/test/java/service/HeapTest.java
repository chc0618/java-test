package com.chc.test.java.service;

import java.util.ArrayList;

public class HeapTest {
    byte[] a = new byte[1024*100]; //100kb

    public static void main(String[] args) throws InterruptedException {
        ArrayList<HeapTest> heapTesta = new ArrayList<>();
        while (true){
            heapTesta.add(new HeapTest());
            Thread.sleep(30l);
        }
    }
}
