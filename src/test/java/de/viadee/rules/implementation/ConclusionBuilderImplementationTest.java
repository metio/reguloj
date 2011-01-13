/*
 * Project: rules
 * Package: de.viadee.rules.implementation
 * File   : ConclusionBuilderImplementationTest.java
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
package de.viadee.rules.implementation;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import de.viadee.rules.Command;
import de.viadee.rules.Conclusion;
import de.viadee.rules.ConclusionBuilder;

/**
 * <p>Test cases for the {@link ConclusionBuilderImplementation}.</p>
 * 
 * @author  Sebastian Hoß (sebastian.hoss@viadee.de)
 * @see     ConclusionBuilderImplementation
 * @since   1.0.0
 */
@SuppressWarnings("unchecked")
public class ConclusionBuilderImplementationTest {

    // * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
    // *                                                     ATTRIBUTES                                                    *
    // * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *

    /** Checks expected exception inside single test cases. */
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    // * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
    // *                                                       TESTS                                                       *
    // * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *

    /**
     * <p>Test method for {@link ConclusionBuilderImplementation#ConclusionBuilderImplementation(java.util.Collection)}</p>
     * 
     * <p>Ensures that this class can be instantiated with an non-<code>null</code> parameter.</p>
     */
    @Test
    public void shouldBeInstantiatedWithNonNullParameter() {
        // given
        final Collection<Command<Object>> commands = new ArrayList<Command<Object>>();

        // when
        final ConclusionBuilder<Object> builder = new ConclusionBuilderImplementation<Object>(commands);

        // then
        assertThat(builder, is(notNullValue()));
    }

    /**
     * <p>Test method for {@link ConclusionBuilderImplementation#ConclusionBuilderImplementation(java.util.Collection)}</p>
     * 
     * <p>Ensures that this class can not be instantiated with a <code>null</code> parameter.</p>
     */
    @Test
    public void shouldNotAcceptNull() {
        // given
        this.thrown.expect(NullPointerException.class);

        // when
        final Collection<Command<Object>> commands = null;
        final ConclusionBuilder<?> builder = new ConclusionBuilderImplementation<Object>(commands);

        // then
        assertThat(builder, is(nullValue()));
    }

    /**
     * <p>Test method for {@link ConclusionBuilderImplementation#get()}</p>
     * 
     * <p>Ensures that the method returns a non-<code>null</code> value.</p>
     */
    @Test
    public void shouldGetNonNullValue() {
        // given
        final Collection<Command<Object>> commands = mock(Collection.class);
        final ConclusionBuilder<Object> builder = new ConclusionBuilderImplementation<Object>(commands);

        // when
        final Conclusion<Object> conclusion = builder.get();

        // then
        assertThat(conclusion, is(notNullValue()));
    }

    /**
     * <p>Test method for {@link ConclusionBuilderImplementation#and(Command)}</p>
     * 
     * <p>Ensures that the method returns the calling builder.</p>
     */
    @Test
    public void shouldSupportFluentInterface() {
        // given
        final Command<Object> command = mock(Command.class);
        final Collection<Command<Object>> commands = mock(Collection.class);

        // when
        final ConclusionBuilder<Object> builder = new ConclusionBuilderImplementation<Object>(commands);
        final ConclusionBuilder<Object> other = builder.and(command);

        // then
        assertThat(other, is(notNullValue()));
        assertThat(other, is(builder));
    }

}
