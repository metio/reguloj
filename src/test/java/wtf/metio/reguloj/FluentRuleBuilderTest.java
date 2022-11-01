/*
 * SPDX-FileCopyrightText: The reguloj Authors
 * SPDX-License-Identifier: 0BSD
 */
package wtf.metio.reguloj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.function.Consumer;
import java.util.function.Predicate;

final class FluentRuleBuilderTest {

    @Test
    void shouldCreateRuleIfAllValuesAreSet() {
        final var builder = new FluentRuleBuilder<Context<Object>>();
        builder.called("test rule").when(Mockito.mock(Predicate.class));
        final var rule = builder.then(Mockito.mock(Consumer.class));
        Assertions.assertNotNull(rule);
    }

}
