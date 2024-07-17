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
public class ShowingMap {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int showingMap_id;
    private int selectionForMap_id;
    private int ranking_num;
    private String service_industry_name;
    private String district_name;

}
