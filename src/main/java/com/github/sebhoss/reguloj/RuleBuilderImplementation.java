/*
 * Copyright © 2010 Sebastian Hoß <mail@shoss.de>
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See http://www.wtfpl.net/ for more details.
 */
package com.github.sebhoss.reguloj;

import javax.annotation.Nullable;

import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;

/**
 * Standard implementation of the {@link RuleBuilder} interface.
 * 
 * @param <CONTEXT>
 *            The context type.
 */
final class RuleBuilderImplementation<CONTEXT extends Context<?>> implements RuleBuilder<CONTEXT> {

    @Nullable
    private String              name;

    @Nullable
    private Predicate<CONTEXT>  predicate;

    @Nullable
    private Conclusion<CONTEXT> conclusion;

    @Override
    public RuleBuilder<CONTEXT> when(final Predicate<CONTEXT> newPredicate) {
        predicate = Preconditions.checkNotNull(newPredicate);

        return this;
    }

    @Override
    public Rule<CONTEXT> then(final Conclusion<CONTEXT> newConclusion) {
        conclusion = Preconditions.checkNotNull(newConclusion);

        Preconditions.checkState(name != null);
        Preconditions.checkState(predicate != null);
        Preconditions.checkState(conclusion != null);

        return new RuleImplementation<>(name, predicate, conclusion);
    }

    @Override
    public RuleBuilder<CONTEXT> called(final String newName) {
        name = Preconditions.checkNotNull(newName);

        return this;
    }

}
