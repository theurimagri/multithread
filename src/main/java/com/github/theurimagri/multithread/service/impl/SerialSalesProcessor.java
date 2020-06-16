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

import java.util.*;
import java.util.Map.Entry;

@Component("serial")
public class SerialSalesProcessor implements Processor {

    private CompanyService companyService;

    public SerialSalesProcessor(CompanyService companyService) {
        this.companyService = companyService;
    }

    public SaleCounter process() {
        Set<Company> allCompanies = companyService.findAllCompanies();
        List<RangeDetailCount> rangeDetailCounts = fetchRangeDetailCount(allCompanies);
        return new SaleCounter(allCompanies.size(), fetchRangeCount(rangeDetailCounts), rangeDetailCounts);
    }

    private RangeCount fetchRangeCount(List<RangeDetailCount> rangeDetailCounts) {
        List<DetailedCount> detailedCounts = new ArrayList<>();
        Map<String, Long> totalSalesByProduct = new HashMap<>();

        for(RangeDetailCount rangeDetailCount : rangeDetailCounts) {
            for(DetailedCount detailedCount : rangeDetailCount.getDetailedCount()) {
                String productName = detailedCount.getProductName();
                if(totalSalesByProduct.containsKey(productName)) {
                    totalSalesByProduct.put(productName, totalSalesByProduct.get(productName) + detailedCount.getSoldCount());
                } else {
                    totalSalesByProduct.put(productName, detailedCount.getSoldCount());
                }
            }
        }

        for(Entry<String, Long> saleByProduct : totalSalesByProduct.entrySet()) {
            detailedCounts.add(new DetailedCount(saleByProduct.getKey(), saleByProduct.getValue()));
        }

        return new RangeCount(detailedCounts);
    }

    private List<RangeDetailCount> fetchRangeDetailCount(Set<Company> allCompanies) {
        List<RangeDetailCount> rangeDetailCounts = new ArrayList<>();

        for(Company company : allCompanies) {
            Map<String, Long> totalSalesByProduct = new HashMap<>();
            Set<Sale> sales = company.getSales();
            for(Sale sale : sales) {
                String product = sale.getProduct();
                if(totalSalesByProduct.containsKey(product)) {
                    totalSalesByProduct.put(product, totalSalesByProduct.get(product) + sale.getQuantity());
                } else {
                    totalSalesByProduct.put(product, sale.getQuantity());
                }
            }

            List<DetailedCount> detailedCounts = new ArrayList<>();

            for(Entry<String, Long> saleByProduct : totalSalesByProduct.entrySet()) {
                detailedCounts.add(new DetailedCount(saleByProduct.getKey(), saleByProduct.getValue()));
            }

            rangeDetailCounts.add(new RangeDetailCount(company.getName(), detailedCounts));
        }
        return rangeDetailCounts;
    }
}
