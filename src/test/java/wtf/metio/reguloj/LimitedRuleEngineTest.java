package wtf.metio.reguloj;

import com.google.common.collect.ImmutableList;
import org.junit.Before;
import org.junit.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;

/**
 * Test case for LimitedRuleEngine.
 */
public class LimitedRuleEngineTest {

    private RuleEngine<Context<Object>> engine;
    private Context<Object> context;
    private Rule<Context<Object>> rule1;
    private Rule<Context<Object>> rule2;

    /**
     * Creates rule engine, context and rules.
     */
    @Before
    public void setup() {
        engine = new LimitedRuleEngine<>(2);
        context = Mockito.mock(Context.class);
        rule1 = Mockito.mock(Rule.class);
        rule2 = Mockito.mock(Rule.class);
    }

    /**
     * Ensures that rules will be called a maximum of two times (as specified in {@link #setup()} with matching rules.
     */
    @Test
    public void shouldRunTwoTimesWithMatchingRules() {
        BDDMockito.given(rule1.fires(context)).willReturn(Boolean.TRUE);
        BDDMockito.given(rule2.fires(context)).willReturn(Boolean.TRUE);

        engine.infer(ImmutableList.of(rule1, rule2), context);

        Mockito.verify(rule1, Mockito.times(2)).run(context);
        Mockito.verify(rule2, Mockito.times(2)).run(context);
    }

    /**
     * Ensures that all rules will be only called once, if there are no matching rules.
     */
    @Test
    public void shouldRunOnceWithNonMatchingRules() {
        BDDMockito.given(rule1.fires(context)).willReturn(Boolean.FALSE);
        BDDMockito.given(rule2.fires(context)).willReturn(Boolean.FALSE);

        engine.infer(ImmutableList.of(rule1, rule2), context);

        Mockito.verify(rule1, Mockito.times(1)).fires(context);
        Mockito.verify(rule2, Mockito.times(1)).fires(context);
    }

}
