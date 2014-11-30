/*
 * Copyright © 2010 Sebastian Hoß <mail@shoss.de>
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See http://www.wtfpl.net/ for more details.
 */
package com.github.sebhoss.reguloj;

import java.util.Collection;

/**
 * Abstract rule engine which provides an implementation for the {@link #analyze(Collection, Context)} method. Therefore
 * implementors only have to write the {@link #infer(Collection, Context)} method.
 *
 * @param <CONTEXT>
 *            The context type.
 */
public abstract class AbstractRuleEngine<CONTEXT extends Context<?>> implements RuleEngine<CONTEXT> {

    @Override
    public final boolean analyze(final Collection<Rule<CONTEXT>> rules, final CONTEXT context) {
        return rules.stream().anyMatch(rule -> rule.fires(context));
    }

    protected final void performSinglePass(final Collection<Rule<CONTEXT>> rules, final CONTEXT context) {
        rules.stream().forEachOrdered(rule -> rule.run(context));
    }

}
