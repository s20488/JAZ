package com.westeros.movies.mappers;

import com.westeros.data.model.Company;
import com.westeros.moviesclient.contract.CompanySummaryDto;
import org.springframework.stereotype.Component;

@Component
public class CompanyMapper implements IMapEntities<CompanySummaryDto, Company>{
    @Override
    public Company map(CompanySummaryDto companySummaryDto) {
        return map(companySummaryDto, new Company());
    }

    @Override
    public Company map(CompanySummaryDto companySummaryDto, Company company) {
        company.setSourceId(companySummaryDto.getId());
        company.setName(companySummaryDto.getName());
        company.setOriginCountry(company.getOriginCountry());
        return company;
    }
}
