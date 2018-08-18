package com.example.demo2.controller;

import com.example.demo2.entity.projection.FamilyProjection;
import com.example.demo2.service.ReadFamilyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ReadFamilyController {

    private final ReadFamilyService readFamilyService;

    @Autowired
    public ReadFamilyController(ReadFamilyService readFamilyService) {
        this.readFamilyService = readFamilyService;
    }

    @RequestMapping(value = "/readFamily/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<FamilyProjection> readFamily(@PathVariable("id") Integer id) {

        FamilyProjection family = readFamilyService.readFamily(id);

        return family == null ?
                ResponseEntity.notFound().build() :
                ResponseEntity.ok(family);
    }
}
