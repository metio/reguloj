/*
 * Project: rules
 * Package: de.viadee.rules.implementation
 * File   : RuleImplementation.java
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

import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;

import de.viadee.rules.Conclusion;
import de.viadee.rules.InferenceContext;
import de.viadee.rules.Rule;

/**
 * <p>Standard implementation of the {@link Rule} interface.</p>
 * 
 * @author      Sebastian Hoß (sebastian.hoss@viadee.de)
 * @param <C>   The context type.
 * @param <T>   The topic of the enclosing rule engine.
 * @since       1.0.0
 */
final class RuleImplementation<C extends InferenceContext<T>, T> implements Rule<C, T> {

    // * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
    // *                                                     ATTRIBUTES                                                    *
    // * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *

    /** The name of this rule. */
    private final String        name;

    /** The premise of this rule. */
    private final Predicate<C>  premise;

    /** The conclusion of this rule. */
    private final Conclusion<C> conclusion;

    // * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
    // *                                                    CONSTRUCTORS                                                   *
    // * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *

    /**
     * Constructor for a new {@link Rule} implementation.
     *
     * @param name          The name of the new rule (<b>may not be <code>null</code></b>).
     * @param premise       The premise of the new rule (<b>may not be <code>null</code></b>).
     * @param conclusion    The conclusion of the new rule (<b>may not be <code>null</code></b>).
     */
    RuleImplementation(final String name, final Predicate<C> premise, final Conclusion<C> conclusion) {
        this.name = Preconditions.checkNotNull(name);
        this.premise = Preconditions.checkNotNull(premise);
        this.conclusion = Preconditions.checkNotNull(conclusion);
    }

    // * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
    // *                                                      METHODS                                                      *
    // * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean run(final C context) {
        if (this.premise.apply(Preconditions.checkNotNull(context))) {
            return this.conclusion.apply(context);
        }

        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean fires(final C context) {
        return this.premise.apply(Preconditions.checkNotNull(context));
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
        return Objects.hashCode(this.name, this.premise, this.conclusion);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(final Object object) {
        if ((object != null) && (object instanceof RuleImplementation)) {
            final RuleImplementation<?, ?> other = (RuleImplementation<?, ?>) object;

            return (Objects.equal(this.name, other.name)
                    && Objects.equal(this.premise, other.premise) && Objects.equal(this.conclusion, other.conclusion));
        }

        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int compareTo(final Rule<C, T> object) {
        return this.name.compareTo(object.getName());
    }

}
