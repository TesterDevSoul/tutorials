package top.testeru.basic;

import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.slf4j.Logger;

import static io.restassured.RestAssured.given;
import static java.lang.invoke.MethodHandles.lookup;
import static org.slf4j.LoggerFactory.getLogger;

/**
 * @author www.testeru.top
 * @version 1.0.0
 * @Project wechat
 * @Description
 * @createTime 2022年12月16日 10:38:59
 */
public class BaseTest {
    public static final Logger logger = getLogger(lookup().lookupClass());
    public static String accessToken;
    @BeforeAll
    public static void getAccessToken(){
        //方式二：get请求参数 queryParam 传参 推荐使用
        Response response = given()
                .log().all()
                .queryParam("corpid", "wwecb0ab2da18a7c3b")
                .queryParam("corpsecret", "6Yg1wwQ_gZIUTXezXfVtnbSfD01619-jo1LpopQT7w0")
                .when()
                .get("https://qyapi.weixin.qq.com/cgi-bin/gettoken")
                .then()
                .log().all()
                .statusCode(200)
                .extract().response();

        Integer errcode = response.path("errcode");
        String errmsg = response.path("errmsg");
        accessToken = 0==errcode ? response.path("access_token").toString() : errmsg;
        logger.info("access_token:{}",accessToken);
    }
}
