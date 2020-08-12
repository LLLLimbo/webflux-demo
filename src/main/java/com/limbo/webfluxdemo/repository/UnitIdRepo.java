package com.limbo.webfluxdemo.repository;

import com.limbo.webfluxdemo.entity.UnitHouseCount;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UnitIdRepo extends CrudRepository<UnitHouseCount, Long> {

    @Query("select u from UnitHouseCount u where u.startDate>='2019-01-01' group by u.unitId")
    List<UnitHouseCount> queryUnitIds();

}
