package com.apps.quantitymeasurement;

import org.junit.Test;
import static org.junit.Assert.*;

public class LengthTest {

    @Test
    public void testFeetToInchEqual() {
        assertTrue(new Length(1, LengthUnit.FEET)
                .equals(new Length(12, LengthUnit.INCHES)));
    }

    @Test
    public void testAddition() {
        Length l1 = new Length(1, LengthUnit.FEET);
        Length l2 = new Length(12, LengthUnit.INCHES);

        Length result = l1.add(l2);

        assertTrue(result.equals(new Length(2, LengthUnit.FEET)));
    }

    @Test
    public void testSubtraction() {
        Length l1 = new Length(2, LengthUnit.FEET);
        Length l2 = new Length(12, LengthUnit.INCHES);

        Length result = l1.subtract(l2);

        assertTrue(result.equals(new Length(1, LengthUnit.FEET)));
    }

    @Test
    public void testDivision() {
        Length l1 = new Length(2, LengthUnit.FEET);
        Length l2 = new Length(1, LengthUnit.FEET);

        assertEquals(2.0, l1.divide(l2), 0.0);
    }

    @Test(expected = ArithmeticException.class)
    public void testDivideByZero() {
        new Length(2, LengthUnit.FEET)
                .divide(new Length(0, LengthUnit.FEET));
    }
}