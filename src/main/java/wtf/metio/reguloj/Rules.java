package wtf.metio.reguloj;

/**
 * Utility class which helps creating new {@link Rule rules}.
 *
 * @see Rule
 */
public final class Rules {

    /**
     * Creates a new {@link RuleBuilder} which offers an easy to use DSL for creating new {@link Rule rules}.
     *
     * @return A new rule builder.
     */
    public static <CONTEXT extends Context<?>> RuleBuilder<CONTEXT> rule() {
        return new RuleBuilderImplementation<>();
    }

    private Rules() {
        // do nothing
    }

}
