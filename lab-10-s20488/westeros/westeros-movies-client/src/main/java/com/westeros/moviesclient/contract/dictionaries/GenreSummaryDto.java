package com.westeros.moviesclient.contract.dictionaries;

import com.westeros.common.interfaces.IHaveName;

public class GenreSummaryDto implements IHaveName {
    private int id;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
