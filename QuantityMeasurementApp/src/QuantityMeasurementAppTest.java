import org.junit.Test;
import static org.junit.Assert.*;

public class QuantityMeasurementAppTest {

    // ---- Feet Tests ----

    @Test
    public void testFeetEquality_SameValue() {
        assertTrue(QuantityMeasurementApp.compareFeet(1.0, 1.0));
    }

    @Test
    public void testFeetEquality_DifferentValue() {
        assertFalse(QuantityMeasurementApp.compareFeet(1.0, 2.0));
    }

    @Test
    public void testFeetEquality_SameReference() {
        QuantityMeasurementApp.Feet f = new QuantityMeasurementApp.Feet(1.0);
        assertTrue(f.equals(f));
    }

    @Test
    public void testFeetEquality_NullComparison() {
        QuantityMeasurementApp.Feet f = new QuantityMeasurementApp.Feet(1.0);
        assertFalse(f.equals(null));
    }

    @Test
    public void testFeetEquality_DifferentType() {
        QuantityMeasurementApp.Feet f = new QuantityMeasurementApp.Feet(1.0);
        assertFalse(f.equals("1.0"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFeetEquality_NonNumericInput() {
        new QuantityMeasurementApp.Feet(Double.NaN);
    }


    // ---- Inch Tests ----

    @Test
    public void testInchEquality_SameValue() {
        assertTrue(QuantityMeasurementApp.compareInch(1.0, 1.0));
    }

    @Test
    public void testInchEquality_DifferentValue() {
        assertFalse(QuantityMeasurementApp.compareInch(1.0, 2.0));
    }

    @Test
    public void testInchEquality_SameReference() {
        QuantityMeasurementApp.Inch i = new QuantityMeasurementApp.Inch(1.0);
        assertTrue(i.equals(i));
    }

    @Test
    public void testInchEquality_NullComparison() {
        QuantityMeasurementApp.Inch i = new QuantityMeasurementApp.Inch(1.0);
        assertFalse(i.equals(null));
    }

    @Test
    public void testInchEquality_DifferentType() {
        QuantityMeasurementApp.Inch i = new QuantityMeasurementApp.Inch(1.0);
        assertFalse(i.equals(1));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInchEquality_NonNumericInput() {
        new QuantityMeasurementApp.Inch(Double.NaN);
    }
}