package com.westeros.movies.mappers;

import com.westeros.data.model.Genre;
import com.westeros.moviesclient.contract.dictionaries.GenreSummaryDto;
import org.springframework.stereotype.Component;

@Component
public class GenreMapper implements IMapEntities<GenreSummaryDto, Genre>{
    @Override
    public Genre map(GenreSummaryDto genreSummaryDto) {
        return map(genreSummaryDto, new Genre());
    }

    @Override
    public Genre map(GenreSummaryDto genreSummaryDto, Genre genre) {
        genre.setName(genreSummaryDto.getName());
        genre.setSourceId(genreSummaryDto.getId());
        return genre;
    }
}
