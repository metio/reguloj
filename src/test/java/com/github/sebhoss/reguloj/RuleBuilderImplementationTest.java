/*
 * Copyright © 2010 Sebastian Hoß <mail@shoss.de>
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See http://www.wtfpl.net/ for more details.
 */
package com.github.sebhoss.reguloj;

import com.github.sebhoss.common.annotation.CompilerWarnings;
import com.google.common.base.Predicate;

import org.hamcrest.core.Is;
import org.hamcrest.core.IsNull;
import org.junit.Assert;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mockito;

/**
 * Test cases for the RuleBuilderImplementation.
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
     * Test method for RuleBuilderImplementation#then(Conclusion)
     * </p>
     * <p>
     * Ensures that rules can be created with a valid RuleBuilderImplementation.
     * </p>
     */
    @Test
    @SuppressWarnings(CompilerWarnings.UNCHECKED)
    public void shouldCreateRuleIfAllValuesAreSet() {
        final RuleBuilder<Context<Object>> builder = new RuleBuilderImplementation<>();
        builder.called(RuleBuilderImplementationTest.NAME).when(Mockito.mock(Predicate.class));

        final Rule<Context<Object>> rule = builder.then(Mockito.mock(Conclusion.class));

        Assert.assertThat(rule, Is.is(IsNull.notNullValue()));
    }

    /**
     * <p>
     * Test method for RuleBuilderImplementation#when(Predicate)
     * </p>
     * <p>
     * Ensures that no <code>null</code> premise can be used.
     * </p>
     */
    @Test
    public void shouldNotAcceptNullPredicate() {
        final RuleBuilder<Context<Object>> builder = new RuleBuilderImplementation<>();

        thrown.expect(NullPointerException.class);

        builder.when(null);
    }

    /**
     * <p>
     * Test method for RuleBuilderImplementation#then(Conclusion)
     * </p>
     * <p>
     * Ensures that no <code>null</code> conclusion can be used.
     * </p>
     */
    @Test
    public void shouldNotAcceptNullConclusion() {
        final RuleBuilder<Context<Object>> builder = new RuleBuilderImplementation<>();

        thrown.expect(NullPointerException.class);

        builder.then(null);
    }

    /**
     * <p>
     * Test method for RuleBuilderImplementation#called(String)
     * </p>
     * <p>
     * Ensures that no <code>null</code> string can be used.
     * </p>
     */
    @Test
    public void shouldNotAcceptNullName() {
        final RuleBuilder<Context<Object>> builder = new RuleBuilderImplementation<>();

        thrown.expect(NullPointerException.class);

        builder.called(null);
    }
}
