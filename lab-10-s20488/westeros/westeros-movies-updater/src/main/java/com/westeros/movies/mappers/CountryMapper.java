package com.westeros.movies.mappers;

import com.westeros.data.model.Country;
import com.westeros.moviesclient.contract.dictionaries.CountryDto;
import org.springframework.stereotype.Component;

@Component
public class CountryMapper implements IMapEntities<CountryDto, Country>{
    @Override
    public Country map(CountryDto countryDto) {
        return map(countryDto, new Country());
    }

    @Override
    public Country map(CountryDto countryDto, Country country) {
        country.setName(countryDto.getName());
        return country;
    }
}
