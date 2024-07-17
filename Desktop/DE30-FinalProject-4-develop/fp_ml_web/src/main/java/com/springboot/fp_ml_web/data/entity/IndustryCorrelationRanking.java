package com.springboot.fp_ml_web.data.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class IndustryCorrelationRanking {

    @Id
    @Column(name = "service_industry_code")
    private String serviceIndustryCode;

    @Column(name = "service_industry_name")
    private String serviceIndustryName;

    @Column(name = "factor")
    private String factor;

    @Column(name = "correlation_coefficient")
    private String correlationCoefficient;

    @Column(name = "rank")
    private int rank;
}
