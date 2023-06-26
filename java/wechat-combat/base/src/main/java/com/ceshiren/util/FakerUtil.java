package com.ceshiren.util;

import com.apifan.common.random.source.OtherSource;
import com.apifan.common.random.source.PersonInfoSource;
import com.apifan.common.random.util.PinyinUtils;
import com.github.javafaker.Faker;

import java.util.Locale;


public class FakerUtil {
    static Faker faker = new Faker(Locale.SIMPLIFIED_CHINESE);
    private static String[] telFirst="134,135,136,137,138,139,150,151,152,157,158,159,130,131,132,155,156,133,153".split(",");
    //名字
    public static String get_name(){
        String name = faker.name().fullName();
        return name;
    }
    public static String get_zh_phone(){
        //随机生成8位的电话号
        String s = faker.phoneNumber().subscriberNumber(8);
        //随机生成手机号开端的下标
        int index= getNum(0,telFirst.length-1);
        //获取手机号开头三位数
        String first=telFirst[index];
        //返回手机号
        return first + s;
    }
    //11位  897 567
    public static String get_phone(){
        return faker.phoneNumber().phoneNumber();
    }
    public static String get_acctid(){
        return faker.phoneNumber().subscriberNumber(12);
    }


    //部门名字
    public static String getDepartName(){
        //随机生成1个公司部门名称
        String department = OtherSource.getInstance().randomCompanyDepartment();
        return department;
    }
    public static String getName(){
        ///生成1个随机中文人名(性别随机)
        String k = PersonInfoSource.getInstance().randomChineseName();
        return k;
    }
    public static String getPinYin(String pinyin){
        return PinyinUtils.toPinyin(pinyin, true);
    }

    public static String getPhone(){
        //生成1个随机中国大陆手机号
        String m = PersonInfoSource.getInstance().randomChineseMobile();
        //返回手机号
        return m;
    }
    public static String getAccount(){
        //生成1个随机中国大陆手机号
        String m = PersonInfoSource.getInstance().randomQQAccount();
        //返回手机号
        return m;
    }
    public static int getNum(int start,int end) {
        return (int)(Math.random()*(end-start+1)+start);
    }
    public static String getChinese(int num) {
        //生成4个随机汉字
        String j = OtherSource.getInstance().randomChinese(num);
        return j;
    }
}