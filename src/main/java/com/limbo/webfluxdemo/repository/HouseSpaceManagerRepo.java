package com.limbo.webfluxdemo.repository;

import com.limbo.webfluxdemo.entity.HouseSpaceManager;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HouseSpaceManagerRepo extends CrudRepository<HouseSpaceManager,Long> {

    HouseSpaceManager findById(String id);
}
