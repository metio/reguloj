/*
 * Copyright © 2010 Sebastian Hoß <mail@shoss.de>
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See http://www.wtfpl.net/ for more details.
 */
package com.github.sebhoss.reguloj;

import java.util.Set;

import com.google.common.collect.FluentIterable;

/**
 * Abstract rule engine which provides an implementation for the {@link #analyze(Set, Context)} method. Therefore
 * implementors only have to write the {@link #infer(Set, Context)} method.
 * 
 * @param <CONTEXT>
 *            The context type.
 */
public abstract class AbstractRuleEngine<CONTEXT extends Context<?>> implements RuleEngine<CONTEXT> {

    @Override
    public final boolean analyze(final Set<Rule<CONTEXT>> rules, final CONTEXT context) {
        return FluentIterable.from(rules).anyMatch(Rules.ruleFires(context));
    }

    protected final boolean performSinglePass(final Set<Rule<CONTEXT>> rules, final CONTEXT context) {
        return FluentIterable.from(rules).filter(Rules.ruleRuns(context)).size() > 0;
    }

}
