package com.example.contracts;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PersonDto {
    @JsonProperty("id")
    private Integer id;
    @JsonProperty("first_name")
    private String first_Name;
    @JsonProperty("last_name")
    private String last_Name;

    public PersonDto(Integer id, String first_Name, String last_Name) {
        this.id = id;
        this.first_Name = first_Name;
        this.last_Name = last_Name;
    }
}
