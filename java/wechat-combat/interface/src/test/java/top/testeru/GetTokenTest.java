package top.testeru;

//import io.restassured.RestAssured;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static java.lang.invoke.MethodHandles.lookup;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.slf4j.LoggerFactory.getLogger;

//获取token测试用例
public class GetTokenTest {
    static final Logger logger = getLogger(lookup().lookupClass());

    /**
     * 获取企业微信的token
     * 请求路径URL：
     * https://qyapi.weixin.qq.com/cgi-bin/gettoken?corpid=ID&corpsecret=SECRET
     * corpid wwecb0ab2da18a7c3b
     * corpsecret 6Yg1wwQ_gZIUTXezXfVtnYAPuE27iajhduOtI369Gqg
     * 请求方式：
     * get
     */
    @Test
    @DisplayName("URL拼接参数")
    public void getToken(){
        //请求参数的准备
        Response response = given()
                                .log().all()
                            //发送具体请求
                            .when()
                                .get("https://qyapi.weixin.qq.com/cgi-bin/gettoken?corpid=wwecb0ab2da18a7c3b&corpsecret=6Yg1wwQ_gZIUTXezXfVtnYAPuE27iajhduOtI369Gqg")
                            //获取响应结果
                            .then()
                                .log().all()
                                //校验HTTPcode码200;
                                .statusCode(200)
                                .extract().response();
        //响应code msg token
        /**
         * {
         *    "errcode": 0,
         *    "errmsg": "ok",
         *    "access_token": "accesstoken000001",
         *    "expires_in": 7200
         * }
         */
        Integer errcode = response.path("errcode");//gpath
        String errmsg = response.path("errcode").toString();
        //三目运算
        String accessToken = 0==errcode ? response.path("access_token").toString() : errmsg ;
        logger.info("access_token:{}", accessToken);

    }

    String URL  = "https://qyapi.weixin.qq.com/cgi-bin/gettoken";
    @Test
    @DisplayName("param参数")
    public void getTokenWithParam(){
        //请求参数的准备
        Response response = given()
                                .log().all()
                                .queryParam("corpid","wwecb0ab2da18a7c3b")
                                .queryParam("corpsecret","6Yg1wwQ_gZIUTXezXfVtnYAPuE27iajhduOtI369Gqg")

                                //发送具体请求
                            .when()
                                .request("get",URL)

                                //获取响应结果
                            .then()
                                .log().all()
                                //校验HTTPcode码200;
                                .statusCode(200)
                                .extract().response();
        //响应code msg token
        /**
         * {
         *    "errcode": 0,
         *    "errmsg": "ok",
         *    "access_token": "accesstoken000001",
         *    "expires_in": 7200
         * }
         */
        Integer errcode = response.path("errcode");//gpath
        String errmsg = response.path("errmsg").toString();
        //三目运算
        String accessToken = 0==errcode ? response.path("access_token").toString() : errmsg ;
        logger.info("access_token:{}", accessToken);
        assertAll(
                () -> assertEquals(0,errcode),
                () -> assertEquals("ok",errmsg)
        );

    }


    @Test
    @DisplayName("没有Corpid参数的请求")
    public void getTokenNoCorpidWithParam(){
        //请求参数的准备
        Response response = given()
                .log().all()
//                .queryParam("corpid","wwecb0ab2da18a7c3b")
                .queryParam("corpsecret","6Yg1wwQ_gZIUTXezXfVtnYAPuE27iajhduOtI369Gqg")

                //发送具体请求
                .when()
                .request("get",URL)

                //获取响应结果
                .then()
                .log().all()
                //校验HTTPcode码200;
                .statusCode(200)
                .extract().response();
        //响应code msg token
        /**
         {
         "errcode": 41002,
         "errmsg": "corpid missing"
         }
         */
        Integer errcode = response.path("errcode");//gpath
        String errmsg = response.path("errmsg").toString();
        //三目运算
        String accessToken = 0==errcode ? response.path("access_token").toString() : errmsg ;
        //corpid missing
        logger.info("access_token:{}", accessToken);
        assertAll(
                () -> assertEquals(41002,errcode),
                () -> assertEquals("corpid missing",errmsg)
        );

    }

