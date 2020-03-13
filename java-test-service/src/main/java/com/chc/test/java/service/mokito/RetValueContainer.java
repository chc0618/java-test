package com.chc.test.java.service.mokito;

import java.util.concurrent.ConcurrentHashMap;

public class RetValueContainer {
    private static String matcherKey = null;
    private static final ConcurrentHashMap<String, Object> candidateMap = new ConcurrentHashMap<>();

    public static void setMatcherKey(String matcherKey) {
        RetValueContainer.matcherKey = matcherKey;
    }

    public static String getKey() {
        return matcherKey;
    }

    public static void add(String key, Object value) {
        candidateMap.put(key, value);
    }

    public static Object match(String hashCode) {
        return candidateMap.get(hashCode);
    }
}
