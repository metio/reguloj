/*
 * Copyright © 2010 Sebastian Hoß <mail@shoss.de>
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See http://www.wtfpl.net/ for more details.
 */
package com.github.sebhoss.reguloj;

import java.util.HashSet;
import java.util.Set;

import com.github.sebhoss.common.annotation.CompilerWarnings;

import org.junit.Assert;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.BDDMockito;
import org.mockito.Mockito;

/**
 * Test cases for the ChainedRuleEngine.
 */
@SuppressWarnings({ CompilerWarnings.NULL, CompilerWarnings.STATIC_METHOD, CompilerWarnings.UNCHECKED })
public class ChainedRuleEngineTest {

    /** Checks expected exception inside single test cases. */
    @org.junit.Rule
    public ExpectedException thrown = ExpectedException.none();

    /**
     * <p>
     * Test method for ChainedRuleEngine#analyze(Context, java.util.Set)
     * </p>
     * <p>
     * Ensures that <code>false</code> is returned when passing in an empty set.
     * </p>
     */
    @Test
    public void shouldReturnFalseForEmptyRuleSet() {
        final RuleEngine<Context<Object>> engine = new ChainedRuleEngine<>();
        final Context<Object> context = Mockito.mock(Context.class);
        final Set<Rule<Context<Object>>> rules = new HashSet<>();

        final boolean fired = engine.analyze(rules, context);

        Assert.assertFalse(fired);
    }

    /**
     * <p>
     * Test method for ChainedRuleEngine#analyze(Context, Set)
     * </p>
     * <p>
     * Ensures that <code>true</code> is returned if any rule can fire.
     * </p>
     */
    @Test
    @SuppressWarnings(CompilerWarnings.BOXING)
    public void shouldReturnTrueIfRuleFired() {
        final RuleEngine<Context<Object>> engine = new ChainedRuleEngine<>();
        final Context<Object> context = Mockito.mock(Context.class);
        final Rule<Context<Object>> rule = Mockito.mock(Rule.class);
        BDDMockito.given(rule.fires(context)).willReturn(true);
        final Set<Rule<Context<Object>>> rules = new HashSet<>();
        rules.add(rule);

        final boolean fired = engine.analyze(rules, context);

        Assert.assertTrue(fired);
    }

    /**
     * <p>
     * Test method for ChainedRuleEngine#analyze(Context, Set)
     * </p>
     * <p>
     * Ensures that <code>false</code> is returned if no rule can fire.
     * </p>
     */
    @Test
    @SuppressWarnings(CompilerWarnings.BOXING)
    public void shouldReturnFalseIfNoRuleFires() {
        final RuleEngine<Context<Object>> engine = new ChainedRuleEngine<>();
        final Context<Object> context = Mockito.mock(Context.class);
        final Rule<Context<Object>> rule = Mockito.mock(Rule.class);
        BDDMockito.given(rule.fires(context)).willReturn(false);
        final Set<Rule<Context<Object>>> rules = new HashSet<>();
        rules.add(rule);

        final boolean fired = engine.analyze(rules, context);

        Assert.assertFalse(fired);
    }

    /**
     * <p>
     * Test method for ChainedRuleEngine#infer(Context, Set)
     * </p>
     * <p>
     * Ensures that the engine can handle an empty rule set.
     * </p>
     */

    @Test
    public void shouldRunWithEmptyRuleSet() {
        final RuleEngine<Context<Object>> engine = new ChainedRuleEngine<>();
        final Context<Object> context = Mockito.mock(Context.class);
        final Set<Rule<Context<Object>>> rules = new HashSet<>();

        engine.infer(rules, context);
    }

    /**
     * <p>
     * Test method for ChainedRuleEngine#infer(Context, Set)
     * </p>
     * <p>
     * Ensures that the engine loops if any rule can fire.
     * </p>
     */
    @Test
    @SuppressWarnings(CompilerWarnings.BOXING)
    public void shouldLoopWithFiringRule() {
        final RuleEngine<Context<Object>> engine = new ChainedRuleEngine<>();
        final Context<Object> context = Mockito.mock(Context.class);
        final Rule<Context<Object>> rule = Mockito.mock(Rule.class);
        BDDMockito.given(rule.run(context)).willReturn(true).willReturn(false);
        final Set<Rule<Context<Object>>> rules = new HashSet<>();
        rules.add(rule);

        engine.infer(rules, context);
    }

    /**
     * <p>
     * Test method for ChainedRuleEngine#infer(Context, Set)
     * </p>
     * <p>
     * Ensures that the engine does not loop if no rule can fire.
     * </p>
     */
    @Test
    @SuppressWarnings(CompilerWarnings.BOXING)
    public void shouldNotLoopWithNotFiringRule() {
        final RuleEngine<Context<Object>> engine = new ChainedRuleEngine<>();
        final Context<Object> context = Mockito.mock(Context.class);
        final Rule<Context<Object>> rule = Mockito.mock(Rule.class);
        BDDMockito.given(rule.run(context)).willReturn(false);
        final Set<Rule<Context<Object>>> rules = new HashSet<>();
        rules.add(rule);

        engine.infer(rules, context);
    }
}
