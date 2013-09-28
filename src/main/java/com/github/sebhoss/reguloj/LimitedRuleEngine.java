package com.github.sebhoss.reguloj;

import java.util.Set;

final class LimitedRuleEngine<CONTEXT extends Context<?>> extends AbstractRuleEngine<CONTEXT> {

    private final int maximumNumberOfRuns;

    LimitedRuleEngine(final int maximumNumberOfRuns) {
        this.maximumNumberOfRuns = maximumNumberOfRuns;
    }

    @Override
    public void infer(final CONTEXT context, final Set<Rule<CONTEXT>> rules) {
        int currentRuns = 0;
        boolean ruleFired = false;

        do {
            currentRuns++;
            for (final Rule<CONTEXT> rule : rules) {
                ruleFired |= rule.run(context);
            }
        } while (ruleFired && currentRuns <= maximumNumberOfRuns);
    }

}
