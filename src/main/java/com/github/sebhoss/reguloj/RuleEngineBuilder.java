package com.github.sebhoss.reguloj;

import com.google.common.base.Supplier;

/**
 * @param <CONTEXT>
 *            The context type.
 */
public interface RuleEngineBuilder<CONTEXT> extends Supplier<CONTEXT> {

    /**
     * Marks the engine to build to enable rule chaining.
     * 
     * @return The current builder.
     */
    RuleEngineBuilder<CONTEXT> chained();

    /**
     * Marks the engine to build to disable rule chaining.
     * 
     * @return The current builder.
     */
    RuleEngineBuilder<CONTEXT> notChained();

    /**
     * Limits the maximum number of runs the engine to build performs. Note: This method is only useful with chained()
     * rule engines. Rule engines that are notChained() only ever perform a single run.
     * 
     * @param maximumNumberOfRuns
     *            The maximum number of runs to perform.
     * @return The current builder.
     */
    RuleEngineBuilder<CONTEXT> limitRuns(int maximumNumberOfRuns);

}
