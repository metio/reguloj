package com.github.sebhoss.reguloj.implementation;

import java.util.Collection;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.BDDMockito;
import org.mockito.Mockito;

import com.github.sebhoss.reguloj.Conclusion;
import com.github.sebhoss.reguloj.implementation.CompositeConclusion;
import com.google.common.collect.Lists;


/**
 * @author  Sebastian Hoß (sebastian.hoss@viadee.de)
 * @since   2.0.0
 */
@SuppressWarnings("static-method")
public class CompositeConclusionTest {

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
     * 
     */
    @SuppressWarnings("unused")
    @Test
    public void shouldNotAllowEmptyCollection() {
        // given
        final Collection<Conclusion<Object>> conclusions = Lists.newArrayList();

        // when
        this.thrown.expect(IllegalArgumentException.class);

        // then
        new CompositeConclusion<Object>(conclusions);
    }

    /**
     * 
     */
    @SuppressWarnings("boxing")
    @Test
    public void shouldCallAllGivenConclusions() {
        // given
        final Object target = new Object();
        final Collection<Conclusion<Object>> conclusions = Lists.newArrayList();
        final Conclusion<Object> conclusion1 = Mockito.mock(Conclusion.class);
        BDDMockito.given(conclusion1.apply(target)).willReturn(true);
        final Conclusion<Object> conclusion2 = Mockito.mock(Conclusion.class);
        BDDMockito.given(conclusion2.apply(target)).willReturn(false);

        conclusions.add(conclusion1);
        conclusions.add(conclusion2);

        // when
        final Conclusion<Object> composite = new CompositeConclusion<Object>(conclusions);

        // then
        Assert.assertTrue(composite.apply(target));
    }

}
