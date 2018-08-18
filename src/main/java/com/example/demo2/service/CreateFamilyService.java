package com.example.demo2.service;

import com.example.demo2.entity.Family;
import com.example.demo2.repository.FamilyRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateFamilyService {

    private final FamilyRepository familyRepository;

    @Autowired
    public CreateFamilyService(FamilyRepository familyRepository) {
        this.familyRepository = familyRepository;
    }

    @NotNull
    public Family createFamily() {
        Family family = new Family();
        familyRepository.saveAndFlush(family);
        return family;
    }
}
