/*
 * Project: viaRules-core
 * Package: de.viadee.rules
 * File   : ConclusionBuilder.java
 * Created: Nov 10, 2010 - 5:55:55 PM
 *
 *
 * Copyright 2010 viadee IT Unternehmensberatung GmbH
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package de.viadee.rules;

import com.google.common.base.Supplier;

/**
 * <p>Offers an easy to use fluent interface for building {@link Conclusion conclusions}. It follows the builder-pattern 
 * described by Joshua Bloch (see <a href="http://java.sun.com/docs/books/effective/">Effective Java, Item 2</a>) and offers
 * one additional methods:</p>
 *
 * <ul>
 *  <li>{@link #and(Command) and}: Use this method to add more {@link Command commands} to the new conclusion.</li>
 * </ul>
 *
 * <h1>Caveats</h1>
 * <ul>
 *  <li>Currently you can only add additional commands with the{@link #and(Command) and}-method but you can't remove any.
 *  For that just create a new conclusion and add the desired commands.</li>
 * </ul>
 *
 * <h1>Examples</h1>
 * <ol>
 *  <li>
 *      <p>Conclusion creation with single command:</p>
 *
 * <pre>
 * Command command = ...;
 * Conclusion conclusion = Conclusions.conclude(new ArrayList&lt;Command&gt;())
 *                               .and(<em>command</em>)
 *                               .get();
 * </pre>
 *
 *      <p>Of course you can pre-populate the initial collection of commands with the one you intend to add later.</p>
 *  </li>
 *  <li>
 *      <p>Conclusion creation with multiple commands:</p>
 *
 * <pre>
 * Command command_1 = ...;
 * Command command_2 = ...;
 * Command command_3 = ...;
 * Conclusion conclusion = Conclusions.conclude(new ArrayList&lt;Command&gt;())
 *                               .and(<em>command_1</em>)
 *                               .and(<em>command_2</em>)
 *                               .and(<em>command_3</em>)
 *                               . . .
 *                               .get();
 * </pre>
 *  </li>
 * </ol>
 *
 * <h1>How to help</h1>
 * <ul>
 *  <li>Test the interface and write back about errors, bugs and wishes.</li>
 * </ul>
 * 
 * @author      Sebastian Hoß (sebastian.hoss@viadee.de)
 * @param <T>   The target type of the new conclusion.
 * @since       1.0.0
 */
public interface ConclusionBuilder<T> extends Supplier<Conclusion<T>> {

    // * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
    // *                                                      METHODS                                                      *
    // * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *

    /**
     * <p>Adds another command to the new conclusion.</p>
     * 
     * <p>If you want to execute a specific command twice (or more) when a conclusion is reached
     * just add the command more then once (<b>duplicates are allowed</b>).</p>
     * 
     * @param command   The other conclusion (<b>may not be <code>null</code></b>).
     * @return          The current conclusion builder.
     */
    ConclusionBuilder<T> and(Command<T> command);

}
