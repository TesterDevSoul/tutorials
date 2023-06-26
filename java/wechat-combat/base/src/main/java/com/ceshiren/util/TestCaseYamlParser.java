package com.ceshiren.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.ceshiren.test.TestCases;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.List;

public class TestCaseYamlParser {
    PageYamlParser yamlParser = new PageYamlParser();

    //解析测试用例

    public void loadYamlTestCase(String YamlTestCasePath) {
        // 使用 ClassLoader 获取资源的 URL
        URL resourceUrl = this.getClass().getClassLoader().getResource(YamlTestCasePath);
        System.out.println("绝对路径：" + resourceUrl);

        // 读取 YAML 文件
        ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());
        TypeReference<TestCases> valueTypeRef = new TypeReference<TestCases>() {};

        //解析数据
        try {
            TestCases testCases = objectMapper.readValue(new File(resourceUrl.getPath()), valueTypeRef);
            System.out.println(testCases);
            testCases.getMethods().forEach(testCase -> {
                System.out.println("method:" + testCase.getMethod());
                System.out.println("desc:" + testCase.getDesc());
                System.out.println("tags:" + testCase.getTags());
                //执行步骤
                List<String> steps = testCase.getSteps();
                System.out.println("steps:" + steps);
                steps.forEach(s -> {
                    System.out.println("step:" + s);
                    if(s.contains("=")) {
                        //赋值操作
                        System.out.println("赋值操作:" + s);
                        // 使用 split() 方法拆分字符串
                        String[] parts = s.split("=");

                        // 获取拆分后的结果
                        String key = parts[0].trim();
                        String value = parts[1].trim();

                        System.out.println("变量名: " + key);
                        System.out.println("变量赋值: " + value);
                        try {
                            if(value.contains("#")){
                                String[] vparts = value.split("#");
                                String className = vparts[0].trim();
                                String methodName = vparts[1].trim();
                                System.out.println("className:"+className);
                                System.out.println("methodName:"+methodName);
                                // 通过反射获取方法对象
                                // 获取当前包名
                                String packageName = this.getClass().getPackage().getName();

                                System.out.println("当前包名：" + packageName);
                                Class<?> cls = Class.forName(packageName + "." + className);
                                Method method = cls.getMethod(methodName);

                                // 创建类的实例并调用方法
                                Object instance = cls.getDeclaredConstructor().newInstance();
                                value = method.invoke(instance).toString();
                                System.out.println("结果："+value);
                                GlobalVariables.put(key, value);
                            }
                            System.out.println(GlobalVariables.get(key));
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    }else {
                        yamlParser.loadYamlPage(s);
                    }
                });
            });

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
