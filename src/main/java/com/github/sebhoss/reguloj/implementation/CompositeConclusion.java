/*
 * Copyright © 2010 Sebastian Hoß <mail@shoss.de>
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See http://www.wtfpl.net/ for more details.
 */
package com.github.sebhoss.reguloj.implementation;

import java.util.Collection;

import com.github.sebhoss.reguloj.Conclusion;
import com.google.common.base.Preconditions;

/**
 * @param <TOPIC>
 *            The topic of the inference process.
 */
public final class CompositeConclusion<TOPIC> implements Conclusion<TOPIC> {

    private final Collection<Conclusion<TOPIC>> conclusions;

    /**
     * @param conclusions
     *            The encapsulated conclusions to call (<b>may not be empty</b>).
     */
    public CompositeConclusion(final Collection<Conclusion<TOPIC>> conclusions) {
        Preconditions.checkNotNull(conclusions);
        Preconditions.checkArgument(!conclusions.isEmpty());

        this.conclusions = conclusions;
    }

    @Override
    public boolean apply(final TOPIC target) {
        boolean contextChanged = false;

        for (final Conclusion<TOPIC> conclusion : conclusions) {
            if (conclusion.apply(target)) {
                contextChanged = true;
            }
        }

        return contextChanged;
    }

}
