package com.apps.quantitymeasurement;

import org.junit.Test;
import static org.junit.Assert.*;

public class QuantityMeasurementAppTest {

    private static final double EPSILON = 1e-4; // FIXED: more stable for floating-point

    @Test
    public void testEquality_KilogramToKilogram_SameValue() {
        assertTrue(new QuantityWeight(1.0, WeightUnit.KILOGRAM)
                .equals(new QuantityWeight(1.0, WeightUnit.KILOGRAM)));
    }

    @Test
    public void testEquality_KilogramToGram() {
        assertTrue(new QuantityWeight(1.0, WeightUnit.KILOGRAM)
                .equals(new QuantityWeight(1000.0, WeightUnit.GRAM)));
    }

    @Test
    public void testEquality_KilogramToPound() {
        // FIXED: avoid strict decimal comparison issue
        QuantityWeight kg = new QuantityWeight(1.0, WeightUnit.KILOGRAM);
        QuantityWeight lb = new QuantityWeight(2.2046226218, WeightUnit.POUND);

        assertTrue(kg.equals(lb));
    }

    @Test
    public void testEquality_NullComparison() {
        assertFalse(new QuantityWeight(1.0, WeightUnit.KILOGRAM).equals(null));
    }

    @Test
    public void testConversion_KilogramToGram() {
        QuantityWeight result =
                new QuantityWeight(1.0, WeightUnit.KILOGRAM)
                        .convertTo(WeightUnit.GRAM);

        assertEquals(1000.0, result.getValue(), EPSILON);
    }

    @Test
    public void testConversion_PoundToKilogram() {
        QuantityWeight result =
                new QuantityWeight(2.2046226218, WeightUnit.POUND)
                        .convertTo(WeightUnit.KILOGRAM);

        assertEquals(1.0, result.getValue(), 1e-4);
    }

    @Test
    public void testConversion_RoundTrip() {
        QuantityWeight w = new QuantityWeight(1.5, WeightUnit.KILOGRAM);

        QuantityWeight result = w.convertTo(WeightUnit.GRAM)
                .convertTo(WeightUnit.KILOGRAM);

        assertEquals(1.5, result.getValue(), EPSILON);
    }

    @Test
    public void testAddition_SameUnit() {
        QuantityWeight result = new QuantityWeight(1.0, WeightUnit.KILOGRAM)
                .add(new QuantityWeight(2.0, WeightUnit.KILOGRAM));

        assertEquals(3.0, result.getValue(), EPSILON);
    }

    @Test
    public void testAddition_CrossUnit() {
        QuantityWeight result = new QuantityWeight(1.0, WeightUnit.KILOGRAM)
                .add(new QuantityWeight(1000.0, WeightUnit.GRAM));

        assertEquals(2.0, result.getValue(), EPSILON);
    }

    @Test
    public void testAddition_WithTargetUnit() {
        QuantityWeight result = QuantityWeight.add(
                new QuantityWeight(1.0, WeightUnit.KILOGRAM),
                new QuantityWeight(1000.0, WeightUnit.GRAM),
                WeightUnit.GRAM);

        assertEquals(2000.0, result.getValue(), EPSILON);
    }

    @Test
    public void testAddition_Commutativity() {
        QuantityWeight a = new QuantityWeight(1.0, WeightUnit.KILOGRAM);
        QuantityWeight b = new QuantityWeight(1000.0, WeightUnit.GRAM);

        QuantityWeight r1 = QuantityWeight.add(a, b, WeightUnit.KILOGRAM);
        QuantityWeight r2 = QuantityWeight.add(b, a, WeightUnit.KILOGRAM);

        assertEquals(r1.getValue(), r2.getValue(), EPSILON);
    }

    @Test
    public void testZeroValue() {
        QuantityWeight result = new QuantityWeight(5.0, WeightUnit.KILOGRAM)
                .add(new QuantityWeight(0.0, WeightUnit.GRAM));

        assertEquals(5.0, result.getValue(), EPSILON);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidUnit() {
        new QuantityWeight(1.0, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidValue() {
        new QuantityWeight(Double.NaN, WeightUnit.KILOGRAM);
    }
}
