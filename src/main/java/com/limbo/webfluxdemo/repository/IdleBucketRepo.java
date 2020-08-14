package com.limbo.webfluxdemo.repository;

import com.limbo.webfluxdemo.entity.IdleBucket;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IdleBucketRepo extends CrudRepository<IdleBucket,Long> {
}
