package com.chc.test.java.service.mokito;

import java.lang.reflect.Proxy;

public class JDKProxyTest {
    public static void main(String[] args) {
// JDK动态代理的方式的缺点在于 目标类必须实现了接口，否则无法进行代理（不然无法替换）
        TargetInterface proxy = (TargetInterface) Proxy.newProxyInstance(JDKProxyTest.class.getClassLoader(),
                Target.class.getInterfaces(), (proxy1, method, args1) -> {
                    return "hello world";
                });
        System.out.println(proxy.doSomeThing("ss")); // hello world
    }
}
