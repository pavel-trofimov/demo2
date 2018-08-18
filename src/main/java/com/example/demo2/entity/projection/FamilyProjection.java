package com.example.demo2.entity.projection;

import com.example.demo2.entity.Child;
import com.example.demo2.entity.Family;
import com.example.demo2.entity.Father;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.config.Projection;

import java.util.List;

@Projection(name = "family-exp", types = {Family.class})
public interface FamilyProjection {

    Integer getId();

    @Value("#{@fatherRepository.findByFamily(target)}")
    Father getFather();

    @Value("#{@childRepository.findByFamily(target)}")
    List<Child> getChildren();

}
