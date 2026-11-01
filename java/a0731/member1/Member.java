package a0731.member1;

public class Member {
    private int no;
    private String name;
    private String email;
    private int age;

    public Member(int no, String name, String email, int age) {
        this.no = no;
        this.name = name;
        this.email = email;
        this.age = age;
    }

    public int getNo() {
        return no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
