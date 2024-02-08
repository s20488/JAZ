package com.westeros.moviesclient.contract.dictionaries;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LanguageSummaryDto {

    @JsonProperty("english_name")
    private String englishName;
    private String name;

    public String getEnglishName() {
        return englishName;
    }

    public void setEnglishName(String englishName) {
        this.englishName = englishName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
