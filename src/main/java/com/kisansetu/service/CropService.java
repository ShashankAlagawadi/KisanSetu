package com.kisansetu.service;

import com.kisansetu.dto.CropRequest;
import com.kisansetu.model.Crop;
import com.kisansetu.model.User;
import com.kisansetu.repository.CropRepository;
import com.kisansetu.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CropService {

    private final CropRepository cropRepository;
    private final UserRepository userRepository;
    private final PriceSuggestionService priceSuggestionService;

    @Autowired
    public CropService(CropRepository cropRepository, 
                       UserRepository userRepository,
                       PriceSuggestionService priceSuggestionService) {
        this.cropRepository = cropRepository;
        this.userRepository = userRepository;
        this.priceSuggestionService = priceSuggestionService;
    }

    public Crop createCrop(CropRequest request) {
        // Verify farmer exists
        Optional<User> farmerOpt = userRepository.findById(request.getFarmerId());
        if (farmerOpt.isEmpty() || !farmerOpt.get().getRole().equals("FARMER")) {
            throw new RuntimeException("Invalid farmer ID");
        }

        User farmer = farmerOpt.get();

        Crop crop = new Crop();
        crop.setName(request.getName());
        crop.setCategory(request.getCategory());
        crop.setQuantity(request.getQuantity());
        crop.setPricePerKg(request.getPricePerKg());
        crop.setUnit(request.getUnit());
        crop.setFarmerId(request.getFarmerId());
        crop.setFarmerName(farmer.getFullName());
        crop.setImageUrl(request.getImageUrl());
        crop.setDescription(request.getDescription());
        crop.setLocation(request.getLocation());
        crop.setIsAvailable(true);

        // Get AI price suggestion
        var suggestion = priceSuggestionService.getPriceSuggestion(
            request.getName(), 
            request.getQuantity(), 
            request.getLocation(), 
            "standard", 
            null
        );
        crop.setSuggestedPrice(suggestion.getSuggestedPricePerKg());
        crop.setMarketTrend(suggestion.getMarketTrend().equals("RISING") ? 1.0 : 
                           suggestion.getMarketTrend().equals("FALLING") ? -1.0 : 0.0);

        return cropRepository.save(crop);
    }

    public List<Crop> getAllAvailableCrops() {
        return cropRepository.findByIsAvailableTrue();
    }

    public List<Crop> getAllCrops() {
        return cropRepository.findAll();
    }

    public Optional<Crop> getCropById(String id) {
        return cropRepository.findById(id);
    }

    public List<Crop> getCropsByFarmer(String farmerId) {
        return cropRepository.findByFarmerId(farmerId);
    }

    public List<Crop> searchCrops(String name) {
        return cropRepository.findByNameContainingIgnoreCase(name);
    }

    public List<Crop> getCropsByCategory(String category) {
        return cropRepository.findByCategory(category);
    }

    public Crop updateCrop(String id, CropRequest request) {
        Optional<Crop> cropOpt = cropRepository.findById(id);
        if (cropOpt.isEmpty()) {
            throw new RuntimeException("Crop not found");
        }

        Crop crop = cropOpt.get();
        crop.setName(request.getName());
        crop.setCategory(request.getCategory());
        crop.setQuantity(request.getQuantity());
        crop.setPricePerKg(request.getPricePerKg());
        crop.setImageUrl(request.getImageUrl());
        crop.setDescription(request.getDescription());
        crop.setLocation(request.getLocation());
        crop.setUpdatedAt(LocalDateTime.now());

        return cropRepository.save(crop);
    }

    public void deleteCrop(String id) {
        cropRepository.deleteById(id);
    }

    public Crop updateAvailability(String id, boolean available) {
        Optional<Crop> cropOpt = cropRepository.findById(id);
        if (cropOpt.isEmpty()) {
            throw new RuntimeException("Crop not found");
        }

        Crop crop = cropOpt.get();
        crop.setIsAvailable(available);
        crop.setUpdatedAt(LocalDateTime.now());

        return cropRepository.save(crop);
    }

    public boolean reduceQuantity(String cropId, Double amount) {
        Optional<Crop> cropOpt = cropRepository.findById(cropId);
        if (cropOpt.isEmpty()) {
            return false;
        }

        Crop crop = cropOpt.get();
        if (crop.getQuantity() < amount) {
            return false;
        }

        crop.setQuantity(crop.getQuantity() - amount);
        if (crop.getQuantity() <= 0) {
            crop.setIsAvailable(false);
        }
        crop.setUpdatedAt(LocalDateTime.now());

        cropRepository.save(crop);
        return true;
    }
}
