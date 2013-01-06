/* This program is free software. It comes without any warranty, to
 * the extent permitted by applicable law. You can redistribute it
 * and/or modify it under the terms of the Do What The Fuck You Want
 * To Public License, Version 2, as published by Sam Hocevar. See
 * http://sam.zoy.org/wtfpl/COPYING for more details.
 */
package com.github.sebhoss.reguloj.implementation;

import com.github.sebhoss.reguloj.Conclusion;
import com.github.sebhoss.reguloj.Context;
import com.github.sebhoss.reguloj.Rule;
import com.github.sebhoss.reguloj.RuleBuilder;
import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;


/**
 * <p>Standard implementation of the {@link RuleBuilder} interface.</p>
 *
 * @param <C>   The context type.
 */
public final class RuleBuilderImplementation<C extends Context<?>> implements RuleBuilder<C> {

    // * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
    // *                                                  ATTRIBUTES                                                 *
    // * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *

    /** The name for the new rule. */
    private String        name;

    /** The premise for the new rule. */
    private Predicate<C>  premise;

    /** The conclusion for the new rule. */
    private Conclusion<C> conclusion;

    // * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
    // *                                                 CONSTRUCTORS                                                *
    // * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *

    /**
     * Default constructor for this class to make checkstyle happy.
     */
    public RuleBuilderImplementation() {
        super();
    }

    // * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
    // *                                                    METHODS                                                  *
    // * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *

    /**
     * {@inheritDoc}
     */
    @Override
    public RuleBuilder<C> when(final Predicate<C> newPremise) {
        this.premise = Preconditions.checkNotNull(newPremise);

        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Rule<C> then(final Conclusion<C> newConclusion) {
        this.conclusion = Preconditions.checkNotNull(newConclusion);

        return new RuleImplementation<C>(this.name, this.premise, this.conclusion);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RuleBuilder<C> called(final String newName) {
        this.name = Preconditions.checkNotNull(newName);

        return this;
    }

}
