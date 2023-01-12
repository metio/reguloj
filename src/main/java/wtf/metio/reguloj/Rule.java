/*
 * SPDX-FileCopyrightText: The reguloj Authors
 * SPDX-License-Identifier: 0BSD
 */
package wtf.metio.reguloj;

import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * <p>
 * A {@link Rule} combines {@link java.util.function.Predicate} and {@link java.util.function.Consumer} interfaces and
 * can be evaluated with a {@link RuleEngine} using a {@link Context}.
 * </p>
 * <h2>Examples</h2>
 * <ol>
 * <li>
 * <p>
 * Check whether a rule would fire inside a given context:
 * </p>
 *
 * <pre>
 * Context&lt;X&gt; context = ...;
 * Rule&lt;Context&lt;X&gt;&gt; rule = ...;
 *
 * boolean canFire = rule.fires(context);
 * </pre>
 *
 * </li>
 * <li>
 * <p>
 * Run a rule inside a given context:
 * </p>
 * <pre>
 * Context&lt;X&gt; context = ...;
 * Rule&lt;Context&lt;X&gt;&gt; rule = ...;
 *
 * rule.run(context);
 * </pre>
 * </li>
 * </ol>
 *
 * @param <CONTEXT> The context type.
 * @see RuleEngine
 * @see RuleBuilder
 * @see Context
 */
public interface Rule<CONTEXT extends Context<?>> {

    /**
     * Start building a new Rule by specifying a Predicate.
     *
     * @param <CONTEXT> The context type.
     * @return A new builder to construct rules.
     */
    static <CONTEXT extends Context<?>> RuleBuilder<CONTEXT> when(final Predicate<CONTEXT> predicate) {
        return new FluentRuleBuilder<CONTEXT>().when(predicate);
    }

    /**
     * Create a new Rule that always fires/runs and calls the given Consumer.
     *
     * @param <CONTEXT> The context type.
     * @return A new builder to construct rules.
     */
    static <CONTEXT extends Context<?>> Rule<CONTEXT> always(final Consumer<CONTEXT> consumer) {
        return new FluentRuleBuilder<CONTEXT>().when(c -> true).then(consumer);
    }

    /**
     * Checks whether this rule would fire for a given context.
     *
     * @param context The context to check.
     * @return <code>true</code> if this rule would fire, <code>false</code> otherwise.
     */
    boolean fires(CONTEXT context);

    /**
     * Runs this rule inside a given context. A rule should only run iff {@link #fires(Context)} returns
     * <code>true</code>.
     *
     * @param context The context to use.
     */
    void run(CONTEXT context);

}
