package com.example.mapping.manytoOne;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudentDto {
    private Long id;
    private String name;

    public StudentDto(Student student){
        this.id = student.getId();
        this.name = student.getName();
    }

}
