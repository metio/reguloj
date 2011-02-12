/*
 * Project: viaRules-core
 * Package: de.viadee.rules.implementation
 * File   : ConclusionImplementationTest.java
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

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.Test;

import de.viadee.rules.Command;
import de.viadee.rules.Conclusion;
import de.viadee.rules.ConclusionBuilder;
import de.viadee.rules.InferenceContext;

/**
 * <p>Test cases for the {@link ConclusionImplementation}.</p>
 *  
 * @author  Sebastian Hoß (sebastian.hoss@viadee.de)
 * @see     ConclusionImplementation
 * @since   1.0.0
 */
@SuppressWarnings({ "boxing", "unchecked" })
public class ConclusionImplementationTest {

    // TODO: Move setup code to its own method, or use @Mock annotations

    // * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
    // *                                                       TESTS                                                       *
    // * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *

    /**
     * <p>Test method for {@link ConclusionImplementation#apply(Object)}</p>
     *
     * <p>Ensures that <code>false</code> is returned when no command can execute.</p>
     */
    @Test
    public void shouldReturnFalseIfNoCommandCanExecute() {
        // given
        final InferenceContext<Object> context = mock(InferenceContext.class);

        final Command<Object> command = mock(Command.class);
        given(command.execute(context)).willReturn(false);

        final Collection<Command<Object>> commands = new ArrayList<Command<Object>>();
        commands.add(command);

        final ConclusionBuilder<Object> builder = new ConclusionBuilderImplementation<Object>(commands);

        // when
        final Conclusion<Object> conclusion = builder.get();

        // then
        assertFalse(conclusion.apply(context));
    }

    /**
     * <p>Test method for {@link ConclusionImplementation#apply(Object)}</p>
     *
     * <p>Ensures that <code>true</code> is returned when commands can execute.</p>
     */
    @Test
    public void shouldReturnTrueIfCommandsCanExecute() {
        // given
        final InferenceContext<Object> context = mock(InferenceContext.class);

        final Command<Object> command = mock(Command.class);
        given(command.execute(context)).willReturn(true);

        final Collection<Command<Object>> commands = new ArrayList<Command<Object>>();
        commands.add(command);

        final ConclusionBuilder<Object> builder = new ConclusionBuilderImplementation<Object>(commands);

        // when
        final Conclusion<Object> conclusion = builder.get();

        // then
        assertTrue(conclusion.apply(context));
    }

}
