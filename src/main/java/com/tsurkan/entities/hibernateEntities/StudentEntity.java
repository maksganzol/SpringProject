package com.tsurkan.entities.hibernateEntities;

import com.tsurkan.entities.Student;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Map;
import java.util.Set;

@Entity
@Table(name = "students")
public class StudentEntity implements Serializable {

    public static final long serialVersionUID = 1488L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idstudents")
    private long id;
    @Column(name = "name")
    private String name;
    @Column(name = "grp")
    private String group;
    @Column(name = "phone")
    private String phone;
    @Column(name = "age")
    private int age;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", group='" + group + '\'' +
                ", phone='" + phone + '\'' +
                ", age=" + age +
                '}';
    }

    public StudentEntity() {
    }

    public StudentEntity(Student student) {
        name = student.getName();
        group = student.getGroup();
        phone = student.getPhone();
        age = student.getAge();
    }

    public StudentEntity(String name, String group, int age, String phone) {
        this.name = name;
        this.group = group;
        this.phone = phone;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setAge(String age){
        this.age = Integer.parseInt(age);
    }

    public Student toStudent(){
        return new Student(name, group, age, phone);
    }
}
