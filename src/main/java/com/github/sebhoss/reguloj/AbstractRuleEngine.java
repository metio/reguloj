package com.github.sebhoss.reguloj;

import java.util.Set;

abstract class AbstractRuleEngine<CONTEXT extends Context<?>> implements RuleEngine<CONTEXT> {

    @Override
    public final boolean analyze(final CONTEXT context, final Set<Rule<CONTEXT>> rules) {
        for (final Rule<CONTEXT> rule : rules) {
            if (rule.fires(context)) {
                return true;
            }
        }

        return false;
    }

}
