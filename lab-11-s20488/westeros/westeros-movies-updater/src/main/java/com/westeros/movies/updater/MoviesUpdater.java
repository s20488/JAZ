package com.westeros.movies.updater;


import com.westeros.common.interfaces.IHaveName;
import com.westeros.data.repositories.ICatalogData;
import com.westeros.movies.mappers.ICatalogMappers;
import com.westeros.movies.mappers.IMapEntities;
import com.westeros.moviesclient.IMoviesClient;
import com.westeros.moviesclient.IMoviesDictionariesClient;
import com.westeros.tools.safeinvoker.SafeInvoking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

@Component
public class MoviesUpdater implements IUpdateMovies{

    private final ICatalogData data;
    private final IMoviesClient moviesClient;
    private final IMoviesDictionariesClient dictionariesClient;
    private final SafeInvoking invoker;

    private final ICatalogMappers entityMapper;

    public MoviesUpdater(ICatalogData dbCatalog,
                         IMoviesClient moviesClient,
                         IMoviesDictionariesClient dictionariesClient,
                         SafeInvoking invoker,
                         ICatalogMappers entityMapper) {
        this.data = dbCatalog;
        this.moviesClient = moviesClient;
        this.dictionariesClient = dictionariesClient;
        this.invoker = invoker;
        this.entityMapper = entityMapper;
    }

    @Override
    public void updateByDateRange(LocalDate from, LocalDate to) {

        updateDictionaries();
        var pagedResultDto = moviesClient.getByDateRange(from, to);

        var movies = pagedResultDto.getResults().stream().map(dto->moviesClient.getMovie(dto.getId())).toList();

        var entities =movies
                .stream()
                .map(dto->entityMapper.forMovie().map(dto))
                .toList();

        var languages = data.getLanguages().findAll();
        var countries = data.getCountries().findAll();
        var genres = data.getGenres().findAll();

        var moviesSourceIds = data.getMovies().findAll().stream().map(entity->entity.getSourceId()).toList();
        var entitiesToSave = entities.stream().filter(Predicate.not(entity-> moviesSourceIds.contains(entity.getSourceId())))
                .toList();

        entitiesToSave.forEach(x->{
            data.getMovies().save(x);
            var moviedto = movies
                    .stream()
                    .filter(dto->dto.getId()==x.getSourceId())
                    .findFirst()
                    .get();
            moviedto.getSpokenLanguages().stream()
                    .map(dto->dto.getName())
                     .map(name->languages.stream()
                             .filter(l->l.getName().equals(name))
                             .findFirst()
                             .get())
                     .forEach(language-> {
                         x.getSpokenLanguages().add(language);
                        language.getMovies().add(x);
                     });



            moviedto.getProductionCountries().stream()
                    .map(dto->dto.getName())
                    .map(name->countries.stream()
                            .filter(l->l.getName().equals(name))
                            .findFirst()
                            .get())
                    .forEach(language-> {
                        x.getProductionCountries().add(language);
                        language.getMovies().add(x);
                    });


            moviedto.getGenres().stream()
                    .map(dto->dto.getName())
                    .map(name->genres.stream()
                            .filter(l->l.getName().equals(name))
                            .findFirst()
                            .get())
                    .forEach(language-> {
                        x.getGenres().add(language);
                        language.getMovies().add(x);
                    });

            data.getMovies().save(x);

            var castDto = moviesClient.getCredits((int)x.getSourceId()).getCast();
            var actorsDto = castDto.stream().map(c->moviesClient.getActorDetails(c.getId())).toList();
            var actorSourceIds = data.getActors().findAll().stream().map(a->a.getSourceId()).toList();
            var actorsToSave = actorsDto.stream()
                    .filter(Predicate.not(a->actorSourceIds.contains(a.getId())))
                    .map(a->entityMapper.forActor().map(a))
                    .toList();
            data.getActors().saveAll(actorsToSave);

            castDto.stream().map(c->{
              var tmp =  entityMapper.forCharacter().map(c);
              tmp.setMovie(x);
              tmp.setActor(actorsToSave.stream().filter(a->a.getSourceId()==c.getId()).findFirst().get());
              return tmp;
            }).forEach(c->data.getCharacter().save(c));



        });
    }

    private void updateDictionaries(){

        var languagesDto = dictionariesClient.getLanguages();
        var countriesDto = dictionariesClient.getCountries();
        var genresDto = dictionariesClient.getGenres();

        var languages = data.getLanguages().findAll();
        var countries = data.getCountries().findAll();
        var genres = data.getGenres().findAll();

        SaveNewDictionaries(languagesDto, languages, entityMapper.forLanguage(), entity->data.getLanguages().save(entity));
        SaveNewDictionaries(countriesDto, countries, entityMapper.forCountry(), entity->data.getCountries().save(entity));
        SaveNewDictionaries(genresDto, genres, entityMapper.forGenre(), entity-> data.getGenres().save(entity));

/*
//        languagesDto.stream()
//                .filter(Predicate.not(
//                        dto-> languages
//                                .stream()
//                                .anyMatch(entity->entity.getName().equals(dto.getName()))))
//                .forEach(languageDto->data.getLanguages().save(entityMapper.forLanguage().map(languageDto)));

//        countriesDto.stream()
//                .filter(Predicate.not(
//                        dto-> countries.stream().anyMatch(entity->entity.getName().equals(dto.getName()))))
//                .forEach(dto->data.getCountries().save(entityMapper.forCountry().map(dto)));
//
//        genresDto.stream()
//                .filter(Predicate.not(dto-> genres.stream().anyMatch(entity->entity.getName().equals(dto.getName()))))
//                .forEach(dto->data.getGenres().save(entityMapper.forGenre().map(dto)));
*/
    }

    private <TDto extends IHaveName, TEntity extends IHaveName> void SaveNewDictionaries(List<TDto> dtos,
                                                                                         List<TEntity> entities,
                                                                                         IMapEntities<TDto, TEntity> mapper,
                                                                                         Consumer<TEntity> saveAction){
        dtos.stream()
                .filter(Predicate.not(
                        dto-> entities
                                .stream()
                                .anyMatch(entity->entity.getName().equals(dto.getName()))))
                .forEach(dto->saveAction.accept(mapper.map(dto)));
    }


}
