package com.blog.student.StudentData.payload;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CourseDto {
    private long id;
    @Pattern(regexp = "[^&%$#@!~]*", message = "Special Characters are not allowed")
    private String name;
    @Pattern(regexp = "[^&%$#@!~]*", message = "Special Characters are not allowed")
    private String institute;
//    @Pattern(regexp = "[^&%$#@!~]*", message = "Special Characters are not allowed")

    @Valid
    private List<TopicDto> topics;
}
