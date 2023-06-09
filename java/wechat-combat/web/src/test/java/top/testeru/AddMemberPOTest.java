package top.testeru;

import io.qameta.allure.Step;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import top.testeru.entity.User;
import top.testeru.util.FakerUtil;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;

/**
 * @author testeru.top
 * @version 1.0.0
 * @Prpject wechat-combat
 * @Description
 * @createTime 2023年05月06日 19:57:00
 */
@DisplayName("成员添加测试用例")
public class AddMemberPOTest {
    static ConcatPage concatPage;
    static MainPage mainPage;
    @Test
    @DisplayName("添加成员")
    @Step("添加成员测试用例")
    public void addMemberTrue() {
        User user = new User(FakerUtil.getName(),FakerUtil.getAccount(),FakerUtil.getAccount(),FakerUtil.getPhone());
        mainPage = new MainPage();
        concatPage = mainPage//首页
                            .toConcatPage() //方法 //跳转到通讯录页面
                            .clickAddMember()//点击添加成员方法  //跳转到 添加成员页面
                            .addMemberTrue(user);
        List<String> memberTextList = concatPage//正确的添加成员方法  //返回到通讯录页面
                                        .getMemberListText();//获取当前成员列表文本方法;

        //断言
        assertThat("成员添加失败",memberTextList.contains(user.getUname()));
    }
}
