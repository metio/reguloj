/*
 * Copyright © 2010 Sebastian Hoß <mail@shoss.de>
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See http://www.wtfpl.net/ for more details.
 */
package com.github.sebhoss.reguloj;

import java.util.function.Consumer;
import java.util.function.Predicate;

import com.github.sebhoss.warnings.CompilerWarnings;

import org.hamcrest.core.Is;
import org.hamcrest.core.IsNull;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

/**
 * Test cases for the RuleBuilderImplementation.
 */
@SuppressWarnings({ CompilerWarnings.STATIC_METHOD, CompilerWarnings.UNCHECKED })
public final class RuleBuilderImplementationTest {

    /**
     * <p>
     * Test method for RuleBuilderImplementation#then(Conclusion)
     * </p>
     * <p>
     * Ensures that rules can be created with a valid RuleBuilderImplementation.
     * </p>
     */
    @Test
    public void shouldCreateRuleIfAllValuesAreSet() {
        final RuleBuilder<Context<Object>> builder = new RuleBuilderImplementation<>();
        builder.called("test rule").when(Mockito.mock(Predicate.class)); //$NON-NLS-1$

        final Rule<Context<Object>> rule = builder.then(Mockito.mock(Consumer.class));

        Assert.assertThat(rule, Is.is(IsNull.notNullValue()));
    }

}
