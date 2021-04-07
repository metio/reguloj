/*
 * This file is part of reguloj. It is subject to the license terms in the LICENSE file found in the top-level
 * directory of this distribution and at http://creativecommons.org/publicdomain/zero/1.0/. No part of reguloj,
 * including this file, may be copied, modified, propagated, or distributed except according to the terms contained
 * in the LICENSE file.
 */

package wtf.metio.reguloj;

import java.util.Collection;

/**
 * <p>
 * The {@link RuleEngine} is responsible for logical reasoning. For that it analyzes a given {@link Context context}
 * based upon a collection of {@link Rule rules}.
 * </p>
 * <p>
 * Both the context and the collection of rules have to be supplied with each invocation of any of the available
 * methods. This means that users of this interface will have to hold on to their collection of rules somewhere else.
 * You can optimize this collection of rules by removing unnecessary elements or combining elements that are known to
 * always fire together.
 * </p>
 * <h2>Examples</h2>
 * <ol>
 * <li>
 * <p>
 * Test whether any rule would fire for a given context:
 * </p>
 *
 * <pre>
 * RuleEngine&lt;Context&lt;X&gt;&gt; engine = ...;
 * Collection&lt;Rule&lt;Context&lt;X&gt;&gt;&gt; rules = ...;
 * Context&lt;X&gt; context = ...;
 *
 * boolean fired = engine.analyze(rules, context);
 * </pre>
 *
 * </li>
 * <li>
 * <p>
 * Launch the engine and fire all valid rules:
 * </p>
 * <pre>
 * RuleEngine&lt;Context&lt;X&gt;&gt; engine = ...;
 * Collection&lt;Rule&lt;Context&lt;X&gt;&gt;&gt; rules = ...;
 * Context&lt;X&gt; context = ...;
 *
 * engine.infer(rules, context);
 * </pre>
 * </li>
 * </ol>
 *
 * @param <CONTEXT> The context type.
 * @see Rule
 * @see Context
 */
public interface RuleEngine<CONTEXT extends Context<?>> {

  /**
   * @param <CONTEXT> The context type of the new rule engine
   * @return A rule engine which supports rule chaining.
   */
  static <CONTEXT extends Context<?>> RuleEngine<CONTEXT> chained() {
    return new ChainedRuleEngine<>();
  }

  /**
   * @param maximumNumberOfRuns The maximum number of runs to perform.
   * @param <CONTEXT>           The context type of the new rule engine.
   * @return A rule engine which supports rule chaining up until maximumNumberOfRuns.
   */
  static <CONTEXT extends Context<?>> RuleEngine<CONTEXT> limited(final int maximumNumberOfRuns) {
    return new LimitedRuleEngine<>(maximumNumberOfRuns);
  }

  /**
   * @param <CONTEXT> The context type of the new rule engine.
   * @return A rule engine which evaluates all rules until the first rule that fires.
   */
  static <CONTEXT extends Context<?>> RuleEngine<CONTEXT> firstWins() {
    return new FirstWinsRuleEngine<>();
  }

  /**
   * Performs a dry-run with this engine by analyzing a given context with a collection of rules. It will only check
   * whether any rule would fire inside the given context but does not infer anything. Call the {@link
   * RuleEngine#infer(Collection, Context) infer}-method in order to do that. This method will call {@link
   * Rule#fires(Context)} for each given rule using the given context.
   *
   * @param rules   The rules to check.
   * @param context The context to use.
   * @return <code>true</code> if any rule would fire, <code>false</code> otherwise.
   */
  boolean analyze(Collection<Rule<CONTEXT>> rules, CONTEXT context);

  /**
   * Launches this engine and lets it analyze and execute a set of rules on a given context. This method will call
   * {@link Rule#run(Context)} for each given rule using the given context.
   *
   * @param rules   The rules to run.
   * @param context The context to use.
   */
  void infer(Collection<Rule<CONTEXT>> rules, CONTEXT context);

}
