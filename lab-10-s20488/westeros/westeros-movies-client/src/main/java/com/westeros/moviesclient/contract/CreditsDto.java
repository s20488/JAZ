package com.westeros.moviesclient.contract;

import java.util.List;

public class CreditsDto {
    private long id;
    private List<ActorSummaryDto> cast;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<ActorSummaryDto> getCast() {
        return cast;
    }

    public void setCast(List<ActorSummaryDto> cast) {
        this.cast = cast;
    }

}

