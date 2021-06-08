package com.msjava.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.msjava.model.GLDelta;

@Repository
public interface GLDeltaRepository extends CrudRepository<GLDelta, Integer>
{
}

