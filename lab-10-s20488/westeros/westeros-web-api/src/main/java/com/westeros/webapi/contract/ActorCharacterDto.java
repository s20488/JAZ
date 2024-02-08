package com.westeros.webapi.contract;

import com.westeros.data.model.CharacterRole;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ActorCharacterDto {

    private String characterName;
    private String actorName;
}
