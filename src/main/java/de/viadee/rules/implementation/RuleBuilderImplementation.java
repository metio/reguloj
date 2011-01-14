/*
 * Project: rules
 * Package: de.viadee.rules.implementation
 * File   : RuleBuilderImplementation.java
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

import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;

import de.viadee.rules.Conclusion;
import de.viadee.rules.InferenceContext;
import de.viadee.rules.Rule;
import de.viadee.rules.RuleBuilder;

/**
 * <p>Standard implementation of the {@link RuleBuilder} interface.</p>
 *
 * @author      Sebastian Hoß (sebastian.hoss@viadee.de)
 * @param <C>   The context type.
 * @param <T>   The topic of the new rule.
 * @since       1.0.0
 */
public final class RuleBuilderImplementation<C extends InferenceContext<T>, T> implements RuleBuilder<C, T> {

    // * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
    // *                                                     ATTRIBUTES                                                    *
    // * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *

    /** The name for the new rule. */
    private String        name;

    /** The premise for the new rule. */
    private Predicate<C>  premise;

    /** The conclusion for the new rule. */
    private Conclusion<C> conclusion;

    // * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
    // *                                                    CONSTRUCTORS                                                   *
    // * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *

    /**
     * Default constructor for this class to make checkstyle happy.
     */
    public RuleBuilderImplementation() {
        super();
    }

    // * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
    // *                                                      METHODS                                                      *
    // * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *

    /**
     * {@inheritDoc}
     */
    @Override
    public Rule<C, T> get() {
        Preconditions.checkState(this.name != null);
        Preconditions.checkState(this.premise != null);
        Preconditions.checkState(this.conclusion != null);

        return new RuleImplementation<C, T>(this.name, this.premise, this.conclusion);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RuleBuilder<C, T> when(final Predicate<C> newPremise) {
        this.premise = Preconditions.checkNotNull(newPremise);

        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RuleBuilder<C, T> then(final Conclusion<C> newConclusion) {
        this.conclusion = Preconditions.checkNotNull(newConclusion);

        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RuleBuilder<C, T> called(final String newName) {
        this.name = Preconditions.checkNotNull(newName);

        return this;
    }

}
