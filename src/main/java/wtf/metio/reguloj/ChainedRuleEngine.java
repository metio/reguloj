/*
 * SPDX-FileCopyrightText: The reguloj Authors
 * SPDX-License-Identifier: 0BSD
 */
package wtf.metio.reguloj;

import java.util.Collection;

/**
 * Chained rule engine that repeatedly runs all rules until no rule fires anymore.
 *
 * @param <CONTEXT> The type of the context.
 * @see FirstWinsRuleEngine
 * @see LimitedRuleEngine
 */
final class ChainedRuleEngine<CONTEXT extends Context<?>> extends AbstractRuleEngine<CONTEXT> {

    @Override
    public void infer(final Collection<Rule<CONTEXT>> rules, final CONTEXT context) {
        while (analyze(rules, context)) {
            rules.forEach(rule -> rule.run(context));
        }
    }

}
