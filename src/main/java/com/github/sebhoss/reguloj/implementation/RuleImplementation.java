/*
 * Copyright © 2010 Sebastian Hoß <mail@shoss.de>
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See http://www.wtfpl.net/ for more details.
 */
package com.github.sebhoss.reguloj.implementation;

import javax.annotation.Nullable;

import com.github.sebhoss.common.annotation.Nullsafe;
import com.github.sebhoss.reguloj.Conclusion;
import com.github.sebhoss.reguloj.Context;
import com.github.sebhoss.reguloj.Rule;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;

/**
 * Standard implementation of the {@link Rule} interface.
 * 
 * @param <CONTEXT>
 *            The context type.
 */
final class RuleImplementation<CONTEXT extends Context<?>> implements Rule<CONTEXT> {

    private final String              name;
    private final Predicate<CONTEXT>  predicate;
    private final Conclusion<CONTEXT> conclusion;

    /**
     * Constructor for a new {@link Rule} implementation.
     * 
     * @param name
     *            The name of the new rule.
     * @param predicate
     *            The predicate of the new rule.
     * @param conclusion
     *            The conclusion of the new rule.
     */
    RuleImplementation(final String name, final Predicate<CONTEXT> predicate, final Conclusion<CONTEXT> conclusion) {
        this.name = Nullsafe.nullsafe(Preconditions.checkNotNull(name));
        this.predicate = Nullsafe.nullsafe(Preconditions.checkNotNull(predicate));
        this.conclusion = Nullsafe.nullsafe(Preconditions.checkNotNull(conclusion));
    }

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
    public boolean equals(final @Nullable Object object) {
        if (object != null && object instanceof RuleImplementation) {
            final RuleImplementation<?> that = (RuleImplementation<?>) object;

            return Objects.equal(this.name, that.name) && Objects.equal(this.predicate, that.predicate)
                    && Objects.equal(this.conclusion, that.conclusion);
        }

        return false;
    }

    @Override
    public int compareTo(final Rule<? extends CONTEXT> object) {
        return this.name.compareTo(object.getName());
    }

}
