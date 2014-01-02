/*
 * Copyright © 2010 Sebastian Hoß <mail@shoss.de>
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See http://www.wtfpl.net/ for more details.
 */
package com.github.sebhoss.reguloj;

import java.lang.reflect.Constructor;
import java.util.Collection;
import java.util.Iterator;

import com.github.sebhoss.warnings.CompilerWarnings;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

import org.hamcrest.core.Is;
import org.hamcrest.core.IsNull;
import org.junit.Assert;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mockito;

/**
 * Test cases for the {@link Conclusions} utility class.
 * 
 * @see Conclusions
 */
@SuppressWarnings({ CompilerWarnings.NULL, CompilerWarnings.STATIC_METHOD })
public final class ConclusionsTest {

    /** Checks expected exception inside single test cases. */
    @org.junit.Rule
    public ExpectedException thrown = ExpectedException.none();

    /**
     * <p>
     * Test method for {@link Conclusions#conclude(Collection)}
     * </p>
     * <p>
     * Ensures that passing in a <code>null</code> collection is not allowed.
     * </p>
     */
    @Test
    public void shouldNotAllowNullCollection() {
        final Collection<Conclusion<String>> conclusions = null;

        thrown.expect(NullPointerException.class);

        Conclusions.conclude(conclusions);
    }

    /**
     * <p>
     * Test method for {@link Conclusions#conclude(Collection)}
     * </p>
     * <p>
     * Ensures that passing in an empty collection is not allowed.
     * </p>
     */
    @Test
    public void shouldNotAllowEmptyCollection() {
        final Collection<Conclusion<String>> conclusions = Lists.newArrayList();

        thrown.expect(IllegalArgumentException.class);

        Conclusions.conclude(conclusions);
    }

    /**
     * <p>
     * Test method for {@link Conclusions#conclude(Collection)}
     * </p>
     * <p>
     * Ensures that a filled collection is accepted.
     * </p>
     */
    @SuppressWarnings(CompilerWarnings.UNCHECKED)
    @Test
    public void shouldAcceptCollection() {
        final Collection<Conclusion<String>> conclusions = Lists.newArrayList();
        conclusions.add(Mockito.mock(Conclusion.class));

        final Conclusion<String> composite = Conclusions.conclude(conclusions);

        Assert.assertThat(composite, Is.is(IsNull.notNullValue()));
    }

    /**
     * <p>
     * Test method for {@link Conclusions#conclude(Iterable)}
     * </p>
     * <p>
     * Ensures that passing in a <code>null</code> iterable is not allowed.
     * </p>
     */
    @Test
    public void shouldNotAllowNullIterable() {
        final Collection<Conclusion<String>> conclusions = null;

        thrown.expect(NullPointerException.class);

        Conclusions.conclude(Iterables.unmodifiableIterable(conclusions));
    }

    /**
     * <p>
     * Test method for {@link Conclusions#conclude(Iterable)}
     * </p>
     * <p>
     * Ensures that passing in an empty iterable is not allowed.
     * </p>
     */
    @Test
    public void shouldNotAllowEmptyIterable() {
        final Collection<Conclusion<String>> conclusions = Lists.newArrayList();

        thrown.expect(IllegalArgumentException.class);

        Conclusions.conclude(Iterables.unmodifiableIterable(conclusions));
    }

    /**
     * <p>
     * Test method for {@link Conclusions#conclude(Iterable)}
     * </p>
     * <p>
     * Ensures that a filled iterable is accepted.
     * </p>
     */
    @Test
    @SuppressWarnings(CompilerWarnings.UNCHECKED)
    public void shouldAcceptIterable() {
        final Collection<Conclusion<String>> conclusions = Lists.newArrayList();
        conclusions.add(Mockito.mock(Conclusion.class));

        final Conclusion<String> composite = Conclusions.conclude(Iterables.unmodifiableIterable(conclusions));

        Assert.assertThat(composite, Is.is(IsNull.notNullValue()));
    }

    /**
     * <p>
     * Test method for {@link Conclusions#conclude(Iterator)}
     * </p>
     * <p>
     * Ensures that passing in a <code>null</code> iterator is not allowed.
     * </p>
     */
    @Test
    public void shouldNotAllowNullIterator() {
        final Iterator<Conclusion<String>> conclusions = null;

        thrown.expect(NullPointerException.class);

        Conclusions.conclude(conclusions);
    }

