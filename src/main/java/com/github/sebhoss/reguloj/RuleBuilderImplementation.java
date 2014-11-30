/*
 * Copyright © 2010 Sebastian Hoß <mail@shoss.de>
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See http://www.wtfpl.net/ for more details.
 */
package com.github.sebhoss.reguloj;

import java.util.function.Consumer;
import java.util.function.Predicate;

final class RuleBuilderImplementation<CONTEXT extends Context<?>> implements RuleBuilder<CONTEXT> {

    private String             name;
    private Predicate<CONTEXT> predicate;

    @Override
    public RuleBuilder<CONTEXT> called(final String newName) {
        name = newName;

        return this;
    }

    @Override
    public RuleBuilder<CONTEXT> when(
            final Predicate<CONTEXT> newPredicate) {
        predicate = newPredicate;

        return this;
    }

    @Override
    public Rule<CONTEXT> then(final Consumer<CONTEXT> consumer) {
        return new RuleImplementation<>(name, predicate, consumer);
    }

}
