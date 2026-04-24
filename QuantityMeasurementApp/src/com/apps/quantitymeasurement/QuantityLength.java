package com.apps.quantitymeasurement;

public class QuantityLength {

    private final double value;
    private final LengthUnit unit;
    private static final double EPSILON = 1e-6;

    public QuantityLength(double value, LengthUnit unit) {
        if (unit == null || !Double.isFinite(value))
            throw new IllegalArgumentException();

        this.value = value;
        this.unit = unit;
    }

    private double toBase() {
        return unit.convertToBaseUnit(value);
    }

    public QuantityLength convertTo(LengthUnit target) {
        if (target == null)
            throw new IllegalArgumentException();

        double base = toBase();
        return new QuantityLength(target.convertFromBaseUnit(base), target);
    }

    public QuantityLength add(QuantityLength other) {
        return add(this, other, this.unit);
    }

    public static QuantityLength add(QuantityLength q1, QuantityLength q2, LengthUnit target) {
        if (q1 == null || q2 == null || target == null)
            throw new IllegalArgumentException();

        double sumBase = q1.toBase() + q2.toBase();
        return new QuantityLength(target.convertFromBaseUnit(sumBase), target);
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
        return Math.abs(this.toBase() - other.toBase()) < EPSILON;
    }

    @Override
    public String toString() {
        return value + " " + unit;
    }
}
