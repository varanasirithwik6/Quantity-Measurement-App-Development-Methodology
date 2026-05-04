package com.apps.quantitymeasurement;

import org.junit.Test;
import static org.junit.Assert.*;

public class LengthTest {

    @Test
    public void testFeetToFeetSameValue() {
        assertTrue(new Length(1.0, LengthUnit.FEET)
                .equals(new Length(1.0, LengthUnit.FEET)));
    }

    @Test
    public void testInchToInchSameValue() {
        assertTrue(new Length(1.0, LengthUnit.INCHES)
                .equals(new Length(1.0, LengthUnit.INCHES)));
    }

    @Test
    public void testFeetToInchEqual() {
        assertTrue(new Length(1.0, LengthUnit.FEET)
                .equals(new Length(12.0, LengthUnit.INCHES)));
    }

    @Test
    public void testInchToFeetEqual() {
        assertTrue(new Length(12.0, LengthUnit.INCHES)
                .equals(new Length(1.0, LengthUnit.FEET)));
    }

    @Test
    public void testDifferentValues() {
        assertFalse(new Length(1.0, LengthUnit.FEET)
                .equals(new Length(2.0, LengthUnit.FEET)));
    }

    @Test
    public void testSameReference() {
        Length l1 = new Length(1.0, LengthUnit.FEET);
        assertTrue(l1.equals(l1));
    }

    @Test
    public void testNullComparison() {
        assertFalse(new Length(1.0, LengthUnit.FEET).equals(null));
    }
}