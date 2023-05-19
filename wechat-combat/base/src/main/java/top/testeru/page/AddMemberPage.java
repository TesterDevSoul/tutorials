package top.testeru.page;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.qameta.allure.Step;

//添加成员页面
public class AddMemberPage extends AppBasePage{

    public AddMemberPage(AndroidDriver androidDriver) {
        super(androidDriver);
    }

    //手动输入添加成员
    @Step("手动输入添加成员")
    public AddMemberPage editAddMember(String name, String phone){
        click(
                AppiumBy.androidUIAutomator(
                        "new UiSelector().text(\"手动输入添加\")"));
        send(AppiumBy.className("android.widget.EditText"),name);
        send(AppiumBy.className("android.widget.EditText"),1,phone);
        click(
                AppiumBy.androidUIAutomator(
                        "new UiSelector().text(\"保存\")"));


        //*[@class='android.widget.Toast']
        waitUtil().until(webDriver -> webDriver.getPageSource().contains("Toast"));
        logger.info("当前页面:{}", page());//输出打印当前页面
        String toastText = text(AppiumBy.xpath("//*[@class=\"android.widget.Toast\"]"),false);
        user.setToast(toastText);
        waitUtil().until(webDriver -> webDriver.getPageSource().contains("添加成员"));
        return this;
    }

    //返回到通讯录页面
    @Step("返回到通讯录页面")
    public ConcatPage toConcatPage(){
        back();
        //显示等待
        waitUtil().until(webDriver -> webDriver.getPageSource().contains("添加成员"));

        return new ConcatPage(androidDriver);
    }
}
