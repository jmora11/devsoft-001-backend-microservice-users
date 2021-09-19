package com.P001SpringBoot.back.usersmicroservices.services;

import com.P001SpringBoot.back.models.entity.Student;
import com.P001SpringBoot.back.service.ICommonService;

import java.util.List;

public interface IStudentService extends ICommonService<Student> {

    public List<Student> findByNameOrLastName(String term);

    Iterable<Student> findAllById(Iterable<Long> ids);

    public void eliminarCursoIdPorAlumno(Long id);
}
