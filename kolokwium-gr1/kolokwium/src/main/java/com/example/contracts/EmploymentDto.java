package com.example.contracts;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmploymentDto {
    @JsonProperty("id")
    private Integer id;
    @JsonProperty("title")
    private String title;
    @JsonProperty("key_skills")
    private String key_skills;

    public EmploymentDto(Integer id, String title, String key_skills) {
        this.id = id;
        this.title = title;
        this.key_skills = key_skills;
    }
}
