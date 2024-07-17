package com.springboot.fp_ml_web.controller;

import com.springboot.fp_ml_web.data.entity.District_category;
import com.springboot.fp_ml_web.data.entity.Service_industry;
import com.springboot.fp_ml_web.data.entity.UserSelection;
import com.springboot.fp_ml_web.service.SelectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class SelectionController {
    @Autowired
    private SelectionService selectionService;

    @GetMapping("/districts")
    public List<District_category> getDistricts() {
        return selectionService.getAllDistricts();
    }

    @GetMapping("/service-industries")
    public List<Service_industry> getServiceIndustries() {
        return selectionService.getAllServiceIndustries();
    }

    @PostMapping("/selections")
    public void saveUserSelection(@RequestBody UserSelection userSelection) {
        selectionService.saveUserSelection(userSelection);
    }

}
