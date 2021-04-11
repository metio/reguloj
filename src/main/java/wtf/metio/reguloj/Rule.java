/*
 * This file is part of reguloj. It is subject to the license terms in the LICENSE file found in the top-level
 * directory of this distribution and at http://creativecommons.org/publicdomain/zero/1.0/. No part of reguloj,
 * including this file, may be copied, modified, propagated, or distributed except according to the terms contained
 * in the LICENSE file.
 */

package wtf.metio.reguloj;

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
   * @param <CONTEXT> The context type.
   * @return A new builder to construct rules.
   */
  static <CONTEXT extends Context<?>> RuleBuilder<CONTEXT> called(final String name) {
    return new FluentRuleBuilder<CONTEXT>().called(name);
  }

  /**
   * @return The human readable name of this rule.
   */
  String name();

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
