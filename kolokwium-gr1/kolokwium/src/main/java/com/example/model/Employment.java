package com.example.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Employment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;
    private String keySkills;
    @ManyToOne
    @JoinColumn(name = "person_id")
    private Person person;

    public void add(Person person) {
        this.person = person;
        person.getEmployment().add(this);
    }
}
