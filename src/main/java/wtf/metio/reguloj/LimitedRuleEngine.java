/*
 * SPDX-FileCopyrightText: The reguloj Authors
 * SPDX-License-Identifier: 0BSD
 */
package wtf.metio.reguloj;

import java.util.Collection;

/**
 * Limits the total number of runs to a user supplied maximum.
 *
 * @param <CONTEXT> The type of the context.
 * @see ChainedRuleEngine
 * @see FirstWinsRuleEngine
 */
final class LimitedRuleEngine<CONTEXT extends Context<?>> extends AbstractRuleEngine<CONTEXT> {

    private final int maximumNumberOfRuns;

    LimitedRuleEngine(final int maximumNumberOfRuns) {
        this.maximumNumberOfRuns = maximumNumberOfRuns;
    }

    @Override
    public void infer(final Collection<Rule<CONTEXT>> rules, final CONTEXT context) {
        int currentRuns = 0;
        while (currentRuns++ < maximumNumberOfRuns && analyze(rules, context)) {
            rules.forEach(rule -> rule.run(context));
        }
    }

}
