package com.apps.quantitymeasurement;

public class Length {

    private double value;
    private LengthUnit unit;

    public Length(double value, LengthUnit unit) {
        this.value = value;
        this.unit = unit;
    }

    private double convertToInches() {
        if (unit == LengthUnit.FEET) {
            return value * 12.0;
        } else {
            return value;
        }
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj) {
            return true;
        }

        if (obj == null) {
            return false;
        }

        if (!(obj instanceof Length)) {
            return false;
        }

        Length other = (Length) obj;

        return Double.compare(this.convertToInches(), other.convertToInches()) == 0;
    }
}