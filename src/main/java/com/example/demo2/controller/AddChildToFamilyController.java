package com.example.demo2.controller;

import com.example.demo2.entity.Child;
import com.example.demo2.entity.projection.FamilyProjection;
import com.example.demo2.service.AddChildToFamilyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;

@Controller
public class AddChildToFamilyController {

    private final AddChildToFamilyService addChildToFamilyService;

    @Autowired
    public AddChildToFamilyController(AddChildToFamilyService addChildToFamilyService) {
        this.addChildToFamilyService = addChildToFamilyService;
    }

    @RequestMapping(value = "/addChildToFamily", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<FamilyProjection> addChildToFamily(@Valid @RequestBody Child child) {

        return ResponseEntity.ok(addChildToFamilyService.addChildToFamily(child));
    }
}
