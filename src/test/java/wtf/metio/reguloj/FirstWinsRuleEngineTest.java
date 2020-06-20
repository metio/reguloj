package wtf.metio.reguloj;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

final class FirstWinsRuleEngineTest {

    private RuleEngine<Context<Object>> engine;
    private Context<Object> context;
    private Rule<Context<Object>> rule1;
    private Rule<Context<Object>> rule2;

    @BeforeEach
    void setup() {
        engine = new FirstWinsRuleEngine<>();
        context = Mockito.mock(Context.class);
        rule1 = Mockito.mock(Rule.class);
        rule2 = Mockito.mock(Rule.class);
    }

    @Test
    void shouldOnlyRunFirstMatchingRule() {
        BDDMockito.given(rule1.fires(context)).willReturn(Boolean.TRUE);
        BDDMockito.given(rule2.fires(context)).willReturn(Boolean.FALSE);

        engine.infer(ImmutableList.of(rule1, rule2), context);

        Mockito.verify(rule1, Mockito.times(1)).fires(context);
        Mockito.verify(rule1, Mockito.times(1)).run(context);
        Mockito.verifyZeroInteractions(rule2);
    }

    @Test
    void shouldOnlyRunFirstMatchingRuleSecond() {
        BDDMockito.given(rule1.fires(context)).willReturn(Boolean.FALSE);
        BDDMockito.given(rule2.fires(context)).willReturn(Boolean.TRUE);

        engine.infer(ImmutableList.of(rule1, rule2), context);

        Mockito.verify(rule1, Mockito.times(1)).fires(context);
        Mockito.verify(rule1, Mockito.times(0)).run(context);
        Mockito.verify(rule2, Mockito.times(1)).fires(context);
        Mockito.verify(rule2, Mockito.times(1)).run(context);
    }

}
