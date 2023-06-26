package com.ceshiren.util;

import java.util.HashMap;
import java.util.Map;

//全局变量声明
public class GlobalVariables {
    private static GlobalVariables instance;
    private static Map<String, Object> globalMap = new HashMap<>();

    private GlobalVariables() {
        // 私有化构造函数，防止外部实例化
    }

    public static GlobalVariables getInstance() {
        if (instance == null) {
            instance = new GlobalVariables();
        }
        return instance;
    }

    public static Object get(String key) {
        System.out.println("globalMap:"+globalMap);
        return globalMap.get(key);
    }

    public static void put(String key, Object value) {
        globalMap.put(key, value);
    }
}
