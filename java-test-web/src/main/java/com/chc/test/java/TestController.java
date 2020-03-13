package com.chc.test.java;

import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

@RestController
public class TestController {

    @Autowired
    private Redisson redisson;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @GetMapping(value = "hello")
    public String testContorller(){
        return "hello2";
    }

    @GetMapping(value = "deduct")
    public String deductStock(){
        synchronized (this){
            int stock = Integer.parseInt(stringRedisTemplate.opsForValue().get("stock"));
            if (stock > 0) {
                int realStock = stock - 1;
                stringRedisTemplate.opsForValue().set("stock", realStock + "");
                System.out.println("扣减成功， 剩余库存： " + realStock + "");
            } else {
                System.out.println("库存不足");
            }
        }
        return "end";
    }

    @GetMapping(value = "deductRedisLock")
    public String deductRedisLock(){
        String lockkey = "stockProduct";
        String clienId = UUID.randomUUID().toString();
        try{
//            Boolean lckk = stringRedisTemplate.opsForValue().setIfAbsent(lockkey, "lckk");
//            stringRedisTemplate.expire(lockkey,10, TimeUnit.SECONDS);
            Boolean lckk = stringRedisTemplate.opsForValue().setIfAbsent(lockkey, clienId, 10, TimeUnit.SECONDS); //上面两行合并成一个原子操作
            if(!lckk){
                return "error";
            }
            int stock = Integer.parseInt(stringRedisTemplate.opsForValue().get("stock"));
            if (stock > 0) {
                int realStock = stock - 1;
                stringRedisTemplate.opsForValue().set("stock", realStock + "");
                System.out.println("扣减成功， 剩余库存： " + realStock + "");
            } else {
                System.out.println("库存不足");
            }
        }finally {
            if(clienId.equals(stringRedisTemplate.opsForValue().get(lockkey))){
                // 释放锁
                stringRedisTemplate.delete(lockkey);  //抛出异常执行
            }
        }
        return "end";
    }

    @GetMapping(value = "deductRedissonLock")
    public String deductRedissonLock(){
        String lockkey = "stockProduct";
        RLock redissonLock = redisson.getLock(lockkey);
        try{
            redissonLock.lock(30, TimeUnit.SECONDS);
            int stock = Integer.parseInt(stringRedisTemplate.opsForValue().get("stock"));
            if (stock > 0) {
                int realStock = stock - 1;
                stringRedisTemplate.opsForValue().set("stock", realStock + "");
                System.out.println("扣减成功， 剩余库存： " + realStock + "");
            } else {
                System.out.println("库存不足");
            }
        }finally {
            redissonLock.unlock();
        }
        return "end";
    }
}
