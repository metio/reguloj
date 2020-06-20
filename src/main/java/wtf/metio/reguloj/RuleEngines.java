package wtf.metio.reguloj;

/**
 * Utility class which helps creating new {@link RuleEngine rule engines}.
 *
 * @see RuleEngine
 */
public final class RuleEngines {

    private RuleEngines() {
        // do nothing
    }

    /**
     * @param <CONTEXT> The context type of the new rule engine
     * @return A rule engine which supports rule chaining.
     */
    public static <CONTEXT extends Context<?>> RuleEngine<CONTEXT> chained() {
        return new ChainedRuleEngine<>();
    }

    /**
     * @param maximumNumberOfRuns The maximum number of runs to perform.
     * @param <CONTEXT>           The context type of the new rule engine.
     * @return A rule engine which does not support rule chaining.
     */
    public static <CONTEXT extends Context<?>> RuleEngine<CONTEXT> limited(final int maximumNumberOfRuns) {
        return new LimitedRuleEngine<>(maximumNumberOfRuns);
    }

    /**
     * @param <CONTEXT> The context type of the new rule engine.
     * @return A rule engine which evaluates all rules until the first rule that fires.
     */
    public static <CONTEXT extends Context<?>> RuleEngine<CONTEXT> firstWins() {
        return new FirstWinsRuleEngine<>();
    }

}
