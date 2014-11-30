/*
 * Copyright © 2010 Sebastian Hoß <mail@shoss.de>
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See http://www.wtfpl.net/ for more details.
 */
package com.github.sebhoss.reguloj;

import java.util.ArrayList;
import java.util.Collection;

import com.github.sebhoss.warnings.CompilerWarnings;

import org.junit.Assert;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.BDDMockito;
import org.mockito.Mockito;

/**
 * Test cases for CompositeConclusion.
 */
@SuppressWarnings({ CompilerWarnings.STATIC_METHOD })
public class CompositeConclusionTest {

    /** Checks expected exception inside single test cases. */
    @org.junit.Rule
    public ExpectedException thrown = ExpectedException.none();

    /**
     * Ensures that an empty list of conclusions won't be accepted by a CompositeConclusion.
     */
    @Test
    @SuppressWarnings(CompilerWarnings.UNUSED)
    public void shouldNotAllowEmptyCollection() {
        final Iterable<Conclusion<Object>> conclusions = new ArrayList<>();

        thrown.expect(IllegalArgumentException.class);

        new CompositeConclusion<Object>(conclusions);
    }

    /**
     * Ensures that all given conclusions are called.
     */
    @Test
    @SuppressWarnings({ CompilerWarnings.BOXING, CompilerWarnings.UNCHECKED })
    public void shouldCallAllGivenConclusions() {
        final Object target = new Object();
        final Collection<Conclusion<Object>> conclusions = new ArrayList<>();
        final Conclusion<Object> conclusion1 = Mockito.mock(Conclusion.class);
        BDDMockito.given(conclusion1.apply(target)).willReturn(true);
        final Conclusion<Object> conclusion2 = Mockito.mock(Conclusion.class);
        BDDMockito.given(conclusion2.apply(target)).willReturn(false);
        conclusions.add(conclusion1);
        conclusions.add(conclusion2);

        final Conclusion<Object> composite = new CompositeConclusion<>(conclusions);

        Assert.assertTrue(composite.apply(target));
    }

}
