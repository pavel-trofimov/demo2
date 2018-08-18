package com.example.demo2.controller;

import com.example.demo2.entity.Father;
import com.example.demo2.entity.projection.FamilyProjection;
import com.example.demo2.service.AddFatherToFamilyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;

@Controller
public class AddFatherToFamilyController {

    private final AddFatherToFamilyService addFatherToFamilyService;

    @Autowired
    public AddFatherToFamilyController(AddFatherToFamilyService addFatherToFamilyService) {
        this.addFatherToFamilyService = addFatherToFamilyService;
    }

    @RequestMapping(value = "/addFatherToFamily", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<FamilyProjection> addFatherToFamily(@Valid @RequestBody Father father) {

        return ResponseEntity.ok(addFatherToFamilyService.addFatherToFamily(father));
    }
}
