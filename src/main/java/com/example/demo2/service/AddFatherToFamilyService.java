package com.example.demo2.service;

import com.example.demo2.entity.Family;
import com.example.demo2.entity.Father;
import com.example.demo2.entity.projection.FamilyProjection;
import com.example.demo2.repository.FatherRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.projection.ProjectionFactory;
import org.springframework.stereotype.Service;

@Service
public class AddFatherToFamilyService {

    private final FatherRepository fatherRepository;
    private final ProjectionFactory projectionFactory;

    @Autowired
    public AddFatherToFamilyService(
            FatherRepository fatherRepository,
            ProjectionFactory projectionFactory) {
        this.fatherRepository = fatherRepository;
        this.projectionFactory = projectionFactory;
    }

    @NotNull
    public FamilyProjection addFatherToFamily(@NotNull Father father) {

        fatherRepository.saveAndFlush(father);

        Family family = father.getFamily();
        return projectionFactory
                .createProjection(FamilyProjection.class, family);
    }
}
