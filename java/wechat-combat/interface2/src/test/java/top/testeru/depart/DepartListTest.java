package top.testeru.depart;

import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import top.testeru.basic.BaseTest;

import java.util.List;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

/**
 * @author www.testeru.top
 * @version 1.0.0
 * @Project wechat
 * @Description 查看所有部门用例
 * @createTime 2022年12月16日 10:38:38
 */
public class DepartListTest extends BaseTest {
    @Test
    public void findAllDepart(){
        given()
                .log().all()
                .with()
                .queryParam("access_token",accessToken)
                .when()
                .request("get","https://qyapi.weixin.qq.com/cgi-bin/department/simplelist")
                .then()
                .log().all();

    }

    @Test
    public void findWithOrder(){

        //查询
        Response response = given().log().all()
                .queryParam("access_token", accessToken)
                .when()
                .request("get","https://qyapi.weixin.qq.com/cgi-bin/department/simplelist")
                .then().log().all()
                .body(matchesJsonSchemaInClasspath("depart/depart.json"))
                .extract().response();


        List<Integer> idList =  response.path("department_id.findAll{ it.order < 1000}.id");
        logger.info("order小于1000的部门ID列表为：{}",idList);
    }
}