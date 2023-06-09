package top.testeru;

//1. 编写学员实体类 Student，对应成员变量包含：学号 id、姓名 name、性别 sex；
public class Student {
    private String id;
    private String name;
    private String sex;


    //学号：1001,姓名：张三,性别：男。
    @Override
    public String toString() {
        return "学号：" + id + ", 姓名：" + name + ", 性别：" + sex ;
    }

    public Student() {
    }

    public Student(String id, String name, String sex) {
        this.id = id;
        this.name = name;
        this.sex = sex;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
