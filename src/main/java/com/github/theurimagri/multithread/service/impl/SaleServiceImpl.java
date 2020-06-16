package com.github.theurimagri.multithread.service.impl;

import com.github.theurimagri.multithread.entity.Sale;
import com.github.theurimagri.multithread.model.CompanyDetailedCount;
import com.github.theurimagri.multithread.model.DetailedCount;
import com.github.theurimagri.multithread.repository.SaleRepository;
import com.github.theurimagri.multithread.service.SaleService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SaleServiceImpl implements SaleService {

    private final SaleRepository saleRepository;

    public SaleServiceImpl(SaleRepository saleRepository) {
        this.saleRepository = saleRepository;
    }

    @Override
    public List<DetailedCount> calculateDetailedCount() {
        return saleRepository.calculateDetailedCount();
    }

    @Override
    public List<CompanyDetailedCount> calculateCompanyDetailedCount() {
        return saleRepository.calculateDetailedCountByCompany();
    }

    @Override
    public List<Sale> fetchSalesByCompany(String companyName) {
        return saleRepository.findByCompany(companyName);
    }
}
