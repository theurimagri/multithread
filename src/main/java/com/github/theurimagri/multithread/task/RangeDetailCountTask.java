package com.github.theurimagri.multithread.task;

import com.github.theurimagri.multithread.entity.Sale;
import com.github.theurimagri.multithread.model.DetailedCount;
import com.github.theurimagri.multithread.model.RangeDetailCount;
import com.github.theurimagri.multithread.service.SaleService;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Phaser;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.summingLong;
import static java.util.stream.Collectors.toList;

public class RangeDetailCountTask implements Callable<RangeDetailCount> {

    private final String companyName;
    private final SaleService saleService;
    private final CountDownLatch latch;

    public RangeDetailCountTask(String companyName, SaleService saleService, CountDownLatch latch) {
        this.companyName = companyName;
        this.saleService = saleService;
        this.latch = latch;
    }

    @Override
    public RangeDetailCount call() throws Exception {
        List<Sale> sales = saleService.fetchSalesByCompany(companyName);
        RangeDetailCount result = process(companyName, sales);
        latch.countDown();
        return result;
    }

    private RangeDetailCount process(String companyName, List<Sale> sales) {
        List<DetailedCount> detailedCounts = sales
                .parallelStream()
                .collect(Collectors.groupingBy(Sale::getProduct, summingLong(Sale::getQuantity)))
                .entrySet()
                .parallelStream()
                .map(entry -> new DetailedCount(entry.getKey(), entry.getValue()))
                .collect(toList());

        return new RangeDetailCount(companyName, detailedCounts);
    }
}
