package com.kisansetu.controller;

import com.kisansetu.dto.CropRequest;
import com.kisansetu.dto.PriceSuggestionRequest;
import com.kisansetu.dto.PriceSuggestionResponse;
import com.kisansetu.model.Crop;
import com.kisansetu.service.CropService;
import com.kisansetu.service.OrderService;
import com.kisansetu.service.PriceSuggestionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/crops")
@CrossOrigin(origins = "*") // Enable CORS for all origins
public class CropController {

    private final CropService cropService;
    private final PriceSuggestionService priceSuggestionService;
    private final OrderService orderService;

    @Autowired
    public CropController(CropService cropService, PriceSuggestionService priceSuggestionService, OrderService orderService) {
        this.cropService = cropService;
        this.priceSuggestionService = priceSuggestionService;
        this.orderService = orderService;
    }

    // Create a new crop listing
    @PostMapping
    public ResponseEntity<?> createCrop(@Valid @RequestBody CropRequest request) {
        try {
            Crop crop = cropService.createCrop(request);
            return ResponseEntity.ok(crop);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }

    // Get all available crops
    @GetMapping
    public ResponseEntity<List<Crop>> getAllCrops(
            @RequestParam(required = false, defaultValue = "true") boolean availableOnly) {
        if (availableOnly) {
            return ResponseEntity.ok(cropService.getAllAvailableCrops());
        } else {
            return ResponseEntity.ok(cropService.getAllCrops());
        }
    }

    // Get crop by ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getCropById(@PathVariable String id) {
        Optional<Crop> crop = cropService.getCropById(id);
        if (crop.isPresent()) {
            return ResponseEntity.ok(crop.get());
        } else {
            Map<String, String> error = new HashMap<>();
            error.put("error", "Crop not found");
            return ResponseEntity.notFound().build();
        }
    }

    // Get crops by farmer
    @GetMapping("/farmer/{farmerId}")
    public ResponseEntity<List<Crop>> getCropsByFarmer(@PathVariable String farmerId) {
        return ResponseEntity.ok(cropService.getCropsByFarmer(farmerId));
    }

    // Search crops by name
    @GetMapping("/search")
    public ResponseEntity<List<Crop>> searchCrops(@RequestParam String name) {
        return ResponseEntity.ok(cropService.searchCrops(name));
    }

    // Get crops by category
    @GetMapping("/category/{category}")
    public ResponseEntity<List<Crop>> getCropsByCategory(@PathVariable String category) {
        return ResponseEntity.ok(cropService.getCropsByCategory(category));
    }

    // Update crop
    @PutMapping("/{id}")
    public ResponseEntity<?> updateCrop(@PathVariable String id, @Valid @RequestBody CropRequest request) {
        try {
            Crop crop = cropService.updateCrop(id, request);
            return ResponseEntity.ok(crop);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }

    // Update crop availability
    @PatchMapping("/{id}/availability")
    public ResponseEntity<?> updateAvailability(@PathVariable String id, @RequestParam boolean available) {
        try {
            Crop crop = cropService.updateAvailability(id, available);
            return ResponseEntity.ok(crop);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }

    // Delete crop and mark related orders as OUT_OF_STOCK
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCrop(@PathVariable String id) {
        try {
            // First, mark all PENDING/CONFIRMED orders for this crop as OUT_OF_STOCK
            orderService.markOrdersAsOutOfStockForCrop(id);
            
            // Then delete the crop
            cropService.deleteCrop(id);
            
            Map<String, String> success = new HashMap<>();
            success.put("message", "Crop deleted successfully. Related orders marked as OUT_OF_STOCK.");
            return ResponseEntity.ok(success);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }

    // AI Price Suggestion endpoint
    @PostMapping("/price-suggestion")
    public ResponseEntity<PriceSuggestionResponse> getPriceSuggestion(
            @Valid @RequestBody PriceSuggestionRequest request) {
        PriceSuggestionResponse suggestion = priceSuggestionService.getPriceSuggestion(request);
        return ResponseEntity.ok(suggestion);
    }
}
