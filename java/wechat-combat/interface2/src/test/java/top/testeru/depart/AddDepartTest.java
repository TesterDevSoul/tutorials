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
import org.junit.jupiter.api.*;
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
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.slf4j.LoggerFactory.getLogger;

/**
 * @author www.testeru.top
 * @version 1.0.0
 * @Project wechat
 * @Description 创建部门
 * @createTime 2022年12月15日 15:29:57
 */
public class AddDepartTest extends BaseTest {
   /* public static final Logger logger = getLogger(lookup().lookupClass());
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
    @Disabled
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
    @Test
    @DisplayName("直接添加部门")
    @Order(12)
    @Disabled
    public void add(){
        String depart = "{\n" +
                "   \"name\": \"质量控制部1\",\n" +
                "   \"name_en\": \"ZLKZ1\",\n" +
                "   \"parentid\": 1,\n" +
                "   \"order\": 7,\n" +
                "   \"id\": 10\n" +
                "}\n" +
                "\n";


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
                () -> assertEquals(0,errcode),
                () -> assertEquals("created",errmsg)
        );
        String id = 0==errcode ? response.path("id").toString() : errmsg;
        logger.info("创建的部门id为:{}",id);
    }

    @Test
    @DisplayName("JsonFactory添加部门")
    @Order(14)
    public void addWithJsonFactory() throws IOException {
        //01.json文件直接解析
        ObjectMapper objectMapper = new ObjectMapper(new JsonFactory());
        TypeReference<HashMap<String,Object>> typeReference = new TypeReference<HashMap<String,Object>>(){};
        HashMap<String, Object> beforeDepart = objectMapper.readValue(new File("src/test/resources/depart/add.json"), typeReference);
        logger.info("解析出的depart：{}",beforeDepart);
        HashMap<String, Object> depart = new HashMap<>();

        //自定义替换规则：$替换及json文件封装

        //原始解析数据：{name=${departName}, parentid=1, order=${orderId}}
        //替换后数据：{name=333, parentid=1, order=8888}

        beforeDepart.forEach(
                (s, o) -> {
                    String value = o.toString();
                    //1) value 以${开头     以}结尾
                    if (value.startsWith("${") && value.endsWith("}")) {
                        //remove ${
                        String v1 = StringUtils.stripStart(value, "${");//departName}   orderId}
                        //remove }
                        String v2 = StringUtils.stripEnd(v1, "}");//departName     orderId

                        if ("departName".equals(v2)) {
                            o = FakerUtil.getDepartName();
                        }

                        if ("orderNum".equals(v2)) {
                            o = FakerUtil.getNum(10, 30);
                        }
                    }
                    //组成新map传递给请求body
                    depart.put(s, o);
                }
        );
        logger.info("自定义解析规则的部门参数为:{}", depart);
        //{name=质量部, parentid=1, order=8888}
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
                () -> assertEquals(0,errcode),
                () -> assertEquals("created",errmsg)
        );
        String id = 0==errcode ? response.path("id").toString() : errmsg;
        logger.info("创建的部门id为:{}",id);
    }

    //Json文件解析为JsonPath进行替换
    @Test
    @DisplayName("JsonPath模版添加部门")
    @Order(66)
    public void addWithJsonPath() {
        DocumentContext documentContext = null;
        try {
            documentContext = JsonPath.parse(new File("src/test/resources/depart/add.json"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        documentContext.set("$.name", FakerUtil.getDepartName());
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
                () -> assertEquals(0, errcode),
                () -> assertEquals("created", errmsg)
        );
        String id = 0 == errcode ? response.path("id").toString() : errmsg;
        logger.info("创建的部门id为:{}", id);
    }

    //Json文件解析根据Mustache工厂模式解析
    @Test
    @DisplayName("Mustache模版添加部门")
    @Order(16)
    public void addWithMustache() throws IOException {
        //HashMap 存入修改字段
        HashMap<String, Object> map = new HashMap<>(){{
            put("departName", FakerUtil.getDepartName());
            put("orderNum",FakerUtil.getNum(10,30));
        }};
        DefaultMustacheFactory factory = new DefaultMustacheFactory();
        Mustache mustache = factory.compile("depart/add.mustache");
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        OutputStreamWriter writer = new OutputStreamWriter(stream);
        mustache.execute(writer,map);
        writer.flush();
        writer.close();
        String depart = new String(stream.toByteArray());

        logger.info("Mustache解析规则的部门参数为:{}", depart);


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
                () -> assertEquals(0, errcode),
                () -> assertEquals("created", errmsg)
        );
        String id = 0 == errcode ? response.path("id").toString() : errmsg;
        logger.info("创建的部门id为:{}", id);
    }
    @ParameterizedTest(name = "参数化添加的部门ID：{0}")
    //静态方法源
    @MethodSource
    @Order(76)
    public void addParams(String depart) {
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
                () -> assertEquals(0, errcode),
                () -> assertEquals("created", errmsg)
        );
        String id = 0 == errcode ? response.path("id").toString() : errmsg;
        logger.info("创建的部门id为:{}", id);
    }


    static List<String> addParams(){
        DocumentContext documentContext = null;
        try {
            documentContext = JsonPath.parse(new File("src/test/resources/depart/add.json"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<String> departList = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            documentContext.set("$.name", FakerUtil.getDepartName()+FakerUtil.getNum(50, 90));
            documentContext.set("$.order", FakerUtil.getNum(50, 90));
            //3.string类型打印
            String depart = documentContext.jsonString();
            departList.add(depart);
        }
        logger.info("部门参数为:{}", departList);
        return departList;
    }


}
