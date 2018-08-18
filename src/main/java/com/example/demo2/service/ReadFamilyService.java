package com.example.demo2.service;

import com.example.demo2.entity.Family;
import com.example.demo2.entity.projection.FamilyProjection;
import com.example.demo2.repository.FamilyRepository;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.projection.ProjectionFactory;
import org.springframework.stereotype.Service;

@Service
public class ReadFamilyService {

    private final FamilyRepository familyRepository;
    private final ProjectionFactory projectionFactory;

    @Autowired
    public ReadFamilyService(FamilyRepository familyRepository,
                             ProjectionFactory projectionFactory) {
        this.familyRepository = familyRepository;
        this.projectionFactory = projectionFactory;
    }

    @Nullable
    public FamilyProjection readFamily(@NotNull Integer id) {

        Family family = familyRepository.findOne(id);
        if (family == null) {
            return null;
        }

        return projectionFactory
                .createProjection(FamilyProjection.class, family);
    }
}
