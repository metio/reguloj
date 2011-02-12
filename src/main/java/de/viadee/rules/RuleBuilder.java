/*
 * Project: viaRules-core
 * Package: de.viadee.rules
 * File   : RuleBuilder.java
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

import com.google.common.base.Predicate;
import com.google.common.base.Supplier;

/**
 * <p>Offers an easy to use fluent interface for building new {@link Rule rules}. It follows the builder-pattern described
 * by Joshua Bloch (see <a href="http://java.sun.com/docs/books/effective/">Effective Java, Item 2</a>) and offers
 * 3 additional methods:</p>
 *
 * <ul>
 *  <li>{@link #called(String) called}: Use this method to name your new rule.</li>
 *  <li>{@link #when(Predicate) when}: Use this method to specify the premise for your new rule.</li>
 *  <li>{@link #then(Conclusion) then}: Use this method to specify the conclusion for your new rule.</li>
 * </ul>
 *
 * <h1>Caveats</h1>
 * <ul>
 *  <li>Methods like {@link RuleBuilder#when(Predicate) when} or {@link RuleBuilder#then(Conclusion) then} can
 *  be called multiple times but implementations of this API should only honor the last call. All previous calls
 *  (and their parameters) should be dismissed. If you want to create complex premises you'll have to construct
 *  them beforehand and use your newly created complex premise as an input for the <em>when</em>-clause.</li>
 *  <li>As noted in the {@link RuleBuilder#then(Conclusion) then}-method documentation, you are not allowed to use
 *  <code>null</code> as an valid input. So in the rare case that you want to have a rule without any conclusion
 *  you have to create and supply some sort of <em>no-action</em> conclusion which does nothing except not being
 *  <code>null</code></li>
 * </ul>
 *
 * <h1>Examples</h1>
 * <ol>
 *  <li>
 *      <p>Rule creation with name, premise and conclusion:</p>
 *
 * <pre>
 * String name = "...";
 * Predicate premise = ...;
 * Conclusion conclusion = ...;
 * Rule rule = Rules.rule()
 *               .called(<em>name</em>)
 *               .when(<em>premise</em>)
 *               .then(<em>conclusion</em>)
 *               .get();
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
 * @author      Sebastian Hoß (sebastian.hoss@viadee.de)
 * @param <C>   The context type.
 * @since       1.0.0
 */
public interface RuleBuilder<C extends InferenceContext<?>> extends Supplier<Rule<C>> {

    // * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
    // *                                                      METHODS                                                      *
    // * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *

    /**
     * Sets the premise for the new rule.
     * 
     * @param premise       The premise to set (<b>may no be <code>null</code></b>).
     * @return              The current rule builder.
     */
    RuleBuilder<C> when(Predicate<C> premise);

    /**
     * Sets the conclusion for the new rule.
     *
     * @param conclusion    The conclusion to set (<b>may no be <code>null</code></b>).
     * @return              The current rule builder.
     */
    RuleBuilder<C> then(Conclusion<C> conclusion);

    /**
     * Sets the name of the new rule.
     *
     * @param name  The name to set (<b>may not be <code>null</code></b>).
     * @return      The current rule builder.
     */
    RuleBuilder<C> called(String name);

}
