/*
 * Copyright © 2010 Sebastian Hoß <mail@shoss.de>
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See http://www.wtfpl.net/ for more details.
 */
package com.github.sebhoss.reguloj;

import java.util.function.Predicate;

import com.github.sebhoss.warnings.CompilerWarnings;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;

/**
 * Test cases for the RuleImplementation.
 */
@SuppressWarnings({ CompilerWarnings.UNCHECKED })
public final class RuleImplementationTest {

    /** Constant name for all rules inside this test. */
    private static final String         NAME = "test rule"; //$NON-NLS-1$

    private Context<Object>             context;
    private Predicate<Context<Object>>  predicate;
    private Conclusion<Context<Object>> conclusion;

    /**
     * Creates rule engine, context and rules.
     */
    @Before
    public void setup() {
        context = Mockito.mock(Context.class);
        predicate = Mockito.mock(Predicate.class);
        conclusion = Mockito.mock(Conclusion.class);
    }

    /**
     * Ensures that a valid rule will be built once all values are given.
     */
    @Test
    public void shouldCreateRuleIfAllValuesAreSet() {
        final Rule<Context<Object>> rule = new RuleImplementation<>(RuleImplementationTest.NAME,
                predicate, conclusion);

        Assert.assertNotNull(rule);
    }

    /**
     * Ensures that <code>false</code> is returned if the predicate does not apply.
     */
    @Test
    @SuppressWarnings(CompilerWarnings.BOXING)
    public void shouldReturnFalseWhenPremiseDoesNotApply() {
        BDDMockito.given(predicate.test(context)).willReturn(Boolean.FALSE);

        final Rule<Context<Object>> rule = new RuleImplementation<>(RuleImplementationTest.NAME,
                predicate, conclusion);

        Assert.assertFalse(rule.run(context));
    }

    /**
     * Ensures that <code>false</code> is returned if the conclusion does not apply.
     */
    @Test
    @SuppressWarnings(CompilerWarnings.BOXING)
    public void shouldReturnFalseWhenConclusionDoesNotApply() {
        BDDMockito.given(predicate.test(context)).willReturn(Boolean.TRUE);
        BDDMockito.given(conclusion.apply(context)).willReturn(Boolean.FALSE);

        final Rule<Context<Object>> rule = new RuleImplementation<>(RuleImplementationTest.NAME,
                predicate, conclusion);

        Assert.assertFalse(rule.run(context));
    }

    /**
     * Ensures that <code>false</code> is returned if the conclusion does not apply.
     */
    @Test
    @SuppressWarnings(CompilerWarnings.BOXING)
    public void shouldReturnTrueWhenPremiseAndConclusionApply() {
        BDDMockito.given(predicate.test(context)).willReturn(Boolean.TRUE);
        BDDMockito.given(conclusion.apply(context)).willReturn(Boolean.TRUE);

        final Rule<Context<Object>> rule = new RuleImplementation<>(RuleImplementationTest.NAME,
                predicate, conclusion);

        Assert.assertTrue(rule.run(context));
    }

    /**
     * Ensures that <code>true</code> is returned if the predicate does apply.
     */
    @Test
    @SuppressWarnings(CompilerWarnings.BOXING)
    public void shouldFireWhenPremiseApplies() {
        BDDMockito.given(predicate.test(context)).willReturn(true);

        final Rule<Context<Object>> rule = new RuleImplementation<>(RuleImplementationTest.NAME,
                predicate, conclusion);

        Assert.assertTrue(rule.fires(context));
    }

    /**
     * Ensures that <code>false</code> is returned if the predicate does not apply.
     */
    @Test
    @SuppressWarnings(CompilerWarnings.BOXING)
    public void shouldNotFireWhenPremiseDoesNotApply() {
        BDDMockito.given(predicate.test(context)).willReturn(false);

        final Rule<Context<Object>> rule = new RuleImplementation<>(RuleImplementationTest.NAME,
                predicate, conclusion);

        Assert.assertFalse(rule.fires(context));
    }

    /**
     * Ensures that the previously set name is returned.
     */
    @Test
    public void shouldReturnTheSetName() {
        final Rule<Context<Object>> rule = new RuleImplementation<>(RuleImplementationTest.NAME,
                predicate, conclusion);

        Assert.assertEquals(rule.getName(), RuleImplementationTest.NAME);
    }

    /**
     * Ensures that equals is reflexive
     */
    @Test
    public void equalsIsReflexive() {
        final Rule<Context<Object>> rule = new RuleImplementation<>(RuleImplementationTest.NAME,
                predicate, conclusion);

        Assert.assertEquals(rule, rule);
    }

