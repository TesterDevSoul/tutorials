package top.testeru.suite;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.platform.suite.api.IncludeTags;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;

/**
 * @author testeru.top
 * @version 1.0.0
 * @Prpject junit5-combat
 * @Description 第二个测试套件
 * 选中某一个具体方法运行
 * 使用tag标签 在要执行的方法上编写tag
 * @createTime 2023年04月18日 11:34:00
 */
@Suite
@SelectPackages({
        "top.testeru.num"
        ,"top.testeru.mix"})
@IncludeTags({"2个数"})
public class TwoSuite {
}
