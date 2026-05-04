package com.apps.quantitymeasurement;

public enum LengthUnit {
    FEET(12.0),
    INCHES(1.0);

    private final double factor;

    LengthUnit(double factor) {
        this.factor = factor;
    }

    public double getFactor() {
        return factor;
    }
}