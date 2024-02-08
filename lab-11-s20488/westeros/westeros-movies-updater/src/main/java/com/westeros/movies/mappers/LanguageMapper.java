package com.westeros.movies.mappers;

import com.westeros.data.model.SpokenLanguage;
import com.westeros.moviesclient.contract.dictionaries.LanguageSummaryDto;
import org.springframework.stereotype.Component;

@Component
public class LanguageMapper implements IMapEntities<LanguageSummaryDto, SpokenLanguage>{
    @Override
    public SpokenLanguage map(LanguageSummaryDto languageSummaryDto) {
        return map(languageSummaryDto, new SpokenLanguage());
    }

    @Override
    public SpokenLanguage map(LanguageSummaryDto languageSummaryDto, SpokenLanguage spokenLanguage) {
        spokenLanguage.setName(languageSummaryDto.getName());
        return spokenLanguage;
    }
}
