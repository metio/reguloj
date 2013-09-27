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
 * <p>
 * Standard implementation of the {@link RuleBuilder} interface.
 * </p>
 * 
 * @param <CONTEXT>
 *            The context type.
 */
public final class RuleBuilderImplementation<CONTEXT extends Context<?>> implements RuleBuilder<CONTEXT> {

    private String              name;

    private Predicate<CONTEXT>  predicate;

    private Conclusion<CONTEXT> conclusion;

    /**
     * Default constructor for this class to make checkstyle happy.
     */
    public RuleBuilderImplementation() {
        super();
    }

    @Override
    public RuleBuilder<CONTEXT> when(final Predicate<CONTEXT> newPredicate) {
        this.predicate = Preconditions.checkNotNull(newPredicate);

        return this;
    }

    @Override
    public Rule<CONTEXT> then(final Conclusion<CONTEXT> newConclusion) {
        this.conclusion = Preconditions.checkNotNull(newConclusion);

        return new RuleImplementation<CONTEXT>(this.name, this.predicate, this.conclusion);
    }

    @Override
    public RuleBuilder<CONTEXT> called(final String newName) {
        this.name = Preconditions.checkNotNull(newName);

        return this;
    }

}
