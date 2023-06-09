package top.testeru;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

//2. 编写学员管理类 StudentManagement ，
// 实现添加学员方法 addStudent()、
// 删除学员方法 deleteStudent()。
public class StudentManagement3 {


    public static void addStudent(List<Student> studentList){
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入学员编号：");
        String id = sc.nextLine();
        System.out.println("请输入学员姓名：");
        String name = sc.nextLine();
        System.out.println("请输入学员性别：");
        String sex = sc.nextLine();
        Student student = new Student(id, name, sex);
        studentList.add(student);
        System.out.println("添加学员成功");
    }
    public static void deleteStudent(List<Student> studentList){
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入要删除的学员编号：");
        String id = sc.nextLine();
        System.out.println("删除后的学员信息：");
        studentList
                .stream()
                .filter(student -> !student.getId().equals(id))
                .forEach(student -> System.out.println(student));

    }

    private static void findStudent(List<Student> studentList){
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入要查找的学员编号：");
        String id = sc.nextLine();
        Optional<Student> student1 = studentList
                .stream()
                .filter(student -> student.getId().equals(id))
                .findFirst();

        System.out.println("查找的学员信息：\n"+ student1);
    }
    public static void main(String[] args) {
        List<Student> studentList = new ArrayList<>();
        Student s1 = new Student("1001", "张三", "男");
        studentList.add(s1);
        Student s2 = new Student("1002", "莉丝", "女");
        studentList.add(s2);
        Student s3 = new Student("1003", "王武", "男");
        studentList.add(s3);

        while (true){
            //主界面：用输出语句完成主界面的编写
            System.out.println("--------欢迎来到学员信息管理系统--------");
            System.out.println("1 根据学号查看学员信息");
            System.out.println("2 添加学员");
            System.out.println("3 根据学号删除学员后，查看所有学员信息");
            System.out.println("4 退出系统");
            System.out.println("请输入你的选择：");
            //Scanner 实现键盘输入
            Scanner scanner = new Scanner(System.in);
            //输入选择
            String choice = scanner.nextLine();
//            String类型转int
            int choiceI = Integer.valueOf(choice);
            switch (choiceI){
                case 1:
                    System.out.println("根据学号查看学员信息");
                    findStudent(studentList);
                    break;
                case 2:
                    System.out.println("添加学员");
                    addStudent(studentList);
                    break;
                case 3:
                    System.out.println("根据学号删除学员后，查看所有学员信息");
                    deleteStudent(studentList);
                    break;
                case 4:
                    //JVM退出
                    System.exit(0);
            }
        }
    }


}
