package com.springboot.fp_ml_web.data.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Service_industry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int serviceIndustry_id;
    private String service_industry_category;
    private String service_industry_name;
}
