package com.example.demo2.service;

import com.example.demo2.entity.Family;
import com.example.demo2.entity.Father;
import com.example.demo2.entity.projection.FamilyProjection;
import com.example.demo2.repository.FatherRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.projection.ProjectionFactory;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;

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

    public void checkFather(
            @NotNull Father father,
            @NotNull BindingResult bindingResult) throws BindException {

        Family family = father.getFamily();
        if(family != null) {
            Father persistentFather = fatherRepository.findByFamily(family);
            if (persistentFather != null) {
                bindingResult.rejectValue(
                        "family",
                        "UniqueConstraintError",
                        "This family already has a father." );
            }
        }

        if ( bindingResult.hasErrors() ) {
            throw new BindException(bindingResult);
        }
    }
}
