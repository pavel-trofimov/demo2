package com.example.demo2.controller;

import com.example.demo2.entity.Child;
import com.example.demo2.service.SearchChildService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDate;
import java.util.List;

@Controller
public class SearchChildController {

    private final SearchChildService searchChildService;

    @Autowired
    public SearchChildController(SearchChildService searchChildService) {
        this.searchChildService = searchChildService;
    }

    @RequestMapping(value = "/searchChild", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<Child>> searchChild(
            @RequestParam(value = "firstName", required = false) String firstName,
            @RequestParam(value = "secondName", required = false) String secondName,
            @RequestParam(value = "sex", required = false) Child.Sex sex,
            @RequestParam(value = "pesel", required = false) String pesel,
            @RequestParam(value = "birthDateFrom", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate birthDateFrom,
            @RequestParam(value = "birthDateTo", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate birthDateTo ) {

         List<Child> childList = searchChildService.searchChild(
                firstName,
                secondName,
                sex,
                pesel,
                birthDateFrom,
                birthDateTo);

        return ResponseEntity.ok(childList);
    }
}
