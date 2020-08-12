package com.limbo.webfluxdemo.repository;

import com.limbo.webfluxdemo.entity.Company;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepo extends CrudRepository<Company,Long> {

    Company findById(String id);
}
