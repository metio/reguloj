/*
 * Project: rules
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

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;

import org.junit.Test;
import org.junit.rules.ExpectedException;

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
public final class RuleBuilderImplementationTest {

    // * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
    // *                                                     CONSTANTS                                                     *
    // * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *

    /** Constant name for all rules inside this test. */
    private static final String NAME   = "test rule";             //$NON-NLS-1$

    // * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
    // *                                                     ATTRIBUTES                                                    *
    // * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *

    /** Checks expected exception inside single test cases. */
    @org.junit.Rule
    public ExpectedException    thrown = ExpectedException.none();

    // * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
    // *                                                       TESTS                                                       *
    // * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *

    /**
     * <p>Test method for {@link RuleBuilderImplementation#get()}</p>
     * 
     * <p>Ensures that no rules can be created with an empty RuleBuilderImplementation.</p>
     */
    @Test
    public void shouldNotReturnRulesAfterCreation() {
        // given
        this.thrown.expect(IllegalStateException.class);

        // when
        final RuleBuilder<InferenceContext<Object>> builder =
                new RuleBuilderImplementation<InferenceContext<Object>>();

        // then
        builder.get();
    }

    /**
     * <p>Test method for {@link RuleBuilderImplementation#get()}</p>
     * 
     * <p>Ensures that no rules can be created with an empty RuleBuilderImplementation.</p>
     */
    @SuppressWarnings("unchecked")
    @Test
    public void shouldCreateRuleIfAllValuesAreSet() {
        // given
        final RuleBuilder<InferenceContext<Object>> builder =
                new RuleBuilderImplementation<InferenceContext<Object>>();
        builder.called(NAME).when(mock(Predicate.class)).then(mock(Conclusion.class));

        // when
        final Rule<InferenceContext<Object>> rule = builder.get();

        // then
        assertThat(rule, is(notNullValue()));
    }

    /**
     * <p>Test method for {@link RuleBuilderImplementation#get()}</p>
     * 
     * <p>Ensures that no rules can be created with only the name set.</p>
     */
    @Test
    public void shouldNotReturnRulesWithOnlyName() {
        // given
        this.thrown.expect(IllegalStateException.class);

        // when
        final RuleBuilder<InferenceContext<Object>> builder =
                new RuleBuilderImplementation<InferenceContext<Object>>();
        builder.called(NAME);

        // then
        builder.get();
    }

    /**
     * <p>Test method for {@link RuleBuilderImplementation#get()}</p>
     * 
     * <p>Ensures that no rules can be created with only the name and the premise set.</p>
     */
    @SuppressWarnings("unchecked")
    @Test
    public void shouldNotReturnRulesWithOnlyNameAndPremise() {
        // given
        this.thrown.expect(IllegalStateException.class);

        // when
        final RuleBuilder<InferenceContext<Object>> builder =
                new RuleBuilderImplementation<InferenceContext<Object>>();
        builder.called(NAME);
        builder.when(mock(Predicate.class));

        // then
        builder.get();
    }

    /**
     * <p>Test method for {@link RuleBuilderImplementation#get()}</p>
     * 
     * <p>Ensures that no rules can be created with only the name and the conclusion set.</p>
     */
    @SuppressWarnings("unchecked")
    @Test
    public void shouldNotReturnRulesWithOnlyNameAndConclusion() {
        // given
        this.thrown.expect(IllegalStateException.class);

        // when
        final RuleBuilder<InferenceContext<Object>> builder =
                new RuleBuilderImplementation<InferenceContext<Object>>();
        builder.called(NAME);
        builder.then(mock(Conclusion.class));

        // then
        builder.get();
    }

    /**
     * <p>Test method for {@link RuleBuilderImplementation#get()}</p>
     * 
     * <p>Ensures that no rules can be created with only the premise and the conclusion set.</p>
     */
    @SuppressWarnings("unchecked")
    @Test
    public void shouldNotReturnRulesWithOnlyPremiseAndConclusion() {
        // given
        this.thrown.expect(IllegalStateException.class);

        // when
        final RuleBuilder<InferenceContext<Object>> builder =
                new RuleBuilderImplementation<InferenceContext<Object>>();
        builder.when(mock(Predicate.class));
        builder.then(mock(Conclusion.class));

        // then
        builder.get();
    }

    /**
     * <p>Test method for {@link RuleBuilderImplementation#when(Predicate)}</p>
     * 
     * <p>Ensures that no <code>null</code> premise can be used.</p>
     */
    @Test
    public void shouldNotAcceptNullPredicate() {
        // given
        this.thrown.expect(NullPointerException.class);

        // when
        final RuleBuilder<InferenceContext<Object>> builder =
                new RuleBuilderImplementation<InferenceContext<Object>>();

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
        this.thrown.expect(NullPointerException.class);

        // when
        final RuleBuilder<InferenceContext<Object>> builder =
                new RuleBuilderImplementation<InferenceContext<Object>>();

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
        this.thrown.expect(NullPointerException.class);

        // when
        final RuleBuilder<InferenceContext<Object>> builder = new RuleBuilderImplementation<InferenceContext<Object>>();

        // then
        builder.called(null);
    }

}
