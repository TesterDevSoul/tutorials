package top.testeru.basic;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;

import static java.lang.invoke.MethodHandles.lookup;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.slf4j.LoggerFactory.getLogger;

/**
 * @author testeru.top
 * @version 1.0.0
 * @Prpject junit5-example
 * @Description
 * @createTime 2023年04月20日 18:59:00
 */
public class AnEachTest {
    static final Logger logger = getLogger(lookup().lookupClass());

    @AfterEach
    @BeforeEach
    public void each(){
        logger.info("@Each ：Every @Test Before And After run...");
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
}