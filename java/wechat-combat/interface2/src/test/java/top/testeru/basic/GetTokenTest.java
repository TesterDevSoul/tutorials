package top.testeru.basic;

import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.hamcrest.core.StringContains;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;

import static io.restassured.RestAssured.given;
import static java.lang.invoke.MethodHandles.lookup;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.slf4j.LoggerFactory.getLogger;

/**
 * @author www.testeru.top
 * @version 1.0.0
 * @Project wechat
 * @Description 获取accessToken
 * @createTime 2022年12月15日 10:43:21
 */
/** 获取企业微信token
 * https://developer.work.weixin.qq.com/document/10013#%E7%AC%AC%E4%B8%89%E6%AD%A5%EF%BC%9A%E8%8E%B7%E5%8F%96access-token
 * 请求方式：GET
 * URL：https://qyapi.weixin.qq.com/cgi-bin/gettoken?corpid=ID&corpsecret=SECRET
 * 参数获取：
 * corpid  企业ID  wwecb0ab2da18a7c3b
 * corpsecret   应用的凭证密钥     6Yg1wwQ_gZIUTXezXfVtnYH3Z_zgg_sZF6XvsHdH2oQ
 *
 * 拼接后的链接
 * https://qyapi.weixin.qq.com/cgi-bin/gettoken?corpid=wwecb0ab2da18a7c3b&corpsecret=6Yg1wwQ_gZIUTXezXfVtnYH3Z_zgg_sZF6XvsHdH2oQ
 */
public class GetTokenTest {
    public static final Logger logger = getLogger(lookup().lookupClass());


    @Test
    @DisplayName("URL拼接参数获取")
    @Order(1)
    public void baseGETToken(){
        //方式一：get请求参数 queryParam 可以直接拼接
        given()
            .log().all()
        .when()
            .get("https://qyapi.weixin.qq.com/cgi-bin/gettoken?corpid=wwecb0ab2da18a7c3b&corpsecret=6Yg1wwQ_gZIUTXezXfVtnbSfD01619-jo1LpopQT7w0")
        .then()
            .log().all();

    }
    @Test
    @DisplayName("queryParam参数获取")
    @Order(2)
    public void getTokenWithQueryParam(){
        //方式二：get请求参数 queryParam 传参 推荐使用
        given()
            .log().all()
            .queryParam("corpid","wwecb0ab2da18a7c3b")
            .queryParam("corpsecret","6Yg1wwQ_gZIUTXezXfVtnbSfD01619-jo1LpopQT7w0")
        .when()
            .get("https://qyapi.weixin.qq.com/cgi-bin/gettoken")
        .then()
            .log().all();
    }
    //根据path路径获取access_token
    @Test
    @DisplayName("queryParam参数获取accessToken")
    @Order(3)
    public void getTokenWithPath(){
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
        String accessToken = 0==errcode ? response.path("access_token").toString() : errmsg;
        logger.info("access_token:{}",accessToken);
    }
    @Test
    @DisplayName("queryParam参数没有corpid获取accessToken")
    @Order(4)
    public void getTokenNocorpid(){
        //corpid未传
        Response response = given()
                .log().all()
                .queryParam("corpsecret", "6Yg1wwQ_gZIUTXezXfVtnbSfD01619-jo1LpopQT7w0")
                .when()
                .get("https://qyapi.weixin.qq.com/cgi-bin/gettoken")
                .then()
                .log().all()
                .statusCode(200)
                .extract().response();
        Integer errcode = response.path("errcode");
        String errmsg = response.path("errmsg");
        assertAll(
                () -> assertEquals(41002,errcode),
                () -> assertEquals("corpid missing",errmsg)
        );
        logger.info("errmsg:{}",errmsg);
    }

    @Test
    @DisplayName("queryParam参数没有corpsecret获取accessToken")
    @Order(8)
    public void getTokenNocorpsecret(){
        //方式二：get请求参数 queryParam 传参 推荐使用
        Response response = given()
                .log().all()
                .queryParam("corpid", "wwecb0ab2da18a7c3b")
                .when()
                .get("https://qyapi.weixin.qq.com/cgi-bin/gettoken")
                .then()
                .log().all()
                .statusCode(200)
                .extract().response();
        Integer errcode = response.path("errcode");
        String errmsg = response.path("errmsg");
        assertAll(
                () -> assertEquals(41004,errcode),
                () -> assertEquals("corpsecret missing",errmsg)
        );
        logger.info("errmsg:{}",errmsg);
    }

    @Test
    @DisplayName("Corpid错误获取accessToken")
    @Order(9)
    public void getTokenCorpidError(){
        Response response = given()
                .log().all()
                .queryParam("corpid", "1wwecb0ab2da18a7c3b1")
                .queryParam("corpsecret", "6Yg1wwQ_gZIUTXezXfVtnbSfD01619-jo1LpopQT7w0")
                .when()
                .get("https://qyapi.weixin.qq.com/cgi-bin/gettoken")
                .then()
                .log().all()
                .statusCode(200)
                .extract().response();
        Integer errcode = response.path("errcode");
        String errmsg = response.path("errmsg");
        assertAll(
                () -> assertEquals(40013,errcode),
                () -> assertThat(errmsg, is(containsString("invalid corpid")))
        );
        logger.info("errmsg:{}",errmsg);
    }
    @Test
    @DisplayName("Corpsecret错误获取accessToken")
    @Order(10)
    public void getTokenCorpsecretError(){
        //方式二：get请求参数 queryParam 传参 推荐使用
        Response response = given()
                .log().all()
                .queryParam("corpid", "wwecb0ab2da18a7c3b")
                .queryParam("corpsecret", "16Yg1wwQ_gZIUTXezXfVtnbSfD01619-jo1LpopQT7w0")
                .when()
                .get("https://qyapi.weixin.qq.com/cgi-bin/gettoken")
                .then()
                .log().all()
                .statusCode(200)
                .extract().response();
        Integer errcode = response.path("errcode");
        String errmsg = response.path("errmsg");
        assertAll(
                () -> assertEquals(40001,errcode),
                () -> assertThat(errmsg, is(containsString("invalid credential")))
        );
        logger.info("errmsg:{}",errmsg);
    }


}
