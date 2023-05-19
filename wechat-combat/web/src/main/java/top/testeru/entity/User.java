package top.testeru.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.checkerframework.checker.units.qual.C;

/**
 * @author testeru.top
 * @version 1.0.0
 * @Prpject wechat-combat
 * @Description
 * @createTime 2023年05月06日 19:48:00
 */
@Getter
@Setter
@ToString
public class User {

    private String uname;
    private String acctid;
    private String mail;
    private String mobile;

    public User(String uname, String acctid, String mail, String mobile) {
        this.uname = uname;
        this.acctid = acctid;
        this.mail = mail;
        this.mobile = mobile;
    }
}
