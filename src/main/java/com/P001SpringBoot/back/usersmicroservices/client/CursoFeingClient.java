package com.P001SpringBoot.back.usersmicroservices.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "microservicio-cursos")
public interface CursoFeingClient {

    @DeleteMapping("/delete-student/{id}")
    public void eliminarCursoIdPorAlumno(@PathVariable Long id);
}
