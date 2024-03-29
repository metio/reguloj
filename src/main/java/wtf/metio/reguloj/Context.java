/*
 * SPDX-FileCopyrightText: The reguloj Authors
 * SPDX-License-Identifier: 0BSD
 */
package wtf.metio.reguloj;

/**
 * <p>
 * A {@link Context} is used by a {@link RuleEngine} to evaluate a collection of {@link Rule rules}.
 * </p>
 *
 * @param <TOPIC> The topic of the context.
 * @see RuleEngine
 * @see Rule
 */
public interface Context<TOPIC> {

    /**
     * Creates an immutable context that just contains the given topic.
     *
     * @param topic   The topic value of the context.
     * @param <TOPIC> The topic type of the context.
     * @return A new context with the given value.
     */
    static <TOPIC> Context<TOPIC> of(final TOPIC topic) {
        return new SimpleContext<>(topic);
    }

    /**
     * @return The topic of this inference context.
     */
    TOPIC topic();

}
