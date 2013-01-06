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

import com.github.sebhoss.reguloj.Conclusion;
import com.github.sebhoss.reguloj.Context;
import com.github.sebhoss.reguloj.Rule;
import com.github.sebhoss.reguloj.implementation.RuleImplementation;
import com.google.common.base.Predicate;


/**
 * <p>Test cases for the {@link RuleImplementation}.</p>
 *
 * @see     RuleImplementation
 */
@SuppressWarnings("static-method")
public final class RuleImplementationTest {

    // * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
    // *                                                  CONSTANTS                                                  *
    // * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *

    /** Constant name for all rules inside this test. */
    private static final String NAME   = "test rule";             //$NON-NLS-1$

    // * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
    // *                                                  ATTRIBUTES                                                 *
    // * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *

    /** Checks expected exception inside single test cases. */
    @org.junit.Rule
    public ExpectedException    thrown = ExpectedException.none();

    // * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
    // *                                                    TESTS                                                    *
    // * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *

    /**
     * <p>Test method for {@link RuleImplementation#RuleImplementation(String, 
     * com.google.common.base.Predicate, com.github.sebhoss.reguloj.Conclusion)}</p>
     * 
     * <p>Ensures that <code>null</code> values are not permitted.</p>
     */
    @Test
    public void shouldNotAcceptNullValues() {
        // given
        this.thrown.expect(NullPointerException.class);

        // when
        final Rule<Context<Object>> rule = new RuleImplementation<Context<Object>>(null, null, null);

        // then
        Assert.assertThat(rule, Is.is(IsNull.nullValue()));
    }

    /**
     * <p>Test method for {@link RuleImplementation#RuleImplementation(String, 
     * com.google.common.base.Predicate, com.github.sebhoss.reguloj.Conclusion)}</p>
     * 
     * <p>Ensures that a valid rule will be built once all values are given.</p>
     */
    @Test
    public void shouldCreateRuleIfAllValuesAreSet() {
        // given
        final Predicate<Context<Object>> predicate = Mockito.mock(Predicate.class);
        final Conclusion<Context<Object>> conclusion = Mockito.mock(Conclusion.class);

        // when
        final Rule<Context<Object>> rule =
                new RuleImplementation<Context<Object>>(RuleImplementationTest.NAME, predicate, conclusion);

        // then
        Assert.assertThat(rule, Is.is(IsNull.notNullValue()));
    }

    /**
     * <p>Test method for {@link RuleImplementation#run(Context)}</p>
     * 
     * <p>Ensures that <code>false</code> is returned if the predicate does not apply.</p>
     */
    @SuppressWarnings("boxing")
    @Test
    public void shouldReturnFalseWhenPremiseDoesNotApply() {
        // given
        final Context<Object> context = Mockito.mock(Context.class);
        final Predicate<Context<Object>> predicate = Mockito.mock(Predicate.class);
        BDDMockito.given(predicate.apply(context)).willReturn(Boolean.FALSE);

        final Conclusion<Context<Object>> conclusion = Mockito.mock(Conclusion.class);

        // when
        final Rule<Context<Object>> rule =
                new RuleImplementation<Context<Object>>(RuleImplementationTest.NAME, predicate, conclusion);

        // then
        Assert.assertFalse(rule.run(context));
    }

    /**
     * <p>Test method for {@link RuleImplementation#run(Context)}</p>
     * 
     * <p>Ensures that <code>false</code> is returned if the conclusion does not apply.</p>
     */
    @SuppressWarnings("boxing")
    @Test
    public void shouldReturnFalseWhenConclusionDoesNotApply() {
        // given
        final Context<Object> context = Mockito.mock(Context.class);
        final Predicate<Context<Object>> predicate = Mockito.mock(Predicate.class);
        BDDMockito.given(predicate.apply(context)).willReturn(Boolean.TRUE);

        final Conclusion<Context<Object>> conclusion = Mockito.mock(Conclusion.class);
        BDDMockito.given(conclusion.apply(context)).willReturn(Boolean.FALSE);

        // when
        final Rule<Context<Object>> rule =
                new RuleImplementation<Context<Object>>(RuleImplementationTest.NAME, predicate, conclusion);

        // then
        Assert.assertFalse(rule.run(context));
    }

    /**
     * <p>Test method for {@link RuleImplementation#run(Context)}</p>
     * 
     * <p>Ensures that <code>false</code> is returned if the conclusion does not apply.</p>
     */
    @SuppressWarnings("boxing")
    @Test
    public void shouldReturnTrueWhenPremiseAndConclusionApply() {
        // given
        final Context<Object> context = Mockito.mock(Context.class);
        final Predicate<Context<Object>> predicate = Mockito.mock(Predicate.class);
        BDDMockito.given(predicate.apply(context)).willReturn(Boolean.TRUE);

        final Conclusion<Context<Object>> conclusion = Mockito.mock(Conclusion.class);
        BDDMockito.given(conclusion.apply(context)).willReturn(Boolean.TRUE);

        // when
        final Rule<Context<Object>> rule =
                new RuleImplementation<Context<Object>>(RuleImplementationTest.NAME, predicate, conclusion);

        // then
        Assert.assertTrue(rule.run(context));
    }

