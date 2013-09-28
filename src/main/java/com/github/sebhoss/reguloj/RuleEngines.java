package com.github.sebhoss.reguloj;

/**
 * Utility class which helps creating new {@link RuleEngine rule engines}.
 * 
 * @see RuleEngine
 */
public class RuleEngines {

    /**
     * @return A simple rule engine which supports rule chaining.
     */
    public static <CONTEXT extends Context<?>> RuleEngine<CONTEXT> simpleEngine() {
        return new SimpleRuleEngine<>();
    }

    /**
     * @return A builder for custom rule engines.
     */
    public static <CONTEXT extends Context<?>> RuleEngineBuilder<CONTEXT> custom() {
        return new RuleEngineBuilderImplementation<>();
    }

    private RuleEngines() {
        // do nothing
    }

}
