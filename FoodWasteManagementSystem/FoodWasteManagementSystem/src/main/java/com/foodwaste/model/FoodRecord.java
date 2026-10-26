package com.foodwaste.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import java.time.LocalDate;

@Entity
@Table(name = "food_records")
public class FoodRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String locationName;

    @NotBlank
    private String foodName;

    @NotNull
    @DecimalMin(value = "0.01", message = "Prepared quantity must be greater than zero")
    private Double preparedQuantityKg;

    @NotNull
    @PositiveOrZero(message = "Consumed quantity cannot be negative")
    private Double consumedQuantityKg;

    private LocalDate recordDate;

    public FoodRecord() {}

    @PrePersist
    public void setDefaults() {
        if (recordDate == null) recordDate = LocalDate.now();
    }

    public Long getId() { return id; }
    public String getLocationName() { return locationName; }
    public void setLocationName(String locationName) { this.locationName = locationName; }
    public String getFoodName() { return foodName; }
    public void setFoodName(String foodName) { this.foodName = foodName; }
    public Double getPreparedQuantityKg() { return preparedQuantityKg; }
    public void setPreparedQuantityKg(Double preparedQuantityKg) { this.preparedQuantityKg = preparedQuantityKg; }
    public Double getConsumedQuantityKg() { return consumedQuantityKg; }
    public void setConsumedQuantityKg(Double consumedQuantityKg) { this.consumedQuantityKg = consumedQuantityKg; }
    public LocalDate getRecordDate() { return recordDate; }
    public void setRecordDate(LocalDate recordDate) { this.recordDate = recordDate; }
}
