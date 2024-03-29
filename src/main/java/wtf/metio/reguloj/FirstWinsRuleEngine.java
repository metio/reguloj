/*
 * SPDX-FileCopyrightText: The reguloj Authors
 * SPDX-License-Identifier: 0BSD
 */
package wtf.metio.reguloj;

import java.util.Collection;

/**
 * Runs the first rule that fires.
 *
 * @param <CONTEXT> The type of the context.
 * @see ChainedRuleEngine
 * @see LimitedRuleEngine
 */
final class FirstWinsRuleEngine<CONTEXT extends Context<?>> extends AbstractRuleEngine<CONTEXT> {

    @Override
    public void infer(final Collection<Rule<CONTEXT>> rules, final CONTEXT context) {
        rules.stream()
                .filter(rule -> rule.fires(context))
                .findFirst()
                .ifPresent(rule -> rule.run(context));
    }

}
