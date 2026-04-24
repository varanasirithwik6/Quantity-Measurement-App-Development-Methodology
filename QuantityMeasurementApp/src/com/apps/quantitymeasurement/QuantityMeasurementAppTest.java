package com.apps.quantitymeasurement;

import com.apps.quantitymeasurement.QuantityMeasurementApp;
import org.junit.Test;
import static org.junit.Assert.*;

public class QuantityMeasurementAppTest {

    private static final double EPSILON = 1e-6;

    @Test
    public void testAddition_ExplicitTargetUnit_Feet() {
        var q1 = new QuantityMeasurementApp.QuantityLength(1.0,
                QuantityMeasurementApp.LengthUnit.FEET);
        var q2 = new QuantityMeasurementApp.QuantityLength(12.0,
                QuantityMeasurementApp.LengthUnit.INCHES);

        var result = QuantityMeasurementApp.QuantityLength
                .add(q1, q2, QuantityMeasurementApp.LengthUnit.FEET);

        assertEquals(2.0, result.getValue(), EPSILON);
    }

    @Test
    public void testAddition_ExplicitTargetUnit_Inches() {
        var q1 = new QuantityMeasurementApp.QuantityLength(1.0,
                QuantityMeasurementApp.LengthUnit.FEET);
        var q2 = new QuantityMeasurementApp.QuantityLength(12.0,
                QuantityMeasurementApp.LengthUnit.INCHES);

        var result = QuantityMeasurementApp.QuantityLength
                .add(q1, q2, QuantityMeasurementApp.LengthUnit.INCHES);

        assertEquals(24.0, result.getValue(), EPSILON);
    }

    @Test
    public void testAddition_ExplicitTargetUnit_Yards() {
        var q1 = new QuantityMeasurementApp.QuantityLength(1.0,
                QuantityMeasurementApp.LengthUnit.FEET);
        var q2 = new QuantityMeasurementApp.QuantityLength(12.0,
                QuantityMeasurementApp.LengthUnit.INCHES);

        var result = QuantityMeasurementApp.QuantityLength
                .add(q1, q2, QuantityMeasurementApp.LengthUnit.YARDS);

        assertEquals(0.6666, result.getValue(), 1e-3);
    }

    @Test
    public void testAddition_ExplicitTargetUnit_Centimeters() {
        var q1 = new QuantityMeasurementApp.QuantityLength(1.0,
                QuantityMeasurementApp.LengthUnit.INCHES);
        var q2 = new QuantityMeasurementApp.QuantityLength(1.0,
                QuantityMeasurementApp.LengthUnit.INCHES);

        var result = QuantityMeasurementApp.QuantityLength
                .add(q1, q2, QuantityMeasurementApp.LengthUnit.CENTIMETERS);

        assertEquals(5.08, result.getValue(), 1e-2);
    }

    @Test
    public void testAddition_TargetSameAsFirstOperand() {
        var q1 = new QuantityMeasurementApp.QuantityLength(2.0,
                QuantityMeasurementApp.LengthUnit.YARDS);
        var q2 = new QuantityMeasurementApp.QuantityLength(3.0,
                QuantityMeasurementApp.LengthUnit.FEET);

        var result = QuantityMeasurementApp.QuantityLength
                .add(q1, q2, QuantityMeasurementApp.LengthUnit.YARDS);

        assertEquals(3.0, result.getValue(), EPSILON);
    }

    @Test
    public void testAddition_TargetSameAsSecondOperand() {
        var q1 = new QuantityMeasurementApp.QuantityLength(2.0,
                QuantityMeasurementApp.LengthUnit.YARDS);
        var q2 = new QuantityMeasurementApp.QuantityLength(3.0,
                QuantityMeasurementApp.LengthUnit.FEET);

        var result = QuantityMeasurementApp.QuantityLength
                .add(q1, q2, QuantityMeasurementApp.LengthUnit.FEET);

        assertEquals(9.0, result.getValue(), EPSILON);
    }

    @Test
    public void testAddition_Commutativity_WithTarget() {
        var q1 = new QuantityMeasurementApp.QuantityLength(1.0,
                QuantityMeasurementApp.LengthUnit.FEET);
        var q2 = new QuantityMeasurementApp.QuantityLength(12.0,
                QuantityMeasurementApp.LengthUnit.INCHES);

        var r1 = QuantityMeasurementApp.QuantityLength
                .add(q1, q2, QuantityMeasurementApp.LengthUnit.YARDS);

        var r2 = QuantityMeasurementApp.QuantityLength
                .add(q2, q1, QuantityMeasurementApp.LengthUnit.YARDS);

        assertEquals(r1.getValue(), r2.getValue(), EPSILON);
    }

    @Test
    public void testAddition_WithZero_TargetUnit() {
        var q1 = new QuantityMeasurementApp.QuantityLength(5.0,
                QuantityMeasurementApp.LengthUnit.FEET);
        var q2 = new QuantityMeasurementApp.QuantityLength(0.0,
                QuantityMeasurementApp.LengthUnit.INCHES);

        var result = QuantityMeasurementApp.QuantityLength
                .add(q1, q2, QuantityMeasurementApp.LengthUnit.YARDS);

        assertEquals(1.6666, result.getValue(), 1e-3);
    }

    @Test
    public void testAddition_NegativeValues_TargetUnit() {
        var q1 = new QuantityMeasurementApp.QuantityLength(5.0,
                QuantityMeasurementApp.LengthUnit.FEET);
        var q2 = new QuantityMeasurementApp.QuantityLength(-2.0,
                QuantityMeasurementApp.LengthUnit.FEET);

        var result = QuantityMeasurementApp.QuantityLength
                .add(q1, q2, QuantityMeasurementApp.LengthUnit.INCHES);

        assertEquals(36.0, result.getValue(), EPSILON);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddition_NullTargetUnit() {
        var q1 = new QuantityMeasurementApp.QuantityLength(1.0,
                QuantityMeasurementApp.LengthUnit.FEET);
        var q2 = new QuantityMeasurementApp.QuantityLength(12.0,
                QuantityMeasurementApp.LengthUnit.INCHES);

        QuantityMeasurementApp.QuantityLength.add(q1, q2, null);
    }

    @Test
    public void testAddition_LargeToSmallScale() {
        var q1 = new QuantityMeasurementApp.QuantityLength(1000.0,
                QuantityMeasurementApp.LengthUnit.FEET);
        var q2 = new QuantityMeasurementApp.QuantityLength(500.0,
                QuantityMeasurementApp.LengthUnit.FEET);

        var result = QuantityMeasurementApp.QuantityLength
                .add(q1, q2, QuantityMeasurementApp.LengthUnit.INCHES);

        assertEquals(18000.0, result.getValue(), EPSILON);
    }

    @Test
    public void testAddition_SmallToLargeScale() {
        var q1 = new QuantityMeasurementApp.QuantityLength(12.0,
                QuantityMeasurementApp.LengthUnit.INCHES);
        var q2 = new QuantityMeasurementApp.QuantityLength(12.0,
                QuantityMeasurementApp.LengthUnit.INCHES);

        var result = QuantityMeasurementApp.QuantityLength
                .add(q1, q2, QuantityMeasurementApp.LengthUnit.YARDS);

        assertEquals(0.6666, result.getValue(), 1e-3);
    }
}