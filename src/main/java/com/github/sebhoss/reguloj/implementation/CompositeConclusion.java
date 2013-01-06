package com.github.sebhoss.reguloj.implementation;

import java.util.Collection;

import com.github.sebhoss.reguloj.Conclusion;
import com.google.common.base.Preconditions;


/**
 * @author      Sebastian Hoß (sebastian.hoss@viadee.de)
 * @param <T>   The topic of the inference process.
 * @since       2.0.0
 */
public final class CompositeConclusion<T> implements Conclusion<T> {

    // * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
    // *                                                  ATTRIBUTES                                                 *
    // * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *

    /** The encapsulated conclusions to call. */
    private final Collection<Conclusion<T>> conclusions;

    // * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
    // *                                                 CONSTRUCTORS                                                *
    // * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *

    /**
     * @param conclusions   The encapsulated conclusions to call (<b>may not be <code>null</code> nor empty</b>).
     */
    public CompositeConclusion(final Collection<Conclusion<T>> conclusions) {
        // Check inputs
        Preconditions.checkNotNull(conclusions);
        Preconditions.checkArgument(!conclusions.isEmpty());

        // Set inputs
        this.conclusions = conclusions;
    }

    // * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
    // *                                                    METHODS                                                  *
    // * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean apply(final T target) {
        boolean contextChanged = false;

        for (final Conclusion<T> conclusion : this.conclusions) {
            if (conclusion.apply(target)) {
                contextChanged = true;
            }
        }

        return contextChanged;
    }

}
