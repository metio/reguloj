package wtf.metio.reguloj;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

final class FirstWinsRuleEngineTest {

    private RuleEngine<Context<Object>> engine;
    private Context<Object> context;
    private Rule<Context<Object>> rule1;
    private Rule<Context<Object>> rule2;

    @BeforeEach
    void setup() {
        engine = new FirstWinsRuleEngine<>();
        context = mock(Context.class);
        rule1 = mock(Rule.class);
        rule2 = mock(Rule.class);
    }

    @Test
    void shouldOnlyRunFirstMatchingRule() {
        given(rule1.fires(context)).willReturn(Boolean.TRUE);
        given(rule2.fires(context)).willReturn(Boolean.FALSE);

        engine.infer(List.of(rule1, rule2), context);

        verify(rule1, times(1)).fires(context);
        verify(rule1, times(1)).run(context);
        verifyNoMoreInteractions(rule2);
    }

    @Test
    void shouldOnlyRunFirstMatchingRuleSecond() {
        given(rule1.fires(context)).willReturn(Boolean.FALSE);
        given(rule2.fires(context)).willReturn(Boolean.TRUE);

        engine.infer(List.of(rule1, rule2), context);

        verify(rule1, times(1)).fires(context);
        verify(rule1, times(0)).run(context);
        verify(rule2, times(1)).fires(context);
        verify(rule2, times(1)).run(context);
    }

}
