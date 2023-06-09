package top.testeru.basic;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;

import static java.lang.invoke.MethodHandles.lookup;
import static org.slf4j.LoggerFactory.getLogger;

/**
 * @author testeru.top
 * @version 1.0.0
 * @Prpject junit5-example
 * @Description Test注解
 * 
 * 1. @Test注解修饰的方法可直接运行「@Test注解 是方法上的」
 * 2. 一个测试类里可以有多个@Test注解修饰的方法
 * 3. @Test注解作用类似Java代码中的main()方法入口
 * 4. @Test注解修饰的方法没有返回值，即方法声明时为void
 * 5. @Test注解里面编写的内容是测试用例执行的具体内容及断言结果
 *
 * @createTime 2023年04月20日 11:17:00
 */
public class An_01Test_Test {
    static final Logger logger = getLogger(lookup().lookupClass());

    @Test
    public void one(){
        System.out.println("第一个测试用例");

    }
    @Test
    public void sum() {
        int result = 2 + 8;
        logger.info("Sum Result：{}",result);
    }
    @Test
    public void sum1() {
        int result = 2 + 1;
        logger.info("Sum Result：{}",result);
    }
    @Test
    public void sum2() {
        int result = 2 + 2;
        logger.info("Sum Result：{}",result);
    }
    @Test
    public void sum3() {
        int result = 2 + 3;
        logger.info("Sum Result：{}",result);
    }
    @Test
    public void sum4 () {
        int result = 2 + 4;
        logger.info("Sum Result：{}",result);
    }
}
