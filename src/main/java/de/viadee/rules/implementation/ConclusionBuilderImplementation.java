/*
 * Project: rules
 * Package: de.viadee.rules.implementation
 * File   : ConclusionBuilderImplementation.java
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

import java.util.Collection;

import com.google.common.base.Preconditions;

import de.viadee.rules.Command;
import de.viadee.rules.Conclusion;
import de.viadee.rules.ConclusionBuilder;

/**
 * <p>Standard implementation of the {@link ConclusionBuilder} interface.</p>
 *
 * @author      Sebastian Hoß (sebastian.hoss@viadee.de)
 * @param <T>   The target type of the new conclusion.
 * @since       1.0.0
 */
public final class ConclusionBuilderImplementation<T> implements ConclusionBuilder<T> {

    // * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
    // *                                                     ATTRIBUTES                                                    *
    // * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *

    /** The commands to trigger once the new conclusion is reached. */
    private final Collection<Command<T>> commands;

    // * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
    // *                                                    CONSTRUCTORS                                                   *
    // * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *

    /**
     * Constructor for a new {@link ConclusionBuilder} implementation.
     *
     * @param commands  The initial commands of the new conclusion (<b>may not be <code>null</code></b>).
     */
    public ConclusionBuilderImplementation(final Collection<Command<T>> commands) {
        this.commands = Preconditions.checkNotNull(commands);
    }

    // * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
    // *                                                      METHODS                                                      *
    // * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *

    /**
     * {@inheritDoc}
     */
    @Override
    public Conclusion<T> get() {
        return new ConclusionImplementation<T>(this.commands);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ConclusionBuilder<T> and(final Command<T> command) {
        this.commands.add(Preconditions.checkNotNull(command));

        return this;
    }

}
