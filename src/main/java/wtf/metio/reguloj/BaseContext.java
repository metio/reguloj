package wtf.metio.reguloj;

/**
 * Basic implementation of the {@link Context} interface.
 *
 * @param <TOPIC> The topic of the context.
 */
public class BaseContext<TOPIC> implements Context<TOPIC> {

    private final TOPIC topic;

    /**
     * @param topic The topic of this context.
     */
    public BaseContext(final TOPIC topic) {
        this.topic = topic;
    }

    @Override
    public final TOPIC getTopic() {
        return topic;
    }

}
