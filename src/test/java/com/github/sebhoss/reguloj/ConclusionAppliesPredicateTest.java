package com.github.sebhoss.reguloj;

import com.github.sebhoss.common.annotation.CompilerWarnings;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

/**
 * Test cases for ConclusionAppliesPredicate.
 */
@SuppressWarnings({ CompilerWarnings.BOXING, CompilerWarnings.NLS, CompilerWarnings.NULL,
        CompilerWarnings.UNCHECKED })
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
        Mockito.when(conclusion.apply("test")).thenReturn(Boolean.TRUE);

        final boolean applied = predicate.apply(conclusion);

        Assert.assertTrue(applied);
    }

    /**
     * Ensures that a <code>true</code> is returned when the given conclusion applies.
     */
    @Test
    public void shouldReturnFalseOnNonAppliedConclusion() {
        Mockito.when(conclusion.apply("test")).thenReturn(Boolean.FALSE);

        final boolean applied = predicate.apply(conclusion);

        Assert.assertFalse(applied);
    }

}
