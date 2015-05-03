/*
 * Copyright © 2010 Sebastian Hoß <mail@shoss.de>
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See http://www.wtfpl.net/ for more details.
 */
package com.github.sebhoss.reguloj;

/**
 * Utility class which helps creating new {@link Rule rules}.
 *
 * @see Rule
 */
public final class Rules {

    private Rules() {
        // do nothing
    }

    /**
     * Creates a new {@link RuleBuilder} which offers an easy to use DSL for creating new {@link Rule rules}.
     *
     * @return A new rule builder.
     */
    public static <CONTEXT extends Context<?>> RuleBuilder<CONTEXT> rule() {
        return new RuleBuilderImplementation<>();
    }

}
