package com.foodwaste.controller;

import com.foodwaste.model.FoodRecord;
import com.foodwaste.service.FoodRecordService;
import com.foodwaste.service.WasteCalculationService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/foods")
public class FoodController {
    private final FoodRecordService foodService;
    private final WasteCalculationService wasteService;

    public FoodController(FoodRecordService foodService, WasteCalculationService wasteService) {
        this.foodService = foodService;
        this.wasteService = wasteService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Map<String, Object> create(@Valid @RequestBody FoodRecord record) {
        return wasteService.calculate(foodService.create(record));
    }

    @GetMapping
    public List<Map<String, Object>> getAll() {
        return foodService.getAll().stream().map(wasteService::calculate).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public Map<String, Object> getById(@PathVariable Long id) {
        return wasteService.calculate(foodService.getById(id));
    }

    @GetMapping("/location/{location}")
    public List<Map<String, Object>> getByLocation(@PathVariable String location) {
        return foodService.getByLocation(location).stream().map(wasteService::calculate).collect(Collectors.toList());
    }

    @PutMapping("/{id}")
    public Map<String, Object> update(@PathVariable Long id, @Valid @RequestBody FoodRecord record) {
        return wasteService.calculate(foodService.update(id, record));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        foodService.delete(id);
    }
}
