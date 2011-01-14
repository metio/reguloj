/*
 * Project: rules
 * Package: de.viadee.rules.implementation
 * File   : RuleImplementationTest.java
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
package de.viadee.rules.implementation;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.google.common.base.Predicate;

import de.viadee.rules.Conclusion;
import de.viadee.rules.InferenceContext;
import de.viadee.rules.Rule;

/**
 * <p>Test cases for the {@link RuleImplementation}.</p>
 *
 * @author  Sebastian Hoß (sebastian.hoss@viadee.de)
 * @see     RuleImplementation
 * @since   1.0.0
 */
public final class RuleImplementationTest {

    // * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
    // *                                                     CONSTANTS                                                     *
    // * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *

    /** Constant name for all rules inside this test. */
    private static final String NAME   = "test rule";             //$NON-NLS-1$

    // * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
    // *                                                     ATTRIBUTES                                                    *
    // * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *

    /** Checks expected exception inside single test cases. */
    @org.junit.Rule
    public ExpectedException    thrown = ExpectedException.none();

    // * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
    // *                                                       TESTS                                                       *
    // * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *

    /**
     * <p>Test method for {@link RuleImplementation#RuleImplementation(String, 
     * com.google.common.base.Predicate, de.viadee.rules.Conclusion)}</p>
     * 
     * <p>Ensures that <code>null</code> values are not permitted.</p>
     */
    @Test
    public void shouldNotAcceptNullValues() {
        // given
        this.thrown.expect(NullPointerException.class);

        // when
        final Rule<InferenceContext<Object>, Object> rule =
                new RuleImplementation<InferenceContext<Object>, Object>(null, null, null);

        // then
        assertThat(rule, is(nullValue()));
    }

    /**
     * <p>Test method for {@link RuleImplementation#RuleImplementation(String, 
     * com.google.common.base.Predicate, de.viadee.rules.Conclusion)}</p>
     * 
     * <p>Ensures that a valid rule will be built once all values are given.</p>
     */
    @SuppressWarnings("unchecked")
    @Test
    public void shouldCreateRuleIfAllValuesAreSet() {
        // given
        final Predicate<InferenceContext<Object>> premise = mock(Predicate.class);
        final Conclusion<InferenceContext<Object>> conclusion = mock(Conclusion.class);

        // when
        final Rule<InferenceContext<Object>, Object> rule =
                new RuleImplementation<InferenceContext<Object>, Object>(NAME, premise, conclusion);

        // then
        assertThat(rule, is(notNullValue()));
    }

    /**
     * <p>Test method for {@link RuleImplementation#run(InferenceContext)}</p>
     * 
     * <p>Ensures that <code>false</code> is returned if the premise does not apply.</p>
     */
    @SuppressWarnings({ "boxing", "unchecked" })
    @Test
    public void shouldReturnFalseWhenPremiseDoesNotApply() {
        // given
        final InferenceContext<Object> context = mock(InferenceContext.class);
        final Predicate<InferenceContext<Object>> premise = mock(Predicate.class);
        given(premise.apply(context)).willReturn(false);

        final Conclusion<InferenceContext<Object>> conclusion = mock(Conclusion.class);

        // when
        final Rule<InferenceContext<Object>, Object> rule =
                new RuleImplementation<InferenceContext<Object>, Object>(NAME, premise, conclusion);

        // then
        assertFalse(rule.run(context));
    }

    /**
     * <p>Test method for {@link RuleImplementation#run(InferenceContext)}</p>
     * 
     * <p>Ensures that <code>false</code> is returned if the conclusion does not apply.</p>
     */
    @SuppressWarnings({ "boxing", "unchecked" })
    @Test
    public void shouldReturnFalseWhenConclusionDoesNotApply() {
        // given
        final InferenceContext<Object> context = mock(InferenceContext.class);
        final Predicate<InferenceContext<Object>> premise = mock(Predicate.class);
        given(premise.apply(context)).willReturn(true);

        final Conclusion<InferenceContext<Object>> conclusion = mock(Conclusion.class);
        given(conclusion.apply(context)).willReturn(false);

        // when
        final Rule<InferenceContext<Object>, Object> rule =
                new RuleImplementation<InferenceContext<Object>, Object>(NAME, premise, conclusion);

        // then
        assertFalse(rule.run(context));
    }

    /**
     * <p>Test method for {@link RuleImplementation#run(InferenceContext)}</p>
     * 
     * <p>Ensures that <code>false</code> is returned if the conclusion does not apply.</p>
     */
    @SuppressWarnings({ "boxing", "unchecked" })
    @Test
    public void shouldReturnTrueWhenPremiseAndConclusionApply() {
        // given
        final InferenceContext<Object> context = mock(InferenceContext.class);
        final Predicate<InferenceContext<Object>> premise = mock(Predicate.class);
        given(premise.apply(context)).willReturn(true);

        final Conclusion<InferenceContext<Object>> conclusion = mock(Conclusion.class);
        given(conclusion.apply(context)).willReturn(true);

        // when
        final Rule<InferenceContext<Object>, Object> rule =
                new RuleImplementation<InferenceContext<Object>, Object>(NAME, premise, conclusion);

        // then
        assertTrue(rule.run(context));
    }

