package com.limbo.webfluxdemo.repository;

import com.limbo.webfluxdemo.entity.HouseSpace;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface HouseSpaceRepo extends CrudRepository<HouseSpace, Long> {

    List<HouseSpace> findAllByCreateTimeAfter(Date createTime);
}
