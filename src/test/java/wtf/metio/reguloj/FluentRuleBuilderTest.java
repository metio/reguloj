/*
 * This file is part of reguloj. It is subject to the license terms in the LICENSE file found in the top-level
 * directory of this distribution and at https://creativecommons.org/publicdomain/zero/1.0/. No part of reguloj,
 * including this file, may be copied, modified, propagated, or distributed except according to the terms contained
 * in the LICENSE file.
 */

package wtf.metio.reguloj;

import java.util.function.Consumer;
import java.util.function.Predicate;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

final class FluentRuleBuilderTest {

  @Test
  void shouldCreateRuleIfAllValuesAreSet() {
    final var builder = new FluentRuleBuilder<Context<Object>>();
    builder.called("test rule").when(Mockito.mock(Predicate.class));
    final var rule = builder.then(Mockito.mock(Consumer.class));
    Assertions.assertNotNull(rule);
  }

}
