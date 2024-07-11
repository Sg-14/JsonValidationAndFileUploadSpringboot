package com.blog.student.StudentData.repository;

import com.blog.student.StudentData.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Long> {
    Image findByEncodedName(String name);
}
