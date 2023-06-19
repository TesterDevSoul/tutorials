package top.testeru.member;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import top.testeru.FakerUtil;
import top.testeru.basic.BaseTest;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author www.testeru.top
 * @version 1.0.0
 * @Project wechat
 * @Description 创建成员
 * @createTime 2022年12月16日 11:45:32
 */
public class AddMemberTest extends BaseTest {
    @Test
    @DisplayName("添加成员部门为1")
    @Order(16)
    public void addWithJsonPath() {
        DocumentContext documentContext = null;
        try {
            documentContext = JsonPath.parse(new File("src/test/resources/member/add.json"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        String name = FakerUtil.getName();
        String pinYin = FakerUtil.getPinYin(name);
        documentContext.set("$.name", name);
        documentContext.set("$.userid", pinYin);
        documentContext.set("$.alias", pinYin);
        documentContext.set("$.mobile", FakerUtil.getPhone());
        //3.string类型打印
        String member = documentContext.jsonString();
        logger.info("JsonPath解析规则的成员参数为:{}", member);


        //添加成员
        Response response = given()
                .log().all()
                .contentType("application/json; charset=utf-8")
                .queryParam("access_token", accessToken)
                .body(member)
                .when()
                .post("https://qyapi.weixin.qq.com/cgi-bin/user/create")
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
    }

    @Test
    @DisplayName("添加成员部门为1")
    @Order(16)
    public void addMember() {
        DocumentContext documentContext = null;
        try {
            documentContext = JsonPath.parse(new File("src/test/resources/member/add.json"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        String name = FakerUtil.getName();
        String pinYin = FakerUtil.getPinYin(name);
        documentContext.set("$.name", name);
        documentContext.set("$.userid", pinYin);
        documentContext.set("$.alias", pinYin);
        documentContext.set("$.mobile", FakerUtil.getPhone());
        documentContext.set("$.department", Arrays.asList(2,3));
        documentContext.set("$.is_leader_in_dept", Arrays.asList(0,0));



        //3.string类型打印
        String member = documentContext.jsonString();
        logger.info("JsonPath解析规则的成员参数为:{}", member);


        //添加成员
        Response response = given()
                .log().all()
                .contentType("application/json; charset=utf-8")
                .queryParam("access_token", accessToken)
                .body(member)
                .when()
                .post("https://qyapi.weixin.qq.com/cgi-bin/user/create")
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
    }
}
