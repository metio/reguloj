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
 * Test cases for RuleRunsPredicate.
 */
@SuppressWarnings({ CompilerWarnings.BOXING, CompilerWarnings.UNCHECKED })
public class RuleRunsPredicateTest {

    private Context<Object>                  context;
    private Rule<Context<Object>>            rule;
    private Predicate<Rule<Context<Object>>> predicate;

    /**
     * Creates predicate and conclusion for each test.
     */
    @Before
    public void setup() {
        context = Mockito.mock(Context.class);
        rule = Mockito.mock(Rule.class);
        predicate = new RuleRunsPredicate<>(context);
    }

    /**
     * Ensures that a <code>true</code> is returned when the given rule fires.
     */
    @Test
    public void shouldReturnTrueOnFiringRule() {
        BDDMockito.given(rule.run(context)).willReturn(Boolean.TRUE);

        final boolean applied = predicate.test(rule);

        Assert.assertTrue(applied);
    }

    /**
     * Ensures that a <code>false</code> is returned when the given rule does not fire.
     */
    @Test
    public void shouldReturnFalseOnNonAppliedConclusion() {
        BDDMockito.given(rule.run(context)).willReturn(Boolean.FALSE);

        final boolean applied = predicate.test(rule);

        Assert.assertFalse(applied);
    }

}
