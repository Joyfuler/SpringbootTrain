package com.example.mapping;

import com.example.mapping.manytoOne.Student;
import com.example.mapping.manytoOne.StudentRepository;
import com.example.mapping.manytoOne.Teacher;
import com.example.mapping.manytoOne.TeacherRepository;
import jakarta.transaction.Transactional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ManyToOneMappingApplicationTests {

    @Autowired
    private TeacherRepository teacherRepository;
    @Autowired
    private StudentRepository studentRepository;

    @Test
    void contextLoads(){
        // 선생님1명
        Teacher t1 = new Teacher();
        t1.setName("선생님1");
        teacherRepository.save(t1);
        // 선생님1명에게배우는학생2명등록

        Student s1 = new Student();
        s1.setName("학생1");
        s1.setTeacher(t1);
        t1.getStudents().add(s1);
        studentRepository.save(s1);

        Student s2 = new Student();
        s2.setName("학생2");
        s2.setTeacher(t1);
        t1.getStudents().add(s2);
        studentRepository.save(s2);

        //Assertions.assertThat(studentRepository.findById(1L).get().getName()).isEqualTo("학생1");
        //Assertions.assertThat(studentRepository.findById(1L).get().getTeacher().getName()).isEqualTo("선생님1");
    }
}
