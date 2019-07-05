package com.hotels.beans.conversion.processor;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.MockitoAnnotations.initMocks;

import org.apache.commons.lang3.tuple.Pair;
import org.mockito.InjectMocks;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.hotels.beans.conversion.processor.impl.BooleanConversionProcessor;
import com.hotels.beans.conversion.processor.impl.ByteConversionProcessor;
import com.hotels.beans.conversion.processor.impl.CharacterConversionProcessor;
import com.hotels.beans.conversion.processor.impl.DoubleConversionProcessor;
import com.hotels.beans.conversion.processor.impl.FloatConversionProcessor;
import com.hotels.beans.conversion.processor.impl.IntegerConversionProcessor;
import com.hotels.beans.conversion.processor.impl.LongConversionProcessor;
import com.hotels.beans.conversion.processor.impl.ShortConversionProcessor;
import com.hotels.beans.conversion.processor.impl.StringConversionProcessor;

/**
 * Unit test for {@link ConversionProcessorFactory}.
 */
public class ConversionProcessorFactoryTest {
    /**
     * The class to be tested.
     */
    @InjectMocks
    private ConversionProcessorFactory underTest;

    /**
     * Initializes mock.
     */
    @BeforeClass
    public void beforeClass() {
        initMocks(this);
    }

    /**
     * Tests that the method {@code getConversionProcessor} returns null in case there is no processor defined for the given type.
     */
    @Test
    public void testGetConversionProcessorReturnsNullInCaseNoProcessorExistsForTheGivenType() {
        // GIVEN

        // WHEN
        ConversionProcessor actual = underTest.getConversionProcessor(Pair.class);

        // THEN
        assertNull(actual);
    }

    /**
     * Tests that the method {@code getConversionProcessor} returns the expected Conversion processor for the given type.
     * @param testCaseDescription the test case description
     * @param targetClass object type for which the conversion processor has to be retrieved
     * @param expectedResult the expected {@link ConversionProcessor} class for the given object type
     */
    @Test(dataProvider = "dataGetConversionProcessorTesting")
    public void testGetConversionProcessorWorksAsExpected(final String testCaseDescription, final Class<?> targetClass,
        final Class<ConversionProcessor> expectedResult) {
        // GIVEN

        // WHEN
        ConversionProcessor actual = underTest.getConversionProcessor(targetClass);

        // THEN
        assertEquals(expectedResult, actual.getClass());
    }

    /**
     * Creates the parameters to be used for testing the method {@code getConversionProcessor}.
     * @return parameters to be used for testing the the method {@code getConversionProcessor}.
     */
    @DataProvider
    private Object[][] dataGetConversionProcessorTesting() {
        return new Object[][]{
                {"Tests that the method returns a ByteConversionProcessor is case the target class ia a byte", byte.class, ByteConversionProcessor.class},
                {"Tests that the method returns a ByteConversionProcessor is case the target class ia a Byte", Byte.class, ByteConversionProcessor.class},
                {"Tests that the method returns a ShortConversionProcessor is case the target class ia a Short", Short.class, ShortConversionProcessor.class},
                {"Tests that the method returns a ShortConversionProcessor is case the target class ia a short", short.class, ShortConversionProcessor.class},
                {"Tests that the method returns a IntegerConversionProcessor is case the target class is an Integer", Integer.class, IntegerConversionProcessor.class},
                {"Tests that the method returns a IntegerConversionProcessor is case the target class is an int", int.class, IntegerConversionProcessor.class},
                {"Tests that the method returns a LongConversionProcessor is case the target class is a Long", Long.class, LongConversionProcessor.class},
                {"Tests that the method returns a LongConversionProcessor is case the target class is a long", long.class, LongConversionProcessor.class},
                {"Tests that the method returns a FloatConversionProcessor is case the target class is a Float", Float.class, FloatConversionProcessor.class},
                {"Tests that the method returns a FloatConversionProcessor is case the target class is a float", float.class, FloatConversionProcessor.class},
                {"Tests that the method returns a DoubleConversionProcessor is case the target class is a Double", Double.class, DoubleConversionProcessor.class},
                {"Tests that the method returns a DoubleConversionProcessor is case the target class is a double", double.class, DoubleConversionProcessor.class},
                {"Tests that the method returns a CharacterConversionProcessor is case the target class is a Character", Character.class, CharacterConversionProcessor.class},
                {"Tests that the method returns a CharacterConversionProcessor is case the target class is a char", char.class, CharacterConversionProcessor.class},
                {"Tests that the method returns a StringConversionProcessor is case the target class is a String", String.class, StringConversionProcessor.class},
                {"Tests that the method returns a BooleanConversionProcessor is case the target class is a Boolean", Boolean.class, BooleanConversionProcessor.class},
                {"Tests that the method returns a BooleanConversionProcessor is case the target class is a boolean", boolean.class, BooleanConversionProcessor.class}
        };
    }
}
