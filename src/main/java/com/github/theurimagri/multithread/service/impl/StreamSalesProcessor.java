package com.github.theurimagri.multithread.service.impl;

import com.github.theurimagri.multithread.entity.Company;
import com.github.theurimagri.multithread.entity.Sale;
import com.github.theurimagri.multithread.model.DetailedCount;
import com.github.theurimagri.multithread.model.RangeCount;
import com.github.theurimagri.multithread.model.RangeDetailCount;
import com.github.theurimagri.multithread.model.SaleCounter;
import com.github.theurimagri.multithread.service.CompanyService;
import com.github.theurimagri.multithread.service.Processor;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

@Component("stream")
public class StreamSalesProcessor implements Processor {

    private CompanyService companyService;

    public StreamSalesProcessor(CompanyService companyService) {
        this.companyService = companyService;
    }

    public SaleCounter process() {
        Set<Company> allCompanies = companyService.findAllCompanies();
        List<RangeDetailCount> rangeDetailCounts = fetchRangeDetailCount(allCompanies);
        return new SaleCounter(allCompanies.size(), fetchRangeCount(rangeDetailCounts), rangeDetailCounts);
    }

    private RangeCount fetchRangeCount(List<RangeDetailCount> rangeDetailCounts) {
        List<DetailedCount> detailedCounts = rangeDetailCounts
                .parallelStream()
                .map(RangeDetailCount::getDetailedCount)
                .flatMap(Collection::stream)
                .collect(groupingBy(DetailedCount::getProductName, summingLong(DetailedCount::getSoldCount)))
                .entrySet()
                .parallelStream()
                .map(entry -> new DetailedCount(entry.getKey(), entry.getValue()))
                .collect(toList());

        return new RangeCount(detailedCounts);
    }

    private List<RangeDetailCount> fetchRangeDetailCount(Set<Company> allCompanies) {
        return allCompanies.stream().map(this::rangeDetailCount).collect(toList());
    }

    private RangeDetailCount rangeDetailCount(Company company) {
        List<DetailedCount> detailedCounts = company.getSales()
                .parallelStream()
                .collect(Collectors.groupingBy(Sale::getProduct, summingLong(Sale::getQuantity)))
                .entrySet()
                .parallelStream()
                .map(entry -> new DetailedCount(entry.getKey(), entry.getValue()))
                .collect(toList());

        return new RangeDetailCount(company.getName(), detailedCounts);
    }
}
