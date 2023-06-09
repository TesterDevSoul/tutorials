package top.testeru.suite;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;
import org.junit.platform.suite.api.SuiteDisplayName;
import top.testeru.cal.Calculator01Test;
import top.testeru.cal.Calculator02Test;

/**
 * @author testeru.top
 * @version 1.0.0
 * @Prpject junit5-example
 * @Description
 * @createTime 2023年04月20日 22:05:00
 */
@Suite
@SelectClasses({
        Calculator01Test.class,
        Calculator02Test.class
})
@SuiteDisplayName("01、02测试类")
public class SuiteClassTest {
}
