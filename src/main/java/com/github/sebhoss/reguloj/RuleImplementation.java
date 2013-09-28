/*
 * Copyright © 2010 Sebastian Hoß <mail@shoss.de>
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See http://www.wtfpl.net/ for more details.
 */
package com.github.sebhoss.reguloj;

import javax.annotation.Nullable;

import com.google.common.base.Objects;
import com.google.common.base.Predicate;

final class RuleImplementation<CONTEXT extends Context<?>> implements Rule<CONTEXT> {

    private final String              name;
    private final Predicate<CONTEXT>  predicate;
    private final Conclusion<CONTEXT> conclusion;

    RuleImplementation(final String name, final Predicate<CONTEXT> predicate, final Conclusion<CONTEXT> conclusion) {
        this.name = name;
        this.predicate = predicate;
        this.conclusion = conclusion;
    }

    @Override
    public boolean run(final CONTEXT context) {
        boolean changed = false;

        if (fires(context)) {
            changed = conclusion.apply(context);
        }

        return changed;
    }

    @Override
    public boolean fires(final CONTEXT context) {
        return predicate.apply(context);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name, predicate, conclusion);
    }

    @Override
    public boolean equals(final @Nullable Object object) {
        if (object != null && object instanceof RuleImplementation) {
            final RuleImplementation<?> that = (RuleImplementation<?>) object;

            return Objects.equal(name, that.name) && Objects.equal(predicate, that.predicate)
                    && Objects.equal(conclusion, that.conclusion);
        }

        return false;
    }

}
