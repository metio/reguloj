/*
 * Project: viaRules-core
 * Package: de.viadee.rules.implementation
 * File   : ConclusionImplementation.java
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

/**
 * <p>Standard implementation of the {@link Conclusion} interface.</p>
 *
 * @author      Sebastian Hoß (sebastian.hoss@viadee.de)
 * @param <T>   The target type.
 * @since       1.0.0
 */
final class ConclusionImplementation<T> implements Conclusion<T> {

    // * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
    // *                                                     ATTRIBUTES                                                    *
    // * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *

    /** The commands to trigger once this conclusion is reached. */
    private final Collection<Command<T>> commands;

    // * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
    // *                                                    CONSTRUCTORS                                                   *
    // * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *

    /**
     * Constructor for a new {@link Conclusion} implementation.
     * 
     * @param commands  The commands to execute once this conclusion is reached (<b>may not be <code>null</code></b>).
     */
    ConclusionImplementation(final Collection<Command<T>> commands) {
        this.commands = Preconditions.checkNotNull(commands);
    }

    // * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
    // *                                                      METHODS                                                      *
    // * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean apply(final T target) {
        Preconditions.checkNotNull(target);

        boolean stateChanged = false;

        for (final Command<T> command : this.commands) {
            if (command.execute(target)) {
                stateChanged = true;
            }
        }

        return stateChanged;
    }

}
