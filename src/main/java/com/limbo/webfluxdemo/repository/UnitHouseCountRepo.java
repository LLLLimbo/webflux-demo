package com.limbo.webfluxdemo.repository;

import com.limbo.webfluxdemo.entity.UnitHouseCount;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
@Repository
public interface UnitHouseCountRepo extends CrudRepository<UnitHouseCount, Long> {

    List<UnitHouseCount> queryAllByUnitIdAndStartDateGreaterThanEqual(String unitId, Date startDate);

}
