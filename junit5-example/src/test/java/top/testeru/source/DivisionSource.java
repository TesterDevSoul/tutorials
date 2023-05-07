package top.testeru.source;

import org.junit.jupiter.params.provider.Arguments;

import java.util.stream.Stream;

/**
 * @author testeru.top
 * @version 1.0.0
 * @Prpject junit5-example
 * @Description
 * @createTime 2023年04月20日 21:41:00
 */
public class DivisionSource {
    public static Stream<Arguments> diviParams(){
        return Stream.of(
                Arguments.arguments(1, 2, 0.5),
                Arguments.arguments(0, 5, 0),
                Arguments.arguments(8, 0, 0));
    }
}
