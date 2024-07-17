package com.springboot.fp_ml_web.service;

import com.springboot.fp_ml_web.data.entity.IndustryCorrelationRanking;
import com.springboot.fp_ml_web.data.repository.IndustryCorrelationRankingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class IndustryCorrelationRankingService {

    @Autowired
    private IndustryCorrelationRankingRepository repository;

    public List<IndustryCorrelationRanking> getByServiceIndustryName(String serviceIndustryName) {
        return repository.findDistinctByServiceIndustryName(serviceIndustryName);
    }

    public List<String> getDistinctFactors(String factorName) {
        return repository.findDistinctByFactor(factorName)
                .stream()
                .map(IndustryCorrelationRanking::getFactor)
                .distinct()
                .toList();
    }

    public List<IndustryCorrelationRanking> getPositiveCorrelationCoefficients(String value) {
        return repository.findByCorrelationCoefficientGreaterThanEqual(value);
    }

    public List<IndustryCorrelationRanking> getNegativeCorrelationCoefficients(String value) {
        return repository.findByCorrelationCoefficientLessThan(value);
    }
}
