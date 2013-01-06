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
 * @param <CONTEXT>   The context type.
 */
final class RuleImplementation<CONTEXT extends Context<?>> implements Rule<CONTEXT> {

    // * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
    // *                                                  ATTRIBUTES                                                 *
    // * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *

    private final String        name;
    private final Predicate<CONTEXT>  predicate;
    private final Conclusion<CONTEXT> conclusion;

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
    RuleImplementation(final String name, final Predicate<CONTEXT> predicate, final Conclusion<CONTEXT> conclusion) {
        this.name = Preconditions.checkNotNull(name);
        this.predicate = Preconditions.checkNotNull(predicate);
        this.conclusion = Preconditions.checkNotNull(conclusion);
    }

    // * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
    // *                                                    METHODS                                                  *
    // * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *

    @Override
    public boolean run(final CONTEXT context) {
        if (this.predicate.apply(Preconditions.checkNotNull(context))) {
            return this.conclusion.apply(context);
        }

        return false;
    }

    @Override
    public boolean fires(final CONTEXT context) {
        return this.predicate.apply(Preconditions.checkNotNull(context));
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.name, this.predicate, this.conclusion);
    }

    @Override
    public boolean equals(final Object object) {
        if (object != null && object instanceof RuleImplementation) {
            final RuleImplementation<?> that = (RuleImplementation<?>) object;

            return Objects.equal(this.name, that.name)
                    && Objects.equal(this.predicate, that.predicate)
                    && Objects.equal(this.conclusion, that.conclusion);
        }

        return false;
    }

    @Override
    public int compareTo(final Rule<? extends CONTEXT> object) {
        return this.name.compareTo(object.getName());
    }

}
