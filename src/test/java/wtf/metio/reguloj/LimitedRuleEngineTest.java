/*
 * SPDX-FileCopyrightText: The reguloj Authors
 * SPDX-License-Identifier: 0BSD
 */
package wtf.metio.reguloj;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;

import java.util.List;

final class LimitedRuleEngineTest extends RuleEngineTCK {

    @Override
    protected RuleEngine<Context<Object>> createRuleEngine() {
        return new LimitedRuleEngine<>(2);
    }

    @Test
    @DisplayName("limit the number of loops")
    void shouldRunTwoTimesWithMatchingRules() {
        BDDMockito.given(rule.fires(context)).willReturn(Boolean.TRUE);
        BDDMockito.given(rule2.fires(context)).willReturn(Boolean.TRUE);

        engine.infer(List.of(rule, rule2), context);

        Mockito.verify(rule, Mockito.times(2)).run(context);
        Mockito.verify(rule2, Mockito.times(2)).run(context);
    }

    @Test
    @DisplayName("iterate over all rules at least once")
    void shouldRunOnceWithNonMatchingRules() {
        BDDMockito.given(rule.fires(context)).willReturn(Boolean.FALSE);
        BDDMockito.given(rule2.fires(context)).willReturn(Boolean.FALSE);

        engine.infer(List.of(rule, rule2), context);

        Mockito.verify(rule, Mockito.times(1)).fires(context);
        Mockito.verify(rule2, Mockito.times(1)).fires(context);
    }

}
