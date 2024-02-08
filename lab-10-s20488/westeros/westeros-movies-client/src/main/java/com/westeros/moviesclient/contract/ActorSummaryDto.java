package com.westeros.moviesclient.contract;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ActorSummaryDto {

    private long id;
    private String name;
    private String character;
    @JsonProperty("profile_path")
    private String profilePath;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCharacter() {
        return character;
    }

    public void setCharacter(String character) {
        this.character = character;
    }

    public String getProfilePath() {
        return profilePath;
    }

    public void setProfilePath(String profilePath) {
        this.profilePath = profilePath;
    }
}
