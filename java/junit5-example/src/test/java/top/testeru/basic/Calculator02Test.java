package top.testeru.basic;

import org.junit.jupiter.api.*;
import org.slf4j.Logger;
import top.testeru.Calculator;

import static java.lang.invoke.MethodHandles.lookup;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.slf4j.LoggerFactory.getLogger;


/**
 * @author testeru.top
 * @version 1.0.0
 * @Prpject junit5-example
 * @Description 常用注解
 *      @DisplayName 增加可读性
 * @createTime 2023年04月20日 11:53:00
 */
@DisplayName("计算器测试用例")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class Calculator02Test {
    @Test
    @DisplayName("1 + 3")
    @Order(3)
    @Tag("true")
    public void sum01(){
        Calculator calculator = new Calculator();
        int result = calculator.sum(1, 3);
        //assertEquals(expected, actual,String message)
        //expected:期望值,  actual:运算的实际值,  message:断言失败的提示信息
        assertEquals(4, result, "1 + 3 计算错误");
    }
    @Test
    @DisplayName("0 + 0")
    @Order(36)
    @Tag("true")
    public void sum02(){
        Calculator calculator = new Calculator();
        int result = calculator.sum(0, 0);
        //assertEquals(expected, actual,String message)
        //expected:期望值,  actual:运算的实际值,  message:断言失败的提示信息
        assertEquals(0, result, "0 + 0 计算错误");
    }
    @Test
    @DisplayName("-1 + -5")
    @Order(49)
    @Tag("true")
    public void sum03(){
        Calculator calculator = new Calculator();
        int result = calculator.sum(-1, -5);
        //assertEquals(expected, actual,String message)
        //expected:期望值,  actual:运算的实际值,  message:断言失败的提示信息
        assertEquals(-6, result, "-1 + -5 计算错误");
    }
    @Test
    @DisplayName("1 / 2")
    @Order(5)
    @Tag("false")
    public void division01(){
        Calculator calculator = new Calculator();
        double result = calculator.division(1, 2);
        //assertEquals(expected, actual,String message)
        //expected:期望值,  actual:运算的实际值,  message:断言失败的提示信息
        assertEquals(0.5 , result, "1 / 2 计算错误");
    }
    @Test
    @DisplayName("0 / 5")
    @Order(38)
    @Tag("true")
    public void division02(){
        Calculator calculator = new Calculator();
        double result = calculator.division(0, 5);
        //assertEquals(expected, actual,String message)
        //expected:期望值,  actual:运算的实际值,  message:断言失败的提示信息
        assertEquals(0 , result, "0 / 5 计算错误");
    }
    @Test
    @DisplayName("8 / 0")
    @Order(50)
    @Tag("false")
    public void division03(){
        Calculator calculator = new Calculator();
        double result = calculator.division(8, 0);
        //assertEquals(expected, actual,String message)
        //expected:期望值,  actual:运算的实际值,  message:断言失败的提示信息
        assertEquals(0 , result, "8 / 0 计算错误");
    }
    static final Logger logger = getLogger(lookup().lookupClass());
    @BeforeEach
    public void be(){
        logger.info("每个计算前 清0校验");
    }
    @AfterEach
    public void ae(){
        logger.info("每个计算后 清0校验");
    }
    @BeforeAll
    public static void bf(){
        logger.info("开始计算器测试");
    }
    @AfterAll
    public static void af(){
        logger.info("计算器测试结束");
    }
}