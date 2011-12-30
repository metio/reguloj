/*
 * Project: viaRules
 * Package: de.viadee.rules.implementation
 * File   : SimpleRuleEngineTest.java
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

import java.util.Set;
import java.util.TreeSet;

import org.hamcrest.core.Is;
import org.junit.Assert;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.BDDMockito;
import org.mockito.Mockito;

import de.viadee.rules.InferenceContext;
import de.viadee.rules.Rule;
import de.viadee.rules.RuleEngine;

/**
 * <p>Test cases for the {@link SimpleRuleEngine}.</p>
 *
 * @author  Sebastian Hoß (sebastian.hoss@viadee.de)
 * @see     SimpleRuleEngine
 * @since   1.0.0
 */
@SuppressWarnings("static-method")
public class SimpleRuleEngineTest {

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
     * <p>Test method for {@link SimpleRuleEngine#analyze(de.viadee.rules.InferenceContext, java.util.Set)}</p>
     * 
     * <p>Ensures that a <code>null</code> context ist not permitted.</p>
     */
    @SuppressWarnings({ "boxing" })
    @Test
    public void shouldNotAnalyzeNullContext() {
        // given
        final RuleEngine<InferenceContext<Object>> engine = new SimpleRuleEngine<InferenceContext<Object>>();
        final Set<Rule<InferenceContext<Object>>> rules = Mockito.mock(Set.class);

        // when
        this.thrown.expect(NullPointerException.class);
        final boolean fired = engine.analyze(null, rules);

        // then
        Assert.assertThat(fired, Is.is(false));
    }

    /**
     * <p>Test method for {@link SimpleRuleEngine#analyze(de.viadee.rules.InferenceContext, java.util.Set)}</p>
     * 
     * <p>Ensures that a <code>null</code> rule set ist not permitted.</p>
     */
    @SuppressWarnings({ "boxing" })
    @Test
    public void shouldNotAnalyzeNullRuleSet() {
        // given
        final RuleEngine<InferenceContext<Object>> engine = new SimpleRuleEngine<InferenceContext<Object>>();
        final InferenceContext<Object> context = Mockito.mock(InferenceContext.class);

        // when
        this.thrown.expect(NullPointerException.class);
        final boolean fired = engine.analyze(context, null);

        // then
        Assert.assertThat(fired, Is.is(false));
    }

    /**
     * <p>Test method for {@link SimpleRuleEngine#analyze(de.viadee.rules.InferenceContext, java.util.Set)}</p>
     * 
     * <p>Ensures that <code>false</code> is returned when passing in an empty set.</p>
     */
    @SuppressWarnings({ "boxing" })
    @Test
    public void shouldReturnFalseForEmptyRuleSet() {
        // given
        final RuleEngine<InferenceContext<Object>> engine = new SimpleRuleEngine<InferenceContext<Object>>();
        final InferenceContext<Object> context = Mockito.mock(InferenceContext.class);
        final Set<Rule<InferenceContext<Object>>> rules = new TreeSet<Rule<InferenceContext<Object>>>();

        // when
        final boolean fired = engine.analyze(context, rules);

        // then
        Assert.assertThat(fired, Is.is(false));
    }

    /**
     * <p>Test method for {@link SimpleRuleEngine#analyze(de.viadee.rules.InferenceContext, java.util.Set)}</p>
     * 
     * <p>Ensures that <code>true</code> is returned if any rule can fire.</p>
     */
    @SuppressWarnings({ "boxing" })
    @Test
    public void shouldReturnTrueIfRuleFired() {
        // given
        final RuleEngine<InferenceContext<Object>> engine = new SimpleRuleEngine<InferenceContext<Object>>();
        final InferenceContext<Object> context = Mockito.mock(InferenceContext.class);

        final Rule<InferenceContext<Object>> rule = Mockito.mock(Rule.class);
        BDDMockito.given(rule.fires(context)).willReturn(true);

        final Set<Rule<InferenceContext<Object>>> rules = new TreeSet<Rule<InferenceContext<Object>>>();
        rules.add(rule);

        // when
        final boolean fired = engine.analyze(context, rules);

        // then
        Assert.assertThat(fired, Is.is(true));
    }

    /**
     * <p>Test method for {@link SimpleRuleEngine#analyze(de.viadee.rules.InferenceContext, java.util.Set)}</p>
     * 
     * <p>Ensures that <code>false</code> is returned if no rule can fire.</p>
     */
    @SuppressWarnings({ "boxing" })
    @Test
    public void shouldReturnFalseIfNoRuleFires() {
        // given
        final RuleEngine<InferenceContext<Object>> engine = new SimpleRuleEngine<InferenceContext<Object>>();
        final InferenceContext<Object> context = Mockito.mock(InferenceContext.class);

        final Rule<InferenceContext<Object>> rule = Mockito.mock(Rule.class);
        BDDMockito.given(rule.fires(context)).willReturn(false);

        final Set<Rule<InferenceContext<Object>>> rules = new TreeSet<Rule<InferenceContext<Object>>>();
        rules.add(rule);

        // when
        final boolean fired = engine.analyze(context, rules);

        // then
        Assert.assertThat(fired, Is.is(false));
    }

    /**
     * <p>Test method for {@link SimpleRuleEngine#infer(InferenceContext, Set)}</p>
     * 
     * <p>Ensures that a <code>null</code> context is not permitted</p>
     */
    @Test
    public void shouldNotInferWithNullContext() {
        // given
        final RuleEngine<InferenceContext<Object>> engine = new SimpleRuleEngine<InferenceContext<Object>>();
        final Set<Rule<InferenceContext<Object>>> rules = Mockito.mock(Set.class);

        // when
        this.thrown.expect(NullPointerException.class);

        // then
        engine.infer(null, rules);
    }

    /**
     * <p>Test method for {@link SimpleRuleEngine#infer(InferenceContext, Set)}</p>
     * 
     * <p>Ensures that a <code>null</code> rule set is not permitted.</p>
     */
    @Test
    public void shouldNotInferWithNullRuleSet() {
        // given
        final RuleEngine<InferenceContext<Object>> engine = new SimpleRuleEngine<InferenceContext<Object>>();
        final InferenceContext<Object> context = Mockito.mock(InferenceContext.class);

        // when
        this.thrown.expect(NullPointerException.class);

        // then
        engine.infer(context, null);
    }

    /**
     * <p>Test method for {@link SimpleRuleEngine#infer(InferenceContext, Set)}</p>
     * 
     * <p>Ensures that the engine can handle an empty rule set.</p>
     */

    @Test
    public void shouldRunWithEmptyRuleSet() {
        // given
        final RuleEngine<InferenceContext<Object>> engine = new SimpleRuleEngine<InferenceContext<Object>>();
        final InferenceContext<Object> context = Mockito.mock(InferenceContext.class);
        final Set<Rule<InferenceContext<Object>>> rules = new TreeSet<Rule<InferenceContext<Object>>>();

        // when

        // then
        engine.infer(context, rules);
    }

    /**
     * <p>Test method for {@link SimpleRuleEngine#infer(InferenceContext, Set)}</p>
     * 
     * <p>Ensures that the engine loops if any rule can fire.</p>
     */
    @SuppressWarnings({ "boxing" })
    @Test
    public void shouldLoopWithFiringRule() {
        // given
        final RuleEngine<InferenceContext<Object>> engine = new SimpleRuleEngine<InferenceContext<Object>>();
        final InferenceContext<Object> context = Mockito.mock(InferenceContext.class);

        final Rule<InferenceContext<Object>> rule = Mockito.mock(Rule.class);
        BDDMockito.given(rule.run(context)).willReturn(true).willReturn(false);

        final Set<Rule<InferenceContext<Object>>> rules = new TreeSet<Rule<InferenceContext<Object>>>();
        rules.add(rule);

        // when

        // then
        engine.infer(context, rules);
    }

    /**
     * <p>Test method for {@link SimpleRuleEngine#infer(InferenceContext, Set)}</p>
     *
     * <p>Ensures that the engine does not loop if no rule can fire.</p>
     */
    @SuppressWarnings({ "boxing" })
    @Test
    public void shouldNotLoopWithNotFiringRule() {
        // given
        final RuleEngine<InferenceContext<Object>> engine = new SimpleRuleEngine<InferenceContext<Object>>();
        final InferenceContext<Object> context = Mockito.mock(InferenceContext.class);

        final Rule<InferenceContext<Object>> rule = Mockito.mock(Rule.class);
        BDDMockito.given(rule.run(context)).willReturn(false);

        final Set<Rule<InferenceContext<Object>>> rules = new TreeSet<Rule<InferenceContext<Object>>>();
        rules.add(rule);

        // when

        // then
        engine.infer(context, rules);
    }
}
