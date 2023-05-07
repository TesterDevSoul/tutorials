package top.testeru;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.slf4j.Logger;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;

import static java.lang.invoke.MethodHandles.lookup;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.slf4j.LoggerFactory.getLogger;

/**
 * @Author www.testeru.top
 * @Description 优化提取
 * @Date 2023/4/17 14:17
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CalculatorProTest extends BaseTest{

    @Test
    @Order(1)
    @DisplayName("2个整数相加")
    public void addWithTwoIntTest(){
        //2. 业务逻辑调用 获取结果值  int result
        result = calculator.sum(1,3);
        //4. 断言 -- assertEquals()
        //expected  期望的值；actual 实际值；message 失败原因的解释说明
        assertEquals(4,result,"计算结果失败");
    }
    //3个数的整数加法计算
    @Test
    @Order(6)
    @DisplayName("3个整数相加")
    public void addWithThreeIntTest(){
        //2. 业务逻辑调用 获取结果值  int result
        result = calculator.sum(1,3,5);
        //4. 断言 -- assertEquals()
        //expected  期望的值；actual 实际值；message 失败原因的解释说明
        assertEquals(9,result,"计算结果失败");
    }
    @Test
    @Order(9)
    @DisplayName("加法抛出NumberFormat异常")
    public void addWithNumberFormatException(){
        //2. 计算
        /**
         * 异常断言：assertThrows  assertThrowsExactly
         * expectedType  抛出的异常类型
         * executable    异常业务流
         * NumberFormatException extends IllegalArgumentException
         * 需求：异常类要严格匹配并且验证异常消息。
         * */
        //它会在比较异常类型时允许继承关系。
        //如果抛出的异常类型是指定类型的子类或子接口，也会被认为是符合预期的。
        //同时，它不会验证异常消息。
    //        Exception nFException = assertThrows(
    //                IllegalArgumentException.class,
    //                () -> calculator.sum(100, 0));
        //它在比较异常类型时要求严格匹配，不允许继承关系。
        // 只有抛出的异常类型与期望的异常类型完全一致时，才会认为测试通过。
        // 同时，它也会验证异常消息。
        Exception nFException = assertThrowsExactly(NumberFormatException.class,
                () -> calculator.sum(100, 0));
        //4. 断言 -- assertTrue()
        assertTrue(nFException.getMessage().contains("加数不可以是100"));
    }
    @Test
    @Order(16)
    @DisplayName("加法抛出IllegalArgument异常")
    public void addWithIllegalArgumentException(){
        //2. 计算
        Exception nFException = assertThrowsExactly(IllegalArgumentException.class,
                () -> calculator.sum(-100, -1));
        //4. 断言 -- assertTrue()
        assertTrue(nFException.getMessage().contains("请输入[-99,99]范围内"));
    }
    @Test
    @Order(26)
    @DisplayName("2个英文字符串拼接")
    public void strWithTwoEnTest () {
        //2. 开始字符串拼接
        strResult = calculator.concatStr("Hello", "JUnit5");
        //4. 断言 -- assertEquals()
        assertEquals("Hello JUnit5",strResult,"字符串拼接错误");
    }
    @Test
    @Order(36)
    @DisplayName("2个中文字符串拼接")
    public void strWithThreeZhTest () {
        //2. 开始字符串拼接
        strResult = calculator.concatStr("这里", "是", "北京");
        //4. 断言 -- assertEquals()
        assertEquals("这里 是 北京",strResult,"字符串拼接错误");
    }
    @Test
    @Order(46)
    @DisplayName("加减字符串拼接混合计算")
    public void mixTest(){
        List<Executable> assertList = new ArrayList<>();

        //2. 调用加法逻辑：result = 3 + 5。
        result = calculator.sum(3, 5);
        assertList.add(() -> assertThat("加法计算不等于",result, equalTo(8)));
        assertList.add(() -> assertThat("加法计算不大于",result, greaterThan(5)));

        //3. 调用减法逻辑：re = result - 6。
        re = calculator.sub(result, 6);
        assertList.add(() -> assertThat("减法计算不等于",re, equalTo(2)));
        assertList.add(() -> assertThat("减法计算不小于",re, lessThan(result)));
        assertList.add(() -> assertThat("减法计算不接近",(double)re, closeTo(result, 16)));

        //4. 调用字符串拼接逻辑：str = "混合结果 %s",re 。
        strResult = calculator.concatStr("混合结果", String.valueOf(re));
        //字符串是否以...开头
        assertList.add(() -> assertThat("字符串是否以...开头",strResult, startsWith("混合结果")));
        //字符串是否以...结尾
        assertList.add(() -> assertThat("字符串是否以...结尾",strResult, endsWith(String.valueOf(re))));
        //字符串是否包含...
        assertList.add(() -> assertThat("字符串是否包含...",strResult, containsString("结果")));
        //6. 断言。
        assertAll(assertList);
    }
    //2个数的整数加法计算
    //@Test
    //{argumentsWithNames}
    //这个代表是参数化  但是不代表提供参数源  Test\ParameterizedTest 注解二选一
    @ParameterizedTest(name = "加法参数：[{0}, {1}]，期望结果：{2}")
    //代表的是参数的源
    @MethodSource("top.testeru.source.AddSource#sumNum")
    @Order(100)
    @DisplayName("2个整数参数化相加")
    public void addWithTwoIntParamTest(int num1, int num2, int addRe){
        //2. 业务逻辑调用 获取结果值  int result
        result = calculator.sum(num1, num2);
        //4. 断言 -- assertEquals()
        //expected  期望的值；actual 实际值；message 失败原因的解释说明
        assertEquals(addRe,result,"计算结果失败");
    }
    @ParameterizedTest(name = "加法参数：{0}，期望结果：{1}")
    //代表的是参数的源
    @MethodSource("top.testeru.source.AddSource#sumIntWithMore")
    @Order(110)
    @DisplayName("多个整数相加")
    public void addIntWithMoreTest(List<Integer> num, Integer re){
        IntStream intStream = num.stream().mapToInt(Integer::intValue);
        //2. 业务逻辑调用 获取结果值  int result
        result = calculator.sum(intStream.toArray());
        //4. 断言 -- assertEquals()
        //expected  期望的值；actual 实际值；message 失败原因的解释说明
        assertEquals(re,result,"计算结果失败");
    }
}
