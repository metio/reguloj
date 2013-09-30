package com.github.sebhoss.reguloj;

import com.github.sebhoss.common.annotation.CompilerWarnings;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

/**
 * Test cases for ConclusionAppliesPredicate.
 */
@SuppressWarnings({ CompilerWarnings.BOXING, CompilerWarnings.NLS, CompilerWarnings.STATIC_METHOD,
        CompilerWarnings.UNCHECKED })
public class ConclusionAppliesPredicateTest {

    /**
     * Ensures that a <code>true</code> is returned when the given conclusion applies.
     */
    @Test
    public void shouldReturnTrueOnAppliedConclusion() {
        final ConclusionPredicate<String> predicate = new ConclusionAppliesPredicate<>("test");
        final Conclusion<String> conclusion = Mockito.mock(Conclusion.class);
        Mockito.when(conclusion.apply("test")).thenReturn(Boolean.TRUE);

        final boolean applied = predicate.apply(conclusion);

        Assert.assertTrue(applied);
    }

    /**
     * Ensures that a <code>true</code> is returned when the given conclusion applies.
     */
    @Test
    public void shouldReturnFalseOnNonAppliedConclusion() {
        final ConclusionPredicate<String> predicate = new ConclusionAppliesPredicate<>("test");
        final Conclusion<String> conclusion = Mockito.mock(Conclusion.class);
        Mockito.when(conclusion.apply("test")).thenReturn(Boolean.FALSE);

        final boolean applied = predicate.apply(conclusion);

        Assert.assertFalse(applied);
    }

}
