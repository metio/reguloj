/*
 * Project: viaRules-core
 * Package: de.viadee.rules
 * File   : Command.java
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

/**
 * <p>A {@link Command} is a single piece of action which can be performed by calling its {@link #execute(Object)
 * execute} method. The target (the given parameter) of this call and the <code>Command</code> itself are parameterized
 * with the generic type <code>T</code> which must match the type of the enclosing rules and the enclosing rule engine.</p>
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
 * </ul>
 * 
 * @author      Sebastian Hoß (sebastian.hoss@viadee.de)
 * @param <T>   The target type of the command.
 * @since       1.0.0
 */
public interface Command<T> {

    // * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
    // *                                                      METHODS                                                      *
    // * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *

    /**
     * <p>Executes this command on the given target.</p>
     * 
     * @param target    The target to use (<b>may not be <code>null</code></b>).
     * @return          <code>true</code> if the command had any effect on the target, <code>false</code> otherwise.
     */
    boolean execute(T target);

}
