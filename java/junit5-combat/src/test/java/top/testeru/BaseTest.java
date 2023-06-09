package top.testeru;

import org.junit.jupiter.api.*;
import org.slf4j.Logger;

import java.lang.reflect.Method;
import java.util.Optional;

import static java.lang.invoke.MethodHandles.lookup;
import static org.slf4j.LoggerFactory.getLogger;

/**
 * @author testeru.top
 * @version 1.0.0
 * @Prpject junit5-combat
 * @Description
 * @createTime 2023年04月18日 11:16:00
 */
public class BaseTest {
    public static final Logger logger = getLogger(lookup().lookupClass());
    public static Calculator calculator;
    //加法计算结果
    public int result;
    //减法计算结果
    public int re;
    //字符串拼接计算结果
    public String strResult;


    //AfterAll注解 static修饰 void返回值   与在代码中的前后顺序无关
    //在测试类里面运行一次，并且是在所有的方法之后运行一次
    //作用：apk卸载 app退出，测试用例结束，web端关闭浏览器操作。。。
    @AfterAll
    public static void afterAll(){
        calculator.close();
    }

    //void返回值
    //AfterEach注解 在每一个@Test注解修饰的方法之后运行一次；
    // 所以，当前测试类有多少个Test注解，AfterEach注解修饰的方法就运行多少次
    //无论@Test注解修饰的测试方法是否断言成功，@AfterEach方法的内容都去运行
    //作用：测试用例中，测试方法需要初始化的内容及属性「app/web端进入固定页面，回退到固定页面；重启app；删除某些产生的测试数据」
    @AfterEach
    public void afterEach(TestInfo testInfo){
        Optional<String> optional = testInfo
                .getTestMethod()
                .map(Method::getName)//获取方法名
                .filter(str -> !str.contains("Exception"));
        if(optional.filter(s -> s.startsWith("add")).isPresent()){
            logger.info("加法计算结果：{}", result);
        } else if (optional.filter(s -> s.startsWith("sub")).isPresent()) {
            logger.info("减法计算结果：{}", re);
        }else if (optional.filter(s -> s.startsWith("str")).isPresent()) {
            logger.info("字符串计算结果：{}", strResult);
        }else{
            logger.info("加法计算结果：{}", result);
            logger.info("减法计算结果：{}", re);
            logger.info("字符串计算结果：{}", strResult);
        }
    }

    //BeforeAll注解  方法上 static修饰的
    //在测试类里面运行一次，并且是在所有的方法之前运行一次
    //作用：对象的声明 测试数据准备，log日志删除，apk安装，启动的某些参数的配置 AppiumDriver webdriver ChromeDriver
    @BeforeAll
    public static void beforeAll(){
        //1. 创建计算器对象 -- new有参构造，传入参数值为：计算器
        calculator = new Calculator("计算器");
    }

    //void返回值
    //BeforeEach注解 在每一个@Test注解修饰的方法之前运行一次；所以，当前测试类有多少个Test注解，BeforeEach注解修饰的方法就运行多少次
    //作用：测试用例中，测试方法需要初始化的内容及属性「app/web端进入固定页面，回退到固定页面；重启app；删除某些产生的测试数据」
    @BeforeEach
    public void beforeEach(TestInfo testInfo){
        String strCase;
        //method            strCase
        // sum 支付           加法   数据库连接、注册会员
        // sub 营销           减法   新用户输入数据库
        // str 主业务线     字符串拼接  apk卸载
        Optional<Method> testMethod = testInfo.getTestMethod();
        //获取 Method 类里面的 getName() 方法的返回值  Optional<String>
        Optional<String> s = testMethod.map(Method::getName);
        if (s.filter(str -> str.contains("add")).isPresent()) {
            strCase = "加法";
        }else if (s.filter(str -> str.contains("sub")).isPresent()){
            strCase = "减法";
        } else if (s.filter(str -> str.contains("str")).isPresent()) {
            strCase = "字符串拼接";
        }else {
            strCase = "混合";
        }
        logger.info("开始进行 {} 计算", strCase);
    }
}
