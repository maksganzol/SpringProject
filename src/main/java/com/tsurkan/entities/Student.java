package com.tsurkan.entities;

import java.util.Map;
import java.util.Set;

public class Student {
    private String name, group, phone;
    private int age;
    private Map card = null;
    private Set<String> keySet;

    public Set<String> getKeySet() {
        return keySet;
    }

    public void setKeySet(Set<String> keySet) {
        this.keySet = keySet;
    }

    public Map getCard() {
        return card;
    }

    public void setCard(Map card) {
        this.card = card;
        keySet = card.keySet();
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

    public Student() {
    }

    public Student(String name, String group, int age, String phone) {
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
}
