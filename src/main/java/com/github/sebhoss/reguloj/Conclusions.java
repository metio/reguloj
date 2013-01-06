/* This program is free software. It comes without any warranty, to
 * the extent permitted by applicable law. You can redistribute it
 * and/or modify it under the terms of the Do What The Fuck You Want
 * To Public License, Version 2, as published by Sam Hocevar. See
 * http://sam.zoy.org/wtfpl/COPYING for more details.
 */
package com.github.sebhoss.reguloj;

import java.util.Collection;
import java.util.Iterator;

import com.github.sebhoss.reguloj.implementation.CompositeConclusion;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;


/**
 * <p>Utility class which helps creating new {@link Conclusion conclusions}.</p>
 */
public final class Conclusions {

    // * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
    // *                                                 CONSTRUCTORS                                                *
    // * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *

    /**
     * Hidden constructor.
     */
    private Conclusions() {
        // do nothing
    }

    // * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
    // *                                                    METHODS                                                  *
    // * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *

    /**
     * <p>
     * Creates a new {@link Conclusion} which encapsulates all given conclusions.
     * </p>
     * 
     * @param <T>           The topic of the inference process.
     * @param conclusions   The conclusions to group (<b>may not be <code>null</code> nor empty</b>).
     * @return              A new conclusion builder.
     */
    public static <T> Conclusion<T> conclude(final Collection<? extends Conclusion<T>> conclusions) {
        // Check inputs
        Preconditions.checkNotNull(conclusions);
        Preconditions.checkArgument(!conclusions.isEmpty());

        // Create composition
        return new CompositeConclusion<T>(ImmutableList.copyOf(conclusions));
    }

    /**
     * <p>
     * Creates a new {@link Conclusion} which encapsulates all given conclusions.
     * </p>
     * 
     * @param <T>           The topic of the inference process.
     * @param conclusion1   The first conclusion to use (<b>may not be <code>null</code></b>).
     * @param conclusion2   The second conclusion to use (<b>may not be <code>null</code></b>).
     * @return              A new conclusion builder.
     */
    public static <T> Conclusion<T> conclude(final Conclusion<T> conclusion1, final Conclusion<T> conclusion2) {
        // Check inputs
        Preconditions.checkNotNull(conclusion1);
        Preconditions.checkNotNull(conclusion2);

        // Create composition
        return new CompositeConclusion<T>(ImmutableList.of(conclusion1, conclusion2));
    }

    /**
     * <p>
     * Creates a new {@link Conclusion} which encapsulates all given conclusions.
     * </p>
     * 
     * @param <T>           The topic of the inference process.
     * @param conclusions   The conclusions to group (<b>may not be <code>null</code> nor empty</b>).
     * @return              A new conclusion builder.
     */
    public static <T> Conclusion<T> conclude(final Iterable<? extends Conclusion<T>> conclusions) {
        // Check inputs
        Preconditions.checkNotNull(conclusions);
        Preconditions.checkArgument(!Iterables.isEmpty(conclusions));

        // Create composition
        return new CompositeConclusion<T>(ImmutableList.copyOf(conclusions));
    }

    /**
     * <p>
     * Creates a new {@link Conclusion} which encapsulates all given conclusions.
     * </p>
     * 
     * @param <T>           The topic of the inference process.
     * @param conclusions   The conclusions to group (<b>may not be <code>null</code> nor empty</b>).
     * @return              A new conclusion builder.
     */
    public static <T> Conclusion<T> conclude(final Iterator<? extends Conclusion<T>> conclusions) {
        // Check inputs
        Preconditions.checkNotNull(conclusions);
        Preconditions.checkArgument(conclusions.hasNext());

        // Create composition
        return new CompositeConclusion<T>(ImmutableList.copyOf(conclusions));
    }

}
