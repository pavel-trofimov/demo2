package com.example.demo2.controller;

import com.example.demo2.entity.Child;
import com.example.demo2.service.ReadChildService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ReadChildController {

    private final ReadChildService readChildService;

    @Autowired
    public ReadChildController(ReadChildService readChildService) {
        this.readChildService = readChildService;
    }

    @RequestMapping(value = "/readChild/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Child> readChild(@PathVariable("id") Integer id) {

        Child child = readChildService.readChild(id);

        return child == null ?
                ResponseEntity.notFound().build() :
                ResponseEntity.ok(child);
    }
}
