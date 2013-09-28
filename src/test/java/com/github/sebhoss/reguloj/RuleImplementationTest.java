/*
 * Copyright © 2010 Sebastian Hoß <mail@shoss.de>
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See http://www.wtfpl.net/ for more details.
 */
package com.github.sebhoss.reguloj;

import com.github.sebhoss.common.annotation.CompilerWarnings;
import com.google.common.base.Predicate;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;

/**
 * Test cases for the RuleImplementation.
 */
@SuppressWarnings({ CompilerWarnings.NULL, CompilerWarnings.STATIC_METHOD, CompilerWarnings.UNCHECKED })
public final class RuleImplementationTest {

    /** Constant name for all rules inside this test. */
    private static final String NAME = "test rule"; //$NON-NLS-1$

    /**
     * Ensures that a valid rule will be built once all values are given.
     */
    @Test
    public void shouldCreateRuleIfAllValuesAreSet() {
        final Predicate<Context<Object>> predicate = Mockito.mock(Predicate.class);
        final Conclusion<Context<Object>> conclusion = Mockito.mock(Conclusion.class);

        final Rule<Context<Object>> rule = new RuleImplementation<>(RuleImplementationTest.NAME, predicate, conclusion);

        Assert.assertNotNull(rule);
    }

    /**
     * Ensures that <code>false</code> is returned if the predicate does not apply.
     */
    @Test
    @SuppressWarnings(CompilerWarnings.BOXING)
    public void shouldReturnFalseWhenPremiseDoesNotApply() {
        final Context<Object> context = Mockito.mock(Context.class);
        final Predicate<Context<Object>> predicate = Mockito.mock(Predicate.class);
        BDDMockito.given(predicate.apply(context)).willReturn(Boolean.FALSE);
        final Conclusion<Context<Object>> conclusion = Mockito.mock(Conclusion.class);

        final Rule<Context<Object>> rule = new RuleImplementation<>(RuleImplementationTest.NAME, predicate, conclusion);

        Assert.assertFalse(rule.run(context));
    }

    /**
     * Ensures that <code>false</code> is returned if the conclusion does not apply.
     */
    @Test
    @SuppressWarnings(CompilerWarnings.BOXING)
    public void shouldReturnFalseWhenConclusionDoesNotApply() {
        final Context<Object> context = Mockito.mock(Context.class);
        final Predicate<Context<Object>> predicate = Mockito.mock(Predicate.class);
        BDDMockito.given(predicate.apply(context)).willReturn(Boolean.TRUE);
        final Conclusion<Context<Object>> conclusion = Mockito.mock(Conclusion.class);
        BDDMockito.given(conclusion.apply(context)).willReturn(Boolean.FALSE);

        final Rule<Context<Object>> rule = new RuleImplementation<>(RuleImplementationTest.NAME, predicate, conclusion);

        Assert.assertFalse(rule.run(context));
    }

    /**
     * Ensures that <code>false</code> is returned if the conclusion does not apply.
     */
    @Test
    @SuppressWarnings(CompilerWarnings.BOXING)
    public void shouldReturnTrueWhenPremiseAndConclusionApply() {
        final Context<Object> context = Mockito.mock(Context.class);
        final Predicate<Context<Object>> predicate = Mockito.mock(Predicate.class);
        BDDMockito.given(predicate.apply(context)).willReturn(Boolean.TRUE);
        final Conclusion<Context<Object>> conclusion = Mockito.mock(Conclusion.class);
        BDDMockito.given(conclusion.apply(context)).willReturn(Boolean.TRUE);

        final Rule<Context<Object>> rule = new RuleImplementation<>(RuleImplementationTest.NAME, predicate, conclusion);

        Assert.assertTrue(rule.run(context));
    }

