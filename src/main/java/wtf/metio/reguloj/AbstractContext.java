package wtf.metio.reguloj;

/**
 * Abstract implementation of the {@link Context} interface. Use this class in case you want to extend your context beyond
 * its topic. Otherwise use {@link SimpleContext} to just pass along your topic. Note that this class is only marked as
 * abstract (and called like that) because users who just want to pass along a topic should use {@link SimpleContext}.
 *
 * @param <TOPIC> The topic of the context.
 */
public abstract class AbstractContext<TOPIC> implements Context<TOPIC> {

  /** The topic for this context. */
  protected final TOPIC topic;

  /**
   * @param topic The topic of this context.
   */
  public AbstractContext(final TOPIC topic) {
    this.topic = topic;
  }

  @Override
  public final TOPIC topic() {
    return topic;
  }

}
