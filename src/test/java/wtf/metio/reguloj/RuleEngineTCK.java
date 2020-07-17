package wtf.metio.reguloj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

abstract class RuleEngineTCK {

    protected RuleEngine<Context<Object>> engine;
    protected Context<Object> context;
    protected Rule<Context<Object>> rule;
    protected Rule<Context<Object>> rule2;

    protected abstract RuleEngine<Context<Object>> createRuleEngine();

    @BeforeEach
    final void setup() {
        engine = createRuleEngine();
        context = mock(Context.class);
        rule = mock(Rule.class);
        rule2 = mock(Rule.class);
    }

    @Test
    @DisplayName("does not fire for empty rules collection")
    final void shouldReturnFalseForEmptyRuleCollection() {
        final var rules = List.<Rule<Context<Object>>>of();
        final boolean fired = engine.analyze(rules, context);
        Assertions.assertFalse(fired);
    }

    @Test
    @DisplayName("fire if any rule fires")
    final void shouldReturnTrueIfRuleFired() {
        given(rule.fires(context)).willReturn(Boolean.TRUE);
        final boolean fired = engine.analyze(List.of(rule), context);
        Assertions.assertTrue(fired);
    }

    @Test
    @DisplayName("does not fire if no rule fires")
    final void shouldReturnFalseIfNoRuleFires() {
        given(rule.fires(context)).willReturn(Boolean.FALSE);
        final boolean fired = engine.analyze(List.of(rule), context);
        Assertions.assertFalse(fired);
    }

    @Test
    @DisplayName("can infer with empty rule collection")
    final void shouldRunWithEmptyRuleSet() {
        final var rules = List.<Rule<Context<Object>>>of();
        engine.infer(rules, context);
    }

}
