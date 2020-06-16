package com.github.theurimagri.multithread.service.impl;

import com.github.theurimagri.multithread.model.DetailedCount;
import com.github.theurimagri.multithread.model.RangeCount;
import com.github.theurimagri.multithread.model.RangeDetailCount;
import com.github.theurimagri.multithread.model.SaleCounter;
import com.github.theurimagri.multithread.service.CompanyService;
import com.github.theurimagri.multithread.service.Processor;
import com.github.theurimagri.multithread.service.SaleService;
import com.github.theurimagri.multithread.task.RangeDetailCountTask;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.concurrent.*;

import static java.util.stream.Collectors.*;

@Component("countDownLatch")
public class CountDownLatchSalesProcessor implements Processor {

    private final CompanyService companyService;
    private final SaleService saleService;
    private final ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    public CountDownLatchSalesProcessor(final CompanyService companyService, final SaleService saleService) {
        this.companyService = companyService;
        this.saleService = saleService;
    }

    public SaleCounter process() {
        Set<String> companies = companyService.fetchDistinctCompanyNames();
        List<RangeDetailCount> rangeDetailCounts = processMultithread(companies);
        return new SaleCounter(companies.size(), fetchRangeCount(rangeDetailCounts), rangeDetailCounts);
    }

    private List<RangeDetailCount> processMultithread(Set<String> companies) {
        List<RangeDetailCount> rangeDetailCounts = new ArrayList<>();
        List<Future<RangeDetailCount>> futures = new ArrayList<>();

        CountDownLatch countDownLatch = new CountDownLatch(companies.size());

        for(String companyName : companies) {
            Future<RangeDetailCount> future = executor.submit(new RangeDetailCountTask(companyName, saleService, countDownLatch));
            futures.add(future);
        }

        try {
            countDownLatch.await();
            for(Future<RangeDetailCount> future : futures) {
                rangeDetailCounts.add(future.get());
            }
        } catch (InterruptedException | ExecutionException ex) {
            System.out.println(ex.getMessage());
        }

        return rangeDetailCounts;
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
}
