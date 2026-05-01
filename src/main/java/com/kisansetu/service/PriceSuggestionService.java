package com.kisansetu.service;

import com.kisansetu.dto.PriceSuggestionResponse;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class PriceSuggestionService {

    // Base prices for common crops (per kg in INR)
    private static final Map<String, Double> BASE_PRICES = new HashMap<>();
    
    static {
        // Vegetables
        BASE_PRICES.put("tomato", 30.0);
        BASE_PRICES.put("potato", 25.0);
        BASE_PRICES.put("onion", 35.0);
        BASE_PRICES.put("carrot", 40.0);
        BASE_PRICES.put("cabbage", 20.0);
        BASE_PRICES.put("cauliflower", 35.0);
        BASE_PRICES.put("brinjal", 30.0);
        BASE_PRICES.put("okra", 45.0);
        BASE_PRICES.put("chili", 60.0);
        BASE_PRICES.put("capsicum", 50.0);
        
        // Fruits
        BASE_PRICES.put("apple", 120.0);
        BASE_PRICES.put("banana", 40.0);
        BASE_PRICES.put("mango", 80.0);
        BASE_PRICES.put("grapes", 100.0);
        BASE_PRICES.put("orange", 70.0);
        BASE_PRICES.put("watermelon", 25.0);
        BASE_PRICES.put("papaya", 35.0);
        
        // Grains
        BASE_PRICES.put("wheat", 25.0);
        BASE_PRICES.put("rice", 35.0);
        BASE_PRICES.put("corn", 20.0);
        BASE_PRICES.put("barley", 22.0);
        
        // Pulses
        BASE_PRICES.put("lentil", 80.0);
        BASE_PRICES.put("chickpea", 70.0);
        BASE_PRICES.put("kidney beans", 90.0);
        BASE_PRICES.put("green gram", 100.0);
        
        // Others
        BASE_PRICES.put("sugarcane", 3.0);
        BASE_PRICES.put("cotton", 70.0);
    }

    public PriceSuggestionResponse getPriceSuggestion(String cropName, Double quantity, 
                                                       String location, String quality, String season) {
        
        String normalizedName = cropName.toLowerCase().trim();
        
        // Get base price
        Double basePrice = BASE_PRICES.getOrDefault(normalizedName, 40.0); // Default 40/kg
        
        // Apply quantity factor (bulk discount or premium for small batches)
        double quantityFactor = 1.0;
        if (quantity >= 1000) {
            quantityFactor = 0.95; // 5% discount for bulk (1000+ kg)
        } else if (quantity <= 10) {
            quantityFactor = 1.1; // 10% premium for small batches
        }
        
        // Apply quality factor
        double qualityFactor = 1.0;
        if (quality != null) {
            switch (quality.toLowerCase()) {
                case "premium":
                    qualityFactor = 1.3;
                    break;
                case "standard":
                    qualityFactor = 1.0;
                    break;
                case "basic":
                    qualityFactor = 0.8;
                    break;
            }
        }
        
        // Apply location factor (metro cities get higher prices)
        double locationFactor = 1.0;
        if (location != null) {
            String loc = location.toLowerCase();
            if (loc.contains("mumbai") || loc.contains("delhi") || 
                loc.contains("bangalore") || loc.contains("hyderabad") ||
                loc.contains("chennai") || loc.contains("kolkata")) {
                locationFactor = 1.15; // 15% premium in metro cities
            } else if (loc.contains("rural") || loc.contains("village")) {
                locationFactor = 0.9; // 10% lower in rural areas
            }
        }
        
        // Apply seasonal factor (mock logic)
        double seasonalFactor = 1.0;
        String marketTrend = "STABLE";
        if (season != null && season.toLowerCase().contains("harvest")) {
            seasonalFactor = 0.9; // Prices dip during harvest
            marketTrend = "FALLING";
        } else if (season != null && season.toLowerCase().contains("lean")) {
            seasonalFactor = 1.2; // Prices rise during lean season
            marketTrend = "RISING";
        }
        
        // Calculate suggested price
        double suggestedPrice = basePrice * quantityFactor * qualityFactor * locationFactor * seasonalFactor;
        
        // Round to 2 decimal places
        suggestedPrice = Math.round(suggestedPrice * 100.0) / 100.0;
        
        // Calculate min and max price range
        double minPrice = Math.round(suggestedPrice * 0.85 * 100.0) / 100.0;
        double maxPrice = Math.round(suggestedPrice * 1.15 * 100.0) / 100.0;
        
        // Determine confidence based on data availability
        String confidence = BASE_PRICES.containsKey(normalizedName) ? "HIGH" : "MEDIUM";
        
        // Build reasoning
        StringBuilder reasoning = new StringBuilder();
        reasoning.append("Price calculated based on: ");
        reasoning.append(String.format("Base price Rs.%.2f/kg; ", basePrice));
        
        if (quantityFactor != 1.0) {
            reasoning.append(String.format("Quantity factor: %.0f%%; ", (quantityFactor - 1) * 100));
        }
        if (qualityFactor != 1.0) {
            reasoning.append(String.format("Quality factor: %+.0f%%; ", (qualityFactor - 1) * 100));
        }
        if (locationFactor != 1.0) {
            reasoning.append(String.format("Location factor: %+.0f%%; ", (locationFactor - 1) * 100));
        }
        if (seasonalFactor != 1.0) {
            reasoning.append(String.format("Seasonal factor: %+.0f%%", (seasonalFactor - 1) * 100));
        }
        
        PriceSuggestionResponse response = new PriceSuggestionResponse();
        response.setCropName(cropName);
        response.setSuggestedPricePerKg(suggestedPrice);
        response.setMinPrice(minPrice);
        response.setMaxPrice(maxPrice);
        response.setMarketTrend(marketTrend);
        response.setConfidence(confidence);
        response.setReasoning(reasoning.toString());
        
        return response;
    }
    
    public PriceSuggestionResponse getPriceSuggestion(com.kisansetu.dto.PriceSuggestionRequest request) {
        return getPriceSuggestion(
            request.getCropName(),
            request.getQuantity(),
            request.getLocation(),
            request.getQuality(),
            request.getSeason()
        );
    }
}
