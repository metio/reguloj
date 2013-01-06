/* This program is free software. It comes without any warranty, to
 * the extent permitted by applicable law. You can redistribute it
 * and/or modify it under the terms of the Do What The Fuck You Want
 * To Public License, Version 2, as published by Sam Hocevar. See
 * http://sam.zoy.org/wtfpl/COPYING for more details.
 */
package com.github.sebhoss.reguloj.implementation;

import java.util.Set;
import java.util.TreeSet;

import org.hamcrest.core.Is;
import org.junit.Assert;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.BDDMockito;
import org.mockito.Mockito;

import com.github.sebhoss.reguloj.Context;
import com.github.sebhoss.reguloj.Rule;
import com.github.sebhoss.reguloj.RuleEngine;
import com.github.sebhoss.reguloj.implementation.SimpleRuleEngine;


/**
 * <p>Test cases for the {@link SimpleRuleEngine}.</p>
 *
 * @see     SimpleRuleEngine
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
     * <p>Test method for {@link SimpleRuleEngine#analyze(com.github.sebhoss.reguloj.Context, java.util.Set)}</p>
     * 
     * <p>Ensures that a <code>null</code> context ist not permitted.</p>
     */
    @SuppressWarnings({ "boxing" })
    @Test
    public void shouldNotAnalyzeNullContext() {
        // given
        final RuleEngine<Context<Object>> engine = new SimpleRuleEngine<Context<Object>>();
        final Set<Rule<Context<Object>>> rules = Mockito.mock(Set.class);

        // when
        this.thrown.expect(NullPointerException.class);
        final boolean fired = engine.analyze(null, rules);

        // then
        Assert.assertThat(fired, Is.is(false));
    }

    /**
     * <p>Test method for {@link SimpleRuleEngine#analyze(com.github.sebhoss.reguloj.Context, java.util.Set)}</p>
     * 
     * <p>Ensures that a <code>null</code> rule set ist not permitted.</p>
     */
    @SuppressWarnings({ "boxing" })
    @Test
    public void shouldNotAnalyzeNullRuleSet() {
        // given
        final RuleEngine<Context<Object>> engine = new SimpleRuleEngine<Context<Object>>();
        final Context<Object> context = Mockito.mock(Context.class);

        // when
        this.thrown.expect(NullPointerException.class);
        final boolean fired = engine.analyze(context, null);

        // then
        Assert.assertThat(fired, Is.is(false));
    }

    /**
     * <p>Test method for {@link SimpleRuleEngine#analyze(com.github.sebhoss.reguloj.Context, java.util.Set)}</p>
     * 
     * <p>Ensures that <code>false</code> is returned when passing in an empty set.</p>
     */
    @SuppressWarnings({ "boxing" })
    @Test
    public void shouldReturnFalseForEmptyRuleSet() {
        // given
        final RuleEngine<Context<Object>> engine = new SimpleRuleEngine<Context<Object>>();
        final Context<Object> context = Mockito.mock(Context.class);
        final Set<Rule<Context<Object>>> rules = new TreeSet<Rule<Context<Object>>>();

        // when
        final boolean fired = engine.analyze(context, rules);

        // then
        Assert.assertThat(fired, Is.is(false));
    }

    /**
     * <p>Test method for {@link SimpleRuleEngine#analyze(com.github.sebhoss.reguloj.Context, java.util.Set)}</p>
     * 
     * <p>Ensures that <code>true</code> is returned if any rule can fire.</p>
     */
    @SuppressWarnings({ "boxing" })
    @Test
    public void shouldReturnTrueIfRuleFired() {
        // given
        final RuleEngine<Context<Object>> engine = new SimpleRuleEngine<Context<Object>>();
        final Context<Object> context = Mockito.mock(Context.class);

        final Rule<Context<Object>> rule = Mockito.mock(Rule.class);
        BDDMockito.given(rule.fires(context)).willReturn(true);

        final Set<Rule<Context<Object>>> rules = new TreeSet<Rule<Context<Object>>>();
        rules.add(rule);

        // when
        final boolean fired = engine.analyze(context, rules);

        // then
        Assert.assertThat(fired, Is.is(true));
    }

    /**
     * <p>Test method for {@link SimpleRuleEngine#analyze(com.github.sebhoss.reguloj.Context, java.util.Set)}</p>
     * 
     * <p>Ensures that <code>false</code> is returned if no rule can fire.</p>
     */
    @SuppressWarnings({ "boxing" })
    @Test
    public void shouldReturnFalseIfNoRuleFires() {
        // given
        final RuleEngine<Context<Object>> engine = new SimpleRuleEngine<Context<Object>>();
        final Context<Object> context = Mockito.mock(Context.class);

        final Rule<Context<Object>> rule = Mockito.mock(Rule.class);
        BDDMockito.given(rule.fires(context)).willReturn(false);

        final Set<Rule<Context<Object>>> rules = new TreeSet<Rule<Context<Object>>>();
        rules.add(rule);

        // when
        final boolean fired = engine.analyze(context, rules);

        // then
        Assert.assertThat(fired, Is.is(false));
    }

    /**
     * <p>Test method for {@link SimpleRuleEngine#infer(Context, Set)}</p>
     * 
     * <p>Ensures that a <code>null</code> context is not permitted</p>
     */
    @Test
    public void shouldNotInferWithNullContext() {
        // given
        final RuleEngine<Context<Object>> engine = new SimpleRuleEngine<Context<Object>>();
        final Set<Rule<Context<Object>>> rules = Mockito.mock(Set.class);

        // when
        this.thrown.expect(NullPointerException.class);

        // then
        engine.infer(null, rules);
    }

    /**
     * <p>Test method for {@link SimpleRuleEngine#infer(Context, Set)}</p>
     * 
     * <p>Ensures that a <code>null</code> rule set is not permitted.</p>
     */
    @Test
    public void shouldNotInferWithNullRuleSet() {
        // given
        final RuleEngine<Context<Object>> engine = new SimpleRuleEngine<Context<Object>>();
        final Context<Object> context = Mockito.mock(Context.class);

        // when
        this.thrown.expect(NullPointerException.class);

        // then
        engine.infer(context, null);
    }

    /**
     * <p>Test method for {@link SimpleRuleEngine#infer(Context, Set)}</p>
     * 
     * <p>Ensures that the engine can handle an empty rule set.</p>
     */

    @Test
    public void shouldRunWithEmptyRuleSet() {
        // given
        final RuleEngine<Context<Object>> engine = new SimpleRuleEngine<Context<Object>>();
        final Context<Object> context = Mockito.mock(Context.class);
        final Set<Rule<Context<Object>>> rules = new TreeSet<Rule<Context<Object>>>();

        // when

        // then
        engine.infer(context, rules);
    }

    /**
     * <p>Test method for {@link SimpleRuleEngine#infer(Context, Set)}</p>
     * 
     * <p>Ensures that the engine loops if any rule can fire.</p>
     */
    @SuppressWarnings({ "boxing" })
    @Test
    public void shouldLoopWithFiringRule() {
        // given
        final RuleEngine<Context<Object>> engine = new SimpleRuleEngine<Context<Object>>();
        final Context<Object> context = Mockito.mock(Context.class);

        final Rule<Context<Object>> rule = Mockito.mock(Rule.class);
        BDDMockito.given(rule.run(context)).willReturn(true).willReturn(false);

        final Set<Rule<Context<Object>>> rules = new TreeSet<Rule<Context<Object>>>();
        rules.add(rule);

        // when

        // then
        engine.infer(context, rules);
    }

    /**
     * <p>Test method for {@link SimpleRuleEngine#infer(Context, Set)}</p>
     *
     * <p>Ensures that the engine does not loop if no rule can fire.</p>
     */
    @SuppressWarnings({ "boxing" })
    @Test
    public void shouldNotLoopWithNotFiringRule() {
        // given
        final RuleEngine<Context<Object>> engine = new SimpleRuleEngine<Context<Object>>();
        final Context<Object> context = Mockito.mock(Context.class);

        final Rule<Context<Object>> rule = Mockito.mock(Rule.class);
        BDDMockito.given(rule.run(context)).willReturn(false);

        final Set<Rule<Context<Object>>> rules = new TreeSet<Rule<Context<Object>>>();
        rules.add(rule);

        // when

        // then
        engine.infer(context, rules);
    }
}
