package top.testeru.entity;

public class AddMember {
    private String name;
    private String phone;

    private String toast;

    public String getToast() {
        return toast;
    }

    public void setToast(String toast) {
        this.toast = toast;
    }

    public AddMember() {
    }


    @Override
    public String toString() {
        return "AddMember{" +
                "name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", toast='" + toast + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
