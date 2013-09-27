/* This program is free software. It comes without any warranty, to
 * the extent permitted by applicable law. You can redistribute it
 * and/or modify it under the terms of the Do What The Fuck You Want
 * To Public License, Version 2, as published by Sam Hocevar. See
 * http://sam.zoy.org/wtfpl/COPYING for more details.
 */
package com.github.sebhoss.reguloj.implementation;

import org.hamcrest.core.Is;
import org.hamcrest.core.IsNull;
import org.junit.Assert;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mockito;

import com.github.sebhoss.common.annotation.CompilerWarnings;
import com.github.sebhoss.reguloj.Conclusion;
import com.github.sebhoss.reguloj.Context;
import com.github.sebhoss.reguloj.Rule;
import com.github.sebhoss.reguloj.RuleBuilder;
import com.google.common.base.Predicate;

/**
 * Test cases for the {@link RuleBuilderImplementation}.
 * 
 * @see RuleBuilderImplementation
 */
@SuppressWarnings({ CompilerWarnings.NULL, CompilerWarnings.STATIC_METHOD })
public final class RuleBuilderImplementationTest {

    /** Constant name for all rules inside this test. */
    private static final String NAME   = "test rule";             //$NON-NLS-1$

    /** Checks expected exception inside single test cases. */
    @org.junit.Rule
    public ExpectedException    thrown = ExpectedException.none();

    /**
     * <p>
     * Test method for {@link RuleBuilderImplementation#then(Conclusion)}
     * </p>
     * <p>
     * Ensures that rules can be created with a valid RuleBuilderImplementation.
     * </p>
     */
    @Test
    public void shouldCreateRuleIfAllValuesAreSet() {
        final RuleBuilder<Context<Object>> builder = new RuleBuilderImplementation<Context<Object>>();
        builder.called(RuleBuilderImplementationTest.NAME).when(Mockito.mock(Predicate.class));

        final Rule<Context<Object>> rule = builder.then(Mockito.mock(Conclusion.class));

        Assert.assertThat(rule, Is.is(IsNull.notNullValue()));
    }

    /**
     * <p>
     * Test method for {@link RuleBuilderImplementation#when(Predicate)}
     * </p>
     * <p>
     * Ensures that no <code>null</code> premise can be used.
     * </p>
     */
    @Test
    public void shouldNotAcceptNullPredicate() {
        final RuleBuilder<Context<Object>> builder = new RuleBuilderImplementation<Context<Object>>();

        thrown.expect(NullPointerException.class);

        builder.when(null);
    }

    /**
     * <p>
     * Test method for {@link RuleBuilderImplementation#then(Conclusion)}
     * </p>
     * <p>
     * Ensures that no <code>null</code> conclusion can be used.
     * </p>
     */
    @Test
    public void shouldNotAcceptNullConclusion() {
        final RuleBuilder<Context<Object>> builder = new RuleBuilderImplementation<Context<Object>>();

        thrown.expect(NullPointerException.class);

        builder.then(null);
    }

    /**
     * <p>
     * Test method for {@link RuleBuilderImplementation#called(String)}
     * </p>
     * <p>
     * Ensures that no <code>null</code> string can be used.
     * </p>
     */
    @Test
    public void shouldNotAcceptNullName() {
        final RuleBuilder<Context<Object>> builder = new RuleBuilderImplementation<Context<Object>>();

        thrown.expect(NullPointerException.class);

        builder.called(null);
    }
}
