package com.github.sebhoss.reguloj;

import com.github.sebhoss.common.annotation.CompilerWarnings;
import com.google.common.collect.ImmutableList;

import org.junit.Before;
import org.junit.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;

/**
 * Test case for FirstWinsRuleEngine.
 */
@SuppressWarnings({ CompilerWarnings.BOXING, CompilerWarnings.NULL, CompilerWarnings.UNCHECKED })
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
        BDDMockito.given(rule1.run(context)).willReturn(Boolean.TRUE);

        engine.infer(ImmutableList.of(rule1, rule2), context);

        Mockito.verify(rule1, Mockito.times(1)).run(context);
        Mockito.verifyZeroInteractions(rule2);
    }

    /**
     * Ensures that the second rule will be run, if the first one did not.
     */
    @Test
    public void shouldOnlyRunFirstMatchingRuleSecond() {
        BDDMockito.given(rule1.run(context)).willReturn(Boolean.FALSE);
        BDDMockito.given(rule2.run(context)).willReturn(Boolean.TRUE);

        engine.infer(ImmutableList.of(rule1, rule2), context);

        Mockito.verify(rule1, Mockito.times(1)).run(context);
        Mockito.verify(rule2, Mockito.times(1)).run(context);
    }

}
