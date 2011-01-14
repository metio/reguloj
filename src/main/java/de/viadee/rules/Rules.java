/*
 * Project: rules
 * Package: de.viadee.rules
 * File   : Rules.java
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
package de.viadee.rules;

import de.viadee.rules.implementation.RuleBuilderImplementation;

/**
 * <p>Utility class which helps creating new {@link Rule rules}.</p>
 *
 * @author  Sebastian Hoß (sebastian.hoss@viadee.de)
 * @see     Rule
 * @since   1.0.0
 */
public final class Rules {

    // * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
    // *                                                    CONSTRUCTORS                                                   *
    // * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *

    /**
     * Hidden constructor.
     */
    private Rules() {
        // do nothing
    }

    // * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
    // *                                                      METHODS                                                      *
    // * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *

    /**
     * <p>Creates a new {@link RuleBuilder} which offers an easy to use DSL for creating new {@link Rule rules}.</p>
     *
     * @param <C>   The context type.
     * @param <T>   The type of the new rule.
     * @return      A new rule builder.
     */
    public static <C extends InferenceContext<T>, T> RuleBuilder<C, T> rule() {
        return new RuleBuilderImplementation<C, T>();
    }

}
