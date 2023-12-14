package com.graph.product.entity;

import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "models")
public class Model {

    @Id
    @GeneratedValue
    private UUID uuid;

    private String name;
    private double onTheROadPrice;
    private int lengthMn;
    private int widthMn;
    private int heightMn;
    private String exteriorColor;
    private String interiorColor;
    private int releaseYear;
    private String transmission;
    private String bodyType;
    private String fuel;
    private int doors;
    private int airbags;
    private boolean isAvailable;

    @OneToOne
    @JoinColumn(name = "engine_uuid")
    private Engine engine;

    @OneToMany
    @JoinColumn(name = "model_uuid")
    private List<Feature> features;

    @ManyToOne
    @JoinColumn(name = "serie_uuid")
    private Serie series;

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getOnTheROadPrice() {
        return onTheROadPrice;
    }

    public void setOnTheROadPrice(double onTheROadPrice) {
        this.onTheROadPrice = onTheROadPrice;
    }

    public int getLengthMn() {
        return lengthMn;
    }

    public void setLengthMn(int lengthMn) {
        this.lengthMn = lengthMn;
    }

    public int getWidthMn() {
        return widthMn;
    }

    public void setWidthMn(int widthMn) {
        this.widthMn = widthMn;
    }

    public int getHeightMn() {
        return heightMn;
    }

    public void setHeightMn(int heightMn) {
        this.heightMn = heightMn;
    }

    public String getExteriorColor() {
        return exteriorColor;
    }

    public void setExteriorColor(String exteriorColor) {
        this.exteriorColor = exteriorColor;
    }

    public String getInteriorColor() {
        return interiorColor;
    }

    public void setInteriorColor(String interiorColor) {
        this.interiorColor = interiorColor;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public String getTransmission() {
        return transmission;
    }

    public void setTransmission(String transmission) {
        this.transmission = transmission;
    }

    public String getBodyType() {
        return bodyType;
    }

    public void setBodyType(String bodyType) {
        this.bodyType = bodyType;
    }

    public String getFuel() {
        return fuel;
    }

    public void setFuel(String fuel) {
        this.fuel = fuel;
    }

    public int getDoors() {
        return doors;
    }

    public void setDoors(int doors) {
        this.doors = doors;
    }

    public int getAirbags() {
        return airbags;
    }

    public void setAirbags(int airbags) {
        this.airbags = airbags;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public Engine getEngine() {
        return engine;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    public List<Feature> getFeatures() {
        return features;
    }

    public void setFeatures(List<Feature> features) {
        this.features = features;
    }

    public Serie getSeries() {
        return series;
    }

    public void setSeries(Serie series) {
        this.series = series;
    }
}
