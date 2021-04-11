/*
 * This file is part of reguloj. It is subject to the license terms in the LICENSE file found in the top-level
 * directory of this distribution and at http://creativecommons.org/publicdomain/zero/1.0/. No part of reguloj,
 * including this file, may be copied, modified, propagated, or distributed except according to the terms contained
 * in the LICENSE file.
 */

package wtf.metio.reguloj;

import java.util.function.Consumer;
import java.util.function.Predicate;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

final class RuleTest {

  private static final String NAME = "test rule";

  @Test
  void shouldCreateBuilder() {
    Assertions.assertNotNull(Rule.called("test"));
  }

  @Test
  void shouldCreateRule() {
    final var builder = Rule.<Context<Object>>called("test");
    builder.called(NAME).when(Mockito.mock(Predicate.class));
    final var rule = builder.then(Mockito.mock(Consumer.class));
    Assertions.assertNotNull(rule);
  }

}
