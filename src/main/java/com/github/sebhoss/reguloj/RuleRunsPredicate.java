/*
 * Copyright © 2010 Sebastian Hoß <mail@shoss.de>
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See http://www.wtfpl.net/ for more details.
 */
package com.github.sebhoss.reguloj;


final class RuleRunsPredicate<CONTEXT extends Context<?>> extends RulePredicate<CONTEXT> {

    RuleRunsPredicate(final CONTEXT context) {
        super(context);
    }

    @Override
    public boolean test(final Rule<CONTEXT> rule) {
        return rule.run(context);
    }

}