    /**
     * <p>Test method for {@link RuleImplementation#fires(InferenceContext)}</p>
     * 
     * <p>Ensures that <code>true</code> is returned if the premise does apply.</p>
     */
    @SuppressWarnings({ "unchecked", "boxing" })
    @Test
    public void shouldFireWhenPremiseApplies() {
        // given
        final InferenceContext<Object> context = mock(InferenceContext.class);
        final Predicate<InferenceContext<Object>> premise = mock(Predicate.class);
        given(premise.apply(context)).willReturn(true);

        final Conclusion<InferenceContext<Object>> conclusion = mock(Conclusion.class);

        // when
        final Rule<InferenceContext<Object>, Object> rule =
                new RuleImplementation<InferenceContext<Object>, Object>(NAME, premise, conclusion);

        // then
        assertTrue(rule.fires(context));
    }

    /**
     * <p>Test method for {@link RuleImplementation#fires(InferenceContext)}</p>
     * 
     * <p>Ensures that <code>false</code> is returned if the premise does not apply.</p>
     */
    @SuppressWarnings({ "unchecked", "boxing" })
    @Test
    public void shouldNotFireWhenPremiseDoesNotApply() {
        // given
        final InferenceContext<Object> context = mock(InferenceContext.class);
        final Predicate<InferenceContext<Object>> premise = mock(Predicate.class);
        given(premise.apply(context)).willReturn(false);

        final Conclusion<InferenceContext<Object>> conclusion = mock(Conclusion.class);

        // when
        final Rule<InferenceContext<Object>, Object> rule =
                new RuleImplementation<InferenceContext<Object>, Object>(NAME, premise, conclusion);

        // then
        assertFalse(rule.fires(context));
    }

    /**
     * <p>Test method for {@link RuleImplementation#getName()}</p>
     * 
     * <p>Ensures that the previously set name is returned.</p>
     */
    @SuppressWarnings("unchecked")
    @Test
    public void shouldReturnTheSetName() {
        // given
        final Predicate<InferenceContext<Object>> premise = mock(Predicate.class);
        final Conclusion<InferenceContext<Object>> conclusion = mock(Conclusion.class);

        // when
        final Rule<InferenceContext<Object>, Object> rule =
                new RuleImplementation<InferenceContext<Object>, Object>(NAME, premise, conclusion);

        // then
        assertThat(rule.getName(), is(NAME));
    }

    /**
     * <p>Test method for {@link RuleImplementation#equals(Object)}</p>
     * 
     * <p>Ensures that equals is reflexive</p>
     */
    @SuppressWarnings({ "boxing", "unchecked" })
    @Test
    public void equalsIsReflexive() {
        // given
        final Predicate<InferenceContext<Object>> premise = mock(Predicate.class);
        final Conclusion<InferenceContext<Object>> conclusion = mock(Conclusion.class);

        // when
        final Rule<InferenceContext<Object>, Object> rule =
                new RuleImplementation<InferenceContext<Object>, Object>(NAME, premise, conclusion);

        // then
        assertThat(rule.equals(rule), is(true));
    }

    /**
     * <p>Test method for {@link RuleImplementation#equals(Object)}</p>
     * 
     * <p>Ensures that equals is symmetric</p>
     */
    @SuppressWarnings({ "boxing", "unchecked" })
    @Test
    public void equalsIsSymmetric() {
        // given
        final Predicate<InferenceContext<Object>> premise = mock(Predicate.class);
        final Conclusion<InferenceContext<Object>> conclusion = mock(Conclusion.class);

        // when
        final Rule<InferenceContext<Object>, Object> rule1 =
                new RuleImplementation<InferenceContext<Object>, Object>(NAME, premise, conclusion);
        final Rule<InferenceContext<Object>, Object> rule2 =
                new RuleImplementation<InferenceContext<Object>, Object>(NAME, premise, conclusion);

        // then
        assertThat(rule1.equals(rule2), is(true));
        assertThat(rule2.equals(rule1), is(true));
    }

