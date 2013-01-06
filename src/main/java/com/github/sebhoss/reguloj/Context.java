/* This program is free software. It comes without any warranty, to
 * the extent permitted by applicable law. You can redistribute it
 * and/or modify it under the terms of the Do What The Fuck You Want
 * To Public License, Version 2, as published by Sam Hocevar. See
 * http://sam.zoy.org/wtfpl/COPYING for more details.
 */
package com.github.sebhoss.reguloj;

/**
 * <p>An {@link Context} is used every time a set of rules shall be evaluated. The only available method in 
 * the generic version is {@link #getTopic()} which returns the main or focal point of your rules (if any).</p>
 *
 * <p>Note that no factory or builder is defined for the <code>Context</code> so you will have to implement
 * the concrete implementation and create an instance out of it yourself.</p>
 *
 * <h1>Caveats</h1>
 * <ul>
 *  <li>No Problems known</li>
 * </ul>
 *
 * <h1>Examples</h1>
 * <p>No examples so far - interface is too abstract for that.</p>
 *
 * <h1>How to help</h1>
 * <ul>
 *  <li>Test the interface and write back about errors, bugs and wishes.</li>
 *  <li>Write an abstract implementation for this interface so others will have it easier to implement it themselves.</li>
 *  <li>Write an example of how to use an Context.</li>
 * </ul>
 * 
 * @param <TOPIC>   The topic of the context.
 */
public interface Context<TOPIC> {

    // * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
    // *                                                    METHODS                                                  *
    // * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *

    /**
     * Gets the topic of this inference context.
     * 
     * @return  The topic of this context.
     */
    TOPIC getTopic();

}
