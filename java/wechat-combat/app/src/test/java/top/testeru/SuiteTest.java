package top.testeru;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;
import top.testeru.appium.AppiumServerEndTest;
import top.testeru.appium.AppiumServerStartTest;
import top.testeru.appium.PngTest;
import top.testeru.member.AddMemberTest;
import top.testeru.member.MemberDakaTest;
import top.testeru.util.AppiumServer;

@Suite
@SelectClasses({
        PngTest.class,
        AppiumServerStartTest.class,
        AddMemberTest.class,
        MemberDakaTest.class
        , AppiumServerEndTest.class
})
public class SuiteTest {
}
