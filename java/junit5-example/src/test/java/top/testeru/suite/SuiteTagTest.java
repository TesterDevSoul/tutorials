package top.testeru.suite;

import org.junit.platform.suite.api.IncludeTags;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;
import org.junit.platform.suite.api.SuiteDisplayName;

/**
 * @author testeru.top
 * @version 1.0.0
 * @Prpject junit5-example
 * @Description
 * @createTime 2023年04月20日 22:06:00
 */
@Suite
@SelectPackages("top.testeru.cal")
@IncludeTags({"true"})
@SuiteDisplayName("true用例")
public class SuiteTagTest {
}
