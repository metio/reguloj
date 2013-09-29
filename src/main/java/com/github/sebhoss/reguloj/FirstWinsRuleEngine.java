/*
 * Copyright © 2010 Sebastian Hoß <mail@shoss.de>
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See http://www.wtfpl.net/ for more details.
 */
package com.github.sebhoss.reguloj;

import java.util.Set;

import com.google.common.collect.FluentIterable;

class FirstWinsRuleEngine<CONTEXT extends Context<?>> extends AbstractRuleEngine<CONTEXT> {

    @Override
    public void infer(final CONTEXT context, final Set<Rule<CONTEXT>> rules) {
        FluentIterable.from(rules).firstMatch(Rules.ruleRuns(context));
    }

}
