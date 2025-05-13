package com.example.studentinformation;

public class Student {
    private int id;
    private String name;
    private String studentId;
    private int imageResource;
    private String email;
    private String phone;
    private String address;
    private String program;

    public Student() {
    }

    public Student(int id, String name, String studentId, int imageResource,
                   String email, String phone, String address, String program) {
        this.id = id;
        this.name = name;
        this.studentId = studentId;
        this.imageResource = imageResource;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.program = program;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public int getImageResource() {
        return imageResource;
    }

    public void setImageResource(int imageResource) {
        this.imageResource = imageResource;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getProgram() {
        return program;
    }

    public void setProgram(String program) {
        this.program = program;
    }
}
