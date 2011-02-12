/*
 * Project: viaRules-core
 * Package: de.viadee.rules.implementation
 * File   : RulesTest.java
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

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;

import java.lang.reflect.Constructor;

import org.junit.Test;

import com.google.common.base.Predicate;

/**
 * <p>Test cases for the {@link Rules} utility class.</p>
 * 
 * @author  Sebastian Hoß (sebastian.hoss@viadee.de)
 * @see     Rules
 * @since   1.0.0
 */
public final class RulesTest {

    // * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
    // *                                                     CONSTANTS                                                     *
    // * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *

    /** Constant name for all rules inside this test. */
    private static final String NAME = "test rule"; //$NON-NLS-1$

    // * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
    // *                                                       TESTS                                                       *
    // * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *

    /**
     * <p>Test method for {@link Rules#rule()}</p>
     * 
     * <p>Ensures that the returned object is not <code>null</code>.</p>
     */
    @Test
    public void shouldCreateBuilder() {
        // given
        RuleBuilder<InferenceContext<Object>> builder;

        // when
        builder = Rules.<InferenceContext<Object>> rule();

        // then
        assertThat(builder, is(notNullValue()));
    }

    /**
     * <p>Test method for {@link Rules#rule()}</p>
     * 
     * <p>Ensures that the returned object is not <code>null</code>.</p>
     */
    @SuppressWarnings("unchecked")
    @Test
    public void shouldCreateRule() {
        // given
        final RuleBuilder<InferenceContext<Object>> builder = Rules.<InferenceContext<Object>> rule();
        builder.called(NAME).when(mock(Predicate.class));

        // when
        final Rule<InferenceContext<Object>> rule = builder.then(mock(Conclusion.class));

        // then
        assertThat(rule, is(notNullValue()));
    }

    /**
     * <p>Test method for {@link Rules Rules()}</p>
     * 
     * <p>Ensures that the constructor is not accessible from the outside.</p>
     */
    @Test
    public void shouldNotBeInvocable() {
        // given
        final Class<?> clazz = Rules.class;

        // when
        final Constructor<?>[] constructors = clazz.getDeclaredConstructors();

        // then
        for (final Constructor<?> constructor : constructors) {
            assertFalse(constructor.isAccessible());
        }
    }

    /**
     * <p>Test method for {@link Rules Rules()}</p>
     * 
     * <p>Ensures that the constructor is accessible via reflection.</p>
     * 
     * @throws Exception 	When no new instance can be created.
     */
    @Test
    public void shouldBeInvocableViaReflection() throws Exception {
        // given
        final Class<?> clazz = Rules.class;
        final Constructor<?> constructor = clazz.getDeclaredConstructors()[0];

        // when
        constructor.setAccessible(true);
        final Object instance = constructor.newInstance((Object[]) null);

        // then
        assertThat(instance, is(notNullValue()));
    }

}
