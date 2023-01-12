/*
 * SPDX-FileCopyrightText: The reguloj Authors
 * SPDX-License-Identifier: 0BSD
 */
package wtf.metio.reguloj;

import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * Implementation of the {@link Rule} interface that uses the java.util.function package.
 *
 * @param <CONTEXT> The type of the context.
 * @see java.util.function.Predicate
 * @see java.util.function.Consumer
 */
record JavaUtilFunctionRule<CONTEXT extends Context<?>>(
        Predicate<CONTEXT> predicate,
        Consumer<CONTEXT> consumer) implements Rule<CONTEXT> {

    @Override
    public void run(final CONTEXT context) {
        if (fires(context)) {
            consumer.accept(context);
        }
    }

    @Override
    public boolean fires(final CONTEXT context) {
        return predicate.test(context);
    }

}
