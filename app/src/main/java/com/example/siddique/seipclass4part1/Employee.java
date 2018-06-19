package com.example.siddique.seipclass4part1;

import java.io.Serializable;
import java.util.List;

public class Employee implements Serializable{
    private String name;
    private String age;
    private String phone;
    private String email;
    private String gender;
    private List<String>languages;
    private String city;

    public Employee(String name, String age, String phone, String email, String gender, List<String>languages, String city){
        this.name = name;
        this.age = age;
        this.phone = phone;
        this.email = email;
        this.gender = gender;
        this.languages = languages;
        this.city = city;
    }

    public String getName() {
        return name;
    }

    public String getAge() {
        return age;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getGender() {
        return gender;
    }

    public List<String> getLanguages() {
        return languages;
    }

    public String getCity() {
        return city;
    }
}
