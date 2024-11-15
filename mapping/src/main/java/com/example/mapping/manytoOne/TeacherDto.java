package com.example.mapping.manytoOne;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Setter
@Getter
@ToString
public class TeacherDto {

    private Long id;
    private String name;
    private List<StudentDto> students;

    public TeacherDto(Teacher teacher, List<StudentDto> students){
        this.id = teacher.getId();
        this.name = teacher.getName();
        this.students = students;
    }
}
