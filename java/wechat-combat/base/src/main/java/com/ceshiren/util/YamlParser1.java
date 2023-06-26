//package top.testeru.util;
//
//import com.fasterxml.jackson.core.type.TypeReference;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
//import org.yaml.snakeyaml.Yaml;
//import top.testeru.entity.Case;
//import top.testeru.entity.Key;
//import top.testeru.entity.Step;
//
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
//import java.net.URL;
//import java.util.List;
//import java.util.Map;
//
//public class YamlParser1 {
//
//    public void loadYaml(String yamlPath) {
//        try {
//            // 使用 ClassLoader 获取资源的 URL
//            URL resourceUrl = YamlParser1.class.getClassLoader().getResource(yamlPath);
//            System.out.println("绝对路径：" + resourceUrl);
//
//            // 读取 YAML 文件
//            ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());
//            TypeReference<List<Case>> valueTypeRef = new TypeReference<List<Case>>() {};
//
//            //解析数据
//            List<Case> testCases = objectMapper.readValue(new File(resourceUrl.getPath()), valueTypeRef);
//
//            System.out.println(testCases);
//            // 遍历测试用例
////            String pageObject = (String) testCase.get("page_object");
//
//            testCases.forEach(testCase -> {
//                System.out.println(testCase);
//                System.out.println("Method: " + testCase.getMethod());
//                System.out.println("Return Class: " + testCase.getReturnClass());
//                System.out.println("Description: " + testCase.getDesc());
//                List<Step> steps = testCase.getSteps();
//                for (Step step : steps) {
//                    //Step: find
//                    System.out.println("Step: " + step.getKey());
//                    String keyword = step.getKey();
//                    Map<String, Object> parameters = step.getValue();
//                    System.out.println("parameters: " + parameters);
////                    for (Map<String, Object> parameter : parameters) {
//                        // 处理步骤的参数
//                        // 根据实际参数结构进行相应的处理
////                        AppBasePage appBasePage = new AppBasePage();
//
////                    }
//                    // 执行关键字操作
////                    invokeKeyword(keyword, parameters);
//                }
//                System.out.println();
//            });
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//    private static void invokeKeyword(String keyword, List<Map<String, Object>> parameters) {
//        // 使用 ClassLoader 获取资源的 URL
//        URL resourceUrl = YamlParser1.class.getClassLoader().getResource("keywords.yml");
//        System.out.println("keyword绝对路径：" + resourceUrl);
//
//        // 读取 YAML 文件
//        ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());
//        TypeReference<Key> valueTypeRef = new TypeReference<>() {};
//
//        //解析数据
//        try {
//            Key keys = objectMapper.readValue(new File(resourceUrl.getPath()), valueTypeRef);
//            System.out.println("keys:" +keys);
////            testCases.getBy()
//            System.out.println("parameters-: " +parameters);
//            System.out.println("keyword-: " +keyword);
////            AppiumKeyWords.class.getMethod(keyword, Map.class);
//
//        } catch (Exception e) {
//
//            throw new RuntimeException(e);
//        }
//
////        // 根据关键字和参数调用对应方法
////        // 这里假设页面对象的方法命名规范为 perform<Keyword>，并接收一个 Map 类型的参数
////        try {
////            String methodName = "perform" + keyword;
//////            page.getClass().getMethod(methodName, Map.class).invoke(parameters);
////        } catch (Exception e) {
////            e.printStackTrace();
////        }
//    }
//
//    public void loadYaml1(String yamlPath) {
//        try {
//            // 读取 YAML 文件
//            Yaml yaml = new Yaml();
//            FileInputStream inputStream = new FileInputStream(yamlPath);
//            List<Map<String, Object>> testCases = yaml.load(inputStream);
//            // 遍历测试用例
//            for (Map<String, Object> testCase : testCases) {
//                String method = (String) testCase.get("method");
//                String returnClass = (String) testCase.get("returnClass");
//                String desc = (String) testCase.get("desc");
//
//                System.out.println("Method: " + method);
//                System.out.println("Return Class: " + returnClass);
//                System.out.println("Description: " + desc);
//
////
//            }
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//    }
//}
