package com.foodwaste;

import com.foodwaste.model.FoodRecord;
import com.foodwaste.service.WasteCalculationService;
import org.junit.jupiter.api.Test;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.assertEquals;

class WasteCalculationServiceTest {
    @Test
    void calculatesWasteAndPercentage() {
        FoodRecord record = new FoodRecord();
        record.setLocationName("College Canteen");
        record.setFoodName("Rice");
        record.setPreparedQuantityKg(100.0);
        record.setConsumedQuantityKg(80.0);

        Map<String, Object> result = new WasteCalculationService().calculate(record);

        assertEquals(20.0, result.get("wastedQuantityKg"));
        assertEquals(20.0, result.get("wastePercentage"));
    }
}
