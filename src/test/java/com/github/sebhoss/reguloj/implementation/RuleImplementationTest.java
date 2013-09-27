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
import org.mockito.BDDMockito;
import org.mockito.Mockito;

import com.github.sebhoss.common.annotation.CompilerWarnings;
import com.github.sebhoss.reguloj.Conclusion;
import com.github.sebhoss.reguloj.Context;
import com.github.sebhoss.reguloj.Rule;
import com.google.common.base.Predicate;

/**
 * Test cases for the {@link RuleImplementation}.
 * 
 * @see RuleImplementation
 */
@SuppressWarnings({ CompilerWarnings.NULL, CompilerWarnings.STATIC_METHOD })
public final class RuleImplementationTest {

    /** Constant name for all rules inside this test. */
    private static final String NAME   = "test rule";             //$NON-NLS-1$

    /** Checks expected exception inside single test cases. */
    @org.junit.Rule
    public ExpectedException    thrown = ExpectedException.none();

    /**
     * <p>
     * Test method for
     * {@link RuleImplementation#RuleImplementation(String, com.google.common.base.Predicate, com.github.sebhoss.reguloj.Conclusion)}
     * </p>
     * <p>
     * Ensures that <code>null</code> values are not permitted.
     * </p>
     */
    @Test
    public void shouldNotAcceptNullValues() {
        thrown.expect(NullPointerException.class);

        final Rule<Context<Object>> rule = new RuleImplementation<>(null, null, null);

        Assert.assertThat(rule, Is.is(IsNull.nullValue()));
    }

    /**
     * <p>
     * Test method for
     * {@link RuleImplementation#RuleImplementation(String, com.google.common.base.Predicate, com.github.sebhoss.reguloj.Conclusion)}
     * </p>
     * <p>
     * Ensures that a valid rule will be built once all values are given.
     * </p>
     */
    @Test
    public void shouldCreateRuleIfAllValuesAreSet() {
        final Predicate<Context<Object>> predicate = Mockito.mock(Predicate.class);
        final Conclusion<Context<Object>> conclusion = Mockito.mock(Conclusion.class);

        final Rule<Context<Object>> rule = new RuleImplementation<>(RuleImplementationTest.NAME, predicate, conclusion);

        Assert.assertThat(rule, Is.is(IsNull.notNullValue()));
    }

    /**
     * <p>
     * Test method for {@link RuleImplementation#run(Context)}
     * </p>
     * <p>
     * Ensures that <code>false</code> is returned if the predicate does not apply.
     * </p>
     */
    @SuppressWarnings("boxing")
    @Test
    public void shouldReturnFalseWhenPremiseDoesNotApply() {
        final Context<Object> context = Mockito.mock(Context.class);
        final Predicate<Context<Object>> predicate = Mockito.mock(Predicate.class);
        BDDMockito.given(predicate.apply(context)).willReturn(Boolean.FALSE);
        final Conclusion<Context<Object>> conclusion = Mockito.mock(Conclusion.class);

        final Rule<Context<Object>> rule = new RuleImplementation<>(RuleImplementationTest.NAME, predicate, conclusion);

        Assert.assertFalse(rule.run(context));
    }