    /**
     * <p>Test method for {@link RuleImplementation#equals(Object)}</p>
     * 
     * <p>Ensures that equals is transitive</p>
     */
    @SuppressWarnings({ "boxing", "unchecked" })
    @Test
    public void equalsIsTransitive() {
        // given
        final Predicate<InferenceContext<Object>> premise = mock(Predicate.class);
        final Conclusion<InferenceContext<Object>> conclusion = mock(Conclusion.class);

        // when
        final Rule<InferenceContext<Object>, Object> rule1 =
                new RuleImplementation<InferenceContext<Object>, Object>(NAME, premise, conclusion);
        final Rule<InferenceContext<Object>, Object> rule2 =
                new RuleImplementation<InferenceContext<Object>, Object>(NAME, premise, conclusion);
        final Rule<InferenceContext<Object>, Object> rule3 =
                new RuleImplementation<InferenceContext<Object>, Object>(NAME, premise, conclusion);

        // then
        assertThat(rule1.equals(rule2) && rule2.equals(rule3), is(true));
        assertThat(rule3.equals(rule1), is(true));
    }

    /**
     * <p>Test method for {@link RuleImplementation#equals(Object)}</p>
     * 
     * <p>Ensures that equals is consistent</p>
     */
    @SuppressWarnings({ "boxing", "unchecked" })
    @Test
    public void equalsIsConsistent() {
        // given
        final Predicate<InferenceContext<Object>> premise = mock(Predicate.class);
        final Conclusion<InferenceContext<Object>> conclusion = mock(Conclusion.class);

        // when
        final Rule<InferenceContext<Object>, Object> rule1 =
                new RuleImplementation<InferenceContext<Object>, Object>(NAME, premise, conclusion);
        final Rule<InferenceContext<Object>, Object> rule2 =
                new RuleImplementation<InferenceContext<Object>, Object>(NAME, premise, conclusion);

        // then
        final boolean alwaysTheSame = rule1.equals(rule2);

        for (int i = 0; i < 30; i++) {
            assertThat(rule1.equals(rule2), is(alwaysTheSame));
        }
    }

    /**
     * <p>Test method for {@link RuleImplementation#equals(Object)}</p>
     * 
     * <p>Ensures that equals is returns <code>false</code> on <code>null</code> values.</p>
     */
    @SuppressWarnings({ "boxing", "unchecked" })
    @Test
    public void equalsReturnFalseOnNull() {
        // given
        final Predicate<InferenceContext<Object>> premise = mock(Predicate.class);
        final Conclusion<InferenceContext<Object>> conclusion = mock(Conclusion.class);

        // when
        final Rule<InferenceContext<Object>, Object> rule =
                new RuleImplementation<InferenceContext<Object>, Object>(NAME, premise, conclusion);

        // then
        assertThat(rule.equals(null), is(false));
    }

    /**
     * <p>Test method for {@link RuleImplementation#equals(Object)}</p>
     * 
     * <p>Ensures that equals is returns <code>false</code> on <code>null</code> values.</p>
     */
    @SuppressWarnings({ "boxing", "unchecked" })
    @Test
    public void equalsReturnFalseOnWrongClass() {
        // given
        final Predicate<InferenceContext<Object>> premise = mock(Predicate.class);
        final Conclusion<InferenceContext<Object>> conclusion = mock(Conclusion.class);

        // when
        final Rule<InferenceContext<Object>, Object> rule =
                new RuleImplementation<InferenceContext<Object>, Object>(NAME, premise, conclusion);

        // then
        assertThat(rule.equals(""), is(false)); //$NON-NLS-1$
    }

    /**
     * <p>Test method for {@link RuleImplementation#equals(Object)}</p>
     * 
     * <p>Ensures that equals works with identical instances.</p>
     */
    @SuppressWarnings({ "boxing", "unchecked" })
    @Test
    public void equalsWorks() {
        // given
        final Predicate<InferenceContext<Object>> premise = mock(Predicate.class);
        final Conclusion<InferenceContext<Object>> conclusion = mock(Conclusion.class);

        // when
        final Rule<InferenceContext<Object>, Object> rule1 =
                new RuleImplementation<InferenceContext<Object>, Object>(NAME, premise, conclusion);
        final Rule<InferenceContext<Object>, Object> rule2 = rule1;

        // then
        assertThat(rule1.equals(rule2), is(true));
    }

    /**
     * <p>Test method for {@link RuleImplementation#equals(Object)}</p>
     * 
     * <p>Ensures that equals works with different names</p>
     */
    @SuppressWarnings({ "boxing", "unchecked" })
    @Test
    public void equalsWorksWithDifferentNames() {
        // given
        final Predicate<InferenceContext<Object>> premise = mock(Predicate.class);
        final Conclusion<InferenceContext<Object>> conclusion = mock(Conclusion.class);

        // when
        final Rule<InferenceContext<Object>, Object> rule1 =
                new RuleImplementation<InferenceContext<Object>, Object>(NAME, premise, conclusion);
        final Rule<InferenceContext<Object>, Object> rule2 =
                new RuleImplementation<InferenceContext<Object>, Object>("rule2", premise, conclusion); //$NON-NLS-1$

        // then
        assertThat(rule1.equals(rule2), is(false));
    }

