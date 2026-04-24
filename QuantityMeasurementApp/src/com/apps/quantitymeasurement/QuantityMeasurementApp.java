package com.apps.quantitymeasurement;

public class QuantityMeasurementApp {

    enum LengthUnit {
        FEET(1.0),
        INCHES(1.0 / 12.0),
        YARDS(3.0),
        CENTIMETERS(0.0328084);

        private final double factor;

        LengthUnit(double factor) {
            this.factor = factor;
        }

        public double toFeet(double value) {
            return value * factor;
        }

        public double fromFeet(double feet) {
            return feet / factor;
        }
    }

    static class QuantityLength {

        private final double value;
        private final LengthUnit unit;
        private static final double EPSILON = 1e-6;

        public QuantityLength(double value, LengthUnit unit) {
            if (unit == null || !Double.isFinite(value))
                throw new IllegalArgumentException();

            this.value = value;
            this.unit = unit;
        }

        private double toFeet() {
            return unit.toFeet(value);
        }

        public QuantityLength convertTo(LengthUnit target) {
            if (target == null) throw new IllegalArgumentException();

            double feet = toFeet();
            return new QuantityLength(target.fromFeet(feet), target);
        }

        // ---------- UC6 ----------
        public QuantityLength add(QuantityLength other) {
            return add(this, other, this.unit);
        }

        // ---------- UC7 ----------
        public static QuantityLength add(QuantityLength q1, QuantityLength q2, LengthUnit target) {
            if (q1 == null || q2 == null || target == null)
                throw new IllegalArgumentException();

            double sumFeet = q1.toFeet() + q2.toFeet();
            double result = target.fromFeet(sumFeet);

            return new QuantityLength(result, target);
        }

        public double getValue() {
            return value;
        }

        public LengthUnit getUnit() {
            return unit;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (!(obj instanceof QuantityLength)) return false;

            QuantityLength other = (QuantityLength) obj;
            return Math.abs(this.toFeet() - other.toFeet()) < EPSILON;
        }

        @Override
        public String toString() {
            return value + " " + unit;
        }
    }

    public static void main(String[] args) {

        QuantityLength q1 = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength q2 = new QuantityLength(12.0, LengthUnit.INCHES);

        System.out.println(QuantityLength.add(q1, q2, LengthUnit.FEET));
        System.out.println(QuantityLength.add(q1, q2, LengthUnit.INCHES));
        System.out.println(QuantityLength.add(q1, q2, LengthUnit.YARDS));
    }
}