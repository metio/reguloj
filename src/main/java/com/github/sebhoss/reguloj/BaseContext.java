/*
 * Copyright © 2010 Sebastian Hoß <mail@shoss.de>
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See http://www.wtfpl.net/ for more details.
 */
package com.github.sebhoss.reguloj;

/**
 * Basic implementation of the {@link Context} interface.
 * 
 * @param <TOPIC>
 *            The topic of the context.
 */
public class BaseContext<TOPIC> implements Context<TOPIC> {

    private final TOPIC topic;

    /**
     * @param topic
     *            The topic of this context.
     */
    public BaseContext(final TOPIC topic) {
        this.topic = topic;
    }

    @Override
    public final TOPIC getTopic() {
        return topic;
    }

}
