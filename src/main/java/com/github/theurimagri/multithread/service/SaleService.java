package com.github.theurimagri.multithread.service;

import com.github.theurimagri.multithread.entity.Sale;
import com.github.theurimagri.multithread.model.CompanyDetailedCount;
import com.github.theurimagri.multithread.model.DetailedCount;

import java.util.List;

public interface SaleService {

    List<DetailedCount> calculateDetailedCount();

    List<CompanyDetailedCount> calculateCompanyDetailedCount();

    List<Sale> fetchSalesByCompany(String companyName);
}
