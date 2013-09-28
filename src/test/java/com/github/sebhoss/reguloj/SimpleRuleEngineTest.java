/*
 * Copyright © 2010 Sebastian Hoß <mail@shoss.de>
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See http://www.wtfpl.net/ for more details.
 */
package com.github.sebhoss.reguloj;

import java.util.Set;
import java.util.TreeSet;

import com.github.sebhoss.common.annotation.CompilerWarnings;

import org.junit.Assert;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.BDDMockito;
import org.mockito.Mockito;

/**
 * Test cases for the {@link SimpleRuleEngine}.
 * 
 * @see SimpleRuleEngine
 */
@SuppressWarnings({ CompilerWarnings.NULL, CompilerWarnings.STATIC_METHOD, CompilerWarnings.UNCHECKED })
public class SimpleRuleEngineTest {

    /** Checks expected exception inside single test cases. */
    @org.junit.Rule
    public ExpectedException thrown = ExpectedException.none();

    /**
     * <p>
     * Test method for {@link SimpleRuleEngine#analyze(com.github.sebhoss.reguloj.Context, java.util.Set)}
     * </p>
     * <p>
     * Ensures that a <code>null</code> context ist not permitted.
     * </p>
     */
    @Test
    public void shouldNotAnalyzeNullContext() {
        final RuleEngine<Context<Object>> engine = new SimpleRuleEngine<>();
        final Set<Rule<Context<Object>>> rules = Mockito.mock(Set.class);

        thrown.expect(NullPointerException.class);
        final boolean fired = engine.analyze(null, rules);

        Assert.assertFalse(fired);
    }

    /**
     * <p>
     * Test method for {@link SimpleRuleEngine#analyze(com.github.sebhoss.reguloj.Context, java.util.Set)}
     * </p>
     * <p>
     * Ensures that a <code>null</code> rule set ist not permitted.
     * </p>
     */
    @Test
    public void shouldNotAnalyzeNullRuleSet() {
        final RuleEngine<Context<Object>> engine = new SimpleRuleEngine<>();
        final Context<Object> context = Mockito.mock(Context.class);

        thrown.expect(NullPointerException.class);
        final boolean fired = engine.analyze(context, null);

        Assert.assertFalse(fired);
    }

    /**
     * <p>
     * Test method for {@link SimpleRuleEngine#analyze(com.github.sebhoss.reguloj.Context, java.util.Set)}
     * </p>
     * <p>
     * Ensures that <code>false</code> is returned when passing in an empty set.
     * </p>
     */
    @Test
    public void shouldReturnFalseForEmptyRuleSet() {
        final RuleEngine<Context<Object>> engine = new SimpleRuleEngine<>();
        final Context<Object> context = Mockito.mock(Context.class);
        final Set<Rule<Context<Object>>> rules = new TreeSet<>();

        final boolean fired = engine.analyze(context, rules);

        Assert.assertFalse(fired);
    }

    /**
     * <p>
     * Test method for {@link SimpleRuleEngine#analyze(com.github.sebhoss.reguloj.Context, java.util.Set)}
     * </p>
     * <p>
     * Ensures that <code>true</code> is returned if any rule can fire.
     * </p>
     */
    @Test
    @SuppressWarnings(CompilerWarnings.BOXING)
    public void shouldReturnTrueIfRuleFired() {
        final RuleEngine<Context<Object>> engine = new SimpleRuleEngine<>();
        final Context<Object> context = Mockito.mock(Context.class);
        final Rule<Context<Object>> rule = Mockito.mock(Rule.class);
        BDDMockito.given(rule.fires(context)).willReturn(true);
        final Set<Rule<Context<Object>>> rules = new TreeSet<>();
        rules.add(rule);

        final boolean fired = engine.analyze(context, rules);

        Assert.assertTrue(fired);
    }

