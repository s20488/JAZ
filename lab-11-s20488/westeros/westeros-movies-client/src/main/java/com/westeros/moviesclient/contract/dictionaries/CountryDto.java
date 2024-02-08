package com.westeros.moviesclient.contract.dictionaries;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.westeros.common.interfaces.IHaveName;

public class CountryDto implements IHaveName {

    @JsonProperty("english_name")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
