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
        Assertions.assertNotNull(Rule.when(Mockito.mock(Predicate.class)));
    }

    @Test
    void shouldCreateRule() {
        final var rule = Rule.when(Mockito.mock(Predicate.class))
                .then(Mockito.mock(Consumer.class));
        Assertions.assertNotNull(rule);
    }

}