    /**
     * <p>
     * Test method for {@link Conclusions#conclude(Iterator)}
     * </p>
     * <p>
     * Ensures that passing in an empty iterator is not allowed.
     * </p>
     */
    @Test
    public void shouldNotAllowEmptyIterator() {
        final Collection<Conclusion<String>> conclusions = Lists.newArrayList();

        thrown.expect(IllegalArgumentException.class);

        Conclusions.conclude(conclusions.iterator());
    }

    /**
     * <p>
     * Test method for {@link Conclusions#conclude(Iterator)}
     * </p>
     * <p>
     * Ensures that a filled iterator is accepted.
     * </p>
     */
    @Test
    @SuppressWarnings(CompilerWarnings.UNCHECKED)
    public void shouldAcceptIterator() {
        final Collection<Conclusion<String>> conclusions = Lists.newArrayList();
        final Conclusion<String> conclusion1 = Mockito.mock(Conclusion.class);
        final Conclusion<String> conclusion2 = Mockito.mock(Conclusion.class);
        conclusions.add(conclusion1);
        conclusions.add(conclusion2);

        final Conclusion<String> composite = Conclusions.conclude(conclusions.iterator());

        Assert.assertThat(composite, Is.is(IsNull.notNullValue()));
    }

    /**
     * <p>
     * Test method for {@link Conclusions#conclude(Conclusion, Conclusion)}
     * </p>
     * <p>
     * Ensures that the returned object is not <code>null</code> when passing in a multiple conclusions.
     * </p>
     */
    @Test
    @SuppressWarnings(CompilerWarnings.UNCHECKED)
    public void shouldCreateCompositionFromMultipleConclusions() {
        final Conclusion<String> conclusion1 = Mockito.mock(Conclusion.class);
        final Conclusion<String> conclusion2 = Mockito.mock(Conclusion.class);

        final Conclusion<String> composition = Conclusions.conclude(conclusion1, conclusion2);

        Assert.assertThat(composition, Is.is(IsNull.notNullValue()));
    }

    /**
     * <p>
     * Test method for {@link Conclusions#conclude(Conclusion, Conclusion)}
     * </p>
     * <p>
     * Ensures that the first conclusion is not allowed to be <code>null</code>.
     * </p>
     */
    @Test
    @SuppressWarnings(CompilerWarnings.UNCHECKED)
    public void shouldNotAllowNullFirstConclusions() {
        final Conclusion<String> conclusion = Mockito.mock(Conclusion.class);

        thrown.expect(NullPointerException.class);

        Conclusions.conclude(null, conclusion);
    }

    /**
     * <p>
     * Test method for {@link Conclusions#conclude(Conclusion, Conclusion)}
     * </p>
     * <p>
     * Ensures that the first conclusion is not allowed to be <code>null</code>.
     * </p>
     */
    @Test
    @SuppressWarnings(CompilerWarnings.UNCHECKED)
    public void shouldNotAllowNullSecondConclusions() {
        final Conclusion<String> conclusion = Mockito.mock(Conclusion.class);

        thrown.expect(NullPointerException.class);

        Conclusions.conclude(conclusion, null);
    }

    /**
     * <p>
     * Test method for {@link Conclusions Conclusions()}
     * </p>
     * <p>
     * Ensures that the constructor is not accessible from the outside.
     * </p>
     */
    @Test
    public void shouldNotBeInvocable() {
        final Class<?> clazz = Conclusions.class;

        final Constructor<?>[] constructors = clazz.getDeclaredConstructors();

        for (final Constructor<?> constructor : constructors) {
            Assert.assertFalse(constructor.isAccessible());
        }
    }

    /**
     * <p>
     * Test method for {@link Conclusions Conclusions()}
     * </p>
     * <p>
     * Ensures that the constructor is accessible via reflection.
     * </p>
     * 
     * @throws Exception
     *             When no new instance can be created.
     */
    @Test
    public void shouldBeInvocableViaReflection() throws Exception {
        final Class<?> clazz = Conclusions.class;
        final Constructor<?> constructor = clazz.getDeclaredConstructors()[0];

        constructor.setAccessible(true);
        final Object instance = constructor.newInstance((Object[]) null);

        Assert.assertThat(instance, Is.is(IsNull.notNullValue()));
    }

}
