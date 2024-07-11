package com.blog.student.StudentData.payload;


import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TopicDto {

    @Pattern(regexp = "[^&%$#@!~]*", message = "Special Characters are not allowed")
    private String topic;
}
