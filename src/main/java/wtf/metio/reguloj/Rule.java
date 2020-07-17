package wtf.metio.reguloj;

/**
 * <p>
 * A {@link Rule} combines {@link java.util.function.Predicate} and {@link java.util.function.Consumer} interfaces and
 * can be evaluated with a {@link RuleEngine} using a {@link Context}.
 * </p>
 * <h2>Examples</h2>
 * <ol>
 * <li>
 * <p>
 * Check whether a rule would fire inside a given context:
 * </p>
 *
 * <pre>
 * Context&lt;X&gt; context = ...;
 * Rule&lt;Context&lt;X&gt;&gt; rule = ...;
 *
 * boolean canFire = rule.fires(context);
 * </pre>
 *
 * </li>
 * <li>
 * <p>
 * Run a rule inside a given context:
 * </p>
 * <pre>
 * Context&lt;X&gt; context = ...;
 * Rule&lt;Context&lt;X&gt;&gt; rule = ...;
 *
 * rule.run(context);
 * </pre>
 * </li>
 * </ol>
 *
 * @param <CONTEXT> The context type.
 * @see RuleEngine
 * @see Context
 * @see java.util.function.Predicate
 * @see java.util.function.Consumer
 */
public interface Rule<CONTEXT extends Context<?>> {

    /**
     * @param <CONTEXT> The context type.
     * @return A new builder to construct rules.
     */
    static <CONTEXT extends Context<?>> RuleBuilder<CONTEXT> builder() {
        return new RuleBuilderImplementation<>();
    }

    /**
     * Runs this rule inside a given context.
     *
     * @param context The context to use.
     */
    void run(CONTEXT context);

    /**
     * Checks whether this rule would fire for a given context.
     *
     * @param context The context to check.
     * @return <code>true</code> if this rule would fire, <code>false</code> otherwise.
     */
    boolean fires(CONTEXT context);

    /**
     * @return The human readable name of this rule.
     */
    String name();

}
