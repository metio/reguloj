package wtf.metio.reguloj;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;

import java.util.List;

final class LimitedRuleEngineTest {

    private RuleEngine<Context<Object>> engine;
    private Context<Object> context;
    private Rule<Context<Object>> rule1;
    private Rule<Context<Object>> rule2;

    @BeforeEach
    void setup() {
        engine = new LimitedRuleEngine<>(2);
        context = Mockito.mock(Context.class);
        rule1 = Mockito.mock(Rule.class);
        rule2 = Mockito.mock(Rule.class);
    }

    @Test
    void shouldRunTwoTimesWithMatchingRules() {
        BDDMockito.given(rule1.fires(context)).willReturn(Boolean.TRUE);
        BDDMockito.given(rule2.fires(context)).willReturn(Boolean.TRUE);

        engine.infer(List.of(rule1, rule2), context);

        Mockito.verify(rule1, Mockito.times(2)).run(context);
        Mockito.verify(rule2, Mockito.times(2)).run(context);
    }

    @Test
    void shouldRunOnceWithNonMatchingRules() {
        BDDMockito.given(rule1.fires(context)).willReturn(Boolean.FALSE);
        BDDMockito.given(rule2.fires(context)).willReturn(Boolean.FALSE);

        engine.infer(List.of(rule1, rule2), context);

        Mockito.verify(rule1, Mockito.times(1)).fires(context);
        Mockito.verify(rule2, Mockito.times(1)).fires(context);
    }

}
