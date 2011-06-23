/*
 * Project: viaRules-core
 * Package: de.viadee.rules
 * File   : ConclusionsTest.java
 * Created: Nov 10, 2010 - 5:55:55 PM
 *
 *
 * Copyright 2010 viadee IT Unternehmensberatung GmbH
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package de.viadee.rules;

import java.lang.reflect.Constructor;
import java.util.Collection;

import org.hamcrest.core.Is;
import org.hamcrest.core.IsNull;
import org.junit.Assert;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mockito;

import com.google.common.collect.Lists;

/**
 * <p>Test cases for the {@link Conclusions} utility class.</p>
 *
 * @author  Sebastian Hoß (sebastian.hoss@viadee.de)
 * @see     Conclusions
 * @since   1.0.0
 */
public final class ConclusionsTest {

    // * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
    // *                                                  ATTRIBUTES                                                 *
    // * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *

    /** Checks expected exception inside single test cases. */
    @org.junit.Rule
    public ExpectedException thrown = ExpectedException.none();

    // * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
    // *                                                    TESTS                                                    *
    // * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *

    /**
     * <p>Test method for {@link Conclusions#conclude(java.util.Collection)}</p>
     * 
     * <p>Ensures that passing in a <code>null</code> collection is not allowed.</p>
     */
    @Test
    public void shouldNotAllowNullCollection() {
        // given
        final Collection<Conclusion<String>> conclusions = null;

        // when
        this.thrown.expect(NullPointerException.class);

        // then
        Conclusions.conclude(conclusions);
    }

    /**
     * <p>Test method for {@link Conclusions#conclude(java.util.Collection)}</p>
     * 
     * <p>Ensures that passing in an empty collection is not allowed.</p>
     */
    @Test
    public void shouldNotAllowEmptyCollection() {
        // given
        final Collection<Conclusion<String>> conclusions = Lists.newArrayList();

        // when
        this.thrown.expect(IllegalArgumentException.class);

        // then
        Conclusions.conclude(conclusions);
    }

    /**
     * <p>Test method for {@link Conclusions#conclude(Conclusion, Conclusion)}</p>
     * 
     * <p>Ensures that the returned object is not <code>null</code> when passing in a multiple conclusions.</p>
     */
    @Test
    public void shouldCreateCompositionFromMultipleConclusions() {
        // given
        final Conclusion<String> conclusion1 = Mockito.mock(Conclusion.class);
        final Conclusion<String> conclusion2 = Mockito.mock(Conclusion.class);

        // when
        final Conclusion<String> composition = Conclusions.conclude(conclusion1, conclusion2);

        // then
        Assert.assertThat(composition, Is.is(IsNull.notNullValue()));
    }

    /**
     * <p>Test method for {@link Conclusions Conclusions()}</p>
     * 
     * <p>Ensures that the constructor is not accessible from the outside.</p>
     */
    @Test
    public void shouldNotBeInvocable() {
        // given
        final Class<?> clazz = Conclusions.class;

        // when
        final Constructor<?>[] constructors = clazz.getDeclaredConstructors();

        // then
        for (final Constructor<?> constructor : constructors) {
            Assert.assertFalse(constructor.isAccessible());
        }
    }

}
