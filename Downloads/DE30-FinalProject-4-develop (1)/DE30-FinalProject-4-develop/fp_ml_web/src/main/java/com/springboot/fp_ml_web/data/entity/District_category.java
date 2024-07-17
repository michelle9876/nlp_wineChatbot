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
public class District_category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int districtCategory_id;
    private String district_name;
    private String administrative_dong_name;
    private String business_district_name;
}
