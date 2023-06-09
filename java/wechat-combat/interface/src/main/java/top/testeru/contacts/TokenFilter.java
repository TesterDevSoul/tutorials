package top.testeru.contacts;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.builder.ResponseBuilder;
import io.restassured.filter.Filter;
import io.restassured.filter.FilterContext;
import io.restassured.filter.log.LogDetail;
import io.restassured.internal.print.RequestPrinter;
import io.restassured.internal.print.ResponsePrinter;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;
import top.testeru.api.BaseApi;
import top.testeru.entity.TokenResponse;

import java.io.*;
import java.util.HashMap;

import static io.qameta.allure.Allure.addAttachment;
import static io.restassured.config.LogConfig.logConfig;

//GetToken的过滤 ContactsFilter
public class TokenFilter extends BaseApi implements Filter {
    String apiLogPath = "api.log";
    @Override
    public Response filter(FilterableRequestSpecification requestSpec,
                           FilterableResponseSpecification responseSpec,
                           FilterContext ctx) {
        //动态添加 请求统一添加某些params参数
        requestSpec.queryParams(getToken());
        //获取请求相关的日志信息
        try {
            String requestPrintInfo = RequestPrinter.print(requestSpec,
                    requestSpec.getMethod(),
                    requestSpec.getURI(),
                    LogDetail.ALL,
                    logConfig().blacklistedHeaders(),
                    new PrintStream(new FileOutputStream(apiLogPath, true)),
                    true
            );
            logger.info("接口请求日志为：\n{}", requestPrintInfo);
            //allure报告动态添加文本
            addAttachment("接口请求",requestPrintInfo);

            Response responseOrign = ctx.next(requestSpec, responseSpec);
            //响应修改
            Response responseAction = new ResponseBuilder().clone(responseOrign).build();
            String responsePrintInfo = ResponsePrinter.print(responseAction,
                    responseAction.body(),
                    new PrintStream(new FileOutputStream(apiLogPath, true)),
                    LogDetail.ALL, true,
                    logConfig().blacklistedHeaders()
            );
            logger.info("接口响应日志为：\n{}", responsePrintInfo);
            //allure报告动态添加文本
            addAttachment("接口响应",responsePrintInfo);
            return responseAction;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }



    }
    private static HashMap<String, Object> getToken(){
        try {
            ObjectMapper objectMapper = new ObjectMapper(new JsonFactory());
            TypeReference<HashMap<String,Object>> typeReference = new TypeReference<HashMap<String, Object>>() {
            };
            HashMap<String, Object> map = objectMapper.readValue(new File("token.json"), typeReference);
            long time = Long.valueOf(map.get("time").toString());
            //获取当前时间戳
            long now = System.currentTimeMillis();
            logger.info("now:{}",now);
            logger.info("time:{}",time);
            if(now < time){
                logger.info("直接使用token");

                //token有效，直接返回
                map.remove("time"); // 删除
                return map;
            }else {
                //重新请求
                logger.info("重新请求token");

                GetTokenApi getTokenApi = new GetTokenApi();
                HashMap<String,Object> token = getTokenApi.getToken();
                token.remove("time"); // 删除
                return token;
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
