package top.testeru.dynamic;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DynamicContainer;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;

import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;

/**
 * @author testeru.top
 * @version 1.0.0
 * @Prpject junit5-example
 * @Description 动态测试用例的创建
 * @createTime 2023年04月18日 17:16:00
 */
public class DynamicSampleTest {


    //方法返回实例为DynamicTest
    @TestFactory
    @DisplayName("返回类型为DynamicTest实例")
    DynamicTest dynamicTest() {
        return DynamicTest.dynamicTest("单个动态测试",
                () -> assertEquals(5, 3+2));
    }

    // 创建动态测试用例
    @TestFactory
    @DisplayName("返回类型为Collection集合")
    Collection<DynamicTest> dynamicTestsFromCollection() {
        return Arrays.asList(
                DynamicTest.dynamicTest("1stDynamic", () -> assertEquals(5, 3+2)),
                DynamicTest.dynamicTest("2ndDynamic", () -> assertEquals(5, 25/5))
        );
    }
    //方法返回实例为DynamicContainer
    @TestFactory
    @DisplayName("返回类型为DynamicContainer容器")
    DynamicContainer dynamicTestsFromDynamicContainer() {
        return DynamicContainer.dynamicContainer("多个动态测试",

                Stream.of(
                        DynamicTest.dynamicTest("1、动态容器",
                                () -> assertEquals(5, 3+2)),
                        DynamicTest.dynamicTest("2、动态容器",
                                () -> assertEquals(5,25/5))
                ));
    }

    @TestFactory
    @DisplayName("返回类型为Stream流")
    Stream<DynamicTest> dynamicTestsFromStream() {
        return Stream.of(6, 8, 10)
                .map(arg -> DynamicTest.dynamicTest("Stream Dy test " + arg, () -> {
                    System.out.println(arg);//6, 8, 10
                    assertThat(arg, greaterThan(2));
                }));
    }

    @TestFactory
    @DisplayName("返回类型为Iterator迭代器")
    Iterator<DynamicTest> dynamicTestsFromIterator(){
        Collection<DynamicTest> dynamicTestIterator = new ArrayList<>();
        //创建了一个包含10个整数的迭代器，这10个整数从2开始，每个整数都比前一个整数大1
        //iterate()方法可以用于生成一个无限流，它从一个初始值开始，然后重复应用一个函数来生成后续的值。
        //limit()方法可以用于截断无限流，使其变为有限流。这里使用limit(10)方法将无限流截断为包含10个元素的有限流。
        PrimitiveIterator.OfInt iterator = IntStream.iterate(2, n -> n + 1).limit(10).iterator();

        while (iterator.hasNext()){
            Integer next = iterator.next();
            String displayName = "加法测试 " + next;
            dynamicTestIterator.add(
                    DynamicTest.dynamicTest(displayName, () -> {
                                                        assertThat(next,greaterThan( 3));
                                                        })
            );
        }
        return dynamicTestIterator.iterator();
    }

    @TestFactory
    @DisplayName("返回类型Iterable")
    Iterable<DynamicTest> dynamicTestsFromIterable(){
        Collection<DynamicTest> dynamicTestIterator = new ArrayList<>();
        PrimitiveIterator.OfInt iterator = IntStream.iterate(2, n -> n + 1).limit(10).iterator();

        while (iterator.hasNext()){
            Integer next = iterator.next();
            String displayName = "Iterable加法测试 " + next;

            dynamicTestIterator.add(
                    DynamicTest.dynamicTest(displayName,  () -> {
                        System.out.println("n:" + next);
                        assertThat(next,greaterThan( 3));
                    }));
        }
        return dynamicTestIterator;
    }

    @TestFactory
    @DisplayName("自定义动态测试排序")
    Collection<DynamicTest> dynamicTestsOrder() {

        List<DynamicTest> dynamicTests = Arrays.asList(
                DynamicTest.dynamicTest("1st dynamic test", () -> {
                    System.out.println("=> 1st dynamic test");
                }),
                DynamicTest.dynamicTest("2nd dynamic test", () -> {
                    System.out.println("=> 2nd dynamic test");
                }),
                DynamicTest.dynamicTest("3rd dynamic test", () -> {
                    System.out.println("=> 3rd dynamic test");
                })
        );

        sortDynamicTests(dynamicTests);
        return dynamicTests;
    }

    static void sortDynamicTests(List<DynamicTest> dynamicTests) {
        //根据displayName对比来定义执行顺序
        dynamicTests.sort((DynamicTest d1, DynamicTest d2) ->
                d2.getDisplayName().compareTo(d1.getDisplayName()));
    }

    // 示例：使用Comparator接口控制动态测试节点的执行顺序
    @TestFactory
    @DisplayName("降序执行")
    public Stream<DynamicTest> dynamicTestOrder() {
        Integer[] numbers = {3, 1, 4, 1, 5, 9, 2, 6, 5, 3, 5};
        Arrays.sort(numbers, Comparator.reverseOrder()); // 逆序排序

        return Arrays.stream(numbers)
                .map(number -> DynamicTest.dynamicTest("Test " + number, () -> {
                    // 测试逻辑
                    assertTrue(number > 0 && number < 10);
                }));
    }

}
