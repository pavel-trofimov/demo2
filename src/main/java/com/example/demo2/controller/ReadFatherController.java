package com.example.demo2.controller;

import com.example.demo2.entity.Father;
import com.example.demo2.service.ReadFatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ReadFatherController {

    private final ReadFatherService readFatherService;

    @Autowired
    public ReadFatherController(ReadFatherService readFatherService) {
        this.readFatherService = readFatherService;
    }

    @RequestMapping(value = "/readFather/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Father> readFather(@PathVariable("id") Integer id) {

        Father father = readFatherService.readFather(id);

        return father == null ?
                ResponseEntity.notFound().build() :
                ResponseEntity.ok(father);
    }
}
