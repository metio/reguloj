/*
 * SPDX-FileCopyrightText: The reguloj Authors
 * SPDX-License-Identifier: 0BSD
 */
package wtf.metio.reguloj;

import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * <p>
 * Fluent interface for building new {@link Rule rules}. It follows the builder-pattern described by Joshua Bloch (see
 * Effective Java, Item 2) and offers 3 methods:
 * </p>
 * <ul>
 * <li>{@link #called(String) called}: Use this method to name your new rule.</li>
 * <li>{@link #when(Predicate) when}: Use this method to specify the {@link Predicate} for your new rule.</li>
 * <li>{@link #then(Consumer) then}: Use this method to specify the {@link Consumer} for your new rule.</li>
 * </ul>
 * <h2>Examples</h2>
 * <ol>
 * <li>
 * <p>
 * Rule creation with name, predicate and consumer:
 * </p>
 * <pre>
 * String name = "...";
 * Predicate predicate = ...;
 * Consumer consumer = ...;
 * Rule rule = Rule.builder()
 *               .called(<em>name</em>)
 *               .when(<em>predicate</em>)
 *               .then(<em>consumer</em>)
 * </pre>
 * </li>
 * </ol>
 *
 * @param <CONTEXT> The context type.
 * @see Rule
 * @see Context
 * @see Predicate
 * @see Consumer
 * @see java.util.function.Predicate
 * @see java.util.function.Consumer
 */
public interface RuleBuilder<CONTEXT extends Context<?>> {

    /**
     * Sets the name of the new rule.
     *
     * @param name The name to set.
     * @return The current rule builder.
     */
    RuleBuilder<CONTEXT> called(String name);

    /**
     * Sets the {@link Predicate} for the new rule.
     *
     * @param predicate The predicate to set.
     * @return The current rule builder.
     */
    RuleBuilder<CONTEXT> when(Predicate<CONTEXT> predicate);

    /**
     * Sets the {@link Consumer} for the new rule.
     *
     * @param consumer The consumer to set.
     * @return The current rule builder.
     */
    Rule<CONTEXT> then(Consumer<CONTEXT> consumer);

}
