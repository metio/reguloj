/*
 * Project: viaRules-core
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
 * @since       1.0.0
 */
public final class RuleBuilderImplementation<C extends InferenceContext<?>> implements RuleBuilder<C> {

    // * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
    // *                                                  ATTRIBUTES                                                 *
    // * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *

    /** The name for the new rule. */
    private String        name;

    /** The premise for the new rule. */
    private Predicate<C>  premise;

    /** The conclusion for the new rule. */
    private Conclusion<C> command;

    // * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
    // *                                                 CONSTRUCTORS                                                *
    // * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
    
    /**
     * Default constructor for this class to make checkstyle happy.
     */
    public RuleBuilderImplementation() {
        super();
    }

    // * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
    // *                                                    METHODS                                                  *
    // * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *

    /**
     * <p>
     * Constructs a new {@link Rule} with the previously given parameters.
     * </p>
     * 
     * @return A newly created rule.
     */
    private Rule<C> get() {
        Preconditions.checkState(this.name != null);
        Preconditions.checkState(this.premise != null);
        Preconditions.checkState(this.command != null);

        return new RuleImplementation<C>(this.name, this.premise, this.command);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RuleBuilder<C> when(final Predicate<C> newPremise) {
        this.premise = Preconditions.checkNotNull(newPremise);

        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Rule<C> then(final Conclusion<C> newConclusion) {
        this.command = Preconditions.checkNotNull(newConclusion);

        return this.get();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RuleBuilder<C> called(final String newName) {
        this.name = Preconditions.checkNotNull(newName);

        return this;
    }

}
