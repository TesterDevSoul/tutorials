package top.testeru.member;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.slf4j.Logger;
import top.testeru.entity.AddMember;
import top.testeru.page.AddMemberPage;
import top.testeru.page.MainPage;
import top.testeru.page.ProfilePage;
import top.testeru.util.FakerUtil;

import static java.lang.invoke.MethodHandles.lookup;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.slf4j.LoggerFactory.getLogger;

public class AddMemberTest {
    static final Logger logger = getLogger(lookup().lookupClass());

    ProfilePage profilePage;
    AddMemberPage addMemberPage;
    @BeforeEach
    public void bf(){
        addMemberPage = new MainPage()//进入企业微信首页-消息页面
                                .toConcatPage()//通讯录页面
                                .toAddMemberPage();//跳转到添加成员页面
    }

//    @ParameterizedTest
//    @ValueSource
//    public void editAdd(String name, String zh_phone){

    @Test
    public void editAdd(){
        String name = FakerUtil.get_name();
        String zh_phone = FakerUtil.get_zh_phone();
        logger.info("添加成员：{}", name);
        profilePage = addMemberPage
                            .editAddMember(name,zh_phone)//手动输入添加成员
                            .toConcatPage()//返回到通讯录页面
                            .toSearchPage()//跳转到搜索页面
                            .toSearchResultPage(name)//输入用户名进入搜索结果页
                            .toProfilePage();//点击第一个搜索结果进入到个人信息页面

        AddMember addMember = profilePage.getMember();//获取个人信息页面的信息


        //断言
        assertAll(
                //断言输入的姓名和搜索结果是否一致
                () -> assertThat(addMember.getName(), is(equalTo(name))),
                () -> assertThat(addMember.getPhone(), is(equalTo(zh_phone))),
                () -> assertThat(addMember.getToast(), is(equalTo("添加成功")))
        );

    }
//    static

    @AfterEach
    public void af(){
        profilePage
                .toSearchResultPage()//返回搜索结果页面
                .toSearchPage()//返回搜索页面
                .toConcatPage();//返回到通讯录页面
    }
}
