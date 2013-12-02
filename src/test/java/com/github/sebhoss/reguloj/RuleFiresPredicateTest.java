/*
 * Copyright © 2010 Sebastian Hoß <mail@shoss.de>
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See http://www.wtfpl.net/ for more details.
 */
package com.github.sebhoss.reguloj;

import com.github.sebhoss.warnings.CompilerWarnings;
import com.google.common.base.Predicate;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;

/**
 * Test cases for RuleFiresPredicate.
 */
@SuppressWarnings({ CompilerWarnings.BOXING, CompilerWarnings.UNCHECKED })
public class RuleFiresPredicateTest {

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
        predicate = new RuleFiresPredicate<>(context);
    }

    /**
     * Ensures that a <code>true</code> is returned when the given rule fires.
     */
    @Test
    public void shouldReturnTrueOnFiringRule() {
        BDDMockito.given(rule.fires(context)).willReturn(Boolean.TRUE);

        final boolean applied = predicate.apply(rule);

        Assert.assertTrue(applied);
    }

    /**
     * Ensures that a <code>false</code> is returned when the given rule does not fire.
     */
    @Test
    public void shouldReturnFalseOnNonAppliedConclusion() {
        BDDMockito.given(rule.fires(context)).willReturn(Boolean.FALSE);

        final boolean applied = predicate.apply(rule);

        Assert.assertFalse(applied);
    }

}
