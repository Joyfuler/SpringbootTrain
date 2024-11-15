package com.example.mapping.manytoOne;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @OneToMany(mappedBy = "teacher")
    private List<Student> students = new ArrayList<Student>();
    // List에 join 조건을 거는 경우 성능이 큰 폭으로 떨어지므로 지양할 것.

}
