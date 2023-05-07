package top.testeru;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import top.testeru.base.BasePage;
import top.testeru.base.BaseProPage;
import top.testeru.member.MemberPage;
import top.testeru.member.MemberProPage;

import java.util.List;

import static java.lang.Thread.sleep;

//通讯录页面
public class ConcatProPage extends BaseProPage {
    public ConcatProPage(WebDriver driver) {
        super(driver);
    }
    //查找到2个元素
    @FindBy(css = ".js_has_member .js_add_member")//添加成员按钮
    WebElement addMemberEle;

    @FindBy(className = "member_colRight_memberTable_td")//成员列表查看
    List<WebElement> memberTdEles;

    //点击添加成员按钮，跳转到添加成员页面
    public MemberProPage clickAddMember(){
        logger.info("进入了通讯录页面，在通讯录页面进行点击添加成员操作，页面跳转");
        //2. 点击添加成员按钮
        //list集合里面的下标为0的元素
        for (int i = 0; i < 3; i++) {
            if(!page().contains("username")){
                click(addMemberEle);
                try {
                    sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }else {
                break;
            }
        }
        waitWithPage("企业邮箱：");
        return new MemberProPage(driver);
    }


    //获取当前通讯录的成员文本的列表
    public List<String> getMemberListText(){
        //member_colRight_memberTable_td   27
        //List<WebElement> tdEleList = driver.findElements(By.className("member_colRight_memberTable_td"));
        //List<String> tdTitleList = new ArrayList<>();
        //tdEleList.forEach(
        //        td -> {
        //            String title = td.getAttribute("title");
        //            System.out.println("title：" + title);
        //            tdTitleList.add(title);
        //
        //        }
        //);
        //
        List<String> titles = getAttributes(memberTdEles, "title");
        logger.info("成员页面：{}", titles);
        return titles;
    }

}
