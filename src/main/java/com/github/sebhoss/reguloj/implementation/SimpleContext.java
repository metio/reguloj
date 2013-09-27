package com.github.sebhoss.reguloj.implementation;

import com.github.sebhoss.reguloj.Context;

/**
 * Simple implementation of the {@link Context} interface.
 * 
 * @param <TOPIC>
 *            The topic of the context.
 */
public final class SimpleContext<TOPIC> implements Context<TOPIC> {

    private final TOPIC topic;

    /**
     * @param topic
     *            The topic of this context.
     */
    public SimpleContext(final TOPIC topic) {
        this.topic = topic;
    }

    @Override
    public TOPIC getTopic() {
        return topic;
    }

}
