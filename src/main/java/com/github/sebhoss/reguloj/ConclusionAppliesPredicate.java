/*
 * Copyright © 2010 Sebastian Hoß <mail@shoss.de>
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See http://www.wtfpl.net/ for more details.
 */
package com.github.sebhoss.reguloj;

import javax.annotation.Nullable;

final class ConclusionAppliesPredicate<TOPIC> extends ConclusionPredicate<TOPIC> {

    ConclusionAppliesPredicate(final TOPIC topic) {
        super(topic);
    }

    @Override
    public boolean apply(final @Nullable Conclusion<TOPIC> conclusion) {
        return conclusion != null && conclusion.apply(topic);
    }

}
