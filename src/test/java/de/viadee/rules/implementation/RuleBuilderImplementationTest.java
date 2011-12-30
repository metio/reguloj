/*
 * Project: viaRules
 * Package: de.viadee.rules.implementation
 * File   : RuleBuilderImplementationTest.java
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

import org.hamcrest.core.Is;
import org.hamcrest.core.IsNull;
import org.junit.Assert;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mockito;

import com.google.common.base.Predicate;

import de.viadee.rules.Conclusion;
import de.viadee.rules.InferenceContext;
import de.viadee.rules.Rule;
import de.viadee.rules.RuleBuilder;

/**
 * <p>Test cases for the {@link RuleBuilderImplementation}.</p>
 *
 * @author  Sebastian Hoß (sebastian.hoss@viadee.de)
 * @see     RuleBuilderImplementation
 * @since   1.0.0
 */
@SuppressWarnings("static-method")
public final class RuleBuilderImplementationTest {

    // * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
    // *                                                  CONSTANTS                                                  *
    // * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *

    /** Constant name for all rules inside this test. */
    private static final String NAME   = "test rule";             //$NON-NLS-1$

    // * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
    // *                                                  ATTRIBUTES                                                 *
    // * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *

    /** Checks expected exception inside single test cases. */
    @org.junit.Rule
    public ExpectedException    thrown = ExpectedException.none();

    // * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
    // *                                                    TESTS                                                    *
    // * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *

    /**
     * <p>Test method for {@link RuleBuilderImplementation#then(Conclusion)}</p>
     * 
     * <p>Ensures that rules can be created with a valid RuleBuilderImplementation.</p>
     */
    @Test
    public void shouldCreateRuleIfAllValuesAreSet() {
        // given
        final RuleBuilder<InferenceContext<Object>> builder = new RuleBuilderImplementation<InferenceContext<Object>>();
        builder.called(RuleBuilderImplementationTest.NAME).when(Mockito.mock(Predicate.class));

        // when
        final Rule<InferenceContext<Object>> rule = builder.then(Mockito.mock(Conclusion.class));

        // then
        Assert.assertThat(rule, Is.is(IsNull.notNullValue()));
    }

    /**
     * <p>Test method for {@link RuleBuilderImplementation#when(Predicate)}</p>
     * 
     * <p>Ensures that no <code>null</code> premise can be used.</p>
     */
    @Test
    public void shouldNotAcceptNullPredicate() {
        // given
        final RuleBuilder<InferenceContext<Object>> builder = new RuleBuilderImplementation<InferenceContext<Object>>();

        // when
        this.thrown.expect(NullPointerException.class);

        // then
        builder.when(null);
    }

    /**
     * <p>Test method for {@link RuleBuilderImplementation#then(Conclusion)}</p>
     * 
     * <p>Ensures that no <code>null</code> conclusion can be used.</p>
     */
    @Test
    public void shouldNotAcceptNullConclusion() {
        // given
        final RuleBuilder<InferenceContext<Object>> builder = new RuleBuilderImplementation<InferenceContext<Object>>();

        // when
        this.thrown.expect(NullPointerException.class);

        // then
        builder.then(null);
    }

    /**
     * <p>Test method for {@link RuleBuilderImplementation#called(String)}</p>
     * 
     * <p>Ensures that no <code>null</code> string can be used.</p>
     */
    @Test
    public void shouldNotAcceptNullName() {
        // given
        final RuleBuilder<InferenceContext<Object>> builder = new RuleBuilderImplementation<InferenceContext<Object>>();

        // when
        this.thrown.expect(NullPointerException.class);

        // then
        builder.called(null);
    }
}
