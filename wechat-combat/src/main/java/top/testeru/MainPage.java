package top.testeru;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import top.testeru.base.BasePage;
import top.testeru.member.MemberPage;

//企业微信登录成功后的首页
public class MainPage extends BasePage {

    /**
     * 无参数构造进行driver 数据声明
     * 有参构造进行driver传递
     */
    public MainPage() {
    }

    //成员变量

    private By menuContacts = By.id("menu_contacts");
    private By searchConcat = By.id("memberSearchInput");



    //点击通讯录的tab,跳转到通讯录
    public ConcatPage toConcatPage(){
        System.out.println("跳转到通讯录页面");
         click(menuContacts);
        //跳转到通讯录页面成功的显示等待条件为搜索输入框 id="memberSearchInput"
        wait.until(
                webDriver -> webDriver.getPageSource().contains("搜索成员、部门、标签"));
        return new ConcatPage(driver);
    }

    //首页直接点击添加成员按钮跳转到添加成员页面
    public MemberPage toMemberPage(){
        System.out.println("跳转到添加成员页面");
        return new MemberPage(driver);
    }
}
