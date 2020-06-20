/*
 * Copyright © 2010 Sebastian Hoß <mail@shoss.de>
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See http://www.wtfpl.net/ for more details.
 */
package wtf.metio.reguloj;

import java.util.Collection;

final class LimitedRuleEngine<CONTEXT extends Context<?>> extends AbstractRuleEngine<CONTEXT> {

    private final int maximumNumberOfRuns;

    LimitedRuleEngine(final int maximumNumberOfRuns) {
        this.maximumNumberOfRuns = maximumNumberOfRuns;
    }

    @Override
    public void infer(final Collection<Rule<CONTEXT>> rules, final CONTEXT context) {
        int currentRuns = 0;
        while (analyze(rules, context)) {
            performSinglePass(rules, context);

            if (++currentRuns >= maximumNumberOfRuns) {
                break;
            }
        }
    }

}
