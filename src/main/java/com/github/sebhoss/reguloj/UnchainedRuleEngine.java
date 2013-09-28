package com.github.sebhoss.reguloj;

import java.util.Set;

final class UnchainedRuleEngine<CONTEXT extends Context<?>> extends AbstractRuleEngine<CONTEXT> {

    @Override
    public void infer(final CONTEXT context, final Set<Rule<CONTEXT>> rules) {
        for (final Rule<CONTEXT> rule : rules) {
            rule.run(context);
        }
    }

}
