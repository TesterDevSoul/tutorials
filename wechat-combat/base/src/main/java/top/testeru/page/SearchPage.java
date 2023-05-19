package top.testeru.page;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.qameta.allure.Step;

//搜索页面
public class SearchPage extends AppBasePage {

    public SearchPage(AndroidDriver androidDriver) {
        super(androidDriver);
    }

    //输入用户名，跳转到搜索结果页面
    @Step("输入用户名，跳转到搜索结果页面")
    public SearchResultPage toSearchResultPage(String name){
        send(AppiumBy.androidUIAutomator("new UiSelector().text(\"搜索\")"), name);
        waitUtil().until(webDriver -> webDriver.getPageSource().contains("联系人"));
        return new SearchResultPage(androidDriver);
    }
    //返回通讯录页面
    @Step("返回通讯录页面")
    public ConcatPage toConcatPage(){
        back();
        waitUtil().until(webDriver -> webDriver.getPageSource().contains("添加成员"));
        return new ConcatPage(androidDriver);
    }
}
