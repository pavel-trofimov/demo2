package com.example.demo2.service;

import com.example.demo2.entity.Father;
import com.example.demo2.repository.FatherRepository;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReadFatherService {

    private final FatherRepository fatherRepository;

    @Autowired
    public ReadFatherService(FatherRepository fatherRepository) {
        this.fatherRepository = fatherRepository;
    }

    @Nullable
    public Father readFather(@NotNull Integer id) {
        Father father = fatherRepository.findOne(id);
        if (father == null) {
            return null;
        }

        return father;
    }
}
