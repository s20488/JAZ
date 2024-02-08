package com.westeros.movies.mappers;

import com.westeros.data.model.Actor;
import com.westeros.moviesclient.contract.ActorDto;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class ActorMapper implements IMapEntities<ActorDto, Actor>{
    @Override
    public Actor map(ActorDto actorDto) {
        return map(actorDto, new Actor());
    }

    @Override
    public Actor map(ActorDto actorDto, Actor actor) {
        actor.setSourceId(actorDto.getId());
        actor.setName(actorDto.getName());
        actor.setAlsoKnownAs(alsoKnownAs(actorDto));
        actor.setBiography(actorDto.getBiography());
        actor.setProfilePath(actorDto.getProfilePath());
        actor.setPopularity(actorDto.getPopularity());
        actor.setBirthday(actorDto.getBirthday());
        actor.setDeathday(actorDto.getDeathday());
        return actor;
    }

    private String alsoKnownAs(ActorDto dto){
        return String.join(";", dto.getAlsoKnownAs());
    }
}
