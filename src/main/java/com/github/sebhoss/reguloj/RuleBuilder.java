/*
 * Copyright © 2010 Sebastian Hoß <mail@shoss.de>
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See http://www.wtfpl.net/ for more details.
 */
package com.github.sebhoss.reguloj;

import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * <p>
 * Offers an easy to use fluent interface for building new {@link Rule rules}. It follows the builder-pattern described
 * by Joshua Bloch (see Effective Java, Item 2) and offers 3 methods:
 * </p>
 * <ul>
 * <li>{@link #called(String) called}: Use this method to name your new rule.</li>
 * <li>{@link #when(Predicate) when}: Use this method to specify the predicate for your new rule.</li>
 * <li>{@link #then(Consumer) then}: Use this method to specify the conclusion for your new rule.</li>
 * </ul>
 * <h1>Caveats</h1>
 * <ul>
 * <li>Methods like {@link RuleBuilder#when(Predicate) when} or {@link RuleBuilder#then(Consumer) then} can be called
 * multiple times but implementations of this API should only honor the last call. All previous calls (and their
 * parameters) should be dismissed. If you want to create complex predicates and/or conclusions you'll have to construct
 * them beforehand and use the newly created complex predicate/conclusion as an input for the <em>when</em>- or
 * <em>then</em>-clause.</li>
 * <li>As noted in the {@link RuleBuilder#then(Consumer) then}-method documentation, you are not allowed to use
 * <code>null</code> as an valid input. So in the rare case that you want to have a rule without any conclusion you have
 * to create and supply some sort of <em>no-action</em> conclusion which does nothing except not being <code>null</code>
 * </li>
 * </ul>
 * <h1>Examples</h1>
 * <ol>
 * <li>
 * <p>
 * Rule creation with name, predicate and conclusion:
 * </p>
 *
 * <pre>
 * String name = "...";
 * Predicate predicate = ...;
 * Conclusion conclusion = ...;
 * Rule rule = Rules.rule()
 *               .called(<em>name</em>)
 *               .when(<em>predicate</em>)
 *               .then(<em>conclusion</em>)
 * </pre>
 *
 * </li>
 * </ol>
 * <h1>How to help</h1>
 * <ul>
 * <li>Test the interface and write back about errors, bugs and wishes.</li>
 * </ul>
 *
 * @param <CONTEXT>
 *            The context type.
 */
public interface RuleBuilder<CONTEXT extends Context<?>> {

    /**
     * Sets the predicate for the new rule.
     *
     * @param predicate
     *            The predicate to set.
     * @return The current rule builder.
     */
    RuleBuilder<CONTEXT> when(Predicate<CONTEXT> predicate);

    /**
     * Sets the conclusion for the new rule.
     *
     * @param conclusion
     *            The conclusion to set.
     * @return The current rule builder.
     */
    Rule<CONTEXT> then(Consumer<CONTEXT> conclusion);

    /**
     * Sets the name of the new rule.
     *
     * @param name
     *            The name to set.
     * @return The current rule builder.
     */
    RuleBuilder<CONTEXT> called(String name);

}
