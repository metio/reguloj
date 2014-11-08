/*
 * Copyright © 2010 Sebastian Hoß <mail@shoss.de>
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See http://www.wtfpl.net/ for more details.
 */
package com.github.sebhoss.reguloj;

import java.util.function.Predicate;
import java.util.stream.StreamSupport;

import org.eclipse.jdt.annotation.NonNull;

/**
 * Abstract rule engine which provides an implementation for the {@link #analyze(Iterable, Context)} method. Therefore
 * implementors only have to write the {@link #infer(Iterable, Context)} method.
 *
 * @param <CONTEXT>
 *            The context type.
 */
public abstract class AbstractRuleEngine<CONTEXT extends Context<@NonNull ?>> implements RuleEngine<CONTEXT> {

    @Override
    public final boolean analyze(final Iterable<Rule<CONTEXT>> rules, final CONTEXT context) {
        return anyRuleMatches(rules, Rules.ruleFires(context));
    }

    protected final boolean performSinglePass(final Iterable<Rule<CONTEXT>> rules, final CONTEXT context) {
        return anyRuleMatches(rules, Rules.ruleRuns(context));
    }

    private boolean anyRuleMatches(final Iterable<Rule<CONTEXT>> rules, final Predicate<Rule<CONTEXT>> predicate) {
        return StreamSupport.stream(rules.spliterator(), false).anyMatch(predicate);
    }

}
