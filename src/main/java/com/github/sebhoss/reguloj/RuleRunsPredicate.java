package com.github.sebhoss.reguloj;

final class RuleRunsPredicate<CONTEXT extends Context<?>> extends RulePredicate<CONTEXT> {

    RuleRunsPredicate(final CONTEXT context) {
        super(context);
    }

    @Override
    public boolean apply(final Rule<CONTEXT> rule) {
        return rule.run(context);
    }

}
