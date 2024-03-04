package com.example.rest1;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class SchoolController {

    private SchoolRepository schoolRepository;

    public SchoolController(SchoolRepository schoolRepository) {
        this.schoolRepository = schoolRepository;
    }

    @PostMapping("/schools")
    public SchoolDto create(@RequestBody SchoolDto dto){
        School school = toSchool(dto);
        return toDto(schoolRepository.save(school));
    }

    private SchoolDto toDto(School school) {
        return new SchoolDto(school.getName());
    }

    private School toSchool(SchoolDto dto) {
        School school = new School();
        school.setName(dto.name());
        return school;
    }

    @GetMapping("/schools")
    public List<SchoolDto> findAll(){
        return schoolRepository.findAll()
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }
}
