package com.chc.test.java.service.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ThreadPoolPkTest {

    public static void main(String[] args) throws InterruptedException {
        Long start = System.currentTimeMillis();
        final List<Integer> list = new ArrayList();
        final Random random = new Random();
        for(int i= 0; i < 10000; i++ ){
            Thread thread = new Thread(){
                @Override
                public void run() {
                    list.add(random.nextInt());
                }
            };
            thread.start();
            thread.join();
        }
        System.out.println("时间" + (System.currentTimeMillis() - start));
        System.out.println("size : " + list.size());
    }
}
