package top.testeru.contacts;


import io.restassured.filter.Filter;
import io.restassured.response.Response;
import top.testeru.api.BaseApi;
import top.testeru.entity.DepartResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;
import static top.testeru.api.WeWorkAPI.CREATDEPART;

//部门相关
public class DepartApi extends BaseApi {
    DepartResponse departResponse = new DepartResponse();

    //添加部门
    public DepartResponse add(String depart) throws IOException {
/*        List<Filter> filterList = new ArrayList<>();
        filterList.add(new TokenFilter());*/
        List<Filter> filterList = new ArrayList<>(){{
            add(new TokenFilter());
        }};
        Response response = run(filterList, CREATDEPART,null,"application/json; charset=UTF-8",null,depart);
        Integer errcode = response.path("errcode");
        String errmsg = response.path("errmsg");
        Long id = 0==errcode ? response.path("id") : 0L;
        logger.info("新增部门id:{}",id);
        departResponse.setErrcode(errcode);
        departResponse.setErrmsg(errmsg);
        departResponse.setId(id);
        return departResponse;
    }
}
