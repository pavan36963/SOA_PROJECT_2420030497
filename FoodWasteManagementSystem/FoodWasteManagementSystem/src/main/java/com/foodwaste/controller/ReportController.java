package com.foodwaste.controller;

import com.foodwaste.model.FoodRecord;
import com.foodwaste.service.FoodRecordService;
import com.foodwaste.service.WasteCalculationService;
import org.springframework.web.bind.annotation.*;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/reports")
public class ReportController {
    private final FoodRecordService foodService;
    private final WasteCalculationService wasteService;

    public ReportController(FoodRecordService foodService, WasteCalculationService wasteService) {
        this.foodService = foodService;
        this.wasteService = wasteService;
    }

    @GetMapping("/summary")
    public Map<String, Object> summary() {
        List<FoodRecord> records = foodService.getAll();
        double prepared = records.stream().mapToDouble(FoodRecord::getPreparedQuantityKg).sum();
        double consumed = records.stream().mapToDouble(FoodRecord::getConsumedQuantityKg).sum();
        double wasted = Math.max(0, prepared - consumed);
        double percentage = prepared > 0 ? (wasted / prepared) * 100.0 : 0.0;

        Map<String, Object> result = new LinkedHashMap<>();
        result.put("totalRecords", records.size());
        result.put("totalPreparedKg", round(prepared));
        result.put("totalConsumedKg", round(consumed));
        result.put("totalWastedKg", round(wasted));
        result.put("overallWastePercentage", round(percentage));
        return result;
    }

    @GetMapping("/record/{id}")
    public Map<String, Object> reportForRecord(@PathVariable Long id) {
        return wasteService.calculate(foodService.getById(id));
    }

    private double round(double value) {
        return Math.round(value * 100.0) / 100.0;
    }
}
