package models;

public class Student {
    private long id;
    private String name;
    private String phone;
    private String address;
    private int yearOfBirth;
    private Gender gender;
    private Grade grade;

    public Student() {
    }

    public Student(long id, String name, String phone, String address, int yearOfBirth, Gender gender, Grade grade) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.yearOfBirth = yearOfBirth;
        this.gender = gender;
        this.grade = grade;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getYearOfBirth() {
        return yearOfBirth;
    }

    public void setYearOfBirth(int yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Grade getGrade() {
        return grade;
    }

    public void setGrade(Grade grade) {
        this.grade = grade;
    }
}
