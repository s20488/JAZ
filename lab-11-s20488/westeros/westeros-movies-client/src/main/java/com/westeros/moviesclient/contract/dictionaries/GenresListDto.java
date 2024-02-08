package com.westeros.moviesclient.contract.dictionaries;

import java.util.List;

public class GenresListDto {

    public List<GenreSummaryDto> getGenres() {
        return genres;
    }

    public void setGenres(List<GenreSummaryDto> genres) {
        this.genres = genres;
    }

    private List<GenreSummaryDto> genres;
}
