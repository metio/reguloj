/* This program is free software. It comes without any warranty, to
 * the extent permitted by applicable law. You can redistribute it
 * and/or modify it under the terms of the Do What The Fuck You Want
 * To Public License, Version 2, as published by Sam Hocevar. See
 * http://sam.zoy.org/wtfpl/COPYING for more details.
 */
package com.github.sebhoss.reguloj;

import java.lang.reflect.Constructor;

import org.hamcrest.core.Is;
import org.hamcrest.core.IsNull;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import com.github.sebhoss.reguloj.Conclusion;
import com.github.sebhoss.reguloj.Context;
import com.github.sebhoss.reguloj.Rule;
import com.github.sebhoss.reguloj.RuleBuilder;
import com.github.sebhoss.reguloj.Rules;
import com.google.common.base.Predicate;

/**
 * <p>Test cases for the {@link Rules} utility class.</p>
 * 
 * @see     Rules
 */
@SuppressWarnings("static-method")
public final class RulesTest {

    // * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
    // *                                                  CONSTANTS                                                  *
    // * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *

    /** Constant name for all rules inside this test. */
    private static final String NAME = "test rule"; //$NON-NLS-1$

    // * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
    // *                                                    TESTS                                                    *
    // * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *

    /**
     * <p>Test method for {@link Rules#rule()}</p>
     * 
     * <p>Ensures that the returned object is not <code>null</code>.</p>
     */
    @Test
    public void shouldCreateBuilder() {
        // given
        RuleBuilder<Context<Object>> builder;

        // when
        builder = Rules.<Context<Object>> rule();

        // then
        Assert.assertThat(builder, Is.is(IsNull.notNullValue()));
    }

    /**
     * <p>Test method for {@link Rules#rule()}</p>
     * 
     * <p>Ensures that the returned object is not <code>null</code>.</p>
     */
    @Test
    public void shouldCreateRule() {
        // given
        final RuleBuilder<Context<Object>> builder = Rules.<Context<Object>> rule();
        builder.called(RulesTest.NAME).when(Mockito.mock(Predicate.class));

        // when
        final Rule<Context<Object>> rule = builder.then(Mockito.mock(Conclusion.class));

        // then
        Assert.assertThat(rule, Is.is(IsNull.notNullValue()));
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
            Assert.assertFalse(constructor.isAccessible());
        }
    }

    /**
     * <p>Test method for {@link Rules Rules()}</p>
     * 
     * <p>Ensures that the constructor is accessible via reflection.</p>
     * 
     * @throws Exception    When no new instance can be created.
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
        Assert.assertThat(instance, Is.is(IsNull.notNullValue()));
    }

}
