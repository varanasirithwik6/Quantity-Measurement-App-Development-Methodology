package com.apps.quantitymeasurement;

import org.junit.Test;
import static org.junit.Assert.*;

public class QuantityMeasurementAppTest {

    private static final double EPSILON = 1e-6;

    @Test
    public void testLengthUnitEnum_FeetConstant() {
        assertEquals(1.0, LengthUnit.FEET.getConversionFactor(), EPSILON);
    }

    @Test
    public void testLengthUnitEnum_InchesConstant() {
        assertEquals(1.0 / 12.0, LengthUnit.INCHES.getConversionFactor(), EPSILON);
    }

    @Test
    public void testConvertToBaseUnit_InchesToFeet() {
        assertEquals(1.0, LengthUnit.INCHES.convertToBaseUnit(12.0), EPSILON);
    }

    @Test
    public void testConvertFromBaseUnit_FeetToInches() {
        assertEquals(12.0, LengthUnit.INCHES.convertFromBaseUnit(1.0), EPSILON);
    }

    @Test
    public void testConvertToBaseUnit_YardsToFeet() {
        assertEquals(3.0, LengthUnit.YARDS.convertToBaseUnit(1.0), EPSILON);
    }

    @Test
    public void testConvertFromBaseUnit_FeetToCentimeters() {
        assertEquals(30.48, LengthUnit.CENTIMETERS.convertFromBaseUnit(1.0), 1e-2);
    }

    @Test
    public void testQuantityLength_Equality() {
        QuantityLength q1 = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength q2 = new QuantityLength(12.0, LengthUnit.INCHES);

        assertTrue(q1.equals(q2));
    }

    @Test
    public void testQuantityLength_ConvertTo() {
        QuantityLength q = new QuantityLength(1.0, LengthUnit.FEET);

        QuantityLength result = q.convertTo(LengthUnit.INCHES);

        assertEquals(12.0, result.getValue(), EPSILON);
    }

    @Test
    public void testQuantityLength_Add() {
        QuantityLength q1 = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength q2 = new QuantityLength(12.0, LengthUnit.INCHES);

        QuantityLength result = QuantityLength.add(q1, q2, LengthUnit.FEET);

        assertEquals(2.0, result.getValue(), EPSILON);
    }

    @Test
    public void testQuantityLength_AddWithTargetUnit() {
        QuantityLength q1 = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength q2 = new QuantityLength(12.0, LengthUnit.INCHES);

        QuantityLength result = QuantityLength.add(q1, q2, LengthUnit.YARDS);

        assertEquals(0.6666, result.getValue(), 1e-3);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testQuantityLength_NullUnit() {
        new QuantityLength(1.0, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testQuantityLength_InvalidValue() {
        new QuantityLength(Double.NaN, LengthUnit.FEET);
    }

    @Test
    public void testRoundTripConversion() {
        double value = 5.0;

        double inches = LengthUnit.INCHES.convertFromBaseUnit(
                LengthUnit.FEET.convertToBaseUnit(value));

        double back = LengthUnit.FEET.convertFromBaseUnit(
                LengthUnit.INCHES.convertToBaseUnit(inches));

        assertEquals(value, back, EPSILON);
    }
}