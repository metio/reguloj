package wtf.metio.reguloj;

/**
 * Smallest possible context that only contains the topic of a rule engine.
 *
 * @param <TOPIC> The type of the topic.
 */
record SimpleContext<TOPIC>(TOPIC topic) implements Context<TOPIC> {
}
