/* This program is free software. It comes without any warranty, to
 * the extent permitted by applicable law. You can redistribute it
 * and/or modify it under the terms of the Do What The Fuck You Want
 * To Public License, Version 2, as published by Sam Hocevar. See
 * http://sam.zoy.org/wtfpl/COPYING for more details.
 */
package com.github.sebhoss.reguloj;

/**
 * <p>A {@link Rule} is composed of a name, a premise and a conclusion. Is the premise of a rule fulfilled the
 * conclusion will get implied.</p>
 *
 * <p>Each <code>Rule</code> is required to be {@link Comparable comparable} to other rules so that users of this
 * API can easily create {@link java.util.Set sets} of rules without any duplicate entries.</p>
 *
 * <h1>Caveats</h1>
 * <ul>
 *  <li>No Problems known</li>
 * </ul>
 *
 * <h1>Examples</h1>
 * <ol>
 *  <li>
 *      <p>Check whether a rule would fire inside a given context:</p>
 *      
 * <pre>
 * Context&lt;X&gt; context = ...;
 * Rule&lt;Context&lt;X&gt;&gt; rule = ...;
 * 
 * boolean canFire = rule.fires(context);     
 * </pre>
 *
 *  </li>
 *  <li>
 *      <p>Check whether a rule inflicted any state change inside a given context:</p>
 *
 * <pre>
 * Context&lt;X&gt; context = ...;
 * Rule&lt;Context&lt;X&gt;&gt; rule = ...;
 * 
 * boolean fired = rule.run(context);
 * </pre>
 *
 *  </li>
 * </ol>
 *
 * <h1>How to help</h1>
 * <ul>
 *  <li>Test the interface and write back about errors, bugs and wishes.</li>
 * </ul>
 * 
 * @param <CONTEXT>   The context type.
 */
public interface Rule<CONTEXT extends Context<?>> extends Comparable<Rule<? extends CONTEXT>> {

    // * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
    // *                                                    METHODS                                                  *
    // * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *

    /**
     * <p>Runs this rule inside a given context. For that it'll check its premises first and if those
     * are fulfilled, it will run its enclosing conclusion.</p>
     * 
     * @param context   The context to use (<b>may not be <code>null</code></b>).
     * @return          <code>true</code> if this rule did change the state of the given context,
     *                  <code>false</code> otherwise.
     */
    boolean run(CONTEXT context);

    /**
     * <p>Checks whether this rule would fire for a given context.</p>
     * 
     * @param context   The context to check (<b>may not be <code>null</code></b>).
     * @return          <code>true</code> if this rule would fire, <code>false</code> otherwise.
     */
    boolean fires(CONTEXT context);

    /**
     * <p>Returns the human readable name of this rule.</p>
     * 
     * @return  The name of this rule.
     */
    String getName();

}
