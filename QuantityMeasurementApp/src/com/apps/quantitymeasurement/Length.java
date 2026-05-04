package com.apps.quantitymeasurement;

public class Length {

    private double value;
    private LengthUnit unit;

    public Length(double value, LengthUnit unit) {
        this.value = value;
        this.unit = unit;
    }

    // Convert to inches
    private double toInches() {
        if (unit == LengthUnit.FEET) {
            return value * 12;
        }
        return value;
    }

    // Convert from inches
    private double fromInches(double inches, LengthUnit targetUnit) {
        if (targetUnit == LengthUnit.FEET) {
            return inches / 12;
        }
        return inches;
    }

    // EQUALS
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || !(obj instanceof Length)) return false;

        Length other = (Length) obj;
        return Double.compare(this.toInches(), other.toInches()) == 0;
    }

    // ADD
    public Length add(Length other) {
        double result = this.toInches() + other.toInches();
        return new Length(fromInches(result, this.unit), this.unit);
    }

    // SUBTRACT
    public Length subtract(Length other) {
        double result = this.toInches() - other.toInches();
        return new Length(fromInches(result, this.unit), this.unit);
    }

    // DIVIDE
    public double divide(Length other) {
        if (other.toInches() == 0) {
            throw new ArithmeticException("Cannot divide by zero");
        }
        return this.toInches() / other.toInches();
    }
}