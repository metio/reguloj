package wtf.metio.reguloj;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

final class FirstWinsRuleEngineTest extends RuleEngineTCK {

    @Override
    protected RuleEngine<Context<Object>> createRuleEngine() {
        return new FirstWinsRuleEngine<>();
    }

    @Test
    @DisplayName("run only first matching rule")
    void shouldOnlyRunFirstMatchingRule() {
        given(rule.fires(context)).willReturn(Boolean.TRUE);
        given(rule2.fires(context)).willReturn(Boolean.FALSE);

        engine.infer(List.of(rule, rule2), context);

        verify(rule, times(1)).fires(context);
        verify(rule, times(1)).run(context);
        verifyNoMoreInteractions(rule2);
    }

    @Test
    @DisplayName("skip rules that are not firing")
    void shouldOnlyRunFirstMatchingRuleSecond() {
        given(rule.fires(context)).willReturn(Boolean.FALSE);
        given(rule2.fires(context)).willReturn(Boolean.TRUE);

        engine.infer(List.of(rule, rule2), context);

        verify(rule, times(1)).fires(context);
        verify(rule, times(0)).run(context);
        verify(rule2, times(1)).fires(context);
        verify(rule2, times(1)).run(context);
    }

}
