package com.limbo.webfluxdemo.repository;

import com.limbo.webfluxdemo.entity.EmptySituationSchemaFact;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FactRepo extends CrudRepository<EmptySituationSchemaFact,Long> {

    EmptySituationSchemaFact findByDimIdAndLoggedYear(Long dimId,Integer loggedYear);
}
