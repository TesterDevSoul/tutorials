package top.testeru.page2;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import top.testeru.entity.AddMember;

//个人信息页面
public class ProfilePage extends AppBasePage {
    public ProfilePage(AndroidDriver androidDriver) {
        super(androidDriver);
    }

    //获取当前用户名，手机号存入User实体类
    public AddMember getMember(){
        String searchName = text(AppiumBy.xpath("//*[@class=\"android.widget.ImageView\"]/following-sibling::*//*[@class=\"android.widget.TextView\"]"));
        //13、获取手机号   getText()
        String searchPhone = text(AppiumBy.xpath("//*[@text=\"手机\"]/following-sibling::*//*[@class=\"android.widget.TextView\"]"));
        user.setName(searchName);
        user.setPhone(searchPhone);
        System.out.println("User:"+user);
        return user;
    }
    // 返回到搜索结果页面
    public SearchResultPage toSearchResultPage(){
        //back();//返回搜索结果页面
        click(By.className("android.widget.TextView"));

        //back
        //显示等待
        waitUtil().until(webDriver -> webDriver.getPageSource().contains("联系人"));

        return new SearchResultPage(androidDriver);
    }
}
