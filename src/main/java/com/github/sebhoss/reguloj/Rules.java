/* This program is free software. It comes without any warranty, to
 * the extent permitted by applicable law. You can redistribute it
 * and/or modify it under the terms of the Do What The Fuck You Want
 * To Public License, Version 2, as published by Sam Hocevar. See
 * http://sam.zoy.org/wtfpl/COPYING for more details.
 */
package com.github.sebhoss.reguloj;

import com.github.sebhoss.reguloj.implementation.RuleBuilderImplementation;

/**
 * <p>Utility class which helps creating new {@link Rule rules}.</p>
 *
 * @see     Rule
 */
public final class Rules {

    // * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
    // *                                                 CONSTRUCTORS                                                *
    // * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *

    /**
     * Hidden constructor.
     */
    private Rules() {
        // do nothing
    }

    // * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
    // *                                                    METHODS                                                  *
    // * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *

    /**
     * <p>Creates a new {@link RuleBuilder} which offers an easy to use DSL for creating new {@link Rule rules}.</p>
     *
     * @param <C>   The context type.
     * @return      A new rule builder.
     */
    public static <C extends Context<?>> RuleBuilder<C> rule() {
        return new RuleBuilderImplementation<C>();
    }

}
