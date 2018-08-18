package com.example.demo2.service;

import com.example.demo2.entity.Child;
import com.example.demo2.repository.ChildRepository;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReadChildService {

    private final ChildRepository childRepository;

    @Autowired
    public ReadChildService(ChildRepository childRepository) {
        this.childRepository = childRepository;
    }

    @Nullable
    public Child readChild(@NotNull Integer id) {
        Child child = childRepository.findOne(id);
        if (child == null) {
            return null;
        }

        return child;
    }
}
