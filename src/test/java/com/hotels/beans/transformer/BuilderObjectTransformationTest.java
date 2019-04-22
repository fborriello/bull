/**
 * Copyright (C) 2019 Expedia Inc.
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

package com.hotels.beans.transformer;

import static org.mockito.MockitoAnnotations.initMocks;
import static org.junit.Assert.assertThat;

import static com.shazam.shazamcrest.matcher.Matchers.sameBeanAs;

import org.mockito.InjectMocks;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.hotels.beans.sample.builder.BuilderToFooWithTwoConstructor;
import com.hotels.beans.sample.builder.BuilderToFoo;
import com.hotels.beans.sample.builder.LombokBuilderToFoo;


/**
 * Unit test for all {@link Transformer} functions related to Object based on Builder Pattern.
 */
public class BuilderObjectTransformationTest extends AbstractTransformerTest {
    /**
     * The class to be tested.
     */
    @InjectMocks
    private TransformerImpl underTest;

    /**
     * Initialized mocks.
     */
    @BeforeMethod
    public void beforeMethod() {
        initMocks(this);
    }

    /**
     * Test mutable beans are correctly copied through builder.
     * @param testDescription the test case description
     * @param sourceObject the object to transform
     * @param targetObjectClass the target object class
     */
    @Test(dataProvider = "transformationThroughBuilderTesting")
    public void testTransformationThroughBuilder(final String testDescription, final Object sourceObject, final Class<?> targetObjectClass) {
        //GIVEN

        //WHEN
        Object actual = underTest.transform(sourceObject, targetObjectClass);

        //THEN
        assertThat(actual, sameBeanAs(sourceObject));
    }

    /**
     * Creates the parameters to be used for testing the bean transformation through builder.
     * @return parameters to be used for testing the bean transformation through builder.
     */
    @DataProvider
    private Object[][] transformationThroughBuilderTesting() {
        return new Object[][] {
                {"Test beans are correctly copied if annotated with {@link lombok.Builder} annotation", fromFoo, LombokBuilderToFoo.class},
                {"Test beans are correctly copied if contains a custom Builder", fromFoo, BuilderToFoo.class},
                {"Test beans are correctly copied if contains a custom Builder with two constructors", fromFoo, BuilderToFooWithTwoConstructor.class}
        };
    }
}
