/*
 * SPDX-FileCopyrightText: The reguloj Authors
 * SPDX-License-Identifier: 0BSD
 */
package wtf.metio.reguloj;

import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * Plain implementation of the {@link RuleBuilder} interface.
 *
 * @param <CONTEXT> The type of the context.
 */
final class FluentRuleBuilder<CONTEXT extends Context<?>> implements RuleBuilder<CONTEXT> {

    private String name;
    private Predicate<CONTEXT> predicate;

    @Override
    public RuleBuilder<CONTEXT> when(final Predicate<CONTEXT> newPredicate) {
        predicate = newPredicate;
        return this;
    }

    @Override
    public Rule<CONTEXT> then(final Consumer<CONTEXT> consumer) {
        return new JavaUtilFunctionRule<>(predicate, consumer);
    }

}
