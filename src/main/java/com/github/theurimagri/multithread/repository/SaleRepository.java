package com.github.theurimagri.multithread.repository;

import com.github.theurimagri.multithread.entity.Sale;
import com.github.theurimagri.multithread.model.CompanyDetailedCount;
import com.github.theurimagri.multithread.model.DetailedCount;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SaleRepository extends CrudRepository<Sale, Long> {

    @Query("SELECT new com.github.theurimagri.multithread.model.DetailedCount(s.product, SUM(s.quantity)) FROM Sale s GROUP BY s.product")
    List<DetailedCount> calculateDetailedCount();

    @Query("SELECT new com.github.theurimagri.multithread.model.CompanyDetailedCount(s.company, s.product, SUM(s.quantity)) FROM Sale s GROUP BY s.company, s.product")
    List<CompanyDetailedCount> calculateDetailedCountByCompany();

    List<Sale> findByCompany(String companyName);
}
