package top.testeru.page2;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;

//添加成员页面
public class AddMemberPage extends AppBasePage {

    public AddMemberPage(AndroidDriver androidDriver) {
        super(androidDriver);
    }

    //手动输入添加成员
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
        //System.out.println(page());//输出打印当前页面
        String toastText = text(AppiumBy.xpath("//*[@class=\"android.widget.Toast\"]"));
        user.setToast(toastText);
        waitUtil().until(webDriver -> webDriver.getPageSource().contains("添加成员"));
        return this;
    }

    //返回到通讯录页面
    public ConcatPage toConcatPage(){
        ////*[@text="添加成员"]/../../../preceding-sibling::*
        click(By.xpath("//*[@text=\"添加成员\"]/../../../preceding-sibling::*"));
        //显示等待
        waitUtil().until(webDriver -> webDriver.getPageSource().contains("添加成员"));

        return new ConcatPage(androidDriver);
    }
}
