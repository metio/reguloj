/*
 * Copyright © 2010 Sebastian Hoß <mail@shoss.de>
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See http://www.wtfpl.net/ for more details.
 */
package com.github.sebhoss.reguloj;

import java.util.Set;

/**
 * Abstract rule engine which provides an implementation for the {@link #analyze(Context, Set)} method. Therefore
 * implementors only have to write the {@link #infer(Context, Set)} method.
 * 
 * @param <CONTEXT>
 *            The context type.
 */
public abstract class AbstractRuleEngine<CONTEXT extends Context<?>> implements RuleEngine<CONTEXT> {

    @Override
    public final boolean analyze(final CONTEXT context, final Set<Rule<CONTEXT>> rules) {
        for (final Rule<CONTEXT> rule : rules) {
            if (rule.fires(context)) {
                return true;
            }
        }

        return false;
    }

}
