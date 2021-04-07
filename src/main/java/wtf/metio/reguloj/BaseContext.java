package wtf.metio.reguloj;

/**
 * Basic implementation of the {@link Context} interface. Use this class in case you want to extend your context beyond
 * its topic. Otherwise use {@link SimpleContext} to just pass along your topic.
 *
 * @param <TOPIC> The topic of the context.
 */
public abstract class BaseContext<TOPIC> implements Context<TOPIC> {

  protected final TOPIC topic;

  /**
   * @param topic The topic of this context.
   */
  public BaseContext(final TOPIC topic) {
    this.topic = topic;
  }

  @Override
  public final TOPIC topic() {
    return topic;
  }

}
