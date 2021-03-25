package com.agent.dto;

import java.util.ArrayList;

public class CodesDTO {

    private ArrayList<String> models= new ArrayList<String>();
    private ArrayList<String> fuels= new ArrayList<String>();
    private ArrayList<String> brands= new ArrayList<String>();
    private ArrayList<String> carClasses= new ArrayList<String>();
    private ArrayList<String> gearShifts= new ArrayList<String>();


    public CodesDTO() {
    }

    public CodesDTO(ArrayList<String> models, ArrayList<String> fuels, ArrayList<String> brands, ArrayList<String> carClasses, ArrayList<String> gearShifts) {
        this.models = models;
        this.fuels = fuels;
        this.brands = brands;
        this.carClasses = carClasses;
        this.gearShifts = gearShifts;
    }

    public ArrayList<String> getModels() {
        return models;
    }

    public void setModels(ArrayList<String> models) {
        this.models = models;
    }

    public ArrayList<String> getFuels() {
        return fuels;
    }

    public void setFuels(ArrayList<String> fuels) {
        this.fuels = fuels;
    }

    public ArrayList<String> getBrands() {
        return brands;
    }

    public void setBrands(ArrayList<String> brands) {
        this.brands = brands;
    }

    public ArrayList<String> getCarClasses() {
        return carClasses;
    }

    public void setCarClasses(ArrayList<String> carClasses) {
        this.carClasses = carClasses;
    }

    public ArrayList<String> getGearShifts() {
        return gearShifts;
    }

    public void setGearShifts(ArrayList<String> gearShifts) {
        this.gearShifts = gearShifts;
    }
}
