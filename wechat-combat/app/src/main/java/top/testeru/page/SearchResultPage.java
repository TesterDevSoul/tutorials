package top.testeru.page;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.qameta.allure.Step;

//搜索结果页
public class SearchResultPage extends AppBasePage{


    public SearchResultPage(AndroidDriver androidDriver) {
        super(androidDriver);
    }

    //跳转到个人信息页面
    @Step("跳转到个人信息页面")
    public ProfilePage toProfilePage(){
        click(
                AppiumBy.xpath(
                        "//*[@text=\"联系人\"]/../following-sibling::*//*[@class=\"android.widget.TextView\"]"));
        waitUtil().until(webDriver -> webDriver.getPageSource().contains("设置备注和描述"));
        return new ProfilePage(androidDriver);
    }

    //跳转到搜索页面
    @Step("跳转到搜索页面")
    public SearchPage toSearchPage(){
        click(AppiumBy.className("android.widget.ImageView"));
        waitUtil().until(webDriver -> webDriver.findElement(AppiumBy.xpath(
                "//*[@class=\"android.widget.EditText\" and @text=\"搜索\"]")));
        return new SearchPage(androidDriver);
    }
}
