package top.testeru.member2;


import org.junit.jupiter.api.Test;
import top.testeru.entity.AddMember;
import top.testeru.page2.MainPage;
import top.testeru.page2.ProfilePage;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertAll;

public class AddMemberTest {

    @Test
    public void getMember(){
        String name = "测试人002";
        ProfilePage profilePage = new MainPage()//进入企业微信首页-消息页面
                .toConcatPage()//通讯录页面
                .toSearchPage()//跳转到搜索页面
                .toSearchResultPage(name)//输入用户名进入搜索结果页
                .toProfilePage();//点击第一个搜索结果进入到个人信息页面
        AddMember addMember = profilePage
                                .getMember();//获取个人信息页面的信息

       profilePage.toSearchResultPage()//返回搜索结果页面
                                .toSearchPage()//返回搜索页面
                                .toConcatPage();//返回到通讯录页面
        //断言
        assertAll(
                //断言输入的姓名和搜索结果是否一致
                () -> assertThat(addMember.getName(), is(equalTo(name)))
        );

    }

}