    /**
     * Ensures that <code>true</code> is returned if the predicate does apply.
     */
    @Test
    @SuppressWarnings(CompilerWarnings.BOXING)
    public void shouldFireWhenPremiseApplies() {
        final Context<Object> context = Mockito.mock(Context.class);
        final Predicate<Context<Object>> predicate = Mockito.mock(Predicate.class);
        BDDMockito.given(predicate.apply(context)).willReturn(true);
        final Conclusion<Context<Object>> conclusion = Mockito.mock(Conclusion.class);

        final Rule<Context<Object>> rule = new RuleImplementation<>(RuleImplementationTest.NAME, predicate, conclusion);

        Assert.assertTrue(rule.fires(context));
    }

    /**
     * Ensures that <code>false</code> is returned if the predicate does not apply.
     */
    @Test
    @SuppressWarnings(CompilerWarnings.BOXING)
    public void shouldNotFireWhenPremiseDoesNotApply() {
        final Context<Object> context = Mockito.mock(Context.class);
        final Predicate<Context<Object>> predicate = Mockito.mock(Predicate.class);
        BDDMockito.given(predicate.apply(context)).willReturn(false);
        final Conclusion<Context<Object>> conclusion = Mockito.mock(Conclusion.class);

        final Rule<Context<Object>> rule = new RuleImplementation<>(RuleImplementationTest.NAME, predicate, conclusion);

        Assert.assertFalse(rule.fires(context));
    }

    /**
     * Ensures that the previously set name is returned.
     */
    @Test
    public void shouldReturnTheSetName() {
        final Predicate<Context<Object>> predicate = Mockito.mock(Predicate.class);
        final Conclusion<Context<Object>> conclusion = Mockito.mock(Conclusion.class);

        final Rule<Context<Object>> rule = new RuleImplementation<>(RuleImplementationTest.NAME, predicate, conclusion);

        Assert.assertEquals(rule.getName(), RuleImplementationTest.NAME);
    }

    /**
     * Ensures that equals is reflexive
     */
    @Test
    public void equalsIsReflexive() {
        final Predicate<Context<Object>> predicate = Mockito.mock(Predicate.class);
        final Conclusion<Context<Object>> conclusion = Mockito.mock(Conclusion.class);

        final Rule<Context<Object>> rule = new RuleImplementation<>(RuleImplementationTest.NAME, predicate, conclusion);

        Assert.assertEquals(rule, rule);
    }

    /**
     * Ensures that equals is symmetric
     */
    @Test
    public void equalsIsSymmetric() {
        final Predicate<Context<Object>> predicate = Mockito.mock(Predicate.class);
        final Conclusion<Context<Object>> conclusion = Mockito.mock(Conclusion.class);

        final Rule<Context<Object>> rule1 = new RuleImplementation<>(RuleImplementationTest.NAME, predicate, conclusion);
        final Rule<Context<Object>> rule2 = new RuleImplementation<>(RuleImplementationTest.NAME, predicate, conclusion);

        Assert.assertEquals(rule1, rule2);
        Assert.assertEquals(rule2, rule1);
    }

    /**
     * Ensures that equals is transitive
     */
    @Test
    public void equalsIsTransitive() {
        final Predicate<Context<Object>> predicate = Mockito.mock(Predicate.class);
        final Conclusion<Context<Object>> conclusion = Mockito.mock(Conclusion.class);

        final Rule<Context<Object>> rule1 = new RuleImplementation<>(RuleImplementationTest.NAME, predicate, conclusion);
        final Rule<Context<Object>> rule2 = new RuleImplementation<>(RuleImplementationTest.NAME, predicate, conclusion);
        final Rule<Context<Object>> rule3 = new RuleImplementation<>(RuleImplementationTest.NAME, predicate, conclusion);

        Assert.assertEquals(rule1, rule2);
        Assert.assertEquals(rule2, rule3);
        Assert.assertEquals(rule3, rule1);
    }

    /**
     * Ensures that equals is returns <code>false</code> on <code>null</code> values.
     */
    @Test
    public void equalsReturnFalseOnNull() {
        final Predicate<Context<Object>> predicate = Mockito.mock(Predicate.class);
        final Conclusion<Context<Object>> conclusion = Mockito.mock(Conclusion.class);

        final Rule<Context<Object>> rule = new RuleImplementation<>(RuleImplementationTest.NAME, predicate, conclusion);

        Assert.assertNotEquals(rule, null);
    }