    /**
     * <p>
     * Test method for {@link SimpleRuleEngine#analyze(com.github.sebhoss.reguloj.Context, java.util.Set)}
     * </p>
     * <p>
     * Ensures that <code>false</code> is returned if no rule can fire.
     * </p>
     */
    @Test
    @SuppressWarnings(CompilerWarnings.BOXING)
    public void shouldReturnFalseIfNoRuleFires() {
        final RuleEngine<Context<Object>> engine = new SimpleRuleEngine<>();
        final Context<Object> context = Mockito.mock(Context.class);
        final Rule<Context<Object>> rule = Mockito.mock(Rule.class);
        BDDMockito.given(rule.fires(context)).willReturn(false);
        final Set<Rule<Context<Object>>> rules = new TreeSet<>();
        rules.add(rule);

        final boolean fired = engine.analyze(context, rules);

        Assert.assertFalse(fired);
    }

    /**
     * <p>
     * Test method for {@link SimpleRuleEngine#infer(Context, Set)}
     * </p>
     * <p>
     * Ensures that a <code>null</code> context is not permitted
     * </p>
     */
    @Test
    public void shouldNotInferWithNullContext() {
        final RuleEngine<Context<Object>> engine = new SimpleRuleEngine<>();
        final Set<Rule<Context<Object>>> rules = Mockito.mock(Set.class);

        thrown.expect(NullPointerException.class);

        engine.infer(null, rules);
    }

    /**
     * <p>
     * Test method for {@link SimpleRuleEngine#infer(Context, Set)}
     * </p>
     * <p>
     * Ensures that a <code>null</code> rule set is not permitted.
     * </p>
     */
    @Test
    public void shouldNotInferWithNullRuleSet() {
        final RuleEngine<Context<Object>> engine = new SimpleRuleEngine<>();
        final Context<Object> context = Mockito.mock(Context.class);

        thrown.expect(NullPointerException.class);

        engine.infer(context, null);
    }

    /**
     * <p>
     * Test method for {@link SimpleRuleEngine#infer(Context, Set)}
     * </p>
     * <p>
     * Ensures that the engine can handle an empty rule set.
     * </p>
     */

    @Test
    public void shouldRunWithEmptyRuleSet() {
        final RuleEngine<Context<Object>> engine = new SimpleRuleEngine<>();
        final Context<Object> context = Mockito.mock(Context.class);
        final Set<Rule<Context<Object>>> rules = new TreeSet<>();

        engine.infer(context, rules);
    }

    /**
     * <p>
     * Test method for {@link SimpleRuleEngine#infer(Context, Set)}
     * </p>
     * <p>
     * Ensures that the engine loops if any rule can fire.
     * </p>
     */
    @Test
    @SuppressWarnings(CompilerWarnings.BOXING)
    public void shouldLoopWithFiringRule() {
        final RuleEngine<Context<Object>> engine = new SimpleRuleEngine<>();
        final Context<Object> context = Mockito.mock(Context.class);
        final Rule<Context<Object>> rule = Mockito.mock(Rule.class);
        BDDMockito.given(rule.run(context)).willReturn(true).willReturn(false);
        final Set<Rule<Context<Object>>> rules = new TreeSet<>();
        rules.add(rule);

        engine.infer(context, rules);
    }

    /**
     * <p>
     * Test method for {@link SimpleRuleEngine#infer(Context, Set)}
     * </p>
     * <p>
     * Ensures that the engine does not loop if no rule can fire.
     * </p>
     */
    @Test
    @SuppressWarnings(CompilerWarnings.BOXING)
    public void shouldNotLoopWithNotFiringRule() {
        final RuleEngine<Context<Object>> engine = new SimpleRuleEngine<>();
        final Context<Object> context = Mockito.mock(Context.class);
        final Rule<Context<Object>> rule = Mockito.mock(Rule.class);
        BDDMockito.given(rule.run(context)).willReturn(false);
        final Set<Rule<Context<Object>>> rules = new TreeSet<>();
        rules.add(rule);

        engine.infer(context, rules);
    }
}
