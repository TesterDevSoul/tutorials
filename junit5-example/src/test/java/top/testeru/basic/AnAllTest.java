package top.testeru.basic;

import org.junit.jupiter.api.*;
import org.slf4j.Logger;

import static java.lang.invoke.MethodHandles.lookup;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.slf4j.LoggerFactory.getLogger;

/**
 * @author testeru.top
 * @version 1.0.0
 * @Prpject junit5-example
 * @Description
 * @createTime 2023年04月20日 19:22:00
 */
public class AnAllTest {
    static final Logger logger = getLogger(lookup().lookupClass());
    @AfterAll
    @BeforeAll
    public static void all(){
        logger.info("@All ：All @Test Before And After run Once...");
    }
    @AfterAll
    public static void afterAll(){
        logger.info("All Annotation After run...");
    }
    @AfterEach
    public void afterEach(){
        logger.info("Every @Test After run...");
    }
    @Test
    public void one(){
        int result = 8 / 4;
        logger.info("Result：{}",result);
        assertEquals(2,result);
        logger.info("assertEquals is True");
    }
    @Test
    public void two(){
        int result = 2 + 8;
        logger.info("Sum Result：{}",result);
        assertEquals(10,result);
        logger.info("assertEquals is True");
    }
    @BeforeEach
    public void beforeEach(){
        logger.info("Every @Test Before run...");
    }
    @BeforeAll
    public static void beforeAll(){
        logger.info("All Annotation Before run...");
    }
}