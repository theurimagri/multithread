package com.github.theurimagri.multithread.service.impl;

import com.github.theurimagri.multithread.model.*;
import com.github.theurimagri.multithread.service.Processor;
import com.github.theurimagri.multithread.service.SaleService;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@Component("database")
public class DatabaseSalesProcessor implements Processor {

    private final SaleService saleService;

    public DatabaseSalesProcessor(final SaleService saleService) {
        this.saleService = saleService;
    }

    public SaleCounter process() {
        RangeCount rangeCount = new RangeCount(saleService.calculateDetailedCount());
        List<RangeDetailCount> rangeDetailCounts = fetchRangeDetailCounts();
        return new SaleCounter(rangeDetailCounts.size(), rangeCount, rangeDetailCounts);
    }

    private List<RangeDetailCount> fetchRangeDetailCounts() {
        List<CompanyDetailedCount> companyDetailedCounts = saleService.calculateCompanyDetailedCount();
        return convertCompanyDetailedCountToRangeDetailCount(companyDetailedCounts);
    }

    private List<RangeDetailCount> convertCompanyDetailedCountToRangeDetailCount(List<CompanyDetailedCount> companyDetailedCounts) {
        return companyDetailedCounts
                .parallelStream()
                .collect(Collectors.groupingBy(CompanyDetailedCount::getCompanyName))
                .entrySet()
                .parallelStream()
                .map(entry -> new RangeDetailCount(entry.getKey(), map(entry.getValue())))
                .collect(toList());
    }

    private List<DetailedCount> map(List<CompanyDetailedCount> companyDetailedCount) {
        return companyDetailedCount
                .parallelStream()
                .map(item -> new DetailedCount(item.getProductName(), item.getSoldCount()))
                .collect(toList());
    }
}
