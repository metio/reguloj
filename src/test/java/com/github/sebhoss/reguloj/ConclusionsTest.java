/*
 * Project: viaRules
 * Package: com.github.sebhoss.reguloj
 * File   : ConclusionsTest.java
 * Created: Nov 10, 2010 - 5:55:55 PM
 *
 *
 * Copyright 2010 viadee IT Unternehmensberatung GmbH
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package com.github.sebhoss.reguloj;

import java.lang.reflect.Constructor;
import java.util.Collection;
import java.util.Iterator;

import org.hamcrest.core.Is;
import org.hamcrest.core.IsNull;
import org.junit.Assert;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mockito;

import com.github.sebhoss.reguloj.Conclusion;
import com.github.sebhoss.reguloj.Conclusions;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

/**
 * <p>Test cases for the {@link Conclusions} utility class.</p>
 *
 * @author  Sebastian Hoß (sebastian.hoss@viadee.de)
 * @see     Conclusions
 * @since   1.0.0
 */
@SuppressWarnings("static-method")
public final class ConclusionsTest {

    // * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
    // *                                                  ATTRIBUTES                                                 *
    // * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *

    /** Checks expected exception inside single test cases. */
    @org.junit.Rule
    public ExpectedException thrown = ExpectedException.none();

    // * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
    // *                                                    TESTS                                                    *
    // * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *

    /**
     * <p>Test method for {@link Conclusions#conclude(Collection)}</p>
     * 
     * <p>Ensures that passing in a <code>null</code> collection is not allowed.</p>
     */
    @Test
    public void shouldNotAllowNullCollection() {
        // given
        final Collection<Conclusion<String>> conclusions = null;

        // when
        this.thrown.expect(NullPointerException.class);

        // then
        Conclusions.conclude(conclusions);
    }

    /**
     * <p>Test method for {@link Conclusions#conclude(Collection)}</p>
     * 
     * <p>Ensures that passing in an empty collection is not allowed.</p>
     */
    @Test
    public void shouldNotAllowEmptyCollection() {
        // given
        final Collection<Conclusion<String>> conclusions = Lists.newArrayList();

        // when
        this.thrown.expect(IllegalArgumentException.class);

        // then
        Conclusions.conclude(conclusions);
    }

    /**
     * <p>Test method for {@link Conclusions#conclude(Collection)}</p>
     * 
     * <p>Ensures that a filled collection is accepted.</p>
     */
    @Test
    public void shouldAcceptCollection() {
        // given
        final Collection<Conclusion<String>> conclusions = Lists.newArrayList();
        conclusions.add(Mockito.mock(Conclusion.class));

        // when
        final Conclusion<String> composite = Conclusions.conclude(conclusions);

        // then
        Assert.assertThat(composite, Is.is(IsNull.notNullValue()));
    }

    /**
     * <p>Test method for {@link Conclusions#conclude(Iterable)}</p>
     * 
     * <p>Ensures that passing in a <code>null</code> iterable is not allowed.</p>
     */
    @Test
    public void shouldNotAllowNullIterable() {
        // given
        final Collection<Conclusion<String>> conclusions = null;

        // when
        this.thrown.expect(NullPointerException.class);

        // then
        Conclusions.conclude(Iterables.unmodifiableIterable(conclusions));
    }

    /**
     * <p>Test method for {@link Conclusions#conclude(Iterable)}</p>
     * 
     * <p>Ensures that passing in an empty iterable is not allowed.</p>
     */
    @Test
    public void shouldNotAllowEmptyIterable() {
        // given
        final Collection<Conclusion<String>> conclusions = Lists.newArrayList();

        // when
        this.thrown.expect(IllegalArgumentException.class);

        // then
        Conclusions.conclude(Iterables.unmodifiableIterable(conclusions));
    }

    /**
     * <p>Test method for {@link Conclusions#conclude(Iterable)}</p>
     * 
     * <p>Ensures that a filled iterable is accepted.</p>
     */
    @Test
    public void shouldAcceptIterable() {
        // given
        final Collection<Conclusion<String>> conclusions = Lists.newArrayList();
        conclusions.add(Mockito.mock(Conclusion.class));

        // when
        final Conclusion<String> composite = Conclusions.conclude(Iterables.unmodifiableIterable(conclusions));

        // then
        Assert.assertThat(composite, Is.is(IsNull.notNullValue()));
    }

