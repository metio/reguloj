/*
 * Copyright © 2010 Sebastian Hoß <mail@shoss.de>
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See http://www.wtfpl.net/ for more details.
 */
package com.github.sebhoss.reguloj;

import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Predicate;

final class RuleImplementation<CONTEXT extends Context<?>> implements Rule<CONTEXT> {

    private final String             name;
    private final Predicate<CONTEXT> predicate;
    private final Consumer<CONTEXT>  consumer;

    RuleImplementation(final String name, final Predicate<CONTEXT> predicate, final Consumer<CONTEXT> consumer) {
        this.name = name;
        this.predicate = predicate;
        this.consumer = consumer;
    }

    @Override
    public boolean run(final CONTEXT context) {
        boolean changed = false;

        if (fires(context)) {
            changed = true;
            consumer.accept(context);
        }

        return changed;
    }

    @Override
    public boolean fires(final CONTEXT context) {
        return predicate.test(context);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, predicate, consumer);
    }

    @Override
    public boolean equals(final Object object) {
        if (object != null && object instanceof RuleImplementation) {
            final RuleImplementation<?> that = (RuleImplementation<?>) object;

            return Objects.equals(name, that.name) && Objects.equals(predicate, that.predicate)
                    && Objects.equals(consumer, that.consumer);
        }

        return false;
    }

}