    @Test
    @DisplayName("没有Corpsecret参数的请求")
    public void getTokenNoCorpsecretWithParam(){
        //请求参数的准备
        Response response = given()
                .log().all()
                .queryParam("corpid","wwecb0ab2da18a7c3b")

                //发送具体请求
                .when()
                .request("get",URL)

                //获取响应结果
                .then()
                .log().all()
                //校验HTTPcode码200;
                .statusCode(200)
                .extract().response();
        //响应code msg token
        /**
         {
         "errcode": 41004,
         "errmsg": "corpsecret missing"
         }
         */
        Integer errcode = response.path("errcode");//gpath
        String errmsg = response.path("errmsg").toString();
        //三目运算
        String accessToken = 0==errcode ? response.path("access_token").toString() : errmsg ;
        //corpid missing
        logger.info("access_token:{}", accessToken);
        assertAll(
                () -> assertEquals(41004,errcode),
                () -> assertEquals("corpsecret missing",errmsg)
        );

    }




    @Test
    @DisplayName("Corpid参数错误的请求")
    public void getTokenCorpidErrorWithParam(){
        //请求参数的准备
        Response response = given()
                .log().all()
                .queryParam("corpid","1wwecb0ab2da18a7c3b")
                .queryParam("corpsecret","6Yg1wwQ_gZIUTXezXfVtnYAPuE27iajhduOtI369Gqg")
                //发送具体请求
                .when()
                .get(URL)

                //获取响应结果
                .then()
                .log().all()
                //校验HTTPcode码200;
                .statusCode(200)
                .extract().response();

        //响应code msg token
        /**
         {
         "errcode": 40013,
         "errmsg": "invalid corpid"
         }
         */
        Integer errcode = response
                .path("errcode");//gpath
        String errmsg = response.path("errmsg").toString();
        //三目运算
        String accessToken = 0==errcode ? response.path("access_token").toString() : errmsg ;
        //corpid missing
        logger.info("access_token:{}", accessToken);
        assertAll(
                () -> assertEquals(40013, errcode),
                () -> assertThat(errmsg, containsString("invalid corpid"))
        );

    }


    //json文件保存

    @Test
    @DisplayName("token写入文件")
    public void getTokenWithFile() throws IOException {
        //获取当前时间戳
        long now = System.currentTimeMillis();
        //请求参数的准备
        Response response = given()
                .log().all()
                .queryParam("corpid","wwecb0ab2da18a7c3b")
                .queryParam("corpsecret","6Yg1wwQ_gZIUTXezXfVtnYAPuE27iajhduOtI369Gqg")

                //发送具体请求
                .when()
                .request("get",URL)

                //获取响应结果
                .then()
                .log().all()
                //校验HTTPcode码200;
                .statusCode(200)
                .extract().response();
        //响应code msg token
        /**
         * {
         *    "errcode": 0,
         *    "errmsg": "ok",
         *    "access_token": "accesstoken000001",
         *    "expires_in": 7200
         * }
         */
        Integer errcode = response.path("errcode");//gpath
        logger.info("errcode:{}", errcode);

        String errmsg = response.path("errmsg").toString();
        logger.info("errmsg:{}", errmsg);
        //三目运算
        String accessToken = 0==errcode ? response.path("access_token").toString() : errmsg ;
        logger.info("access_token:{}", accessToken);
        //多断言
        assertAll(
                () -> assertEquals(0,errcode),
                () -> assertEquals("ok",errmsg)
        );

        HashMap<String,Object> token = new HashMap<>();
        token.put("access_token", accessToken);
        //失效时间保存
        long time = now + 7200000L;
        token.put("time", time);
        //yaml文件保存
        ObjectMapper mapper = new ObjectMapper(new JsonFactory());
        //new File(路径)     -------->     Paths.get(路径).toFile()
//        mapper.writeValue(new File("cookie.yaml"), cookies);
        mapper.writeValue(Paths.get("token.json").toFile(), token);

    }



}
