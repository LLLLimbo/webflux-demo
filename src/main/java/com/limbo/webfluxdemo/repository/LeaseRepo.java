package com.limbo.webfluxdemo.repository;

import com.limbo.webfluxdemo.entity.Lease;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface LeaseRepo extends CrudRepository<Lease, Long> {

    List<Lease> findAllByContractIdIn(Set<String> contractIdSet);

}
