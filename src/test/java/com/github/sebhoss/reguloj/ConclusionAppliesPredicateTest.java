/*
 * Copyright © 2010 Sebastian Hoß <mail@shoss.de>
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See http://www.wtfpl.net/ for more details.
 */
package com.github.sebhoss.reguloj;

import com.github.sebhoss.warnings.CompilerWarnings;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;

/**
 * Test cases for ConclusionAppliesPredicate.
 */
@SuppressWarnings({ CompilerWarnings.BOXING, CompilerWarnings.NLS, CompilerWarnings.UNCHECKED })
public class ConclusionAppliesPredicateTest {

    private ConclusionPredicate<String> predicate;
    private Conclusion<String>          conclusion;

    /**
     * Creates predicate and conclusion for each test.
     */
    @Before
    public void setup() {
        predicate = new ConclusionAppliesPredicate<>("test");
        conclusion = Mockito.mock(Conclusion.class);
    }

    /**
     * Ensures that a <code>true</code> is returned when the given conclusion applies.
     */
    @Test
    public void shouldReturnTrueOnAppliedConclusion() {
        BDDMockito.given(conclusion.apply("test")).willReturn(Boolean.TRUE);

        final boolean applied = predicate.test(conclusion);

        Assert.assertTrue(applied);
    }

    /**
     * Ensures that a <code>false</code> is returned when the given conclusion does not apply.
     */
    @Test
    public void shouldReturnFalseOnNonAppliedConclusion() {
        BDDMockito.given(conclusion.apply("test")).willReturn(Boolean.FALSE);

        final boolean applied = predicate.test(conclusion);

        Assert.assertFalse(applied);
    }

}
