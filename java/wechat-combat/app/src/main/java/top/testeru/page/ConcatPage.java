package top.testeru.page;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.qameta.allure.Step;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;

//通讯录页面
public class ConcatPage extends AppBasePage{

    public ConcatPage(AndroidDriver androidDriver) {
        super(androidDriver);
    }

    //跳转到添加成员页面
    @Step("跳转到添加成员页面")
    public AddMemberPage toAddMemberPage(){
        //点击添加成员
//        find(AppiumBy.androidUIAutomator(
//"new UiScrollable(new UiSelector().scrollable(true)).scrollIntoView(new UiSelector().text(\"添加成员\"))"));
        for (int i = 0; i < 10; i++) {
            try{
                find(AppiumBy.cssSelector("[text=\"添加成员\"]"));
                break;
            }catch (Exception e){
                upAndDown(0.8, 0.2);
            }
        }

        click(AppiumBy.cssSelector("[text=\"添加成员\"]"));

        waitUtil().until(webDriver -> webDriver.getPageSource().contains("手动输入添加"));

        return new AddMemberPage(androidDriver);
    }

    //跳转到搜索页面
    @Step("跳转到搜索页面")
    public SearchPage toSearchPage(){
        click(AppiumBy.className("android.widget.TextView"),1);
        waitUtil().until(webDriver -> webDriver.findElement(AppiumBy.xpath(
                "//*[@class=\"android.widget.EditText\" and @text=\"搜索\"]")));

        return new SearchPage(androidDriver);
    }

}
