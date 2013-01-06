/* This program is free software. It comes without any warranty, to
 * the extent permitted by applicable law. You can redistribute it
 * and/or modify it under the terms of the Do What The Fuck You Want
 * To Public License, Version 2, as published by Sam Hocevar. See
 * http://sam.zoy.org/wtfpl/COPYING for more details.
 */
package com.github.sebhoss.reguloj;

import java.util.Set;

/**
 * <p>The {@link RuleEngine} is responsible for logical reasoning. For that it analyzes a given {@link Context 
 * context} based upon a set of {@link Rule rules}.</p>
 *
 * <p>Both the context and the set of rules have to be supplied with each invocation of any of the available methods.
 * This means that users of this interface will have to hold on to their set of rules somewhere else. You can optimize
 * this set of rules by removing unnecessary elements or combining elements that are known to always fire together.</p>
 *
 * <h1>Caveats</h1>
 * <ul>
 *  <li>The current API design forces every user to call an instance of this engine explicitly and supply both
 *  the context and a set of rules. This may be inefficient for large sets of rules or overly complex context types.</li>
 * </ul>
 *
 * <h1>Examples</h1>
 * <ol>
 *  <li>
 *      <p>Test whether any rule would fire for a given context:</p>
 * <pre>
 * Context&lt;X&gt; context = ...;
 * Set&lt;Rule&lt;Context&lt;X&gt;&gt;&gt; rules = ...;
 * RuleEngine&lt;Context&lt;X&gt;&gt; engine = ...;
 *
 * boolean fired = engine.analyze(context, rules);
 * </pre>
 *
 *  </li>
 *  <li>
 *      <p>Launch the engine and fire all valid rules:</p>
 * <pre>
 * Context&lt;X&gt; context = ...;
 * Set&lt;Rule&lt;Context&lt;X&gt;&gt;&gt; rules = ...;
 * RuleEngine&lt;Context&lt;X&gt;&gt; engine = ...;
 *
 * engine.infer(context, rules);
 * </pre>
 *
 *  </li>
 * </ol>
 *
 * <h1>How to help</h1>
 * <ul>
 *  <li>Test the interface and write back about errors, bugs and wishes.</li>
 *  <li>Evaluate whether something like RETE can be applied to this interface and how it can be done.</li>
 * </ul>
 *
 * @author      Sebastian Hoß (sebastian.hoss@viadee.de)
 * @param <C>   The context type.
 * @since       1.0.0
 */
public interface RuleEngine<C extends Context<?>> {

    // * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
    // *                                                    METHODS                                                  *
    // * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *

    /**
     * <p>Performs a dry-run with this engine by analyzing a given context based upon a set of rules. It will only
     * check whether any rule would fires inside the given context but does not apply any conclusions. For that call
     * the {@link RuleEngine#infer(Context, Set) infer}-method.</p>
     * 
     * @param context   The context to analyze (<b>may not be <code>null</code></b>).
     * @param rules     The rule set to use (<b>may not be <code>null</code></b>).
     * @return          <code>true</code> if any rule would fire, <code>false</code> otherwise.
     */
    boolean analyze(C context, Set<Rule<C>> rules);

    /**
     * <p>Launches this engine and lets it analyze and execute a set of rules on a given context.</p>
     * 
     * @param context   The context to analyze (<b>may not be <code>null</code></b>).
     * @param rules     The rules to use (<b>may not be <code>null</code></b>).
     */
    void infer(C context, Set<Rule<C>> rules);

}
