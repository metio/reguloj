package wtf.metio.reguloj;

/**
 * <p>
 * A {@link Context} is used by a {@link RuleEngine} to evaluate a collection of {@link Rule rules}.
 * </p>
 *
 * @param <TOPIC> The topic of the context.
 * @see RuleEngine
 * @see Rule
 */
public interface Context<TOPIC> {

  /**
   * @param topic   The topic value of the context.
   * @param <TOPIC> The topic type of the context.
   * @return A new context with the given value.
   */
  static <TOPIC> Context<TOPIC> of(final TOPIC topic) {
    return new ContextRecord<>(topic);
  }

  /**
   * @return The topic of this inference context.
   */
  TOPIC topic();

}
