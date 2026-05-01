package com.kisansetu.dto;

public class PriceSuggestionResponse {

    private String cropName;
    private Double suggestedPricePerKg;
    private Double minPrice;
    private Double maxPrice;
    private String marketTrend; // RISING, STABLE, FALLING
    private String confidence; // HIGH, MEDIUM, LOW
    private String reasoning;

    public PriceSuggestionResponse() {}

    public PriceSuggestionResponse(String cropName, Double suggestedPricePerKg, 
                                   Double minPrice, Double maxPrice, 
                                   String marketTrend, String reasoning) {
        this.cropName = cropName;
        this.suggestedPricePerKg = suggestedPricePerKg;
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
        this.marketTrend = marketTrend;
        this.reasoning = reasoning;
    }

    // Getters and Setters
    public String getCropName() {
        return cropName;
    }

    public void setCropName(String cropName) {
        this.cropName = cropName;
    }

    public Double getSuggestedPricePerKg() {
        return suggestedPricePerKg;
    }

    public void setSuggestedPricePerKg(Double suggestedPricePerKg) {
        this.suggestedPricePerKg = suggestedPricePerKg;
    }

    public Double getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(Double minPrice) {
        this.minPrice = minPrice;
    }

    public Double getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(Double maxPrice) {
        this.maxPrice = maxPrice;
    }

    public String getMarketTrend() {
        return marketTrend;
    }

    public void setMarketTrend(String marketTrend) {
        this.marketTrend = marketTrend;
    }

    public String getConfidence() {
        return confidence;
    }

    public void setConfidence(String confidence) {
        this.confidence = confidence;
    }

    public String getReasoning() {
        return reasoning;
    }

    public void setReasoning(String reasoning) {
        this.reasoning = reasoning;
    }
}