    /**
     * <p>Test method for {@link Conclusions#conclude(Iterator)}</p>
     * 
     * <p>Ensures that passing in a <code>null</code> iterator is not allowed.</p>
     */
    @Test
    public void shouldNotAllowNullIterator() {
        // given
        final Iterator<Conclusion<String>> conclusions = null;

        // when
        this.thrown.expect(NullPointerException.class);

        // then
        Conclusions.conclude(conclusions);
    }

    /**
     * <p>Test method for {@link Conclusions#conclude(Iterator)}</p>
     * 
     * <p>Ensures that passing in an empty iterator is not allowed.</p>
     */
    @Test
    public void shouldNotAllowEmptyIterator() {
        // given
        final Collection<Conclusion<String>> conclusions = Lists.newArrayList();

        // when
        this.thrown.expect(IllegalArgumentException.class);

        // then
        Conclusions.conclude(conclusions.iterator());
    }

    /**
     * <p>Test method for {@link Conclusions#conclude(Iterator)}</p>
     * 
     * <p>Ensures that a filled iterator is accepted.</p>
     */
    @Test
    public void shouldAcceptIterator() {
        // given
        final Collection<Conclusion<String>> conclusions = Lists.newArrayList();
        final Conclusion<String> conclusion1 = Mockito.mock(Conclusion.class);
        final Conclusion<String> conclusion2 = Mockito.mock(Conclusion.class);
        conclusions.add(conclusion1);
        conclusions.add(conclusion2);

        // when
        final Conclusion<String> composite = Conclusions.conclude(conclusions.iterator());

        // then
        Assert.assertThat(composite, Is.is(IsNull.notNullValue()));
    }

    /**
     * <p>Test method for {@link Conclusions#conclude(Conclusion, Conclusion)}</p>
     * 
     * <p>Ensures that the returned object is not <code>null</code> when passing in a multiple conclusions.</p>
     */
    @Test
    public void shouldCreateCompositionFromMultipleConclusions() {
        // given
        final Conclusion<String> conclusion1 = Mockito.mock(Conclusion.class);
        final Conclusion<String> conclusion2 = Mockito.mock(Conclusion.class);

        // when
        final Conclusion<String> composition = Conclusions.conclude(conclusion1, conclusion2);

        // then
        Assert.assertThat(composition, Is.is(IsNull.notNullValue()));
    }

    /**
     * <p>Test method for {@link Conclusions#conclude(Conclusion, Conclusion)}</p>
     * 
     * <p>Ensures that the first conclusion is not allowed to be <code>null</code>.</p>
     */
    @Test
    public void shouldNotAllowNullFirstConclusions() {
        // given
        final Conclusion<String> conclusion = Mockito.mock(Conclusion.class);

        // when
        this.thrown.expect(NullPointerException.class);

        // then
        Conclusions.conclude(null, conclusion);
    }

    /**
     * <p>Test method for {@link Conclusions#conclude(Conclusion, Conclusion)}</p>
     * 
     * <p>Ensures that the first conclusion is not allowed to be <code>null</code>.</p>
     */
    @Test
    public void shouldNotAllowNullSecondConclusions() {
        // given
        final Conclusion<String> conclusion = Mockito.mock(Conclusion.class);

        // when
        this.thrown.expect(NullPointerException.class);

        // then
        Conclusions.conclude(conclusion, null);
    }

    /**
     * <p>Test method for {@link Conclusions Conclusions()}</p>
     * 
     * <p>Ensures that the constructor is not accessible from the outside.</p>
     */
    @Test
    public void shouldNotBeInvocable() {
        // given
        final Class<?> clazz = Conclusions.class;

        // when
        final Constructor<?>[] constructors = clazz.getDeclaredConstructors();

        // then
        for (final Constructor<?> constructor : constructors) {
            Assert.assertFalse(constructor.isAccessible());
        }
    }

    /**
     * <p>Test method for {@link Conclusions Conclusions()}</p>
     * 
     * <p>Ensures that the constructor is accessible via reflection.</p>
     * 
     * @throws Exception    When no new instance can be created.
     */
    @Test
    public void shouldBeInvocableViaReflection() throws Exception {
        // given
        final Class<?> clazz = Conclusions.class;
        final Constructor<?> constructor = clazz.getDeclaredConstructors()[0];

        // when
        constructor.setAccessible(true);
        final Object instance = constructor.newInstance((Object[]) null);

        // then
        Assert.assertThat(instance, Is.is(IsNull.notNullValue()));
    }

}
