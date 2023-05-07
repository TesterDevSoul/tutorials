package top.testeru.cal;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
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
public class Calculator03Test {
    Calculator calculator = new Calculator();
    @ParameterizedTest(name = "{0} + {1}")
    @MethodSource("top.testeru.source.SumSource#sumParams")
    @Order(3)
    @DisplayName("加法测试用例")
    public void sumParams(int a, int b, int re){
        int result = calculator.sum(a, b);
        //assertEquals(expected, actual,String message)
        //expected:期望值,  actual:运算的实际值,  message:断言失败的提示信息
        assertEquals(re, result, a + "+" + b + "计算错误");
    }

    @ParameterizedTest(name = "{0} / {1}")
    @MethodSource("top.testeru.source.DivisionSource#diviParams")
    @DisplayName("除法测试用例")
    @Order(5)
    public void diviParams(int a, int b, double re){
        double result = calculator.division(a, b);
        //assertEquals(expected, actual,String message)
        //expected:期望值,  actual:运算的实际值,  message:断言失败的提示信息
        assertEquals(re , result, a + "/" + b + "计算错误");
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