/*
 * This file is part of reguloj. It is subject to the license terms in the LICENSE file found in the top-level
 * directory of this distribution and at https://creativecommons.org/publicdomain/zero/1.0/. No part of reguloj,
 * including this file, may be copied, modified, propagated, or distributed except according to the terms contained
 * in the LICENSE file.
 */

package wtf.metio.reguloj;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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
