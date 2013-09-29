package com.github.sebhoss.reguloj;

import com.google.common.base.Predicate;

abstract class RulePredicate<CONTEXT extends Context<?>> implements Predicate<Rule<CONTEXT>> {

    protected final CONTEXT context;

    RulePredicate(final CONTEXT context) {
        this.context = context;
    }

}
