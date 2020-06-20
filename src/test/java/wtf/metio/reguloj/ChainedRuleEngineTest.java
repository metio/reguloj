package wtf.metio.reguloj;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

final class ChainedRuleEngineTest {

    private RuleEngine<Context<Object>> engine;
    private Context<Object> context;
    private Rule<Context<Object>> rule;

    @BeforeEach
    void setup() {
        engine = new ChainedRuleEngine<>();
        context = Mockito.mock(Context.class);
        rule = Mockito.mock(Rule.class);
    }

    @Test
    void shouldReturnFalseForEmptyRuleSet() {
        final Set<Rule<Context<Object>>> rules = new HashSet<>();
        final boolean fired = engine.analyze(rules, context);
        Truth.assertThat(fired).isFalse();
    }

    @Test
    void shouldReturnTrueIfRuleFired() {
        BDDMockito.given(rule.fires(context)).willReturn(Boolean.TRUE);

        final boolean fired = engine.analyze(ImmutableList.of(rule), context);

        Truth.assertThat(fired).isTrue();
    }

    @Test
    void shouldReturnFalseIfNoRuleFires() {
        BDDMockito.given(rule.fires(context)).willReturn(Boolean.FALSE);

        final boolean fired = engine.analyze(ImmutableList.of(rule), context);

        Truth.assertThat(fired).isFalse();
    }

    @Test
    void shouldRunWithEmptyRuleSet() {
        final Set<Rule<Context<Object>>> rules = new HashSet<>();
        engine.infer(rules, context);
    }

    @Test
    void shouldLoopWithFiringRule() {
        BDDMockito.given(rule.fires(context)).willReturn(Boolean.TRUE).willReturn(Boolean.FALSE);

        engine.infer(ImmutableList.of(rule), context);

        Mockito.verify(rule, Mockito.times(2)).fires(context);
        Mockito.verify(rule, Mockito.times(1)).run(context);
    }

    @Test
    void shouldNotLoopWithNotFiringRule() {
        BDDMockito.given(rule.fires(context)).willReturn(Boolean.FALSE);

        engine.infer(ImmutableList.of(rule), context);

        Mockito.verify(rule, Mockito.times(1)).fires(context);
        Mockito.verify(rule, Mockito.times(0)).run(context);
    }

}
