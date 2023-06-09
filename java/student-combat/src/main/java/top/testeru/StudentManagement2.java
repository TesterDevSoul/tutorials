package top.testeru;


import java.util.ArrayList;
import java.util.List;

//2. 编写学员管理类 StudentManagement ，
// 实现添加学员方法 addStudent()、
// 删除学员方法 deleteStudent()。
public class StudentManagement2 {
    private static List<Student> studentList = new ArrayList<>();
    public void addStudent(String id, String name, String sex){
        Student student = new Student(id, name, sex);
        System.out.println(student);
        studentList.add(student);
    }
    public void deleteStudent(String id){
        studentList
                .stream()
                .filter(student -> !student.getId().equals(id))
                .forEach(student -> System.out.println(student));

    }
    public static void main(String[] args) {
        System.out.println("添加的学员信息：");
        StudentManagement2 studentManagement = new StudentManagement2();
        //学号：1001,姓名：张三,性别：男。
        studentManagement.addStudent("1001", "张三", "男");
        //学号：1002,姓名：莉丝,性别：女。
        studentManagement.addStudent("1002", "莉丝", "女");
        //学号：1003,姓名：王武,性别：男。
        studentManagement.addStudent("1003", "王武", "男");
        System.out.println("删除后的学员信息：");
        studentManagement.deleteStudent("1002");
    }
}
