package com.example.demo2.controller;

import com.example.demo2.entity.Family;
import com.example.demo2.service.CreateFamilyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class CreateFamilyController {

    private final CreateFamilyService familyManager;

    @Autowired
    public CreateFamilyController(CreateFamilyService familyManager) {
        this.familyManager = familyManager;
    }

    @RequestMapping(value = "/createFamily", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Family> createFamily() {
        return ResponseEntity.ok(familyManager.createFamily());
    }
}
