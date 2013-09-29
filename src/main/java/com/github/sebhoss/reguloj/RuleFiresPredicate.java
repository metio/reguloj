package com.github.sebhoss.reguloj;

final class RuleFiresPredicate<CONTEXT extends Context<?>> extends RulePredicate<CONTEXT> {

    RuleFiresPredicate(final CONTEXT context) {
        super(context);
    }

    @Override
    public boolean apply(final Rule<CONTEXT> rule) {
        return rule.fires(context);
    }

}
