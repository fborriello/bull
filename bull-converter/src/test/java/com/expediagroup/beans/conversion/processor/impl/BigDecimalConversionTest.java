/**
 * Copyright (C) 2019-2022 Expedia, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.expediagroup.beans.conversion.processor.impl;

import static java.lang.Character.getNumericValue;
import static java.math.BigDecimal.valueOf;
import static java.nio.ByteBuffer.wrap;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.MockitoAnnotations.openMocks;

import java.math.BigDecimal;
import java.math.BigInteger;

import org.mockito.InjectMocks;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.expediagroup.beans.conversion.AbstractConversionTest;
import com.expediagroup.beans.conversion.error.TypeConversionException;

/**
 * Unit test for {@link  BigDecimalConversionProcessor}.
 */
public class BigDecimalConversionTest extends AbstractConversionTest {
    /**
     * The class to be tested.
     */
    @InjectMocks
    private BigDecimalConversionProcessor underTest;

    /**
     * Initializes mock.
     */
    @BeforeClass
    public void beforeClass() {
        openMocks(this);
    }

    @Test
    public void testConvertByteShouldReturnProperResult() {
        // GIVEN
        BigDecimal expected = valueOf(BYTE_VALUE.doubleValue());

        // WHEN
        BigDecimal actual = underTest.convertByte().apply(BYTE_VALUE);

        // THEN
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void testConvertByteArrayShouldReturnProperResult() {
        // GIVEN
        BigDecimal expected = valueOf(wrap(EIGHT_BYTE_BYTE_ARRAY).getDouble());

        // WHEN
        BigDecimal actual = underTest.convertByteArray().apply(EIGHT_BYTE_BYTE_ARRAY);

        // THEN
        assertThat(actual).isEqualTo(expected);
    }

    @Test(expectedExceptions = TypeConversionException.class)
    public void testConvertByteArrayShouldThrowExceptionIfByteArrayIsTooSmall() {
        // GIVEN

        // WHEN
        underTest.convertByteArray().apply(ONE_BYTE_BYTE_ARRAY);
    }

    @Test
    public void testConvertShortShouldReturnProperResult() {
        // GIVEN
        BigDecimal expected = valueOf(SHORT_VALUE);

        // WHEN
        BigDecimal actual = underTest.convertShort().apply(SHORT_VALUE);

        // THEN
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void testConvertIntegerShouldReturnProperResult() {
        // GIVEN
        BigDecimal expected = valueOf(INTEGER_VALUE);

        // WHEN
        BigDecimal actual = underTest.convertInteger().apply(INTEGER_VALUE);

        // THEN
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void testConvertLongShouldReturnProperResult() {
        // GIVEN
        BigDecimal expected = valueOf(LONG_VALUE);

        // WHEN
        BigDecimal actual = underTest.convertLong().apply(LONG_VALUE);

        // THEN
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void testConvertFloatShouldReturnProperResult() {
        // GIVEN
        BigDecimal expected = valueOf(FLOAT_VALUE);

        // WHEN
        BigDecimal actual = underTest.convertFloat().apply(FLOAT_VALUE);

        // THEN
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void testConvertDoubleShouldReturnProperResult() {
        // GIVEN
        BigDecimal expected = valueOf(DOUBLE_VALUE);

        // WHEN
        BigDecimal actual = underTest.convertDouble().apply(DOUBLE_VALUE);

        // THEN
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void testConvertCharacterShouldReturnProperResult() {
        // GIVEN
        BigDecimal expected = valueOf(getNumericValue(CHAR_VALUE));

        // WHEN
        BigDecimal actual = underTest.convertCharacter().apply(CHAR_VALUE);

        // THEN
        assertThat(actual).isEqualTo(expected);
    }

    /**
     * Tests that the method {@code convertBoolean} returns the expected BigDecimal.
     * @param testCaseDescription the test case description
     * @param valueToConvert the value to be converted
     * @param expectedResult the expected result
     */
    @Test(dataProvider = "booleanToIntConvertValueTesting")
    public void testConvertBooleanShouldReturnProperResult(final String testCaseDescription, final boolean valueToConvert, final BigDecimal expectedResult) {
        // GIVEN

        // WHEN
        BigDecimal actual = underTest.convertBoolean().apply(valueToConvert);

        // THEN
        assertThat(actual).isEqualTo(expectedResult);
    }

    /**
     * Creates the parameters to be used for testing that the method {@code convertBoolean} returns the expected result.
     * @return parameters to be used for testing that the method {@code convertBoolean} returns the expected result.
     */
    @DataProvider
    private Object[][] booleanToIntConvertValueTesting() {
        return new Object[][]{
                {"Tests that the method returns 1 if the value is true", BOOLEAN_VALUE, BigDecimal.ONE},
                {"Tests that the method returns 0 if the value is false", Boolean.FALSE, BigDecimal.ZERO}
        };
    }

    @Test
    public void testConvertStringShouldReturnProperResult() {
        // GIVEN
        BigDecimal expected = new BigDecimal(STRING_VALUE);

        // WHEN
        BigDecimal actual = underTest.convertString().apply(STRING_VALUE);

        // THEN
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void testConvertBigIntegerShouldReturnProperResult() {
        // GIVEN

        // WHEN
        BigDecimal actual = underTest.convertBigInteger().apply(BigInteger.ZERO);

        // THEN
        assertThat(actual).isZero();
    }

    @Test
    public void testConvertBigDecimalShouldReturnProperResult() {
        // GIVEN

        // WHEN
        BigDecimal actual = underTest.convertBigDecimal().apply(BigDecimal.ZERO);

        // THEN
        assertThat(actual).isZero();
    }
}
