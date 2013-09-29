package com.github.sebhoss.reguloj;

import com.google.common.base.Predicate;

final class RuleRunsPredicate<CONTEXT extends Context<?>> implements Predicate<Rule<CONTEXT>> {

    private final CONTEXT context;

    RuleRunsPredicate(final CONTEXT context) {
        this.context = context;
    }

    @Override
    public boolean apply(final Rule<CONTEXT> rule) {
        return rule.run(context);
    }

}
