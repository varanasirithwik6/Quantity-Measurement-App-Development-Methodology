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
        return (unit == LengthUnit.FEET) ? value * 12 : value;
    }

    // Convert from inches
    private double fromInches(double val, LengthUnit target) {
        return (target == LengthUnit.FEET) ? val / 12 : val;
    }

    // 🔥 CENTRAL METHOD (DRY)
    private double operate(Length other, String op) {

        if (other == null) {
            throw new IllegalArgumentException("Null value");
        }

        double a = this.toInches();
        double b = other.toInches();

        switch (op) {
            case "ADD": return a + b;
            case "SUB": return a - b;
            case "DIV":
                if (b == 0) throw new ArithmeticException("Divide by zero");
                return a / b;
        }

        return 0;
    }

    // ADD
    public Length add(Length other) {
        double res = operate(other, "ADD");
        return new Length(fromInches(res, this.unit), this.unit);
    }

    // SUBTRACT
    public Length subtract(Length other) {
        double res = operate(other, "SUB");
        return new Length(fromInches(res, this.unit), this.unit);
    }

    // DIVIDE
    public double divide(Length other) {
        return operate(other, "DIV");
    }

    // EQUALS
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || !(obj instanceof Length)) return false;

        Length other = (Length) obj;
        return Double.compare(this.toInches(), other.toInches()) == 0;
    }
}