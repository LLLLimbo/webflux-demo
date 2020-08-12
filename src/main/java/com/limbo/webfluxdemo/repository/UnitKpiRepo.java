package com.limbo.webfluxdemo.repository;

import com.limbo.webfluxdemo.entity.UnitKpiValue;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UnitKpiRepo extends CrudRepository<UnitKpiValue, Long> {
}
