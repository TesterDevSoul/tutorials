package top.testeru.mix;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import top.testeru.BaseTest;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertAll;

/**
 * @author testeru.top
 * @version 1.0.0
 * @Prpject junit5-combat
 * @Description 混合计算测试用例
 * @createTime 2023年04月18日 11:23:00
 */
@DisplayName("混合计算测试用例")
public class MixMputingTest extends BaseTest {
    @Test
    @Order(46)
    @DisplayName("加减字符串拼接混合计算")
    @Tag("2个数")
    public void mixTest(){
        List<Executable> assertList = new ArrayList<>();

        //2. 调用加法逻辑：result = 3 + 5。
        result = calculator.sum(3, 5);
        assertList.add(() -> assertThat("加法计算不等于",result, equalTo(8)));
        assertList.add(() -> assertThat("加法计算不大于",result, greaterThan(5)));

        //3. 调用减法逻辑：re = result - 6。
        re = calculator.sub(result, 6);
        assertList.add(() -> assertThat("减法计算不等于",re, equalTo(2)));
        assertList.add(() -> assertThat("减法计算不小于",re, lessThan(result)));
        assertList.add(() -> assertThat("减法计算不接近",(double)re, closeTo(result, 16)));

        //4. 调用字符串拼接逻辑：str = "混合结果 %s",re 。
        strResult = calculator.concatStr("混合结果", String.valueOf(re));
        //字符串是否以...开头
        assertList.add(() -> assertThat("字符串是否以...开头",strResult, startsWith("混合结果")));
        //字符串是否以...结尾
        assertList.add(() -> assertThat("字符串是否以...结尾",strResult, endsWith(String.valueOf(re))));
        //字符串是否包含...
        assertList.add(() -> assertThat("字符串是否包含...",strResult, containsString("结果")));
        //6. 断言。
        assertAll(assertList);
    }
}
