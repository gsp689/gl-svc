package com.msjava.repository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.msjava.model.Student;
@Repository
public interface StudentRepository extends CrudRepository<Student, Integer>
{
}
