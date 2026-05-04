package com.apps.quantitymeasurement;

import org.junit.Test;
import static org.junit.Assert.*;

public class LengthTest {

    @Test
    public void testAddition() {
        assertTrue(new Length(1, LengthUnit.FEET)
                .add(new Length(12, LengthUnit.INCHES))
                .equals(new Length(2, LengthUnit.FEET)));
    }

    @Test
    public void testSubtraction() {
        assertTrue(new Length(2, LengthUnit.FEET)
                .subtract(new Length(12, LengthUnit.INCHES))
                .equals(new Length(1, LengthUnit.FEET)));
    }

    @Test
    public void testDivision() {
        assertEquals(2.0,
                new Length(2, LengthUnit.FEET)
                        .divide(new Length(1, LengthUnit.FEET)),
                0.0);
    }

    @Test(expected = ArithmeticException.class)
    public void testDivideByZero() {
        new Length(2, LengthUnit.FEET)
                .divide(new Length(0, LengthUnit.FEET));
    }
}