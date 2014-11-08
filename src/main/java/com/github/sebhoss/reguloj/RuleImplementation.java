/*
 * Copyright © 2010 Sebastian Hoß <mail@shoss.de>
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See http://www.wtfpl.net/ for more details.
 */
package com.github.sebhoss.reguloj;

import java.util.Objects;
import java.util.function.Predicate;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;

final class RuleImplementation<CONTEXT extends Context<@NonNull ?>> implements Rule<CONTEXT> {

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
        return predicate.test(context);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, predicate, conclusion);
    }

    @Override
    public boolean equals(final @Nullable Object object) {
        if (object != null && object instanceof RuleImplementation) {
            final RuleImplementation<?> that = (RuleImplementation<?>) object;

            return Objects.equals(name, that.name) && Objects.equals(predicate, that.predicate)
                    && Objects.equals(conclusion, that.conclusion);
        }

        return false;
    }

}
