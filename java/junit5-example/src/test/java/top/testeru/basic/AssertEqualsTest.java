package top.testeru.basic;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;

import static java.lang.invoke.MethodHandles.lookup;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.slf4j.LoggerFactory.getLogger;

/**
 * @author testeru.top
 * @version 1.0.0
 * @Prpject junit5-example
 * @Description
 *
 *  assertEquals(expected, actual,String message)
 *  expected:期望值,  actual:运算的实际值,  message:断言失败的提示信息
 *
 *  assertEquals(expected, actual,Supplier<String> messageSupplier)
 *  expected:期望值,  actual:运算的实际值,  messageSupplier:断言失败的运行代码后的提示信息
 * @createTime 2023年04月20日 16:34:00
 */

public class AssertEqualsTest {
    static final Logger logger = getLogger(lookup().lookupClass());

    @Test
    public void sum() {
        int result = 2 + 8;
        logger.info("Sum Result：{}",result);
        //2个参数 -- 断言成功
        //Assertions.assertEquals(10,result);
        assertEquals(10,result);
        logger.info("assertEquals is True");
    }
    @Test
    public void sum1() {
        int result = 2 + 1;
        logger.info("\n Sum Result：{}",result);
        //3个参数 -- String 断言失败
        //Assertions.assertEquals(10, result, "2 + 1运算失败");
        assertEquals(10,result, "2 + 1运算失败");
        logger.info("\n assertEquals is Fail");
    }
    @Test
    public void sum2() {
        int expected = 10;
        int result = 2 + 2;
        logger.info("\nSum Result：{}",result);
        //3个参数 -- Supplier<String> 断言失败
        //Assertions.assertEquals(10, result,  ()->"2 + 2计算结果为："+result+"，期望结果为："+ expected);
        assertEquals(expected,result, ()->"2 + 2计算结果为：" + result + "，期望结果为："+ expected);
        logger.info("\n assertEquals is Fail");
    }
}