    /**
     * Ensures that equals is symmetric
     */
    @Test
    public void equalsIsSymmetric() {
        final Rule<Context<Object>> rule1 = new RuleImplementation<>(RuleImplementationTest.NAME,
                predicate, conclusion);
        final Rule<Context<Object>> rule2 = new RuleImplementation<>(RuleImplementationTest.NAME,
                predicate, conclusion);

        Assert.assertEquals(rule1, rule2);
        Assert.assertEquals(rule2, rule1);
    }

    /**
     * Ensures that equals is transitive
     */
    @Test
    public void equalsIsTransitive() {
        final Rule<Context<Object>> rule1 = new RuleImplementation<>(RuleImplementationTest.NAME,
                predicate, conclusion);
        final Rule<Context<Object>> rule2 = new RuleImplementation<>(RuleImplementationTest.NAME,
                predicate, conclusion);
        final Rule<Context<Object>> rule3 = new RuleImplementation<>(RuleImplementationTest.NAME,
                predicate, conclusion);

        Assert.assertEquals(rule1, rule2);
        Assert.assertEquals(rule2, rule3);
        Assert.assertEquals(rule3, rule1);
    }

    /**
     * Ensures that equals is returns <code>false</code> on <code>null</code> values.
     */
    @Test
    public void equalsReturnFalseOnNull() {
        final Rule<Context<Object>> rule = new RuleImplementation<>(RuleImplementationTest.NAME,
                predicate, conclusion);

        Assert.assertNotEquals(rule, null);
    }

    /**
     * Ensures that equals is returns <code>false</code> on <code>null</code> values.
     */
    @Test
    public void equalsReturnFalseOnWrongClass() {
        final Rule<Context<Object>> rule = new RuleImplementation<>(RuleImplementationTest.NAME,
                predicate, conclusion);

        Assert.assertNotEquals(rule, ""); //$NON-NLS-1$
    }

    /**
     * Ensures that equals works with identical instances.
     */
    @Test
    public void equalsWorks() {
        final Rule<Context<Object>> rule1 = new RuleImplementation<>(RuleImplementationTest.NAME,
                predicate, conclusion);
        final Rule<Context<Object>> rule2 = rule1;

        Assert.assertEquals(rule1, rule2);
    }

    /**
     * Ensures that equals works with different names
     */
    @Test
    public void equalsWorksWithDifferentNames() {
        final Rule<Context<Object>> rule1 = new RuleImplementation<>(RuleImplementationTest.NAME,
                predicate, conclusion);
        final Rule<Context<Object>> rule2 = new RuleImplementation<>("rule2", predicate, conclusion); //$NON-NLS-1$

        Assert.assertNotEquals(rule1, rule2);
    }

    /**
     * Ensures that equals works with different predicates.
     */
    @Test
    public void equalsWorksWithDifferentPremises() {
        final Predicate<Context<Object>> predicate2 = Mockito.mock(Predicate.class);

        final Rule<Context<Object>> rule1 = new RuleImplementation<>(RuleImplementationTest.NAME,
                predicate,
                conclusion);
        final Rule<Context<Object>> rule2 = new RuleImplementation<>(RuleImplementationTest.NAME,
                predicate2,
                conclusion);

        Assert.assertNotEquals(rule1, rule2);
    }

    /**
     * Ensures that equals works with different conclusions.
     */
    @Test
    public void equalsWorksWithDifferentConclusions() {
        final Conclusion<Context<Object>> conclusion2 = Mockito.mock(Conclusion.class);

        final Rule<Context<Object>> rule1 = new RuleImplementation<>(RuleImplementationTest.NAME,
                predicate,
                conclusion);
        final Rule<Context<Object>> rule2 = new RuleImplementation<>(RuleImplementationTest.NAME,
                predicate,
                conclusion2);

        Assert.assertNotEquals(rule1, rule2);
    }

    /**
     * Ensures that hashCode() is consistent with equals().
     */
    @Test
    public void hashCodeIsConsistentWithEquals() {
        final Rule<Context<Object>> rule1 = new RuleImplementation<>(RuleImplementationTest.NAME,
                predicate, conclusion);
        final Rule<Context<Object>> rule2 = new RuleImplementation<>(RuleImplementationTest.NAME,
                predicate, conclusion);

        Assert.assertEquals(rule1, rule2);
        Assert.assertEquals(rule1.hashCode(), rule2.hashCode());
    }

}
