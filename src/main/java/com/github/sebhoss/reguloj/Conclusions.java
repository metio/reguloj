/*
 * Copyright © 2010 Sebastian Hoß <mail@shoss.de>
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See http://www.wtfpl.net/ for more details.
 */
package com.github.sebhoss.reguloj;

import java.util.Collection;
import java.util.Iterator;
import java.util.function.Predicate;

import com.github.sebhoss.nullanalysis.Nullsafe;
import com.google.common.collect.ImmutableList;

/**
 * Utility class which helps creating new {@link Conclusion conclusions}.
 */
public final class Conclusions {

    /**
     * Creates a new {@link Conclusion} which encapsulates all given conclusions.
     *
     * @param <T>
     *            The topic of the inference process.
     * @param conclusions
     *            The conclusions to group (<b>may not be empty</b>).
     * @return A new conclusion builder.
     */
    public static <T> Conclusion<T> conclude(final Collection<? extends Conclusion<T>> conclusions) {
        return new CompositeConclusion<>(Nullsafe.nullsafe(ImmutableList.copyOf(conclusions)));
    }

    /**
     * Creates a new {@link Conclusion} which encapsulates all given conclusions.
     *
     * @param <T>
     *            The topic of the inference process.
     * @param conclusion1
     *            The first conclusion to use.
     * @param conclusion2
     *            The second conclusion to use.
     * @return A new conclusion builder.
     */
    public static <T> Conclusion<T> conclude(final Conclusion<T> conclusion1, final Conclusion<T> conclusion2) {
        return new CompositeConclusion<>(Nullsafe.nullsafe(ImmutableList.of(conclusion1, conclusion2)));
    }

    /**
     * Creates a new {@link Conclusion} which encapsulates all given conclusions.
     *
     * @param <T>
     *            The topic of the inference process.
     * @param conclusions
     *            The conclusions to group (<b>may not be empty</b>).
     * @return A new conclusion builder.
     */
    public static <T> Conclusion<T> conclude(final Iterable<? extends Conclusion<T>> conclusions) {
        return new CompositeConclusion<>(Nullsafe.nullsafe(ImmutableList.copyOf(conclusions)));
    }

    /**
     * Creates a new {@link Conclusion} which encapsulates all given conclusions.
     *
     * @param <T>
     *            The topic of the inference process.
     * @param conclusions
     *            The conclusions to group (<b>may not be empty</b>).
     * @return A new conclusion builder.
     */
    public static <T> Conclusion<T> conclude(final Iterator<? extends Conclusion<T>> conclusions) {
        return new CompositeConclusion<>(Nullsafe.nullsafe(ImmutableList.copyOf(conclusions)));
    }

    /**
     * @param topic
     *            The topic to check.
     * @return A predicate that checks whether a conclusion applies in the given topic or not.
     */
    public static <TOPIC> Predicate<Conclusion<TOPIC>> conlusionApplies(final TOPIC topic) {
        return new ConclusionAppliesPredicate<>(topic);
    }

    private Conclusions() {
        // do nothing
    }

}
