package com.ceshiren.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.yaml.snakeyaml.Yaml;
import com.ceshiren.AppiumKeyWords;

import com.ceshiren.page.Case;
import com.ceshiren.page.Step;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.ceshiren.AppiumKeyWords.getInstance;

public class PageYamlParser {

    //解析页面
    public void loadYamlPage(String yamlPagePath) {
        
        // 使用 split() 方法拆分字符串
        String[] parts = yamlPagePath.split("#");

        // 获取拆分后的结果
        String yamlFile = parts[0];
        String methodName = parts[1];

        System.out.println("YAML File: " + yamlFile);
        System.out.println("Method Name: " + methodName);
        try {
            // 使用 ClassLoader 获取资源的 URL
            URL resourceUrl = this.getClass().getClassLoader().getResource(yamlFile);
            System.out.println("绝对路径：" + resourceUrl);

            // 读取 YAML 文件
            ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());
            TypeReference<List<Case>> valueTypeRef = new TypeReference<List<Case>>() {};
            
            //解析数据
            List<Case> cases = objectMapper.readValue(new File(resourceUrl.getPath()), valueTypeRef);

            System.out.println(cases);
            // 遍历测试用例
//            String pageObject = (String) testCase.get("page_object");

            List<Case> caseList = cases
                    .stream()
                    .filter(testCase -> testCase.getMethod().equals(methodName))
                    .collect(Collectors.toList());
            caseList.forEach(testCase -> {
                System.out.println(testCase);
                System.out.println("Method: " + testCase.getMethod());
                System.out.println("Return Class: " + testCase.getReturnClass());
                System.out.println("Description: " + testCase.getDesc());
                HashMap<String, String> args = testCase.getArgs();
                System.out.println("args: " + args);
                //args进行全局变量替换 args遍历
                for (Map.Entry<String, String> entry : args.entrySet()) {
                    String value = entry.getValue().toString().trim();
                    if(value.startsWith("${") && value.endsWith("}")){
                        value = value.replace("${", "").replace("}", "");                    }
                        value = GlobalVariables.get(value).toString();
                        System.out.println("o:" + value);
                        //放入args内
                        entry.setValue(value);

                }
                System.out.println("argsAfter: " + args);

                List<Step> steps = testCase.getSteps();
                for (Step step : steps) {
                    //Step: find
                    System.out.println("Step: " + step.getKey());
                    String keyword = step.getKey();
                    Map<String, Object> parameters = step.getValue();
                    System.out.println("parameters: " + parameters);
                    //执行替换操作
                    for (Map.Entry<String, Object> entry : parameters.entrySet()) {
                        String key = entry.getKey();
                        Object value = entry.getValue();

                        if (args.containsKey(value)) {
                            Object replacement = args.get(value);
                            parameters.put(key, replacement.toString());
                            System.out.println("replacement:"+replacement);
                        }
                    }

                    System.out.println("args:"+args);
                    System.out.println("替换后的parameters:"+parameters);
                    // 执行关键字操作
                    invokeKeyword(keyword, parameters);
                }
                System.out.println();
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private static void invokeKeyword(String keyword, Map<String, Object> parameters) {

        System.out.println("parameters-: " +parameters);
        System.out.println("keyword-: " +keyword);
        try {
            Method method = AppiumKeyWords.class.getMethod(keyword, Map.class);
//            AppiumKeyWords appiumKeyWords = new AppiumKeyWords();
            method.invoke(getInstance(), parameters);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void loadYaml1(String yamlPath) {
        try {
            // 读取 YAML 文件
            Yaml yaml = new Yaml();
            FileInputStream inputStream = new FileInputStream(yamlPath);
            List<Map<String, Object>> testCases = yaml.load(inputStream);
            // 遍历测试用例
            for (Map<String, Object> testCase : testCases) {
                String method = (String) testCase.get("method");
                String returnClass = (String) testCase.get("returnClass");
                String desc = (String) testCase.get("desc");

                System.out.println("Method: " + method);
                System.out.println("Return Class: " + returnClass);
                System.out.println("Description: " + desc);

//
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
