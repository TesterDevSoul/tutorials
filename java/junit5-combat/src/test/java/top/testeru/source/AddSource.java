package top.testeru.source;

import org.junit.jupiter.params.provider.Arguments;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * @author testeru.top
 * @version 1.0.0
 * @Prpject junit5-combat
 * @Description 加法数据源
 * @createTime 2023年04月17日 22:13:00
 */
public class AddSource {

    public static Stream<Arguments> sumNum(){
        return Stream.of(
                Arguments.arguments(1,3,4),
                Arguments.arguments(4,5,9),
                Arguments.arguments(8,3,11)
        );
    }


    public static Stream<Arguments> sumIntWithMore(){
        return Stream.of(
                Arguments.arguments(Arrays.asList(2, 2), 4),
                Arguments.arguments(Arrays.asList(4, 5), 9),
                Arguments.arguments(Arrays.asList(4, 3, 7), 14),
                Arguments.arguments(Arrays.asList(2, 6, 8, 9), 25)
                );
    }
}
