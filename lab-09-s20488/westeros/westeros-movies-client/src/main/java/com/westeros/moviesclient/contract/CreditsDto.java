package com.westeros.moviesclient.contract;

import java.util.List;

public class CreditsDto {
    private int id;
    private List<ActorSummaryDto> cast;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<ActorSummaryDto> getCast() {
        return cast;
    }

    public void setCast(List<ActorSummaryDto> cast) {
        this.cast = cast;
    }
}

