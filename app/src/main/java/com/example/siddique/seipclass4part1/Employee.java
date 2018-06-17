package com.example.siddique.seipclass4part1;

import java.io.Serializable;
import java.util.List;

public class Employee implements Serializable{
    private String name;
    private String age;
    private String gender;
    private List<String>languages;

    public Employee(String name, String age, String gender, List<String>languages){
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.languages = languages;
    }

    public String getName() {
        return name;
    }

    public String getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }

    public List<String> getLanguages() {
        return languages;
    }
}
