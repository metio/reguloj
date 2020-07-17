package wtf.metio.reguloj;

/**
 * <p>
 * An {@link Context} is used every time a set of rules shall be evaluated. The only available method in the generic
 * version is {@link #topic()} which returns the main topic of your rules.
 * </p>
 *
 * @param <TOPIC> The topic of the context.
 */
public interface Context<TOPIC> {

    /**
     * @param topic The topic value of the context.
     * @param <TOPIC> The topic type of the context.
     * @return A new context with the given value.
     */
    static <TOPIC>  Context<TOPIC> of(final TOPIC topic) {
        return new ContextRecord<>(topic);
    }

    /**
     * @return The topic of this inference context.
     */
    TOPIC topic();

}
