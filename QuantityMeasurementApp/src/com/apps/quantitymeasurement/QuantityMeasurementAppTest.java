package com.apps.quantitymeasurement;

import org.junit.Test;
import static org.junit.Assert.*;

public class QuantityMeasurementAppTest {

    private static final double EPSILON = 1e-6;

    @Test
    public void testAddition_SameUnit_FeetPlusFeet() {
        var q1 = new QuantityMeasurementApp.QuantityLength(1.0,
                QuantityMeasurementApp.LengthUnit.FEET);
        var q2 = new QuantityMeasurementApp.QuantityLength(2.0,
                QuantityMeasurementApp.LengthUnit.FEET);

        var result = q1.add(q2);

        assertEquals(3.0, result.convertTo(
                QuantityMeasurementApp.LengthUnit.FEET).convertTo(
                QuantityMeasurementApp.LengthUnit.FEET).convertTo(
                QuantityMeasurementApp.LengthUnit.FEET).value, EPSILON);
    }

    @Test
    public void testAddition_SameUnit_InchPlusInch() {
        var q1 = new QuantityMeasurementApp.QuantityLength(6.0,
                QuantityMeasurementApp.LengthUnit.INCHES);
        var q2 = new QuantityMeasurementApp.QuantityLength(6.0,
                QuantityMeasurementApp.LengthUnit.INCHES);

        var result = q1.add(q2);

        assertEquals(12.0, result.convertTo(
                QuantityMeasurementApp.LengthUnit.INCHES).value, EPSILON);
    }

    @Test
    public void testAddition_CrossUnit_FeetPlusInches() {
        var q1 = new QuantityMeasurementApp.QuantityLength(1.0,
                QuantityMeasurementApp.LengthUnit.FEET);
        var q2 = new QuantityMeasurementApp.QuantityLength(12.0,
                QuantityMeasurementApp.LengthUnit.INCHES);

        var result = q1.add(q2);

        assertEquals(2.0, result.convertTo(
                QuantityMeasurementApp.LengthUnit.FEET).value, EPSILON);
    }

    @Test
    public void testAddition_CrossUnit_InchPlusFeet() {
        var q1 = new QuantityMeasurementApp.QuantityLength(12.0,
                QuantityMeasurementApp.LengthUnit.INCHES);
        var q2 = new QuantityMeasurementApp.QuantityLength(1.0,
                QuantityMeasurementApp.LengthUnit.FEET);

        var result = q1.add(q2);

        assertEquals(24.0, result.convertTo(
                QuantityMeasurementApp.LengthUnit.INCHES).value, EPSILON);
    }

    @Test
    public void testAddition_CrossUnit_YardPlusFeet() {
        var q1 = new QuantityMeasurementApp.QuantityLength(1.0,
                QuantityMeasurementApp.LengthUnit.YARDS);
        var q2 = new QuantityMeasurementApp.QuantityLength(3.0,
                QuantityMeasurementApp.LengthUnit.FEET);

        var result = q1.add(q2);

        assertEquals(2.0, result.convertTo(
                QuantityMeasurementApp.LengthUnit.YARDS).value, EPSILON);
    }

    @Test
    public void testAddition_CrossUnit_CentimeterPlusInch() {
        var q1 = new QuantityMeasurementApp.QuantityLength(2.54,
                QuantityMeasurementApp.LengthUnit.CENTIMETERS);
        var q2 = new QuantityMeasurementApp.QuantityLength(1.0,
                QuantityMeasurementApp.LengthUnit.INCHES);

        var result = q1.add(q2);

        assertEquals(5.08, result.convertTo(
                QuantityMeasurementApp.LengthUnit.CENTIMETERS).value, 1e-2);
    }

    @Test
    public void testAddition_Commutativity() {
        var q1 = new QuantityMeasurementApp.QuantityLength(1.0,
                QuantityMeasurementApp.LengthUnit.FEET);
        var q2 = new QuantityMeasurementApp.QuantityLength(12.0,
                QuantityMeasurementApp.LengthUnit.INCHES);

        var r1 = q1.add(q2).convertTo(QuantityMeasurementApp.LengthUnit.FEET);
        var r2 = q2.add(q1).convertTo(QuantityMeasurementApp.LengthUnit.FEET);

        assertEquals(r1.value, r2.value, EPSILON);
    }

    @Test
    public void testAddition_WithZero() {
        var q1 = new QuantityMeasurementApp.QuantityLength(5.0,
                QuantityMeasurementApp.LengthUnit.FEET);
        var q2 = new QuantityMeasurementApp.QuantityLength(0.0,
                QuantityMeasurementApp.LengthUnit.INCHES);

        var result = q1.add(q2);

        assertEquals(5.0, result.convertTo(
                QuantityMeasurementApp.LengthUnit.FEET).value, EPSILON);
    }

    @Test
    public void testAddition_NegativeValues() {
        var q1 = new QuantityMeasurementApp.QuantityLength(5.0,
                QuantityMeasurementApp.LengthUnit.FEET);
        var q2 = new QuantityMeasurementApp.QuantityLength(-2.0,
                QuantityMeasurementApp.LengthUnit.FEET);

        var result = q1.add(q2);

        assertEquals(3.0, result.convertTo(
                QuantityMeasurementApp.LengthUnit.FEET).value, EPSILON);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddition_NullSecondOperand() {
        var q1 = new QuantityMeasurementApp.QuantityLength(1.0,
                QuantityMeasurementApp.LengthUnit.FEET);

        q1.add(null);
    }

    @Test
    public void testAddition_LargeValues() {
        var q1 = new QuantityMeasurementApp.QuantityLength(1e6,
                QuantityMeasurementApp.LengthUnit.FEET);
        var q2 = new QuantityMeasurementApp.QuantityLength(1e6,
                QuantityMeasurementApp.LengthUnit.FEET);

        var result = q1.add(q2);

        assertEquals(2e6, result.convertTo(
                QuantityMeasurementApp.LengthUnit.FEET).value, EPSILON);
    }

    @Test
    public void testAddition_SmallValues() {
        var q1 = new QuantityMeasurementApp.QuantityLength(0.001,
                QuantityMeasurementApp.LengthUnit.FEET);
        var q2 = new QuantityMeasurementApp.QuantityLength(0.002,
                QuantityMeasurementApp.LengthUnit.FEET);

        var result = q1.add(q2);

        assertEquals(0.003, result.convertTo(
                QuantityMeasurementApp.LengthUnit.FEET).value, EPSILON);
    }
}