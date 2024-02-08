package com.westeros.webapi.contract;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;


@Getter
@Setter
public class MovieDto extends MovieSummaryDto{

    private boolean adult;
    private int budget;
    private String overview;
    private LocalDate releaseDate;
    private int runtime;

}
