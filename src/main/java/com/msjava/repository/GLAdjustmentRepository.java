package com.msjava.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.msjava.model.GLAdjustment;

@Repository
public interface GLAdjustmentRepository extends CrudRepository<GLAdjustment, Integer>
{
}

