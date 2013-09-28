/*
 * Copyright © 2010 Sebastian Hoß <mail@shoss.de>
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See http://www.wtfpl.net/ for more details.
 */
package com.github.sebhoss.reguloj;

import java.util.Set;

/**
 * Simple implementation of the {@link RuleEngine} interface which supports rule-chaining.
 * 
 * @param <CONTEXT>
 *            The context type.
 */
public final class SimpleRuleEngine<CONTEXT extends Context<?>> implements RuleEngine<CONTEXT> {

    @Override
    public boolean analyze(final CONTEXT context, final Set<Rule<CONTEXT>> rules) {
        for (final Rule<CONTEXT> rule : rules) {
            if (rule.fires(context)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public void infer(final CONTEXT context, final Set<Rule<CONTEXT>> rules) {
        boolean ruleFired;
        do {
            ruleFired = false;

            for (final Rule<CONTEXT> rule : rules) {
                ruleFired |= rule.run(context);
            }
        } while (ruleFired);
    }

}
