package com.foodwaste.service;

import com.foodwaste.model.FoodRecord;
import org.springframework.stereotype.Service;
import java.util.LinkedHashMap;
import java.util.Map;

@Service
public class WasteCalculationService {
    public Map<String, Object> calculate(FoodRecord record) {
        double prepared = record.getPreparedQuantityKg();
        double consumed = record.getConsumedQuantityKg();
        double wasted = Math.max(0, prepared - consumed);
        double percentage = prepared > 0 ? (wasted / prepared) * 100.0 : 0.0;

        Map<String, Object> result = new LinkedHashMap<>();
        result.put("recordId", record.getId());
        result.put("locationName", record.getLocationName());
        result.put("foodName", record.getFoodName());
        result.put("preparedQuantityKg", round(prepared));
        result.put("consumedQuantityKg", round(consumed));
        result.put("wastedQuantityKg", round(wasted));
        result.put("wastePercentage", round(percentage));
        result.put("recordDate", record.getRecordDate());
        return result;
    }

    private double round(double value) {
        return Math.round(value * 100.0) / 100.0;
    }
}
