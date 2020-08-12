package com.limbo.webfluxdemo.repository;

import com.limbo.webfluxdemo.entity.EmptySituationSchemaDim;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface DimRepo extends CrudRepository<EmptySituationSchemaDim, Long> {

    EmptySituationSchemaDim findByHouseSpaceId(String houseSpaceId);

    List<EmptySituationSchemaDim> findAllByHouseCreateTimeAfter(Date time);

    List<EmptySituationSchemaDim> findAllByCurStatusIsNot(String status);
}
