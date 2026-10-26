package com.foodwaste.repository;

import com.foodwaste.model.FoodRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface FoodRecordRepository extends JpaRepository<FoodRecord, Long> {
    List<FoodRecord> findByLocationNameIgnoreCase(String locationName);
}
