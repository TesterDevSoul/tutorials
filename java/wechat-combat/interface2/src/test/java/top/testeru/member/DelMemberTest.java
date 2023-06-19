package top.testeru.member;

import io.restassured.response.Response;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import top.testeru.basic.BaseTest;

import java.util.HashMap;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author www.testeru.top
 * @version 1.0.0
 * @Project wechat
 * @Description 删除成员
 * @createTime 2022年12月16日 12:46:37
 */
public class DelMemberTest extends BaseTest {

    @ParameterizedTest(name = "参数化删除的成员：{0}")
    //静态方法源
    @MethodSource
    @Order(76)
    public void delete(String member){
        Response response = given()
                .log().all()
                .contentType("application/json; charset=utf-8")
                .queryParam("access_token", accessToken)
                .queryParam("userid",member)
                .when()
                .get("https://qyapi.weixin.qq.com/cgi-bin/user/delete")
                .then()
                .log().all()
                .statusCode(200)
                .extract().response();
        Integer errcode = response.path("errcode");
        String errmsg = response.path("errmsg");
        assertAll(
                () -> assertEquals(0, errcode),
                () -> assertEquals("deleted", errmsg)
        );
    }
    static List<String> delete(){
        Response response = given()
                .log().all()
                .contentType("application/json; charset=utf-8")
                .queryParam("access_token", accessToken)
                .when()
                .post("https://qyapi.weixin.qq.com/cgi-bin/user/list_id")
                .then()
                .log().all()
                .statusCode(200)
                .extract().response();
        List<String> uidList = response.path("dept_user.userid");
        logger.info("uid:{}",uidList);
        uidList.remove("gaigai");
        return uidList;
    }
}