    /**
     * Ensures that equals is returns <code>false</code> on <code>null</code> values.
     */
    @Test
    public void equalsReturnFalseOnWrongClass() {
        final Predicate<Context<Object>> predicate = Mockito.mock(Predicate.class);
        final Conclusion<Context<Object>> conclusion = Mockito.mock(Conclusion.class);

        final Rule<Context<Object>> rule = new RuleImplementation<>(RuleImplementationTest.NAME, predicate, conclusion);

        Assert.assertNotEquals(rule, ""); //$NON-NLS-1$
    }

    /**
     * Ensures that equals works with identical instances.
     */
    @Test
    public void equalsWorks() {
        final Predicate<Context<Object>> predicate = Mockito.mock(Predicate.class);
        final Conclusion<Context<Object>> conclusion = Mockito.mock(Conclusion.class);

        final Rule<Context<Object>> rule1 = new RuleImplementation<>(RuleImplementationTest.NAME, predicate, conclusion);
        final Rule<Context<Object>> rule2 = rule1;

        Assert.assertEquals(rule1, rule2);
    }

    /**
     * Ensures that equals works with different names
     */
    @Test
    public void equalsWorksWithDifferentNames() {
        final Predicate<Context<Object>> predicate = Mockito.mock(Predicate.class);
        final Conclusion<Context<Object>> conclusion = Mockito.mock(Conclusion.class);

        final Rule<Context<Object>> rule1 = new RuleImplementation<>(RuleImplementationTest.NAME, predicate, conclusion);
        final Rule<Context<Object>> rule2 = new RuleImplementation<>("rule2", predicate, conclusion); //$NON-NLS-1$

        Assert.assertNotEquals(rule1, rule2);
    }

    /**
     * Ensures that equals works with different predicates.
     */
    @Test
    public void equalsWorksWithDifferentPremises() {
        final Predicate<Context<Object>> predicate1 = Mockito.mock(Predicate.class);
        final Predicate<Context<Object>> predicate2 = Mockito.mock(Predicate.class);
        final Conclusion<Context<Object>> conclusion = Mockito.mock(Conclusion.class);

        final Rule<Context<Object>> rule1 = new RuleImplementation<>(RuleImplementationTest.NAME, predicate1,
                conclusion);
        final Rule<Context<Object>> rule2 = new RuleImplementation<>(RuleImplementationTest.NAME, predicate2,
                conclusion);

        Assert.assertNotEquals(rule1, rule2);
    }

    /**
     * Ensures that equals works with different conclusions.
     */
    @Test
    public void equalsWorksWithDifferentConclusions() {
        final Predicate<Context<Object>> predicate = Mockito.mock(Predicate.class);
        final Conclusion<Context<Object>> conclusion1 = Mockito.mock(Conclusion.class);
        final Conclusion<Context<Object>> conclusion2 = Mockito.mock(Conclusion.class);

        final Rule<Context<Object>> rule1 = new RuleImplementation<>(RuleImplementationTest.NAME, predicate,
                conclusion1);
        final Rule<Context<Object>> rule2 = new RuleImplementation<>(RuleImplementationTest.NAME, predicate,
                conclusion2);

        Assert.assertNotEquals(rule1, rule2);
    }

    /**
     * Ensures that hashCode() is consistent with equals().
     */
    @Test
    public void hashCodeIsConsistentWithEquals() {
        final Predicate<Context<Object>> predicate = Mockito.mock(Predicate.class);
        final Conclusion<Context<Object>> conclusion = Mockito.mock(Conclusion.class);

        final Rule<Context<Object>> rule1 = new RuleImplementation<>(RuleImplementationTest.NAME, predicate, conclusion);
        final Rule<Context<Object>> rule2 = new RuleImplementation<>(RuleImplementationTest.NAME, predicate, conclusion);

        Assert.assertEquals(rule1, rule2);
        Assert.assertEquals(rule1.hashCode(), rule2.hashCode());
    }

}
