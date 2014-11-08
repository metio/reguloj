/*
 * Copyright © 2010 Sebastian Hoß <mail@shoss.de>
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See http://www.wtfpl.net/ for more details.
 */
package com.github.sebhoss.reguloj;

import java.util.function.Predicate;

import org.eclipse.jdt.annotation.NonNull;

final class RuleBuilderImplementation<CONTEXT extends Context<@NonNull ?>> implements RuleBuilder<@NonNull CONTEXT> {

    @SuppressWarnings("null")
    private String              name;
    @SuppressWarnings("null")
    private Predicate<CONTEXT>  predicate;
    @SuppressWarnings("null")
    private Conclusion<CONTEXT> conclusion;

    @Override
    public RuleBuilder<CONTEXT> called(final String newName) {
        name = newName;

        return this;
    }

    @Override
    public @NonNull RuleBuilder<@NonNull CONTEXT> when(
            final @NonNull Predicate<@NonNull CONTEXT> newPredicate) {
        predicate = newPredicate;

        return this;
    }

    @Override
    public Rule<CONTEXT> then(final Conclusion<CONTEXT> newConclusion) {
        return new RuleImplementation<@NonNull CONTEXT>(name, predicate, conclusion);
    }

}
