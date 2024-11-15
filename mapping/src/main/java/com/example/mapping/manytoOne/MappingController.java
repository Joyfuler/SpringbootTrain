package com.example.mapping.manytoOne;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MappingController {

    @Autowired
    StudentService studentService;

    @GetMapping("/{id}")
    public TeacherDto getTeacher(@PathVariable("id") Long id) throws ChangeSetPersister.NotFoundException {
        TeacherDto dto = null;
        dto = studentService.getTeacherAndStudents(id);
        return dto;
    }
}
