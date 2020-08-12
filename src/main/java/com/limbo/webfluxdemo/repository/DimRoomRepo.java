package com.limbo.webfluxdemo.repository;

import com.limbo.webfluxdemo.entity.DimRoom;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DimRoomRepo extends CrudRepository<DimRoom,Long> {

    DimRoom findFirstByZoneId(String zoneId);
}
