/*
 * Copyright © 2010 Sebastian Hoß <mail@shoss.de>
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See http://www.wtfpl.net/ for more details.
 */
package com.github.sebhoss.reguloj;

import javax.annotation.Nullable;

import com.google.common.base.Predicate;

final class ConclusionAppliesPredicate<TOPIC> implements Predicate<Conclusion<TOPIC>> {

    private final TOPIC topic;

    ConclusionAppliesPredicate(final TOPIC topic) {
        this.topic = topic;
    }

    @Override
    public boolean apply(final @Nullable Conclusion<TOPIC> conclusion) {
        return conclusion != null && conclusion.apply(topic);
    }

}
