import org.junit.Test;
import static org.junit.Assert.*;

public class QuantityMeasurementAppTest {

    @Test
    public void givenSameFeetValues_shouldReturnTrue() {
        QuantityMeasurementApp.Feet f1 = new QuantityMeasurementApp.Feet(1.0);
        QuantityMeasurementApp.Feet f2 = new QuantityMeasurementApp.Feet(1.0);

        assertTrue(f1.equals(f2));
    }

    @Test
    public void givenDifferentFeetValues_shouldReturnFalse() {
        QuantityMeasurementApp.Feet f1 = new QuantityMeasurementApp.Feet(1.0);
        QuantityMeasurementApp.Feet f2 = new QuantityMeasurementApp.Feet(2.0);

        assertFalse(f1.equals(f2));
    }

    @Test
    public void givenNull_shouldReturnFalse() {
        QuantityMeasurementApp.Feet f1 = new QuantityMeasurementApp.Feet(1.0);

        assertFalse(f1.equals(null));
    }

    @Test
    public void givenDifferentType_shouldReturnFalse() {
        QuantityMeasurementApp.Feet f1 = new QuantityMeasurementApp.Feet(1.0);

        assertFalse(f1.equals("1.0"));
    }

    @Test
    public void givenSameReference_shouldReturnTrue() {
        QuantityMeasurementApp.Feet f1 = new QuantityMeasurementApp.Feet(1.0);

        assertTrue(f1.equals(f1));
    }
}