    /**
     * <p>Test method for {@link RuleImplementation#fires(Context)}</p>
     * 
     * <p>Ensures that <code>true</code> is returned if the predicate does apply.</p>
     */
    @SuppressWarnings("boxing")
    @Test
    public void shouldFireWhenPremiseApplies() {
        // given
        final Context<Object> context = Mockito.mock(Context.class);
        final Predicate<Context<Object>> predicate = Mockito.mock(Predicate.class);
        BDDMockito.given(predicate.apply(context)).willReturn(true);

        final Conclusion<Context<Object>> conclusion = Mockito.mock(Conclusion.class);

        // when
        final Rule<Context<Object>> rule =
                new RuleImplementation<Context<Object>>(RuleImplementationTest.NAME, predicate, conclusion);

        // then
        Assert.assertTrue(rule.fires(context));
    }

    /**
     * <p>Test method for {@link RuleImplementation#fires(Context)}</p>
     * 
     * <p>Ensures that <code>false</code> is returned if the predicate does not apply.</p>
     */
    @SuppressWarnings("boxing")
    @Test
    public void shouldNotFireWhenPremiseDoesNotApply() {
        // given
        final Context<Object> context = Mockito.mock(Context.class);
        final Predicate<Context<Object>> predicate = Mockito.mock(Predicate.class);
        BDDMockito.given(predicate.apply(context)).willReturn(false);

        final Conclusion<Context<Object>> conclusion = Mockito.mock(Conclusion.class);

        // when
        final Rule<Context<Object>> rule =
                new RuleImplementation<Context<Object>>(RuleImplementationTest.NAME, predicate, conclusion);

        // then
        Assert.assertFalse(rule.fires(context));
    }

    /**
     * <p>Test method for {@link RuleImplementation#getName()}</p>
     * 
     * <p>Ensures that the previously set name is returned.</p>
     */
    @Test
    public void shouldReturnTheSetName() {
        // given
        final Predicate<Context<Object>> predicate = Mockito.mock(Predicate.class);
        final Conclusion<Context<Object>> conclusion = Mockito.mock(Conclusion.class);

        // when
        final Rule<Context<Object>> rule =
                new RuleImplementation<Context<Object>>(RuleImplementationTest.NAME, predicate, conclusion);

        // then
        Assert.assertThat(rule.getName(), Is.is(RuleImplementationTest.NAME));
    }

    /**
     * <p>Test method for {@link RuleImplementation#equals(Object)}</p>
     * 
     * <p>Ensures that equals is reflexive</p>
     */
    @SuppressWarnings("boxing")
    @Test
    public void equalsIsReflexive() {
        // given
        final Predicate<Context<Object>> predicate = Mockito.mock(Predicate.class);
        final Conclusion<Context<Object>> conclusion = Mockito.mock(Conclusion.class);

        // when
        final Rule<Context<Object>> rule =
                new RuleImplementation<Context<Object>>(RuleImplementationTest.NAME, predicate, conclusion);

        // then
        Assert.assertThat(rule.equals(rule), Is.is(true));
    }

    /**
     * <p>Test method for {@link RuleImplementation#equals(Object)}</p>
     * 
     * <p>Ensures that equals is symmetric</p>
     */
    @SuppressWarnings("boxing")
    @Test
    public void equalsIsSymmetric() {
        // given
        final Predicate<Context<Object>> predicate = Mockito.mock(Predicate.class);
        final Conclusion<Context<Object>> conclusion = Mockito.mock(Conclusion.class);

        // when
        final Rule<Context<Object>> rule1 =
                new RuleImplementation<Context<Object>>(RuleImplementationTest.NAME, predicate, conclusion);
        final Rule<Context<Object>> rule2 =
                new RuleImplementation<Context<Object>>(RuleImplementationTest.NAME, predicate, conclusion);

        // then
        Assert.assertThat(rule1.equals(rule2), Is.is(true));
        Assert.assertThat(rule2.equals(rule1), Is.is(true));
    }

