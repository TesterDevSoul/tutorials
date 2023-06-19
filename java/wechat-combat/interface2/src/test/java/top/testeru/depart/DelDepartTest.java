package top.testeru.depart;

import io.restassured.response.Response;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import top.testeru.basic.BaseTest;

import java.util.List;
import java.util.stream.Stream;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

/**
 * @author www.testeru.top
 * @version 1.0.0
 * @Project wechat
 * @Description 删除部门
 * @createTime 2022年12月16日 11:42:47
 */
public class DelDepartTest extends BaseTest {
    static Response response;


    @ParameterizedTest(name = "删除的部门ID：{0}")
    //静态方法源
    @MethodSource
    void deleteDepart(int id){
        //根据order 获取id列表 获取 order  1
        //删除  order < 1000
        response = given().log().all()
                .queryParam("access_token",accessToken)
                .queryParam("id",id)
                .when()
                .get("https://qyapi.weixin.qq.com/cgi-bin/department/delete")
                .then().log().all()
                .statusCode(200)
                .body("errcode", equalTo(0))
                .body("errmsg",equalTo("deleted"))
                .extract().response();


    }

    static Stream<Integer> deleteDepart(){
        //查询
        response = given().log().all()
                .queryParam("access_token",accessToken)
                .when()
                .request("get","https://qyapi.weixin.qq.com/cgi-bin/department/simplelist")
                .then().log().all()
                .extract().response();
        List<Integer> idList =  response.path("department_id.findAll{ it.order < 1000}.id");
        logger.info("order小于1000的部门ID列表为：{}",idList);
        return idList.stream();
    }
    /**
     * 删除部门包含用户
     * 60005 -- department contains user
     */
}
