package com.example.rest1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentController {

    private StudentRepository studentRepository;
    @Autowired
    private SchoolRepository schoolRepository;

    public StudentController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    private Student toStudent(StudentDto dto){
        Student student = new Student();
        student.setFirstName(dto.firstName());
        student.setLastName(dto.lastName());
        student.setEmail(dto.email());
        student.setAge(dto.age());

        School school = schoolRepository.findById(dto.schoolId()).orElse(null);
//        System.out.println(school.getName());
//        List<Student> list = school.getStudents();
//        for(Student s : list){
//            System.out.println(s.getFirstName());
//        }
        student.setSchool(school);

        return student;
    }

    private StudentResponseDto toStudentResponseDto(Student student){
        return new StudentResponseDto(student.getFirstName(), student.getLastName(), student.getEmail());
    }

    @PostMapping("/students")
    public StudentResponseDto post(@RequestBody StudentDto dto){
        StudentResponseDto d = toStudentResponseDto(studentRepository.save(toStudent(dto)));

//        School school = schoolRepository.findById(dto.schoolId()).orElse(null);
//        System.out.println(school.getName());
//        List<Student> list = school.getStudents();
//        for(Student s : list){
//            System.out.println(s.getFirstName());
//        }

        return d;
    }

    @GetMapping("/students/{student-id}")
    public Student getById(@PathVariable("student-id") Integer id){
        return studentRepository.findById(id)
                .orElse(null);
    }

    @GetMapping("/students/search/{student-name}")
    public List<Student> getByName(@PathVariable("student-name") String name){
        return studentRepository.findAllByFirstNameContaining(name);
    }

    @GetMapping("/students")
    public List<Student> getAll(){
        return studentRepository.findAll();
    }

    @DeleteMapping("/students/{student-id}")
    public void delete(@PathVariable("student-id") int id){
        studentRepository.deleteById(id);
    }


    //    @GetMapping("/home/{name}")
//    public String func(@PathVariable String name){
//        return "huppp!!!!!!!!!!!! " + name;
//    }

    @GetMapping("/home")
    public String func(@RequestParam String name){
        return "huppp!!!!!!!!!!!! " + name;
    }
}
