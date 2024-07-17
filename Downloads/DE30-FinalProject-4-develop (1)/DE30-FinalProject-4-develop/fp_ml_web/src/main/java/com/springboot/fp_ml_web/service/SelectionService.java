package com.springboot.fp_ml_web.service;

import com.springboot.fp_ml_web.data.entity.District_category;
import com.springboot.fp_ml_web.data.entity.Service_industry;
import com.springboot.fp_ml_web.data.entity.UserSelection;
import com.springboot.fp_ml_web.data.repository.DistrictCategoryRepository;
import com.springboot.fp_ml_web.data.repository.ServiceIndustryRepository;
import com.springboot.fp_ml_web.data.repository.UserSelectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SelectionService {
    @Autowired
    private DistrictCategoryRepository districtCategoryRepository;

    @Autowired
    private ServiceIndustryRepository serviceIndustryRepository;

    @Autowired
    private UserSelectionRepository userSelectionRepository;

    public List<District_category> getAllDistricts() {
        return districtCategoryRepository.findAll();
    }

    public List<Service_industry> getAllServiceIndustries() {
        return serviceIndustryRepository.findAll();
    }

    public void saveUserSelection(UserSelection userSelection) {
        userSelectionRepository.save(userSelection);
    }
}
