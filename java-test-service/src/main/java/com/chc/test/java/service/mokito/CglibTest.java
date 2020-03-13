package com.chc.test.java.service.mokito;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;

public class CglibTest {
    public static void main(String[] args) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(Target.class);
        enhancer.setCallback((MethodInterceptor) (o, method, objects, methodProxy) -> "helloworld");
        Target proxy = (Target) enhancer.create();
        System.out.println(proxy.doSomeThing("sddd")); // helloworld
    }
}
