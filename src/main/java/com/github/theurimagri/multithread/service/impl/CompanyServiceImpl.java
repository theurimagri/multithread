package com.github.theurimagri.multithread.service.impl;

import com.github.theurimagri.multithread.entity.Company;
import com.github.theurimagri.multithread.repository.CompanyRepository;
import com.github.theurimagri.multithread.service.CompanyService;
import org.springframework.stereotype.Service;

import java.util.Set;

import static java.util.stream.Collectors.toSet;
import static java.util.stream.StreamSupport.stream;

@Service
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;

    public CompanyServiceImpl(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Override
    public Set<Company> findAllCompanies() {
        return stream(companyRepository.findAll().spliterator(), true)
                .collect(toSet());
    }

    @Override
    public Set<String> fetchDistinctCompanyNames() {
        return stream(companyRepository.findAll().spliterator(), true).map(Company::getName).collect(toSet());
    }
}
