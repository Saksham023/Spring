package com.example.rest1;

public record StudentDto(
        String firstName,
        String lastName,
        String email,
        Integer age,
        Integer schoolId
) {
}
