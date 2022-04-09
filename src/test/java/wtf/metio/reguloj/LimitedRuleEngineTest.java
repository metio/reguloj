/*
 * This file is part of reguloj. It is subject to the license terms in the LICENSE file found in the top-level
 * directory of this distribution and at https://creativecommons.org/publicdomain/zero/1.0/. No part of reguloj,
 * including this file, may be copied, modified, propagated, or distributed except according to the terms contained
 * in the LICENSE file.
 */

package wtf.metio.reguloj;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;

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
