package com.foodwaste.service;

import com.foodwaste.model.FoodRecord;
import com.foodwaste.repository.FoodRecordRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class FoodRecordService {
    private final FoodRecordRepository repository;

    public FoodRecordService(FoodRecordRepository repository) {
        this.repository = repository;
    }

    public FoodRecord create(FoodRecord record) {
        if (record.getConsumedQuantityKg() > record.getPreparedQuantityKg()) {
            throw new IllegalArgumentException("Consumed quantity cannot exceed prepared quantity.");
        }
        return repository.save(record);
    }

    public List<FoodRecord> getAll() {
        return repository.findAll();
    }

    public FoodRecord getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("Food record not found: " + id));
    }

    public List<FoodRecord> getByLocation(String location) {
        return repository.findByLocationNameIgnoreCase(location);
    }

    public FoodRecord update(Long id, FoodRecord updated) {
        FoodRecord current = getById(id);
        if (updated.getConsumedQuantityKg() > updated.getPreparedQuantityKg()) {
            throw new IllegalArgumentException("Consumed quantity cannot exceed prepared quantity.");
        }
        current.setLocationName(updated.getLocationName());
        current.setFoodName(updated.getFoodName());
        current.setPreparedQuantityKg(updated.getPreparedQuantityKg());
        current.setConsumedQuantityKg(updated.getConsumedQuantityKg());
        current.setRecordDate(updated.getRecordDate());
        return repository.save(current);
    }

    public void delete(Long id) {
        FoodRecord current = getById(id);
        repository.delete(current);
    }

    public static class RecordNotFoundException extends RuntimeException {
        public RecordNotFoundException(String message) { super(message); }
    }
}
