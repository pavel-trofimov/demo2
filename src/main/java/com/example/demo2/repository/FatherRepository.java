package com.example.demo2.repository;

import com.example.demo2.entity.Family;
import com.example.demo2.entity.Father;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FatherRepository extends JpaRepository<Father, Integer> {

    Father findByFamily(Family family);
}

