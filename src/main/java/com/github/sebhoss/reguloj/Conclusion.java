/*
 * Copyright © 2010 Sebastian Hoß <mail@shoss.de>
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See http://www.wtfpl.net/ for more details.
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
@FunctionalInterface
public interface Conclusion<TOPIC> {

    /**
     * Applies the encapsulated action on a given target.
     *
     * @param target
     *            The target itself.
     * @return <code>true</code> if the conclusion did change the state of the target, <code>false</code> otherwise.
     */
    boolean apply(TOPIC target);

}
