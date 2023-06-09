package top.testeru.contacts;


import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import io.restassured.response.Response;
import org.slf4j.Logger;
import top.testeru.api.BaseApi;
import top.testeru.entity.TokenResponse;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static java.lang.invoke.MethodHandles.lookup;
import static org.slf4j.LoggerFactory.getLogger;
import static top.testeru.api.WeWorkAPI.GETTOKEN;

public class GetTokenApi extends BaseApi {
    //TokenResponse getTokenResponse = new TokenResponse();
    //log日志

    //封装一个方法，为获取token的方法
    //调用的URL：GETTOKEN
    public HashMap<String,Object> getToken() throws IOException {
        logger.info("调用GetTokenAPI");
        //获取当前时间戳
        long now = System.currentTimeMillis();
        //yaml文件读取解析  HashMap<String, HashMap<String, HashMap<String,String>>>

        ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());
        TypeReference<HashMap<String, HashMap<String, HashMap<String,Object>>>> valueTypeRef =
                new TypeReference<HashMap<String, HashMap<String, HashMap<String,Object>>>>() {};
        HashMap<String, HashMap<String, HashMap<String, Object>>> readValue = objectMapper.readValue( new File("src/test/resources/token.yaml"), valueTypeRef);

        logger.info("token解析对象为：{}",readValue);
        HashMap<String, Object> tokenQuerys = readValue.get("master").get("contacts");
        //1.token 请求
//        (String URL,
//                HashMap<String, Object> querys,
//                String contentType,
//                HashMap<String, Object> headers)
        //请求参数的准备
        Response response = run(null, GETTOKEN, tokenQuerys, null, null);
        /*Response response = given()
                        .log().all()
                        .queryParams(tokenQuerys)
                         //发送具体请求
                    .when()
                        .request("get",GETTOKEN)
                    //获取响应结果
                    .then()
                        .log().all()
                        //校验HTTPcode码200;
                        .statusCode(200)
                        .extract().response();*/

        Integer errcode = response.path("errcode");//gpath
        String errmsg = response.path("errmsg").toString();
        //三目运算
        String accessToken = 0==errcode ? response.path("access_token").toString() : errmsg ;

        //2.token 的文件写入
        HashMap<String,Object> token = new HashMap<>();
        token.put("access_token",accessToken);
        //失效时间保存
        long time = now + 7200000L;
        token.put("time", time);

        //json文件保存
        ObjectMapper mapper = new ObjectMapper(new JsonFactory());
        //new File(路径)     -------->     Paths.get(路径).toFile()
        mapper.writeValue(Paths.get("token.json").toFile(), token);
        return token;
    }
}
