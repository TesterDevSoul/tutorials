package top.testeru;

import com.apifan.common.random.source.OtherSource;
import com.apifan.common.random.source.PersonInfoSource;
import com.apifan.common.random.util.PinyinUtils;

/**
 * @author www.testeru.top
 * @version 1.0.0
 * @Project wechat
 * @Description source
 * @createTime 2022年12月15日 16:32:57
 */
public class FakerUtil {
    private static String[] telFirst="134,135,136,137,138,139,150,151,152,157,158,159,130,131,132,155,156,133,153".split(",");
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

    public static int getNum(int start,int end) {
        return (int)(Math.random()*(end-start+1)+start);
    }
    public static String getChinese(int num) {
        //生成4个随机汉字
        String j = OtherSource.getInstance().randomChinese(num);
        return j;
    }


}

