package com.github.sebhoss.reguloj;

/**
 * Utility class which helps creating new {@link RuleEngine rule engines}.
 * 
 * @see RuleEngine
 */
public final class RuleEngines {

    /**
     * @return A rule engine which supports rule chaining.
     */
    public static <CONTEXT extends Context<?>> RuleEngine<CONTEXT> chained() {
        return new ChainedRuleEngine<>();
    }

    /**
     * @return A rule engine which does not support rule chaining.
     */
    public static <CONTEXT extends Context<?>> RuleEngine<CONTEXT> unchained() {
        return new UnchainedRuleEngine<>();
    }

    /**
     * @param maximumNumberOfRuns
     *            The maximum number of runs to perform.
     * @return A rule engine which does not support rule chaining.
     */
    public static <CONTEXT extends Context<?>> RuleEngine<CONTEXT> limited(final int maximumNumberOfRuns) {
        return new LimitedRuleEngine<>(maximumNumberOfRuns);
    }

    private RuleEngines() {
        // do nothing
    }

}
