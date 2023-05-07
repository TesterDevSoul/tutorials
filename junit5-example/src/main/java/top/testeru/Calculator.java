package top.testeru;

import org.slf4j.Logger;

import static java.lang.invoke.MethodHandles.lookup;
import static org.slf4j.LoggerFactory.getLogger;

/**
 * @author testeru.top
 * @version 1.0.0
 * @Prpject junit5-example
 * @Description 计算器
 * @createTime 2023年04月20日 11:05:00
 */
public class Calculator {
    //获得具有所需名称的记录器
    static final Logger logger = getLogger(lookup().lookupClass());

    //加法
    public int sum(int a, int b){
        int result = a + b;
        logger.info("{} + {} = {}", a, b, result);
        return result;
    }

    //除法
    public double division(int a, int b){
        double result = a / b;
        logger.info("{} / {} = {}", a, b, result);
        return result;
    }
}