    /**
     * <p>Test method for {@link RuleImplementation#equals(Object)}</p>
     * 
     * <p>Ensures that equals is transitive</p>
     */
    @SuppressWarnings("boxing")
    @Test
    public void equalsIsTransitive() {
        // given
        final Predicate<Context<Object>> predicate = Mockito.mock(Predicate.class);
        final Conclusion<Context<Object>> conclusion = Mockito.mock(Conclusion.class);

        // when
        final Rule<Context<Object>> rule1 =
                new RuleImplementation<Context<Object>>(RuleImplementationTest.NAME, predicate, conclusion);
        final Rule<Context<Object>> rule2 =
                new RuleImplementation<Context<Object>>(RuleImplementationTest.NAME, predicate, conclusion);
        final Rule<Context<Object>> rule3 =
                new RuleImplementation<Context<Object>>(RuleImplementationTest.NAME, predicate, conclusion);

        // then
        Assert.assertThat(rule1.equals(rule2) && rule2.equals(rule3), Is.is(true));
        Assert.assertThat(rule3.equals(rule1), Is.is(true));
    }

    /**
     * <p>Test method for {@link RuleImplementation#equals(Object)}</p>
     * 
     * <p>Ensures that equals is consistent</p>
     */
    @SuppressWarnings("boxing")
    @Test
    public void equalsIsConsistent() {
        // given
        final Predicate<Context<Object>> predicate = Mockito.mock(Predicate.class);
        final Conclusion<Context<Object>> conclusion = Mockito.mock(Conclusion.class);

        // when
        final Rule<Context<Object>> rule1 =
                new RuleImplementation<Context<Object>>(RuleImplementationTest.NAME, predicate, conclusion);
        final Rule<Context<Object>> rule2 =
                new RuleImplementation<Context<Object>>(RuleImplementationTest.NAME, predicate, conclusion);

        // then
        final boolean alwaysTheSame = rule1.equals(rule2);

        for (int i = 0; i < 30; i++) {
            Assert.assertThat(rule1.equals(rule2), Is.is(alwaysTheSame));
        }
    }

    /**
     * <p>Test method for {@link RuleImplementation#equals(Object)}</p>
     * 
     * <p>Ensures that equals is returns <code>false</code> on <code>null</code> values.</p>
     */
    @SuppressWarnings("boxing")
    @Test
    public void equalsReturnFalseOnNull() {
        // given
        final Predicate<Context<Object>> predicate = Mockito.mock(Predicate.class);
        final Conclusion<Context<Object>> conclusion = Mockito.mock(Conclusion.class);

        // when
        final Rule<Context<Object>> rule =
                new RuleImplementation<Context<Object>>(RuleImplementationTest.NAME, predicate, conclusion);

        // then
        Assert.assertThat(rule.equals(null), Is.is(false));
    }

    /**
     * <p>Test method for {@link RuleImplementation#equals(Object)}</p>
     * 
     * <p>Ensures that equals is returns <code>false</code> on <code>null</code> values.</p>
     */
    @SuppressWarnings("boxing")
    @Test
    public void equalsReturnFalseOnWrongClass() {
        // given
        final Predicate<Context<Object>> predicate = Mockito.mock(Predicate.class);
        final Conclusion<Context<Object>> conclusion = Mockito.mock(Conclusion.class);

        // when
        final Rule<Context<Object>> rule =
                new RuleImplementation<Context<Object>>(RuleImplementationTest.NAME, predicate, conclusion);

        // then
        Assert.assertThat(rule.equals(""), Is.is(false)); //$NON-NLS-1$
    }

    /**
     * <p>Test method for {@link RuleImplementation#equals(Object)}</p>
     * 
     * <p>Ensures that equals works with identical instances.</p>
     */
    @SuppressWarnings("boxing")
    @Test
    public void equalsWorks() {
        // given
        final Predicate<Context<Object>> predicate = Mockito.mock(Predicate.class);
        final Conclusion<Context<Object>> conclusion = Mockito.mock(Conclusion.class);

        // when
        final Rule<Context<Object>> rule1 =
                new RuleImplementation<Context<Object>>(RuleImplementationTest.NAME, predicate, conclusion);
        final Rule<Context<Object>> rule2 = rule1;

        // then
        Assert.assertThat(rule1.equals(rule2), Is.is(true));
    }

    /**
     * <p>Test method for {@link RuleImplementation#equals(Object)}</p>
     * 
     * <p>Ensures that equals works with different names</p>
     */
    @SuppressWarnings("boxing")
    @Test
    public void equalsWorksWithDifferentNames() {
        // given
        final Predicate<Context<Object>> predicate = Mockito.mock(Predicate.class);
        final Conclusion<Context<Object>> conclusion = Mockito.mock(Conclusion.class);

        // when
        final Rule<Context<Object>> rule1 =
                new RuleImplementation<Context<Object>>(RuleImplementationTest.NAME, predicate, conclusion);
        final Rule<Context<Object>> rule2 =
                new RuleImplementation<Context<Object>>("rule2", predicate, conclusion); //$NON-NLS-1$

        // then
        Assert.assertThat(rule1.equals(rule2), Is.is(false));
    }

