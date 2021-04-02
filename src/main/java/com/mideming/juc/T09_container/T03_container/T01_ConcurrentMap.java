package com.mideming.juc.T09_container.T03_container;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListMap;

public class T01_ConcurrentMap {
    public static void main(String[] args) {
        Map<String,String> map = new ConcurrentHashMap<>();
        // 高并发并且排序
//        Map<String,String> map = new ConcurrentSkipListMap<>();
    }
}
