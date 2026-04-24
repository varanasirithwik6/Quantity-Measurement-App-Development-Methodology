package com.apps.quantitymeasurement;

public class QuantityWeight {

    private final double value;
    private final WeightUnit unit;
    private static final double EPSILON = 1e-6;

    public QuantityWeight(double value, WeightUnit unit) {
        if (unit == null || !Double.isFinite(value))
            throw new IllegalArgumentException();

        this.value = value;
        this.unit = unit;
    }

    private double toBase() {
        return unit.convertToBaseUnit(value);
    }

    public QuantityWeight convertTo(WeightUnit target) {
        if (target == null)
            throw new IllegalArgumentException();

        double base = toBase();
        return new QuantityWeight(target.convertFromBaseUnit(base), target);
    }

    public QuantityWeight add(QuantityWeight other) {
        return add(this, other, this.unit);
    }

    public static QuantityWeight add(QuantityWeight w1, QuantityWeight w2, WeightUnit target) {
        if (w1 == null || w2 == null || target == null)
            throw new IllegalArgumentException();

        double sumBase = w1.toBase() + w2.toBase();
        return new QuantityWeight(target.convertFromBaseUnit(sumBase), target);
    }

    public double getValue() {
        return value;
    }

    public WeightUnit getUnit() {
        return unit;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        QuantityWeight other = (QuantityWeight) obj;
        return Math.abs(this.toBase() - other.toBase()) < EPSILON;
    }

    @Override
    public int hashCode() {
        return Double.hashCode(toBase());
    }

    @Override
    public String toString() {
        return value + " " + unit;
    }
}