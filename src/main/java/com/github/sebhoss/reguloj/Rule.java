/*
 * Copyright © 2010 Sebastian Hoß <mail@shoss.de>
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See http://www.wtfpl.net/ for more details.
 */
package com.github.sebhoss.reguloj;

/**
 * <p>
 * A {@link Rule} is something that can run inside or together with a given context.
 * </p>
 * <h1>Caveats</h1>
 * <ul>
 * <li>No Problems known</li>
 * </ul>
 * <h1>Examples</h1>
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
 *
 * <pre>
 * Context&lt;X&gt; context = ...;
 * Rule&lt;Context&lt;X&gt;&gt; rule = ...;
 *
 * rule.run(context);
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
public interface Rule<CONTEXT extends Context<?>> {

    /**
     * Runs this rule inside a given context.
     *
     * @param context
     *            The context to use.
     */
    void run(CONTEXT context);

    /**
     * Checks whether this rule would fire for a given context.
     *
     * @param context
     *            The context to check.
     * @return <code>true</code> if this rule would fire, <code>false</code> otherwise.
     */
    boolean fires(CONTEXT context);

    /**
     * @return The human readable name of this rule.
     */
    String getName();

}
