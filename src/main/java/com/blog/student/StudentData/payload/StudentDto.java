package com.blog.student.StudentData.payload;

import com.blog.student.StudentData.entity.Course;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudentDto {
    private long id;

    @NotEmpty
    @Pattern(regexp = "[^&%$#@!~]*", message = "Special Characters are not allowed")
    private String name;
    @Pattern(regexp = "[^&%$#@!~]*", message = "Special Characters are not allowed")
    private String dob;
    @Pattern(regexp = "[^&%$#@!~]*", message = "Special Characters are not allowed")
    private String branch;

    @Valid
    private List<CourseDto> courses;
}
