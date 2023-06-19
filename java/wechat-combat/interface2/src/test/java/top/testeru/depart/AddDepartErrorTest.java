package top.testeru.depart;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.Mustache;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import io.restassured.response.Response;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.slf4j.Logger;
import top.testeru.FakerUtil;
import top.testeru.basic.BaseTest;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static io.restassured.RestAssured.given;
import static java.lang.invoke.MethodHandles.lookup;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.slf4j.LoggerFactory.getLogger;

/**
 * @author www.testeru.top
 * @version 1.0.0
 * @Project wechat
 * @Description 创建部门错误的测试用例
 * @createTime 2022年12月15日 15:29:57
 */
public class AddDepartErrorTest extends BaseTest {
    /*public static final Logger logger = getLogger(lookup().lookupClass());
    static String accessToken;
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
    }*/
    /**
     *  请求方式：POST
     *  请求URL：https://qyapi.weixin.qq.com/cgi-bin/department/create
     *  ?access_token=ACCESS_TOKEN
     *  请求参数 - queryParam：
     *      access_token：@BeoforeAll获取到的access_token值
     *  请求参数 - body体：
     *  {
     *    "name": "广州研发中心",
     *    "name_en": "RDGZ",
     *    "parentid": 1,
     *    "order": 1,
     *    "id": 2
     * }
     *
     *   60009     部门名称含有非法字符----未声明接口对应编码格式导致
     */
    @Test
    @DisplayName("直接添加部门含有非法字符")
    @Order(11)
    public void addWithError(){
        String depart = "{\n" +
                "   \"name\": \"质量控制部\",\n" +
                "   \"name_en\": \"ZLKZ\",\n" +
                "   \"parentid\": 1,\n" +
                "   \"order\": 6,\n" +
                "   \"id\": 9\n" +
                "}\n" +
                "\n";


        Response response = given()
                                .log().all()
                                .queryParam("access_token", accessToken)
                                .body(depart)
                            .when()
                                .post("https://qyapi.weixin.qq.com/cgi-bin/department/create")
                            .then()
                                .log().all()
                                .statusCode(200)
                                .extract().response();
    }




    /**
     * name 参数长度为0位
     * {
     *     "errcode": 40058,
     *     "errmsg": "name exceed min utf8 words 1. invalid Request Parameter, hint: [1671098526431493138190819], from ip: 120.245.114.24, more info at https://open.work.weixin.qq.com/devtool/query?e=40058"
     * }
     */
    @Test
    @DisplayName("name长度为0")
    @Order(77)
    public void addWithNameLength0() {
        DocumentContext documentContext = null;
        try {
            documentContext = JsonPath.parse(new File("src/test/resources/depart/add.json"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        documentContext.set("$.name", "");
        documentContext.set("$.order", FakerUtil.getNum(10, 30));
        //3.string类型打印
        String depart = documentContext.jsonString();
        logger.info("JsonPath解析规则的部门参数为:{}", depart);


        //添加部门
        Response response = given()
                .log().all()
                .contentType("application/json; charset=utf-8")
                .queryParam("access_token", accessToken)
                .body(depart)
                .when()
                .post("https://qyapi.weixin.qq.com/cgi-bin/department/create")
                .then()
                .log().all()
                .statusCode(200)
                .extract().response();

        Integer errcode = response.path("errcode");
        String errmsg = response.path("errmsg");
        assertAll(
                () -> assertEquals(40058, errcode),
                () -> assertThat(errmsg,is(containsString("name exceed min utf8 words 1")))
        );
        String id = 0 == errcode ? response.path("id").toString() : errmsg;
        logger.info("创建的部门id为:{}", id);
    }

    //name 参数长度为32位

    //name 参数长度为33位
    @Test
    @DisplayName("name长度为33")
    @Order(78)
    public void addWithNameLength33() {
        DocumentContext documentContext = null;
        try {
            documentContext = JsonPath.parse(new File("src/test/resources/depart/add.json"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        documentContext.set("$.name", FakerUtil.getChinese(32));
        documentContext.set("$.order", FakerUtil.getNum(10, 30));
        //3.string类型打印
        String depart = documentContext.jsonString();
        logger.info("JsonPath解析规则的部门参数为:{}", depart);


        //添加部门
        Response response = given()
                                .log().all()
                                .contentType("application/json; charset=utf-8")
                                .queryParam("access_token", accessToken)
                                .body(depart)
                            .when()
                                .post("https://qyapi.weixin.qq.com/cgi-bin/department/create")
                            .then()
                                .log().all()
                                .statusCode(200)
                                .extract().response();

        Integer errcode = response.path("errcode");
        String errmsg = response.path("errmsg");
        assertAll(
                () -> assertEquals(60001, errcode),
                () -> assertThat(errmsg,is(containsString("department invalid length")))
        );
        String id = 0 == errcode ? response.path("id").toString() : errmsg;
        logger.info("创建的部门id为:{}", id);
    }

    //nameEn 参数长度为0位
    @Test
    @DisplayName("nameEn长度为0")
    @Order(79)
    public void addWithNameEnLength0() {
        DocumentContext documentContext = null;
        try {
            documentContext = JsonPath.parse(new File("src/test/resources/depart/add1.json"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        documentContext.set("$.name", FakerUtil.getChinese(32));
        documentContext.set("$.name_en", "");
        documentContext.set("$.order", FakerUtil.getNum(10, 30));
        //3.string类型打印
        String depart = documentContext.jsonString();
        logger.info("JsonPath解析规则的部门参数为:{}", depart);


        //添加部门
        Response response = given()
                .log().all()
                .contentType("application/json; charset=utf-8")
                .queryParam("access_token", accessToken)
                .body(depart)
                .when()
                .post("https://qyapi.weixin.qq.com/cgi-bin/department/create")
                .then()
                .log().all()
                .statusCode(200)
                .extract().response();

        Integer errcode = response.path("errcode");
        String errmsg = response.path("errmsg");
        assertAll(
                () -> assertEquals(60001, errcode),
                () -> assertThat(errmsg,is(containsString("department invalid length")))
        );
        String id = 0 == errcode ? response.path("id").toString() : errmsg;
        logger.info("创建的部门id为:{}", id);
    }
    //nameEn 参数长度为33位
    @Test
    @DisplayName("nameEn长度为33")
    @Order(80)
    public void addWithNameEnLength33() {
        DocumentContext documentContext = null;
        try {
            documentContext = JsonPath.parse(new File("src/test/resources/depart/add1.json"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        documentContext.set("$.name", FakerUtil.getChinese(32));
        documentContext.set("$.name_en", FakerUtil.getChinese(33));
        documentContext.set("$.order", FakerUtil.getNum(10, 30));
        //3.string类型打印
        String depart = documentContext.jsonString();
        logger.info("JsonPath解析规则的部门参数为:{}", depart);


        //添加部门
        Response response = given()
                .log().all()
                .contentType("application/json; charset=utf-8")
                .queryParam("access_token", accessToken)
                .body(depart)
                .when()
                .post("https://qyapi.weixin.qq.com/cgi-bin/department/create")
                .then()
                .log().all()
                .statusCode(200)
                .extract().response();

        Integer errcode = response.path("errcode");
        String errmsg = response.path("errmsg");
        assertAll(
                () -> assertEquals(40058, errcode),
                () -> assertThat(errmsg,is(containsString("name_en exceed max length 32.")))
        );
        String id = 0 == errcode ? response.path("id").toString() : errmsg;
        logger.info("创建的部门id为:{}", id);
    }
}
