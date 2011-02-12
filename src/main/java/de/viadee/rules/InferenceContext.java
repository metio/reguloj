/*
 * Project: viaRules-core
 * Package: de.viadee.rules
 * File   : InferenceContext.java
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
 * <p>An {@link InferenceContext} is used every time a set of rules shall be evaluated. Currently you'll most
 * likely cast the context instance to your specific implementation and use that instead of the generic
 * interface defined here. The only available method in the generic version is {@link #getGist()} which returns
 * the main or focal point of your rules (if any).</p>
 *
 * <p>Note that no factory or builder is defined for the <code>InferenceContext</code> so you will have to implement
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
 *  <li>Write an example of how to use an InferenceContext.</li>
 * </ul>
 * 
 * @author      Sebastian Hoß (sebastian.hoss@viadee.de)
 * @param <T>   The topic of the context.
 * @since       1.0.0
 */
public interface InferenceContext<T> {

    // * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
    // *                                                      METHODS                                                      *
    // * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *

    /**
     * Gets the gist of this inference context.
     * 
     * @return  The gist of this context.
     */
    T getGist();

}
