package com.github.sebhoss.reguloj;

import java.util.Set;

class FirstWinsRuleEngine<CONTEXT extends Context<?>> extends AbstractRuleEngine<CONTEXT> {

    @Override
    public void infer(final CONTEXT context, final Set<Rule<CONTEXT>> rules) {
        for (final Rule<CONTEXT> rule : rules) {
            if (rule.run(context)) {
                break;
            }
        }
    }

}
