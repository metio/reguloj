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
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;


/**
 * <p>Standard implementation of the {@link Rule} interface.</p>
 * 
 * @param <C>   The context type.
 */
final class RuleImplementation<C extends Context<?>> implements Rule<C> {

    // * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
    // *                                                  ATTRIBUTES                                                 *
    // * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *

    /** The name of this rule. */
    private final String        name;

    /** The predicate of this rule. */
    private final Predicate<C>  predicate;

    /** The conclusion of this rule. */
    private final Conclusion<C> conclusion;

    // * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
    // *                                                 CONSTRUCTORS                                                *
    // * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *

    /**
     * Constructor for a new {@link Rule} implementation.
     *
     * @param name          The name of the new rule (<b>may not be <code>null</code></b>).
     * @param predicate     The predicate of the new rule (<b>may not be <code>null</code></b>).
     * @param conclusion    The conclusion of the new rule (<b>may not be <code>null</code></b>).
     */
    RuleImplementation(final String name, final Predicate<C> predicate, final Conclusion<C> conclusion) {
        this.name = Preconditions.checkNotNull(name);
        this.predicate = Preconditions.checkNotNull(predicate);
        this.conclusion = Preconditions.checkNotNull(conclusion);
    }

    // * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
    // *                                                    METHODS                                                  *
    // * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean run(final C context) {
        if (this.predicate.apply(Preconditions.checkNotNull(context))) {
            return this.conclusion.apply(context);
        }

        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean fires(final C context) {
        return this.predicate.apply(Preconditions.checkNotNull(context));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getName() {
        return this.name;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return Objects.hashCode(this.name, this.predicate, this.conclusion);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(final Object object) {
        if (object != null && object instanceof RuleImplementation) {
            final RuleImplementation<?> other = (RuleImplementation<?>) object;

            return Objects.equal(this.name, other.name)
                    && Objects.equal(this.predicate, other.predicate)
                    && Objects.equal(this.conclusion, other.conclusion);
        }

        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int compareTo(final Rule<? extends C> object) {
        return this.name.compareTo(object.getName());
    }

}
