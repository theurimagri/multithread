package com.github.theurimagri.multithread.service;

import com.github.theurimagri.multithread.entity.Company;

import java.util.Set;

public interface CompanyService {
    Set<Company> findAllCompanies();

    Set<String> fetchDistinctCompanyNames();

}
