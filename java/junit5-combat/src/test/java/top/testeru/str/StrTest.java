package top.testeru.str;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import top.testeru.BaseTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author testeru.top
 * @version 1.0.0
 * @Prpject junit5-combat
 * @Description 字符串拼接测试用例
 * @createTime 2023年04月18日 11:22:00
 */
@DisplayName("字符串测试用例")
public class StrTest extends BaseTest {
    @Test
    @Order(26)
    @DisplayName("2个英文字符串拼接")
    public void strWithTwoEnTest () {
        //2. 开始字符串拼接
        strResult = calculator.concatStr("Hello", "JUnit5");
        //4. 断言 -- assertEquals()
        assertEquals("Hello JUnit5",strResult,"字符串拼接错误");
    }
    @Test
    @Order(36)
    @DisplayName("2个中文字符串拼接")
    public void strWithThreeZhTest () {
        //2. 开始字符串拼接
        strResult = calculator.concatStr("这里", "是", "北京");
        //4. 断言 -- assertEquals()
        assertEquals("这里 是 北京",strResult,"字符串拼接错误");
    }
}
