package top.testeru.source;

import org.junit.jupiter.params.provider.Arguments;

import java.util.Arrays;
import java.util.stream.Stream;

/**
 * @author testeru.top
 * @version 1.0.0
 * @Prpject junit5-example
 * @Description 加法测试参数化源
 * @createTime 2023年04月20日 21:34:00
 */
public class SumSource {
    public static Stream<Arguments> sumParams(){
        return Stream.of(
                Arguments.arguments(1, 3, 4),
                Arguments.arguments(0, 0, 0),
                Arguments.arguments(-1, -5, -6));
    }
}
