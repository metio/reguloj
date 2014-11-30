/*
 * Copyright © 2010 Sebastian Hoß <mail@shoss.de>
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See http://www.wtfpl.net/ for more details.
 */
package com.github.sebhoss.reguloj;

import java.util.function.Predicate;

abstract class ConclusionPredicate<TOPIC> implements Predicate<Conclusion<TOPIC>> {

    protected final TOPIC topic;

    ConclusionPredicate(final TOPIC topic) {
        this.topic = topic;
    }

}
