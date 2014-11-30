/*
 * Copyright © 2010 Sebastian Hoß <mail@shoss.de>
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See http://www.wtfpl.net/ for more details.
 */
package com.github.sebhoss.reguloj;

import java.util.HashSet;
import java.util.Set;

import com.github.sebhoss.warnings.CompilerWarnings;
import com.google.common.collect.ImmutableList;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.BDDMockito;
import org.mockito.Mockito;

/**
 * Test cases for the ChainedRuleEngine.
 */
@SuppressWarnings({ CompilerWarnings.BOXING, CompilerWarnings.UNCHECKED })
public class ChainedRuleEngineTest {

    /** Checks expected exception inside single test cases. */
    @org.junit.Rule
    public ExpectedException            thrown = ExpectedException.none();

    private RuleEngine<Context<Object>> engine;
    private Context<Object>             context;
    private Rule<Context<Object>>       rule;

    /**
     * Creates rule engine and context.
     */
    @Before
    public void setup() {
        engine = new ChainedRuleEngine<>();
        context = Mockito.mock(Context.class);
        rule = Mockito.mock(Rule.class);
    }

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
    public void shouldReturnTrueIfRuleFired() {
        BDDMockito.given(rule.fires(context)).willReturn(Boolean.TRUE);

        final boolean fired = engine.analyze(ImmutableList.of(rule), context);

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
    public void shouldReturnFalseIfNoRuleFires() {
        BDDMockito.given(rule.fires(context)).willReturn(Boolean.FALSE);

        final boolean fired = engine.analyze(ImmutableList.of(rule), context);

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
    public void shouldLoopWithFiringRule() {
        BDDMockito.given(rule.fires(context)).willReturn(Boolean.TRUE).willReturn(Boolean.FALSE);

        engine.infer(ImmutableList.of(rule), context);

        Mockito.verify(rule, Mockito.times(2)).fires(context);
        Mockito.verify(rule, Mockito.times(1)).run(context);
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
    public void shouldNotLoopWithNotFiringRule() {
        BDDMockito.given(rule.fires(context)).willReturn(Boolean.FALSE);

        engine.infer(ImmutableList.of(rule), context);

        Mockito.verify(rule, Mockito.times(1)).fires(context);
        Mockito.verify(rule, Mockito.times(0)).run(context);
    }

}
