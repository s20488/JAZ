package com.westeros.movies.mappers;

import com.westeros.data.model.CharacterRole;
import com.westeros.moviesclient.contract.ActorSummaryDto;
import org.springframework.stereotype.Component;

@Component
public class CharacterMapper implements IMapEntities<ActorSummaryDto, CharacterRole>{


    @Override
    public CharacterRole map(ActorSummaryDto actorSummaryDto) {
        return map(actorSummaryDto, new CharacterRole());
    }

    @Override
    public CharacterRole map(ActorSummaryDto actorSummaryDto, CharacterRole characterRole) {
        characterRole.setName(actorSummaryDto.getCharacter());
        return characterRole;
    }
}
