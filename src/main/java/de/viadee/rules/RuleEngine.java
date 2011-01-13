/*
 * Project: rules
 * Package: de.viadee.rules
 * File   : RuleEngine.java
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

import java.util.Set;

/**
 * <h1>Overview</h1>
 * <p>The {@link RuleEngine} is responsible for logical reasoning. For that it analyzes a given {@link InferenceContext 
 * context} based upon a set of {@link Rule rules}.</p>
 *
 * <p>Both the context and the set of rules have to be supplied with each invocation of any of the available methods. This
 * means that users of this interface will have to hold on to their set of rules somewhere else. You can optimize this
 * set of rules by removing unnecessary elements or combining elements that are known to always fire together.</p>
 *
 * <h1>Caveats</h1>
 * <ul>
 *  <li>The current API design forces every user to call an instance of this engine explicitly and supply both
 *  the context and a set of rules. This may be inefficient for large sets of rules
 *  or overly complex context types.</li>
 *  <li>Enhancements like the RETE-algorithm can't be applied because this instances of this interface have no
 *  knowledge</li>
 * </ul>
 *
 * <h1>Examples</h1>
 * <ol>
 *  <li>
 *      <p>Test whether any rule would fire for a given context:</p>
 * <pre>
 * InferenceContext context = ...;
 * Set<Rule> rules = ...;
 * RuleEngine engine = ...;
 *
 * boolean fired = engine.analyze(context, rules);
 * </pre>
 *
 *  </li>
 *  <li>
 *      <p>Launch the engine and fire all valid rules:</p>
 * <pre>
 * InferenceContext context = ...;
 * Set<Rule> rules = ...;
 * RuleEngine engine = ...;
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
 * @param <T>   The topic of the rule engine.
 * @since       1.0.0
 */
public interface RuleEngine<T> {

    // * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
    // *                                                      METHODS                                                      *
    // * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *

    /**
     * <p>Performs a dry-run with this engine by analyzing a given context based upon a set of rules. It will only
     * check whether any rule would fires inside the given context but does not apply any conclusions. For that call
     * the {@link RuleEngine#infer(InferenceContext, Set) infer}-method.</p>
     * 
     * @param context   The context to analyze (<b>may not be <code>null</code></b>).
     * @param rules     The rule set to use (<b>may not be <code>null</code></b>).
     * @return          <code>true</code> if any rule would fire, <code>false</code> otherwise.
     */
    public boolean analyze(InferenceContext<T> context, Set<Rule<T>> rules);

    /**
     * <p>Launches this engine and lets it analyze and execute a set of rules on a given context.</p>
     * 
     * @param context   The context to analyze (<b>may not be <code>null</code></b>).
     * @param rules     The rules to use (<b>may not be <code>null</code></b>).
     */
    public void infer(InferenceContext<T> context, Set<Rule<T>> rules);

}
