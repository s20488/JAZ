package com.westeros.moviesclient.contract.dictionaries;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CountryDto {

    @JsonProperty("native_name")
    private String name;
    @JsonProperty("english_name")
    private String englishName;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEnglishName() {
        return englishName;
    }

    public void setEnglishName(String englishName) {
        this.englishName = englishName;
    }
}
