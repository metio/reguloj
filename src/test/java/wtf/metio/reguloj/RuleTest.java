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
