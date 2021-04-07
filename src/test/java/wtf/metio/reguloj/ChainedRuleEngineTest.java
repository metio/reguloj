/*
 * This file is part of reguloj. It is subject to the license terms in the LICENSE file found in the top-level
 * directory of this distribution and at http://creativecommons.org/publicdomain/zero/1.0/. No part of reguloj,
 * including this file, may be copied, modified, propagated, or distributed except according to the terms contained
 * in the LICENSE file.
 */

package wtf.metio.reguloj;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

final class ChainedRuleEngineTest extends RuleEngineTCK {

    @Override
    protected RuleEngine<Context<Object>> createRuleEngine() {
        return new ChainedRuleEngine<>();
    }

    @Test
    @DisplayName("loop as long as rules are firing")
    void shouldLoopWithFiringRule() {
        given(rule.fires(context)).willReturn(Boolean.TRUE).willReturn(Boolean.FALSE);
        engine.infer(List.of(rule), context);
        verify(rule, times(2)).fires(context);
        verify(rule, times(1)).run(context);
    }

    @Test
    @DisplayName("do not loop when no rule is firing")
    void shouldNotLoopWithNotFiringRule() {
        given(rule.fires(context)).willReturn(Boolean.FALSE);
        engine.infer(List.of(rule), context);
        verify(rule, times(1)).fires(context);
        verify(rule, times(0)).run(context);
    }

}
