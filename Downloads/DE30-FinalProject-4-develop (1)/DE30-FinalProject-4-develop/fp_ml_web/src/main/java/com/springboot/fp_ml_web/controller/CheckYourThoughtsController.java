package com.springboot.fp_ml_web.controller;

import com.springboot.fp_ml_web.data.entity.CheckingThought;
import com.springboot.fp_ml_web.data.entity.IndustryCorrelationRanking;
import com.springboot.fp_ml_web.service.CheckingThoughtService;
import com.springboot.fp_ml_web.service.IndustryCorrelationRankingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class CheckYourThoughtsController {

    @Autowired
    private IndustryCorrelationRankingService industryCorrelationRankingService;

    @Autowired
    private CheckingThoughtService checkingThoughtService;

    @GetMapping("/industries")
    public List<IndustryCorrelationRanking> getIndustries(@RequestParam String serviceIndustryName) {
        System.out.println(serviceIndustryName);
        return industryCorrelationRankingService.getByServiceIndustryName(serviceIndustryName);
    }

    @GetMapping("/factors")
    public List<String> getFactors(@RequestParam String factorName) {
        return industryCorrelationRankingService.getDistinctFactors(factorName);
    }

    @PostMapping("/check-your-thoughts")
    public ResponseEntity<String> checkYourThoughts(@RequestBody Map<String, String> params) {
        String industry = params.get("industry");
        String factor = params.get("factor");
        String condition = params.get("condition");

        CheckingThought checkingThought = new CheckingThought();
        checkingThought.setUserId(1); // 사용자의 아이디를 설정해야 합니다. 예시로 1로 설정했습니다.
        checkingThought.setService_industry_name(industry); // 인자를 제공해야 합니다.
        checkingThought.setReasons_columns(factor);
        checkingThought.setUp_and_down(condition);

        checkingThoughtService.saveCheckingThought(checkingThought);

        List<IndustryCorrelationRanking> results;
        if ("높으면".equals(condition)) {
            results = industryCorrelationRankingService.getPositiveCorrelationCoefficients("0");
        } else {
            results = industryCorrelationRankingService.getNegativeCorrelationCoefficients("0");
        }

        return ResponseEntity.ok("결과 데이터: " + results.size());
    }
}
