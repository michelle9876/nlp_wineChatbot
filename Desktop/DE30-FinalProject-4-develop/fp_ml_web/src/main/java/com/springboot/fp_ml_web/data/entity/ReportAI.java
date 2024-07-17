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
public class ReportAI {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int reportAI_id;
    private int ranking_id;
    private int predict_sales_amount_month;
    private String rent_fee_actual;

}
