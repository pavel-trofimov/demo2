package com.example.demo2.service;

import com.example.demo2.entity.Child;
import com.example.demo2.repository.ChildRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class SearchChildService {

    private final ChildRepository childRepository;

    @Autowired
    public SearchChildService(ChildRepository childRepository) {
        this.childRepository = childRepository;
    }

    public List<Child> searchChild(
            String firstName,
            String secondName,
            Child.Sex sex,
            String pesel,
            LocalDate birthDateFrom,
            LocalDate birthDateTo) {

        return childRepository.filter(
                firstName,
                secondName,
                sex,
                pesel,
                birthDateFrom,
                birthDateTo
        );
    }
}