    /**
     * <p>
     * Test method for {@link RuleImplementation#run(Context)}
     * </p>
     * <p>
     * Ensures that <code>false</code> is returned if the conclusion does not apply.
     * </p>
     */
    @SuppressWarnings("boxing")
    @Test
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
     * <p>
     * Test method for {@link RuleImplementation#run(Context)}
     * </p>
     * <p>
     * Ensures that <code>false</code> is returned if the conclusion does not apply.
     * </p>
     */
    @SuppressWarnings("boxing")
    @Test
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
     * <p>
     * Test method for {@link RuleImplementation#fires(Context)}
     * </p>
     * <p>
     * Ensures that <code>true</code> is returned if the predicate does apply.
     * </p>
     */
    @SuppressWarnings("boxing")
    @Test
    public void shouldFireWhenPremiseApplies() {
        final Context<Object> context = Mockito.mock(Context.class);
        final Predicate<Context<Object>> predicate = Mockito.mock(Predicate.class);
        BDDMockito.given(predicate.apply(context)).willReturn(true);
        final Conclusion<Context<Object>> conclusion = Mockito.mock(Conclusion.class);

        final Rule<Context<Object>> rule = new RuleImplementation<>(RuleImplementationTest.NAME, predicate, conclusion);

        Assert.assertTrue(rule.fires(context));
    }

    /**
     * <p>
     * Test method for {@link RuleImplementation#fires(Context)}
     * </p>
     * <p>
     * Ensures that <code>false</code> is returned if the predicate does not apply.
     * </p>
     */
    @SuppressWarnings("boxing")
    @Test
    public void shouldNotFireWhenPremiseDoesNotApply() {
        final Context<Object> context = Mockito.mock(Context.class);
        final Predicate<Context<Object>> predicate = Mockito.mock(Predicate.class);
        BDDMockito.given(predicate.apply(context)).willReturn(false);
        final Conclusion<Context<Object>> conclusion = Mockito.mock(Conclusion.class);

        final Rule<Context<Object>> rule = new RuleImplementation<>(RuleImplementationTest.NAME, predicate, conclusion);

        Assert.assertFalse(rule.fires(context));
    }

    /**
     * <p>
     * Test method for {@link RuleImplementation#getName()}
     * </p>
     * <p>
     * Ensures that the previously set name is returned.
     * </p>
     */
    @Test
    public void shouldReturnTheSetName() {
        final Predicate<Context<Object>> predicate = Mockito.mock(Predicate.class);
        final Conclusion<Context<Object>> conclusion = Mockito.mock(Conclusion.class);

        final Rule<Context<Object>> rule = new RuleImplementation<>(RuleImplementationTest.NAME, predicate, conclusion);

        Assert.assertThat(rule.getName(), Is.is(RuleImplementationTest.NAME));
    }

    /**
     * <p>
     * Test method for {@link RuleImplementation#equals(Object)}
     * </p>
     * <p>
     * Ensures that equals is reflexive
     * </p>
     */
    @SuppressWarnings("boxing")
    @Test
    public void equalsIsReflexive() {
        final Predicate<Context<Object>> predicate = Mockito.mock(Predicate.class);
        final Conclusion<Context<Object>> conclusion = Mockito.mock(Conclusion.class);

        final Rule<Context<Object>> rule = new RuleImplementation<>(RuleImplementationTest.NAME, predicate, conclusion);

        Assert.assertThat(rule.equals(rule), Is.is(true));
    }

    /**
     * <p>
     * Test method for {@link RuleImplementation#equals(Object)}
     * </p>
     * <p>
     * Ensures that equals is symmetric
     * </p>
     */
    @SuppressWarnings("boxing")
    @Test
    public void equalsIsSymmetric() {
        final Predicate<Context<Object>> predicate = Mockito.mock(Predicate.class);
        final Conclusion<Context<Object>> conclusion = Mockito.mock(Conclusion.class);

        final Rule<Context<Object>> rule1 = new RuleImplementation<>(RuleImplementationTest.NAME, predicate, conclusion);
        final Rule<Context<Object>> rule2 = new RuleImplementation<>(RuleImplementationTest.NAME, predicate, conclusion);

        Assert.assertThat(rule1.equals(rule2), Is.is(true));
        Assert.assertThat(rule2.equals(rule1), Is.is(true));
    }

    /**
     * <p>
     * Test method for {@link RuleImplementation#equals(Object)}
     * </p>
     * <p>
     * Ensures that equals is transitive
     * </p>
     */
    @SuppressWarnings("boxing")
    @Test
    public void equalsIsTransitive() {
        final Predicate<Context<Object>> predicate = Mockito.mock(Predicate.class);
        final Conclusion<Context<Object>> conclusion = Mockito.mock(Conclusion.class);

        final Rule<Context<Object>> rule1 = new RuleImplementation<>(RuleImplementationTest.NAME, predicate, conclusion);
        final Rule<Context<Object>> rule2 = new RuleImplementation<>(RuleImplementationTest.NAME, predicate, conclusion);
        final Rule<Context<Object>> rule3 = new RuleImplementation<>(RuleImplementationTest.NAME, predicate, conclusion);

        Assert.assertThat(rule1.equals(rule2) && rule2.equals(rule3), Is.is(true));
        Assert.assertThat(rule3.equals(rule1), Is.is(true));
    }

    /**
     * <p>
     * Test method for {@link RuleImplementation#equals(Object)}
     * </p>
     * <p>
     * Ensures that equals is consistent
     * </p>
     */
    @SuppressWarnings("boxing")
    @Test
    public void equalsIsConsistent() {
        final Predicate<Context<Object>> predicate = Mockito.mock(Predicate.class);
        final Conclusion<Context<Object>> conclusion = Mockito.mock(Conclusion.class);

        final Rule<Context<Object>> rule1 = new RuleImplementation<>(RuleImplementationTest.NAME, predicate, conclusion);
        final Rule<Context<Object>> rule2 = new RuleImplementation<>(RuleImplementationTest.NAME, predicate, conclusion);

        final boolean alwaysTheSame = rule1.equals(rule2);

        for (int i = 0; i < 30; i++) {
            Assert.assertThat(rule1.equals(rule2), Is.is(alwaysTheSame));
        }
    }

    /**
     * <p>
     * Test method for {@link RuleImplementation#equals(Object)}
     * </p>
     * <p>
     * Ensures that equals is returns <code>false</code> on <code>null</code> values.
     * </p>
     */
    @SuppressWarnings("boxing")
    @Test
    public void equalsReturnFalseOnNull() {
        final Predicate<Context<Object>> predicate = Mockito.mock(Predicate.class);
        final Conclusion<Context<Object>> conclusion = Mockito.mock(Conclusion.class);

        final Rule<Context<Object>> rule = new RuleImplementation<>(RuleImplementationTest.NAME, predicate, conclusion);

        Assert.assertThat(rule.equals(null), Is.is(false));
    }

    /**
     * <p>
     * Test method for {@link RuleImplementation#equals(Object)}
     * </p>
     * <p>
     * Ensures that equals is returns <code>false</code> on <code>null</code> values.
     * </p>
     */
    @SuppressWarnings("boxing")
    @Test
    public void equalsReturnFalseOnWrongClass() {
        final Predicate<Context<Object>> predicate = Mockito.mock(Predicate.class);
        final Conclusion<Context<Object>> conclusion = Mockito.mock(Conclusion.class);

        final Rule<Context<Object>> rule = new RuleImplementation<>(RuleImplementationTest.NAME, predicate, conclusion);

        Assert.assertThat(rule.equals(""), Is.is(false)); //$NON-NLS-1$
    }

    /**
     * <p>
     * Test method for {@link RuleImplementation#equals(Object)}
     * </p>
     * <p>
     * Ensures that equals works with identical instances.
     * </p>
     */
    @SuppressWarnings("boxing")
    @Test
    public void equalsWorks() {
        final Predicate<Context<Object>> predicate = Mockito.mock(Predicate.class);
        final Conclusion<Context<Object>> conclusion = Mockito.mock(Conclusion.class);

        final Rule<Context<Object>> rule1 = new RuleImplementation<>(RuleImplementationTest.NAME, predicate, conclusion);
        final Rule<Context<Object>> rule2 = rule1;

        Assert.assertThat(rule1.equals(rule2), Is.is(true));
    }

    /**
     * <p>
     * Test method for {@link RuleImplementation#equals(Object)}
     * </p>
     * <p>
     * Ensures that equals works with different names
     * </p>
     */
    @SuppressWarnings("boxing")
    @Test
    public void equalsWorksWithDifferentNames() {
        final Predicate<Context<Object>> predicate = Mockito.mock(Predicate.class);
        final Conclusion<Context<Object>> conclusion = Mockito.mock(Conclusion.class);

        final Rule<Context<Object>> rule1 = new RuleImplementation<>(RuleImplementationTest.NAME, predicate, conclusion);
        final Rule<Context<Object>> rule2 = new RuleImplementation<>("rule2", predicate, conclusion); //$NON-NLS-1$

        Assert.assertThat(rule1.equals(rule2), Is.is(false));
    }

    /**
     * <p>
     * Test method for {@link RuleImplementation#equals(Object)}
     * </p>
     * <p>
     * Ensures that equals works with different predicates.
     * </p>
     */
    @SuppressWarnings("boxing")
    @Test
    public void equalsWorksWithDifferentPremises() {
        final Predicate<Context<Object>> predicate1 = Mockito.mock(Predicate.class);
        final Predicate<Context<Object>> predicate2 = Mockito.mock(Predicate.class);
        final Conclusion<Context<Object>> conclusion = Mockito.mock(Conclusion.class);

        final Rule<Context<Object>> rule1 = new RuleImplementation<>(RuleImplementationTest.NAME, predicate1,
                conclusion);
        final Rule<Context<Object>> rule2 = new RuleImplementation<>(RuleImplementationTest.NAME, predicate2,
                conclusion);

        Assert.assertThat(rule1.equals(rule2), Is.is(false));
    }

    /**
     * <p>
     * Test method for {@link RuleImplementation#equals(Object)}
     * </p>
     * <p>
     * Ensures that equals works with different conclusions.
     * </p>
     */
    @SuppressWarnings("boxing")
    @Test
    public void equalsWorksWithDifferentConclusions() {
        final Predicate<Context<Object>> predicate = Mockito.mock(Predicate.class);
        final Conclusion<Context<Object>> conclusion1 = Mockito.mock(Conclusion.class);
        final Conclusion<Context<Object>> conclusion2 = Mockito.mock(Conclusion.class);

        final Rule<Context<Object>> rule1 = new RuleImplementation<>(RuleImplementationTest.NAME, predicate,
                conclusion1);
        final Rule<Context<Object>> rule2 = new RuleImplementation<>(RuleImplementationTest.NAME, predicate,
                conclusion2);

        Assert.assertThat(rule1.equals(rule2), Is.is(false));
    }

    /**
     * <p>
     * Test method for {@link RuleImplementation#hashCode()}
     * </p>
     * <p>
     * Ensures that hashCode() always returns the same value for a same instance.
     * </p>
     */
    @SuppressWarnings("boxing")
    @Test
    public void hashCodeIsSelfConsistent() {
        final Predicate<Context<Object>> predicate = Mockito.mock(Predicate.class);
        final Conclusion<Context<Object>> conclusion = Mockito.mock(Conclusion.class);

        final Rule<Context<Object>> rule = new RuleImplementation<>(RuleImplementationTest.NAME, predicate, conclusion);

        final int alwaysTheSame = rule.hashCode();

        for (int i = 0; i < 30; i++) {
            Assert.assertThat(rule.hashCode(), Is.is(alwaysTheSame));
        }
    }

    /**
     * <p>
     * Test method for {@link RuleImplementation#hashCode()}
     * </p>
     * <p>
     * Ensures that hashCode() is consistent with equals().
     * </p>
     */
    @SuppressWarnings("boxing")
    @Test
    public void hashCodeIsConsistentWithEquals() {
        final Predicate<Context<Object>> predicate = Mockito.mock(Predicate.class);
        final Conclusion<Context<Object>> conclusion = Mockito.mock(Conclusion.class);

        final Rule<Context<Object>> rule1 = new RuleImplementation<>(RuleImplementationTest.NAME, predicate, conclusion);
        final Rule<Context<Object>> rule2 = new RuleImplementation<>(RuleImplementationTest.NAME, predicate, conclusion);

        Assert.assertThat(rule1.equals(rule2), Is.is(true));
        Assert.assertThat(rule1.hashCode(), Is.is(rule2.hashCode()));
    }

    /**
     * <p>
     * Test method for {@link RuleImplementation#compareTo(com.github.sebhoss.reguloj.Rule)}
     * </p>
     * <p>
     * Ensures that compareTo() is consistent with equals().
     * </p>
     */
    @SuppressWarnings("boxing")
    @Test
    public void shoudCompareToOtherRules() {
        final Predicate<Context<Object>> predicate = Mockito.mock(Predicate.class);
        final Conclusion<Context<Object>> conclusion = Mockito.mock(Conclusion.class);

        final Rule<Context<Object>> rule1 = new RuleImplementation<>(RuleImplementationTest.NAME, predicate, conclusion);
        final Rule<Context<Object>> rule2 = new RuleImplementation<>(RuleImplementationTest.NAME, predicate, conclusion);

        Assert.assertThat(rule1.compareTo(rule2), Is.is(0));
    }
}
