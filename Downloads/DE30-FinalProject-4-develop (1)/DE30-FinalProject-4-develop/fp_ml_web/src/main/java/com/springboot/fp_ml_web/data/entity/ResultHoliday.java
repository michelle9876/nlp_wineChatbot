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
public class ResultHoliday {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private int resultHoliday_id;
    private int selectionForHoliday_id;
    private String holiday_graph;
}
