/*
 * Copyright © 2010 Sebastian Hoß <mail@shoss.de>
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See http://www.wtfpl.net/ for more details.
 */
package com.github.sebhoss.reguloj.implementation;

import java.util.Set;

import com.github.sebhoss.reguloj.Context;
import com.github.sebhoss.reguloj.Rule;
import com.github.sebhoss.reguloj.RuleEngine;
import com.google.common.base.Preconditions;

/**
 * Simple implementation of the {@link RuleEngine} interface which supports rule-chaining.
 * 
 * @param <CONTEXT>
 *            The context type.
 */
public final class SimpleRuleEngine<CONTEXT extends Context<?>> implements RuleEngine<CONTEXT> {

    @Override
    public boolean analyze(final CONTEXT context, final Set<Rule<CONTEXT>> rules) {
        checkInputs(context, rules);

        // Check whether any rule fires
        for (final Rule<CONTEXT> rule : rules) {
            if (rule.fires(context)) {
                // A rule fired..

                // .. so we are returning true
                return true;
            }
        }

        return false;
    }

    @Override
    public void infer(final CONTEXT context, final Set<Rule<CONTEXT>> rules) {
        checkInputs(context, rules);

        // Setup
        boolean ruleFired;

        // Loop at least once through all rules
        do {
            // Deactive looping after this iteration
            ruleFired = false;

            // Check whether any rule fires
            for (final Rule<CONTEXT> rule : rules) {
                if (rule.run(context)) {
                    // A rule fired..

                    // .. so we re-activate looping
                    ruleFired = true;
                }
            }
        } while (ruleFired);
    }

    private void checkInputs(final CONTEXT context, final Set<Rule<CONTEXT>> rules) {
        Preconditions.checkNotNull(context);
        Preconditions.checkNotNull(rules);
    }

}
