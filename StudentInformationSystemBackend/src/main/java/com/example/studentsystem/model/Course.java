package com.example.studentsystem.model;

import lombok.Data;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Data
@ToString
@Entity
public class Course {

    private @Id @GeneratedValue Integer id;
    private String name;
    private String desc;
    private String letters;

    public Course() {
    }

    public Course(String name, String desc) {
        this.name = name;
        this.desc = desc;
        Pattern p = Pattern.compile("\\b[A-Z0-9]");
        Matcher m = p.matcher(name);
        this.letters = "";
        while(m.find()) {
            this.letters += m.group().toUpperCase();
        }
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getLetters() {
        return letters;
    }

    public void setLetters(String letters) {
        this.letters = letters;
    }
}
