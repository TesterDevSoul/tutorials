package top.testeru.member1;


import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import top.testeru.page1.MainPage;
import top.testeru.util.FakerUtil;

import java.util.List;

import static java.lang.invoke.MethodHandles.lookup;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.slf4j.LoggerFactory.getLogger;


public class AddMemberTest {
    static final Logger logger = getLogger(lookup().lookupClass());

    @Test
    public void editAdd(){
        String name = FakerUtil.get_name();
        String zh_phone = FakerUtil.get_zh_phone();
        logger.info("添加成员：{}", name);
        List<String> nameList = new MainPage()//进入企业微信首页-消息页面
                                    .toConcatPage()//通讯录页面
                                    .toAddMemberPage()//跳转到添加成员页面
                                    .editAddMember(name, zh_phone)//手动输入添加成员
                                    .toConcatPage()//返回到通讯录页面
                                    .getNameList();
        assertThat("通讯录不包含 " + name + " 成员", nameList.contains(name));
    }

}
