package top.testeru;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.slf4j.Logger;

import java.util.ArrayList;
import java.util.List;

import static java.lang.invoke.MethodHandles.lookup;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.slf4j.LoggerFactory.getLogger;

/**
 * @Author www.testeru.top
 * @Description
 * @Date 2023/4/17 14:17
 */
public class CalculatorTest {
    static final Logger logger = getLogger(lookup().lookupClass());

    //Test注解 是方法上的
    //一个测试类中可以有多个测试方法，即多个@Test注解
    //Test注解修饰的方法返回值类型是void
    //Test注解里面编写的内容是测试用例执行的具体内容及断言结果
    //2个数的整数加法计算
    @Test
    public void addWithTwoIntTest(){
        //1. 创建计算器对象 -- new有参构造，传入参数值为：计算器
        Calculator calculator = new Calculator("计算器");
        //log日志打印：开始进行加法计算
        logger.info("开始进行加法计算");
        //2. 业务逻辑调用 获取结果值  int result
        int result = calculator.sum(1,3);
        logger.info("计算结果为：{}",result);
        //3. 关闭计算器
        calculator.close();
        //4. 断言 -- assertEquals()
        //expected  期望的值；actual 实际值；message 失败原因的解释说明
        assertEquals(4,result,"计算结果失败");
    }
    //3个数的整数加法计算
    @Test
    public void addWithThreeIntTest(){
        //1. 创建计算器对象 -- new有参构造，传入参数值为：计算器
        Calculator calculator = new Calculator("计算器");
        //log日志打印：开始进行加法计算
        logger.info("开始进行加法计算");
        //2. 业务逻辑调用 获取结果值  int result
        int result = calculator.sum(1,3,5);
        logger.info("计算结果为：{}",result);
        //3. 关闭计算器
        calculator.close();
        //4. 断言 -- assertEquals()
        //expected  期望的值；actual 实际值；message 失败原因的解释说明
        assertEquals(9,result,"计算结果失败");
    }

    @Test
    public void addWithNumberFormatException(){
        //1. 创建计算器对象 -- new有参构造，传入参数值为：计算器
        Calculator calculator = new Calculator("计算器");
        //log日志打印：开始进行加法计算
        logger.info("开始进行加法计算");
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
        //3. 关闭计算器
        calculator.close();
        //4. 断言 -- assertTrue()
        assertTrue(nFException.getMessage().contains("加数不可以是100"));
    }

    @Test
    public void addWithIllegalArgumentException(){
        //1. 创建计算器对象 -- new有参构造，传入参数值为：计算器
        Calculator calculator = new Calculator("计算器");
        //log日志打印：开始进行加法计算
        logger.info("开始进行加法计算");
        //2. 计算
        Exception nFException = assertThrowsExactly(IllegalArgumentException.class,
                () -> calculator.sum(-100, -1));
        //3. 关闭计算器
        calculator.close();
        //4. 断言 -- assertTrue()
        assertTrue(nFException.getMessage().contains("请输入[-99,99]范围内"));
    }

    @Test
    public void strWithTwoEnTest () {
        //1. 创建计算器对象 -- new有参构造，传入参数值为：计算器
        Calculator calculator = new Calculator("计算器");
        //log日志打印：开始进行字符串拼接计算
        logger.info("开始进行字符串拼接计算");
        //2. 开始字符串拼接
        String strResult = calculator.concatStr("Hello", "JUnit5");
        logger.info("计算结果为：{}",strResult);//Hello JUnit5
        //3. 关闭计算器
        calculator.close();
        //4. 断言 -- assertEquals()
        assertEquals("Hello JUnit5",strResult,"字符串拼接错误");
    }

    @Test
    public void strWithThreeZhTest () {
        //1. 创建计算器对象 -- new有参构造，传入参数值为：计算器
        Calculator calculator = new Calculator("计算器");
        //log日志打印：开始进行字符串拼接计算
        logger.info("开始进行字符串拼接计算");
        //2. 开始字符串拼接
        String strResult = calculator.concatStr("这里", "是", "北京");
        logger.info("计算结果为：{}",strResult);//Hello JUnit5
        //3. 关闭计算器
        calculator.close();
        //4. 断言 -- assertEquals()
        assertEquals("这里 是 北京",strResult,"字符串拼接错误");
    }

    @Test
    public void mix(){
        List<Executable> assertList = new ArrayList<>();

        //1. 创建计算器对象；有参构造，传入参数值为：计算器。
        Calculator calculator = new Calculator("计算器");
        //2. 调用加法逻辑：result = 3 + 5。
        int result = calculator.sum(3, 5);
        logger.info("加法计算结果：{}", result);
        assertList.add(() -> assertThat("加法计算不等于",result, equalTo(8)));
        assertList.add(() -> assertThat("加法计算不大于",result, greaterThan(5)));

        //3. 调用减法逻辑：re = result - 6。
        int re = calculator.sub(result, 6);
        assertList.add(() -> assertThat("减法计算不等于",re, equalTo(2)));
        assertList.add(() -> assertThat("减法计算不小于",re, lessThan(result)));
        assertList.add(() -> assertThat("减法计算不接近",(double)re, closeTo(result, 6)));
        logger.info("减法计算结果：{}", re);

        //4. 调用字符串拼接逻辑：str = "混合结果 %s",re 。
        String strResult = calculator.concatStr("混合结果", String.valueOf(re));
        logger.info("字符串计算结果：{}", strResult);
        //字符串是否以...开头
        assertList.add(() -> assertThat("字符串是否以...开头",strResult, startsWith("混合结果")));
        //字符串是否以...结尾
        assertList.add(() -> assertThat("字符串是否以...结尾",strResult, endsWith(String.valueOf(re))));
        //字符串是否包含...
        assertList.add(() -> assertThat("字符串是否包含...",strResult, containsString("结果")));

        //5. 关闭计算器。
        calculator.close();
        //6. 断言。
        assertAll(assertList);
    }

}
