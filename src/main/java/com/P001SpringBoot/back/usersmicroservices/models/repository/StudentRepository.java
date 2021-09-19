package com.P001SpringBoot.back.usersmicroservices.models.repository;

import com.P001SpringBoot.back.models.entity.Student;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface StudentRepository extends PagingAndSortingRepository<Student, Long> {

    @Query("select a from Student a where upper(a.name) like upper(concat('%', ?1, '%')) or upper(a.lastName) like upper(concat('%', ?1, '%'))")
    public List<Student> findByNameOrLastName(String term);

}
