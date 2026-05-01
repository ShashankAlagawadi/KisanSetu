package com.kisansetu.repository;

import com.kisansetu.model.Crop;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CropRepository extends MongoRepository<Crop, String> {

    List<Crop> findByFarmerId(String farmerId);

    List<Crop> findByIsAvailableTrue();

    List<Crop> findByNameContainingIgnoreCase(String name);

    List<Crop> findByCategory(String category);

    List<Crop> findByIsAvailableTrueAndQuantityGreaterThan(Double quantity);
}
