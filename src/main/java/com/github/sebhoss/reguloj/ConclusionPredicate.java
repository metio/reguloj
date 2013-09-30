package com.github.sebhoss.reguloj;

import com.google.common.base.Predicate;

abstract class ConclusionPredicate<TOPIC> implements Predicate<Conclusion<TOPIC>> {

    protected final TOPIC topic;

    ConclusionPredicate(final TOPIC topic) {
        this.topic = topic;
    }

}
