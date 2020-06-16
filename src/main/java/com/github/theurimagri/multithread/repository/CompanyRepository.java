package com.github.theurimagri.multithread.repository;

import com.github.theurimagri.multithread.entity.Company;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends CrudRepository<Company, Long> {
}
