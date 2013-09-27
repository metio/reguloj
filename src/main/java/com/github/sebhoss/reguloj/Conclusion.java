/* This program is free software. It comes without any warranty, to
 * the extent permitted by applicable law. You can redistribute it
 * and/or modify it under the terms of the Do What The Fuck You Want
 * To Public License, Version 2, as published by Sam Hocevar. See
 * http://sam.zoy.org/wtfpl/COPYING for more details.
 */
package com.github.sebhoss.reguloj;

/**
 * <p>
 * A {@link Conclusion} encapsulates the final action a {@link Rule} will trigger once its predicate is fulfilled.
 * </p>
 * <h1>Caveats</h1>
 * <ul>
 * <li>No Problems known</li>
 * </ul>
 * <h1>Examples</h1>
 * <p>
 * No examples so far - interface is too abstract for that.
 * </p>
 * <h1>How to help</h1>
 * <ul>
 * <li>Test the interface and write back about errors, bugs and wishes.</li>
 * </ul>
 * 
 * @param <TOPIC>
 *            The topic of the inference process.
 */
public interface Conclusion<TOPIC> {

    /**
     * Applies the encapsulated action on a given target.
     * 
     * @param target
     *            The target itself (<b>may not be <code>null</code></b>).
     * @return <code>true</code> if the conclusion did change the state of the target, <code>false</code> otherwise.
     */
    boolean apply(TOPIC target);

}
