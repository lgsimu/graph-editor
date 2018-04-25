package com.lgsim.engine.graphEditor.data.test;

import java.util.List;

public class Student {
    private String name;
    private List<String> Age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getAge() {
        return Age;
    }

    public void setAge(List<String> Age) {
        this.Age = Age;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age='" + Age + '\'' +
                '}';
    }
}
