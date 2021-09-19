package com.P001SpringBoot.back.usersmicroservices.controllers;

import com.P001SpringBoot.back.controllers.CommonController;
import com.P001SpringBoot.back.models.entity.Student;
import com.P001SpringBoot.back.usersmicroservices.services.StudentService;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
public class StudentController extends CommonController<Student, StudentService> {

    @GetMapping("/students-by-curse")
    public ResponseEntity<?> getStudentsByCurse(@RequestParam List<Long> ids) {
        return ResponseEntity.ok(service.findAllById(ids));
    }

    @GetMapping("/uploads/img/{id}")
    public ResponseEntity<?> seePhoto(@PathVariable Long id) {
        Optional<Student> optionalStudent = service.findById(id);

        if(optionalStudent.isEmpty() || optionalStudent.get().getFoto() == null) {
            return ResponseEntity.notFound().build();
        }

        Resource imagen = new ByteArrayResource(optionalStudent.get().getFoto());

        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(imagen);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> editStudent(@Valid @RequestBody Student student, @PathVariable Long id, BindingResult result) {

        if (result.hasErrors()) {
            return this.validar(result);
        }

        Optional<Student> optionalStudent = service.findById(id);

        if(optionalStudent.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Student studentToUpdate = optionalStudent.get();
        studentToUpdate.setName(student.getName());
        studentToUpdate.setLastName(student.getLastName());
        studentToUpdate.setEmail(student.getEmail());

        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(studentToUpdate));
    }

    @GetMapping("filter/{term}")
    public ResponseEntity<?> filter(@PathVariable String term) {
        return ResponseEntity.ok(service.findByNameOrLastName(term));
    }

    @PostMapping("add/photo")
    public ResponseEntity<?> addStudentPhoto(@Valid Student entity, BindingResult result,
                                             @RequestParam MultipartFile archivo) throws IOException {
        if(!archivo.isEmpty()) {
            entity.setFoto(archivo.getBytes());
        }
        return super.addStudent(entity, result);
    }

    @PutMapping("/update/photo/{id}")
    public ResponseEntity<?> editStudentPhoto(@Valid Student student, @PathVariable Long id, BindingResult result,
                                              @RequestParam MultipartFile archivo) throws IOException {

        if (result.hasErrors()) {
            return this.validar(result);
        }

        Optional<Student> optionalStudent = service.findById(id);

        if(optionalStudent.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Student studentToUpdate = optionalStudent.get();
        studentToUpdate.setName(student.getName());
        studentToUpdate.setLastName(student.getLastName());
        studentToUpdate.setEmail(student.getEmail());
        if(!archivo.isEmpty()) {
            studentToUpdate.setFoto(archivo.getBytes());
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(studentToUpdate));
    }
}
