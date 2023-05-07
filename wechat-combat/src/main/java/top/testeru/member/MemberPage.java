package top.testeru.member;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import top.testeru.ConcatPage;
import top.testeru.base.BasePage;
import top.testeru.entity.User;

import java.time.Duration;

//成员相关页面
//添加成员
//编辑成员
//查看成员
public class MemberPage extends BasePage {

    public MemberPage(WebDriver driver) {
        super(driver);
    }

    private By uname = By.id("username");//姓名
    private By acctid = By.id("memberAdd_acctid");//账号
    private By bizMail = By.name("biz_mail");//邮箱
    private By mail3 = By.name("biz_mail1");//邮箱

    private By mobile = By.xpath("//*[@name=\"mobile\"]");//手机号
    private By save = By.linkText("保存");//保存按钮


    //元素定位私有化
//    private By nameEle = By.id();
    //addMember 添加成员
    //第一种：首页点击跳转到添加成员方法
    //第二种：从通讯录页面点击跳转到添加成员方法
    //正确的添加成员
    @Step("添加成员")
    public ConcatPage addMemberTrue(User user){
        System.out.println("添加成员，成员的正确添加");
        addMemberStep(user);

        //页面跳转，显示等待跳转条件

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15), Duration.ofSeconds(2));
        wait.until(webDriver -> !webDriver.getPageSource().contains("企业邮箱："));
        return new ConcatPage(driver);
    }
    //错误的添加成员 返回值为String，收集的错误信息
    public String addMemberFalse(){
//        addMemberStep();
        return "error";
    }
    private void addMemberStep(User user) {

        //    姓名：    id="username"
        send(uname, user.getUname());
        //    账号：    id="memberAdd_acctid"
        send(acctid, user.getAcctid());
        //    邮箱：    name="biz_mail"
        send(bizMail, user.getMail());
        //    手机号：    name="mobile"
        send(mobile, user.getMobile());
        //4. 点击添加
        // a标签   保存
        click(save);
    }

}
