package wtf.metio.reguloj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

final class ChainedRuleEngineTest {

    private RuleEngine<Context<Object>> engine;
    private Context<Object> context;
    private Rule<Context<Object>> rule;

    @BeforeEach
    void setup() {
        engine = new ChainedRuleEngine<>();
        context = mock(Context.class);
        rule = mock(Rule.class);
    }

    @Test
    void shouldReturnFalseForEmptyRuleSet() {
        final var rules = new HashSet<Rule<Context<Object>>>();
        final boolean fired = engine.analyze(rules, context);
        Assertions.assertFalse(fired);
    }

    @Test
    void shouldReturnTrueIfRuleFired() {
        given(rule.fires(context)).willReturn(Boolean.TRUE);
        final boolean fired = engine.analyze(List.of(rule), context);
        Assertions.assertTrue(fired);
    }

    @Test
    void shouldReturnFalseIfNoRuleFires() {
        given(rule.fires(context)).willReturn(Boolean.FALSE);
        final boolean fired = engine.analyze(List.of(rule), context);
        Assertions.assertFalse(fired);
    }

    @Test
    void shouldRunWithEmptyRuleSet() {
        final Set<Rule<Context<Object>>> rules = new HashSet<>();
        engine.infer(rules, context);
    }

    @Test
    void shouldLoopWithFiringRule() {
        given(rule.fires(context)).willReturn(Boolean.TRUE).willReturn(Boolean.FALSE);
        engine.infer(List.of(rule), context);
        verify(rule, times(2)).fires(context);
        verify(rule, times(1)).run(context);
    }

    @Test
    void shouldNotLoopWithNotFiringRule() {
        given(rule.fires(context)).willReturn(Boolean.FALSE);
        engine.infer(List.of(rule), context);
        verify(rule, times(1)).fires(context);
        verify(rule, times(0)).run(context);
    }

}
