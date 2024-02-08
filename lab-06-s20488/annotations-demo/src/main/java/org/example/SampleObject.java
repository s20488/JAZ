package org.example;

import org.example.validation.annotations.NotNull;
import org.example.validation.annotations.Range;
import org.example.validation.annotations.Regex;


public class SampleObject {

    @NotNull(message="pole jest null")
    private String name;


    @NotNull
    @Regex(pattern = "^\\w+@[a-zA-Z_]+?\\.[a-zA-Z]{2,3}$", message = "email should be in correct format")
    private String email;

    @Range(min=0, max=10)
    private int number;

    public SampleObject(String name, String email, int number) {
        this.name = name;
        this.email = email;
        this.number = number;
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

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}