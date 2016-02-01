package com.example.laura.southwel_fueltrack;

public class Entry {

    private String _date, _station, _odometer, _fuelGrade, _fuelAmount, _fuelUnitCost, _fuelTotalCost;

    public Entry(String date, String station, String odometer, String fuelGrade, String fuelAmount, String fuelUnitCost, String fuelTotalCost) {
        _date = date;
        _station = station;
        _odometer = odometer;
        _fuelGrade = fuelGrade;
        _fuelAmount = fuelAmount;
        _fuelUnitCost = fuelUnitCost;
        _fuelTotalCost = fuelTotalCost;
    }


    public String get_date() {
        return _date;
    }

    public String get_station() {
        return _station;
    }

    public String get_odometer() {
        return _odometer;
    }

    public String get_fuelGrade() {
        return _fuelGrade;
    }

    public String get_fuelAmount() {
        return _fuelAmount;
    }

    public String get_fuelUnitCost() {
        return _fuelUnitCost;
    }

    public String get_fuelTotalCost() {
        return _fuelTotalCost;
    }


}

