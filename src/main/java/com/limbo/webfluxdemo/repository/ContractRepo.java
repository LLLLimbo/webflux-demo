package com.limbo.webfluxdemo.repository;

import com.limbo.webfluxdemo.entity.Contract;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContractRepo extends CrudRepository<Contract,Long> {

    List<Contract> findAllByHouseSpaceIdAndContractStatusIsNot(String houseSpaceId, String contractStatus);
}
