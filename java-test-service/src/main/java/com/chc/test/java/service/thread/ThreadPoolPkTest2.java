package com.chc.test.java.service.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolPkTest2 {
    public static void main(String[] args) {
        Long start = System.currentTimeMillis();
        final List<Integer> list = new ArrayList<>();
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        final Random random = new Random();
        for(int i = 0; i < 10000; i++){
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    list.add(random.nextInt());
                }
            });
        }
        executorService.shutdown();
    }
}
