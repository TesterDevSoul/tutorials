package top.testeru.suite;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;
import org.junit.platform.suite.api.SuiteDisplayName;
import top.testeru.mix.MixMputingTest;
import top.testeru.num.SumTest;

/**
 * @author testeru.top
 * @version 1.0.0
 * @Prpject junit5-combat
 * @Description 第一个测试用例顺序
 * 运行加法 -- 混合计算
 * @createTime 2023年04月18日 11:32:00
 */
@Suite
@SelectClasses({
        SumTest.class,
        MixMputingTest.class
})
@SuiteDisplayName("加法--》混合")
public class OneSuite {
}
