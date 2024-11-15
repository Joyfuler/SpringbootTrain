package com.example.mapping.manytoOne;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private StudentRepository studentRepository;

    private List<Student> getStudentByTeacher(Teacher teacher){
        return studentRepository.findByTeacher(teacher);
    }

    public TeacherDto getTeacherAndStudents(Long id) throws ChangeSetPersister.NotFoundException {
        Teacher teacher = teacherRepository.findById(id).orElseThrow(ChangeSetPersister.NotFoundException::new);
        List<StudentDto> studentDtos = getStudentByTeacher(teacher)
                .stream()
                .map(s -> new StudentDto(s))
                .toList();

        return new TeacherDto(teacher, studentDtos);
    }
}
