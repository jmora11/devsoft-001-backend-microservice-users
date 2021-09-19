package com.P001SpringBoot.back.usersmicroservices.services;

import com.P001SpringBoot.back.models.entity.Student;
import com.P001SpringBoot.back.service.CommonService;
import com.P001SpringBoot.back.usersmicroservices.client.CursoFeingClient;
import com.P001SpringBoot.back.usersmicroservices.models.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class StudentService extends CommonService<Student, StudentRepository>implements IStudentService {

    @Autowired
    private CursoFeingClient cursoFeingClient;

    @Override
    @Transactional(readOnly = true)
    public List<Student> findByNameOrLastName(String term) {
        return repository.findByNameOrLastName(term);
    }

    @Override
    @Transactional(readOnly = true)
    public Iterable<Student> findAllById(Iterable<Long> ids) {
        return repository.findAllById(ids);
    }

    @Override
    public void eliminarCursoIdPorAlumno(Long id) {
        cursoFeingClient.eliminarCursoIdPorAlumno(id);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        super.deleteById(id);
        this.eliminarCursoIdPorAlumno(id);
    }
}
