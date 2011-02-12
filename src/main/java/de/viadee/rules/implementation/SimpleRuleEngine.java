/*
 * Project: viaRules-core
 * Package: de.viadee.rules.implementation
 * File   : SimpleRuleEngine.java
 * Created: Nov 10, 2010 - 5:55:55 PM
 *
 *
 * Copyright 2010 viadee IT Unternehmensberatung GmbH
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package de.viadee.rules.implementation;

import java.util.Set;

import com.google.common.base.Preconditions;

import de.viadee.rules.InferenceContext;
import de.viadee.rules.Rule;
import de.viadee.rules.RuleEngine;

/**
 * <p>Simple implementation of the {@link RuleEngine} interface which supports rule-chaining.</p>
 * 
 * @author      Sebastian Hoß (sebastian.hoss@viadee.de)
 * @param <C>   The context type.
 * @since       1.0.0
 */
public final class SimpleRuleEngine<C extends InferenceContext<?>> implements RuleEngine<C> {

    // * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
    // *                                                    CONSTRUCTORS                                                   *
    // * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *

    /**
     * Default constructor for this class to make checkstyle happy.
     */
    public SimpleRuleEngine() {
        super();
    }

    // * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
    // *                                                      METHODS                                                      *
    // * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *

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
