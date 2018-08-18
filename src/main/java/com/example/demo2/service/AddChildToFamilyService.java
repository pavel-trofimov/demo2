package com.example.demo2.service;

import com.example.demo2.entity.Family;
import com.example.demo2.entity.Child;
import com.example.demo2.entity.projection.FamilyProjection;
import com.example.demo2.repository.ChildRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.projection.ProjectionFactory;
import org.springframework.stereotype.Service;

@Service
public class AddChildToFamilyService {

    private final ChildRepository childRepository;
    private final ProjectionFactory projectionFactory;

    @Autowired
    public AddChildToFamilyService(
            ChildRepository childRepository,
            ProjectionFactory projectionFactory) {
        this.childRepository = childRepository;
        this.projectionFactory = projectionFactory;
    }

    @NotNull
    public FamilyProjection addChildToFamily(@NotNull Child child) {

        childRepository.saveAndFlush(child);

        Family family = child.getFamily();
        return projectionFactory
                .createProjection(FamilyProjection.class, family);
    }
}
