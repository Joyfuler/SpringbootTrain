package com.example.mapping.manytoOne;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;


@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    // 선생님 별로 학생 리스트를 받고 싶다면?
    public List<Student> findByTeacher(Teacher teacher);

}
