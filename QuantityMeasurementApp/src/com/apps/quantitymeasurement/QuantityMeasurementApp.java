package com.apps.quantitymeasurement;

public class QuantityMeasurementApp {

    // ---------------- ENUM ----------------
    enum LengthUnit {
        FEET(1.0),
        INCHES(1.0 / 12.0),
        YARDS(3.0),
        CENTIMETERS(0.0328084); // 1 cm = 0.0328084 feet

        private final double toFeetFactor;

        LengthUnit(double toFeetFactor) {
            this.toFeetFactor = toFeetFactor;
        }

        public double toFeet(double value) {
            return value * toFeetFactor;
        }

        public double fromFeet(double feetValue) {
            return feetValue / toFeetFactor;
        }
    }

    // ---------------- QUANTITY CLASS ----------------
    static class QuantityLength {
        private final double value;
        private final LengthUnit unit;

        private static final double EPSILON = 1e-6;

        public QuantityLength(double value, LengthUnit unit) {
            if (unit == null)
                throw new IllegalArgumentException("Unit cannot be null");

            if (!Double.isFinite(value))
                throw new IllegalArgumentException("Invalid numeric value");

            this.value = value;
            this.unit = unit;
        }

        private double toFeet() {
            return unit.toFeet(value);
        }

        // Instance conversion
        public QuantityLength convertTo(LengthUnit targetUnit) {
            if (targetUnit == null)
                throw new IllegalArgumentException("Target unit cannot be null");

            double feet = this.toFeet();
            double converted = targetUnit.fromFeet(feet);
            return new QuantityLength(converted, targetUnit);
        }

        // Static conversion API (UC5 requirement)
        public static double convert(double value, LengthUnit source, LengthUnit target) {
            if (source == null || target == null)
                throw new IllegalArgumentException("Units cannot be null");

            if (!Double.isFinite(value))
                throw new IllegalArgumentException("Invalid numeric value");

            double feet = source.toFeet(value);
            return target.fromFeet(feet);
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;

            QuantityLength other = (QuantityLength) obj;
            return Math.abs(this.toFeet() - other.toFeet()) < EPSILON;
        }

        @Override
        public int hashCode() {
            return Double.valueOf(toFeet()).hashCode();
        }

        @Override
        public String toString() {
            return value + " " + unit;
        }
    }

    // ---------------- OVERLOADED METHODS ----------------
    public static void demonstrateLengthConversion(double value, LengthUnit from, LengthUnit to) {
        double result = QuantityLength.convert(value, from, to);
        System.out.println("convert(" + value + ", " + from + ", " + to + ") = " + result);
    }

    public static void demonstrateLengthConversion(QuantityLength quantity, LengthUnit to) {
        QuantityLength converted = quantity.convertTo(to);
        System.out.println(quantity + " = " + converted);
    }

    public static void demonstrateLengthEquality(QuantityLength q1, QuantityLength q2) {
        System.out.println(q1 + " == " + q2 + " : " + q1.equals(q2));
    }

    public static void demonstrateLengthComparison(double v1, LengthUnit u1, double v2, LengthUnit u2) {
        QuantityLength q1 = new QuantityLength(v1, u1);
        QuantityLength q2 = new QuantityLength(v2, u2);
        demonstrateLengthEquality(q1, q2);
    }

    // ---------------- MAIN (TEST CASES) ----------------
    public static void main(String[] args) {

        // Basic conversions
        demonstrateLengthConversion(1.0, LengthUnit.FEET, LengthUnit.INCHES);     // 12
        demonstrateLengthConversion(3.0, LengthUnit.YARDS, LengthUnit.FEET);      // 9
        demonstrateLengthConversion(36.0, LengthUnit.INCHES, LengthUnit.YARDS);   // 1
        demonstrateLengthConversion(1.0, LengthUnit.CENTIMETERS, LengthUnit.INCHES); // ~0.39
        demonstrateLengthConversion(0.0, LengthUnit.FEET, LengthUnit.INCHES);     // 0

        // Instance conversion
        QuantityLength length = new QuantityLength(2.0, LengthUnit.YARDS);
        demonstrateLengthConversion(length, LengthUnit.INCHES);

        // Equality checks
        demonstrateLengthComparison(1.0, LengthUnit.FEET, 12.0, LengthUnit.INCHES);
        demonstrateLengthComparison(3.0, LengthUnit.FEET, 1.0, LengthUnit.YARDS);

        // Negative & zero
        demonstrateLengthConversion(-1.0, LengthUnit.FEET, LengthUnit.INCHES);

        // Round-trip test
        double original = 5.0;
        double converted = QuantityLength.convert(original, LengthUnit.FEET, LengthUnit.INCHES);
        double back = QuantityLength.convert(converted, LengthUnit.INCHES, LengthUnit.FEET);
        System.out.println("Round-trip: " + original + " -> " + back);

        // Exception tests
        try {
            QuantityLength.convert(Double.NaN, LengthUnit.FEET, LengthUnit.INCHES);
        } catch (Exception e) {
            System.out.println("NaN error: " + e.getMessage());
        }

        try {
            QuantityLength.convert(1.0, null, LengthUnit.FEET);
        } catch (Exception e) {
            System.out.println("Null unit error: " + e.getMessage());
        }
    }
}