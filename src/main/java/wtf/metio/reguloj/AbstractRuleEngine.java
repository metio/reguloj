/*
 * SPDX-FileCopyrightText: The reguloj Authors
 * SPDX-License-Identifier: 0BSD
 */
package wtf.metio.reguloj;

import java.util.Collection;

/**
 * Abstract rule engine which provides an implementation for the {@link #analyze(Collection, Context)} method. Therefore,
 * implementors only have to write the {@link #infer(Collection, Context)} method.
 *
 * @param <CONTEXT> The context type.
 */
public abstract class AbstractRuleEngine<CONTEXT extends Context<?>> implements RuleEngine<CONTEXT> {

    /**
     * Checks whether a single rule fires for the given context.
     *
     * @param rules   The rules to check.
     * @param context The context to use.
     * @return true if any rule fired, false otherwise.
     */
    @Override
    public final boolean analyze(final Collection<Rule<CONTEXT>> rules, final CONTEXT context) {
        return rules.stream().anyMatch(rule -> rule.fires(context));
    }

}
