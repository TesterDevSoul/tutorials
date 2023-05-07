package top.testeru.suite;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

/**
 * @author testeru.top
 * @version 1.0.0
 * @Prpject junit5-example
 * @Description
 * @createTime 2023年04月20日 22:07:00
 */
@Suite
@SelectClasses({
        SuiteClassTest.class,
        SuiteTagTest.class
})
public class SuiteAll {
}