    /**
     * <p>Test method for {@link RuleImplementation#equals(Object)}</p>
     * 
     * <p>Ensures that equals works with different premises.</p>
     */
    @SuppressWarnings({ "boxing", "unchecked" })
    @Test
    public void equalsWorksWithDifferentPremises() {
        // given
        final Predicate<InferenceContext<Object>> premise1 = mock(Predicate.class);
        final Predicate<InferenceContext<Object>> premise2 = mock(Predicate.class);
        final Conclusion<InferenceContext<Object>> conclusion = mock(Conclusion.class);

        // when
        final Rule<InferenceContext<Object>, Object> rule1 =
                new RuleImplementation<InferenceContext<Object>, Object>(NAME, premise1, conclusion);
        final Rule<InferenceContext<Object>, Object> rule2 =
                new RuleImplementation<InferenceContext<Object>, Object>(NAME, premise2, conclusion);

        // then
        assertThat(rule1.equals(rule2), is(false));
    }

    /**
     * <p>Test method for {@link RuleImplementation#equals(Object)}</p>
     * 
     * <p>Ensures that equals works with different conclusions.</p>
     */
    @SuppressWarnings({ "boxing", "unchecked" })
    @Test
    public void equalsWorksWithDifferentConclusions() {
        // given
        final Predicate<InferenceContext<Object>> premise = mock(Predicate.class);
        final Conclusion<InferenceContext<Object>> conclusion1 = mock(Conclusion.class);
        final Conclusion<InferenceContext<Object>> conclusion2 = mock(Conclusion.class);

        // when
        final Rule<InferenceContext<Object>, Object> rule1 =
                new RuleImplementation<InferenceContext<Object>, Object>(NAME, premise, conclusion1);
        final Rule<InferenceContext<Object>, Object> rule2 =
                new RuleImplementation<InferenceContext<Object>, Object>(NAME, premise, conclusion2);

        // then
        assertThat(rule1.equals(rule2), is(false));
    }

    /**
     * <p>Test method for {@link RuleImplementation#hashCode()}</p>
     * 
     * <p>Ensures that hashCode() always returns the same value for a same instance.</p>
     */
    @SuppressWarnings({ "boxing", "unchecked" })
    @Test
    public void hashCodeIsSelfConsistent() {
        // given
        final Predicate<InferenceContext<Object>> premise = mock(Predicate.class);
        final Conclusion<InferenceContext<Object>> conclusion = mock(Conclusion.class);

        // when
        final Rule<InferenceContext<Object>, Object> rule =
                new RuleImplementation<InferenceContext<Object>, Object>(NAME, premise, conclusion);

        // then
        final int alwaysTheSame = rule.hashCode();

        for (int i = 0; i < 30; i++) {
            assertThat(rule.hashCode(), is(alwaysTheSame));
        }
    }

    /**
     * <p>Test method for {@link RuleImplementation#hashCode()}</p>
     * 
     * <p>Ensures that hashCode() is consistent with equals().</p>
     */
    @SuppressWarnings({ "boxing", "unchecked" })
    @Test
    public void hashCodeIsConsistentWithEquals() {
        // given
        final Predicate<InferenceContext<Object>> premise = mock(Predicate.class);
        final Conclusion<InferenceContext<Object>> conclusion = mock(Conclusion.class);

        // when
        final Rule<InferenceContext<Object>, Object> rule1 =
                new RuleImplementation<InferenceContext<Object>, Object>(NAME, premise, conclusion);
        final Rule<InferenceContext<Object>, Object> rule2 =
                new RuleImplementation<InferenceContext<Object>, Object>(NAME, premise, conclusion);

        // then
        assertThat(rule1.equals(rule2), is(true));
        assertThat(rule1.hashCode(), is(rule2.hashCode()));
    }

    /**
     * <p>Test method for {@link RuleImplementation#compareTo(de.viadee.rules.Rule)}</p>
     * 
     * <p>Ensures that compareTo() is consistent with equals().</p>
     */
    @SuppressWarnings({ "boxing", "unchecked" })
    @Test
    public void shoudCompareToOtherRules() {
        // given
        final Predicate<InferenceContext<Object>> premise = mock(Predicate.class);
        final Conclusion<InferenceContext<Object>> conclusion = mock(Conclusion.class);

        // when
        final Rule<InferenceContext<Object>, Object> rule1 =
                new RuleImplementation<InferenceContext<Object>, Object>(NAME, premise, conclusion);
        final Rule<InferenceContext<Object>, Object> rule2 =
                new RuleImplementation<InferenceContext<Object>, Object>(NAME, premise, conclusion);

        // then
        assertThat(rule1.compareTo(rule2), is(0));
    }

}
