/*
 * Copyright © 2010 Sebastian Hoß <mail@shoss.de>
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See http://www.wtfpl.net/ for more details.
 */
package com.github.sebhoss.reguloj;

import com.github.sebhoss.warnings.CompilerWarnings;
import com.google.common.collect.ImmutableList;

import org.junit.Before;
import org.junit.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;

/**
 * Test case for FirstWinsRuleEngine.
 */
@SuppressWarnings({ CompilerWarnings.BOXING, CompilerWarnings.UNCHECKED })
public class FirstWinsRuleEngineTest {

    private RuleEngine<Context<Object>> engine;
    private Context<Object>             context;
    private Rule<Context<Object>>       rule1;
    private Rule<Context<Object>>       rule2;

    /**
     * Creates rule engine, context and rules.
     */
    @Before
    public void setup() {
        engine = new FirstWinsRuleEngine<>();
        context = Mockito.mock(Context.class);
        rule1 = Mockito.mock(Rule.class);
        rule2 = Mockito.mock(Rule.class);
    }

    /**
     * Ensures that only the first matching rule will be run.
     */
    @Test
    public void shouldOnlyRunFirstMatchingRule() {
        BDDMockito.given(rule1.fires(context)).willReturn(Boolean.TRUE);
        BDDMockito.given(rule2.fires(context)).willReturn(Boolean.FALSE);

        engine.infer(ImmutableList.of(rule1, rule2), context);

        Mockito.verify(rule1, Mockito.times(1)).fires(context);
        Mockito.verify(rule1, Mockito.times(1)).run(context);
        Mockito.verifyZeroInteractions(rule2);
    }

    /**
     * Ensures that the second rule will be run, if the first one did not.
     */
    @Test
    public void shouldOnlyRunFirstMatchingRuleSecond() {
        BDDMockito.given(rule1.fires(context)).willReturn(Boolean.FALSE);
        BDDMockito.given(rule2.fires(context)).willReturn(Boolean.TRUE);

        engine.infer(ImmutableList.of(rule1, rule2), context);

        Mockito.verify(rule1, Mockito.times(1)).fires(context);
        Mockito.verify(rule1, Mockito.times(0)).run(context);
        Mockito.verify(rule2, Mockito.times(1)).fires(context);
        Mockito.verify(rule2, Mockito.times(1)).run(context);
    }

}