    /**
     * <p>Test method for {@link RuleImplementation#equals(Object)}</p>
     * 
     * <p>Ensures that equals works with different predicates.</p>
     */
    @SuppressWarnings("boxing")
    @Test
    public void equalsWorksWithDifferentPremises() {
        // given
        final Predicate<Context<Object>> predicate1 = Mockito.mock(Predicate.class);
        final Predicate<Context<Object>> predicate2 = Mockito.mock(Predicate.class);
        final Conclusion<Context<Object>> conclusion = Mockito.mock(Conclusion.class);

        // when
        final Rule<Context<Object>> rule1 =
                new RuleImplementation<Context<Object>>(RuleImplementationTest.NAME, predicate1, conclusion);
        final Rule<Context<Object>> rule2 =
                new RuleImplementation<Context<Object>>(RuleImplementationTest.NAME, predicate2, conclusion);

        // then
        Assert.assertThat(rule1.equals(rule2), Is.is(false));
    }

    /**
     * <p>Test method for {@link RuleImplementation#equals(Object)}</p>
     * 
     * <p>Ensures that equals works with different conclusions.</p>
     */
    @SuppressWarnings("boxing")
    @Test
    public void equalsWorksWithDifferentConclusions() {
        // given
        final Predicate<Context<Object>> predicate = Mockito.mock(Predicate.class);
        final Conclusion<Context<Object>> conclusion1 = Mockito.mock(Conclusion.class);
        final Conclusion<Context<Object>> conclusion2 = Mockito.mock(Conclusion.class);

        // when
        final Rule<Context<Object>> rule1 =
                new RuleImplementation<Context<Object>>(RuleImplementationTest.NAME, predicate, conclusion1);
        final Rule<Context<Object>> rule2 =
                new RuleImplementation<Context<Object>>(RuleImplementationTest.NAME, predicate, conclusion2);

        // then
        Assert.assertThat(rule1.equals(rule2), Is.is(false));
    }

    /**
     * <p>Test method for {@link RuleImplementation#hashCode()}</p>
     * 
     * <p>Ensures that hashCode() always returns the same value for a same instance.</p>
     */
    @SuppressWarnings("boxing")
    @Test
    public void hashCodeIsSelfConsistent() {
        // given
        final Predicate<Context<Object>> predicate = Mockito.mock(Predicate.class);
        final Conclusion<Context<Object>> conclusion = Mockito.mock(Conclusion.class);

        // when
        final Rule<Context<Object>> rule =
                new RuleImplementation<Context<Object>>(RuleImplementationTest.NAME, predicate, conclusion);

        // then
        final int alwaysTheSame = rule.hashCode();

        for (int i = 0; i < 30; i++) {
            Assert.assertThat(rule.hashCode(), Is.is(alwaysTheSame));
        }
    }

    /**
     * <p>Test method for {@link RuleImplementation#hashCode()}</p>
     * 
     * <p>Ensures that hashCode() is consistent with equals().</p>
     */
    @SuppressWarnings("boxing")
    @Test
    public void hashCodeIsConsistentWithEquals() {
        // given
        final Predicate<Context<Object>> predicate = Mockito.mock(Predicate.class);
        final Conclusion<Context<Object>> conclusion = Mockito.mock(Conclusion.class);

        // when
        final Rule<Context<Object>> rule1 =
                new RuleImplementation<Context<Object>>(RuleImplementationTest.NAME, predicate, conclusion);
        final Rule<Context<Object>> rule2 =
                new RuleImplementation<Context<Object>>(RuleImplementationTest.NAME, predicate, conclusion);

        // then
        Assert.assertThat(rule1.equals(rule2), Is.is(true));
        Assert.assertThat(rule1.hashCode(), Is.is(rule2.hashCode()));
    }

    /**
     * <p>Test method for {@link RuleImplementation#compareTo(com.github.sebhoss.reguloj.Rule)}</p>
     * 
     * <p>Ensures that compareTo() is consistent with equals().</p>
     */
    @SuppressWarnings("boxing")
    @Test
    public void shoudCompareToOtherRules() {
        // given
        final Predicate<Context<Object>> predicate = Mockito.mock(Predicate.class);
        final Conclusion<Context<Object>> conclusion = Mockito.mock(Conclusion.class);

        // when
        final Rule<Context<Object>> rule1 =
                new RuleImplementation<Context<Object>>(RuleImplementationTest.NAME, predicate, conclusion);
        final Rule<Context<Object>> rule2 =
                new RuleImplementation<Context<Object>>(RuleImplementationTest.NAME, predicate, conclusion);

        // then
        Assert.assertThat(rule1.compareTo(rule2), Is.is(0));
    }
}
