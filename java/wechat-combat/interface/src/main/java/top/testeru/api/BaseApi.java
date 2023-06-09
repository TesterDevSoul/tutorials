package top.testeru.api;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.filter.Filter;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.slf4j.Logger;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import static io.restassured.RestAssured.given;
import static java.lang.invoke.MethodHandles.lookup;
import static org.slf4j.LoggerFactory.getLogger;

//封装请求
//get请求 post请求
public class BaseApi {

    public static final Logger logger = getLogger(lookup().lookupClass());

    /**
     * method ：请求方法 GET POST
     * URL：请求地址 URL
     * queryMap：HashMap类型的query参数 queryParams
     * contentType：请求编码格式
     * headers：请求头相关信息
     * body：POST请求的body体参数
     *
     */
    public Response run(List<Filter> filterList, //过滤
                        String method,
                        String URL,
                        HashMap<String, Object> querys,
                        String contentType,
                        HashMap<String, Object> headers,
                        String body){
        //given when then
        RequestSpecification requestSpecification = given().log().all();
        //        ""
//        .filters()
        System.out.println("filter:");
        System.out.println(null != filterList && 0 < filterList.size());
        if(null != filterList && 0 < filterList.size()){
            requestSpecification.filters(filterList);
        }
        if(null != contentType){
            requestSpecification.contentType(contentType);
        }
        if(null != headers){
            requestSpecification.headers(headers);
        }
        if(null != querys && 0 < querys.size()){
            requestSpecification.queryParams(querys);
        }
        if(null != body){
            requestSpecification.body(body);
        }
        Response response = requestSpecification
                                .when()
                                    .request(method, URL)
                                .then()
                                    .log().all()
                                    .extract().response();
        return response;
    }


    public Response run(List<Filter> filterList, String URL,
                        HashMap<String, Object> querys,
                        String contentType,
                        HashMap<String, Object> headers){
        logger.info("GET请求");
        Response getResponse = run(filterList,"get", URL, querys, contentType, headers, null);
        return getResponse;
    }
    public Response run(List<Filter> filterList, String URL,
                        HashMap<String, Object> querys,
                        String contentType,
                        HashMap<String, Object> headers,
                        String body){
        logger.info("POST请求");
        Response postResponse = run(filterList, "post", URL, querys, contentType, headers, body);
        return postResponse;

    }
}
