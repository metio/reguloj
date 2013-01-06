/* This program is free software. It comes without any warranty, to
 * the extent permitted by applicable law. You can redistribute it
 * and/or modify it under the terms of the Do What The Fuck You Want
 * To Public License, Version 2, as published by Sam Hocevar. See
 * http://sam.zoy.org/wtfpl/COPYING for more details.
 */
package com.github.sebhoss.reguloj.implementation;

import java.util.Set;

import com.github.sebhoss.reguloj.Context;
import com.github.sebhoss.reguloj.Rule;
import com.github.sebhoss.reguloj.RuleEngine;
import com.google.common.base.Preconditions;


/**
 * <p>Simple implementation of the {@link RuleEngine} interface which supports rule-chaining.</p>
 * 
 * @param <C>   The context type.
 */
public final class SimpleRuleEngine<C extends Context<?>> implements RuleEngine<C> {

    // * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
    // *                                                 CONSTRUCTORS                                                *
    // * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *

    /**
     * Default constructor for this class to make checkstyle happy.
     */
    public SimpleRuleEngine() {
        super();
    }

    // * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
    // *                                                    METHODS                                                  *
    // * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean analyze(final C context, final Set<Rule<C>> rules) {
        // Check inputs
        Preconditions.checkNotNull(context);
        Preconditions.checkNotNull(rules);

        // Check whether any rule fires
        for (final Rule<C> rule : rules) {
            if (rule.fires(context)) {
                // A rule fired..

                // .. so we are returning true
                return true;
            }
        }
        // No rule fired..

        // .. so we are returning false
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void infer(final C context, final Set<Rule<C>> rules) {
        // Check inputs
        Preconditions.checkNotNull(context);
        Preconditions.checkNotNull(rules);

        // Setup
        boolean ruleFired;

        // Loop at least once through all rules
        do {
            // Deactive looping after this iteration
            ruleFired = false;

            // Check whether any rule fires
            for (final Rule<C> rule : rules) {
                if (rule.run(context)) {
                    // A rule fired..

                    // .. so we re-activate looping
                    ruleFired = true;
                }
            }
        } while (ruleFired);
    }

}
