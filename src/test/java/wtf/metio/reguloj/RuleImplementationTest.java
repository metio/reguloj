package wtf.metio.reguloj;

import com.google.common.truth.Truth;
import org.junit.Before;
import org.junit.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;

import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * Test cases for the RuleImplementation.
 */
public final class RuleImplementationTest {

    /**
     * Constant name for all rules inside this test.
     */
    private static final String NAME = "test rule"; //$NON-NLS-1$

    private Context<Object> context;
    private Predicate<Context<Object>> predicate;
    private Consumer<Context<Object>> consumer;

    /**
     * Creates rule engine, context and rules.
     */
    @Before
    public void setup() {
        context = Mockito.mock(Context.class);
        predicate = Mockito.mock(Predicate.class);
        consumer = Mockito.mock(Consumer.class);
    }

    /**
     * Ensures that a valid rule will be built once all values are given.
     */
    @Test
    public void shouldCreateRuleIfAllValuesAreSet() {
        final Rule<Context<Object>> rule = new RuleImplementation<>(RuleImplementationTest.NAME,
                predicate, consumer);

        Truth.assertThat(rule).isNotNull();
    }

    /**
     * Ensures that <code>false</code> is returned if the predicate does not apply.
     */
    @Test
    public void shouldReturnFalseWhenPremiseDoesNotApply() {
        BDDMockito.given(predicate.test(context)).willReturn(Boolean.FALSE);

        final Rule<Context<Object>> rule = new RuleImplementation<>(RuleImplementationTest.NAME,
                predicate, consumer);

        Truth.assertThat(rule.fires(context)).isFalse();
    }

    /**
     * Ensures that <code>true</code> is returned if the predicate does apply.
     */
    @Test
    public void shouldFireWhenPremiseApplies() {
        BDDMockito.given(predicate.test(context)).willReturn(Boolean.TRUE);

        final Rule<Context<Object>> rule = new RuleImplementation<>(RuleImplementationTest.NAME,
                predicate, consumer);

        Truth.assertThat(rule.fires(context)).isTrue();
    }

    /**
     * Ensures that the previously set name is returned.
     */
    @Test
    public void shouldReturnTheSetName() {
        final Rule<Context<Object>> rule = new RuleImplementation<>(RuleImplementationTest.NAME,
                predicate, consumer);

        Truth.assertThat(rule.getName()).isEqualTo(RuleImplementationTest.NAME);
    }

    /**
     * Ensures that equals is reflexive
     */
    @Test
    public void equalsIsReflexive() {
        final Rule<Context<Object>> rule = new RuleImplementation<>(RuleImplementationTest.NAME,
                predicate, consumer);

        Truth.assertThat(rule).isEqualTo(rule);
    }

    /**
     * Ensures that equals is symmetric
     */
    @Test
    public void equalsIsSymmetric() {
        final Rule<Context<Object>> rule1 = new RuleImplementation<>(RuleImplementationTest.NAME,
                predicate, consumer);
        final Rule<Context<Object>> rule2 = new RuleImplementation<>(RuleImplementationTest.NAME,
                predicate, consumer);

        Truth.assertThat(rule1).isEqualTo(rule2);
        Truth.assertThat(rule2).isEqualTo(rule1);
    }

    /**
     * Ensures that equals is transitive
     */
    @Test
    public void equalsIsTransitive() {
        final Rule<Context<Object>> rule1 = new RuleImplementation<>(RuleImplementationTest.NAME,
                predicate, consumer);
        final Rule<Context<Object>> rule2 = new RuleImplementation<>(RuleImplementationTest.NAME,
                predicate, consumer);
        final Rule<Context<Object>> rule3 = new RuleImplementation<>(RuleImplementationTest.NAME,
                predicate, consumer);

        Truth.assertThat(rule1).isEqualTo(rule2);
        Truth.assertThat(rule2).isEqualTo(rule3);
        Truth.assertThat(rule3).isEqualTo(rule1);
    }

    /**
     * Ensures that equals is returns <code>false</code> on <code>null</code> values.
     */
    @Test
    public void equalsReturnFalseOnNull() {
        final Rule<Context<Object>> rule = new RuleImplementation<>(RuleImplementationTest.NAME,
                predicate, consumer);

        Truth.assertThat(rule).isNotEqualTo(null);
    }

    /**
     * Ensures that equals is returns <code>false</code> on <code>null</code> values.
     */
    @Test
    public void equalsReturnFalseOnWrongClass() {
        final Rule<Context<Object>> rule = new RuleImplementation<>(RuleImplementationTest.NAME,
                predicate, consumer);

        Truth.assertThat(rule).isNotEqualTo(""); //$NON-NLS-1$
    }

    /**
     * Ensures that equals works with identical instances.
     */
    @Test
    public void equalsWorks() {
        final Rule<Context<Object>> rule1 = new RuleImplementation<>(RuleImplementationTest.NAME,
                predicate, consumer);
        final Rule<Context<Object>> rule2 = rule1;

        Truth.assertThat(rule1).isEqualTo(rule2);
    }

    /**
     * Ensures that equals works with different names
     */
    @Test
    public void equalsWorksWithDifferentNames() {
        final Rule<Context<Object>> rule1 = new RuleImplementation<>(RuleImplementationTest.NAME,
                predicate, consumer);
        final Rule<Context<Object>> rule2 = new RuleImplementation<>("rule2", predicate, consumer); //$NON-NLS-1$

        Truth.assertThat(rule1).isNotEqualTo(rule2);
    }

    /**
     * Ensures that equals works with different predicates.
     */
    @Test
    public void equalsWorksWithDifferentPremises() {
        final Predicate<Context<Object>> predicate2 = Mockito.mock(Predicate.class);

        final Rule<Context<Object>> rule1 = new RuleImplementation<>(RuleImplementationTest.NAME,
                predicate,
                consumer);
        final Rule<Context<Object>> rule2 = new RuleImplementation<>(RuleImplementationTest.NAME,
                predicate2,
                consumer);

        Truth.assertThat(rule1).isNotEqualTo(rule2);
    }

    /**
     * Ensures that equals works with different conclusions.
     */
    @Test
    public void equalsWorksWithDifferentConclusions() {
        final Consumer<Context<Object>> consumer2 = Mockito.mock(Consumer.class);

        final Rule<Context<Object>> rule1 = new RuleImplementation<>(RuleImplementationTest.NAME,
                predicate,
                consumer);
        final Rule<Context<Object>> rule2 = new RuleImplementation<>(RuleImplementationTest.NAME,
                predicate,
                consumer2);

        Truth.assertThat(rule1).isNotEqualTo(rule2);
    }

    /**
     * Ensures that hashCode() is consistent with equals().
     */
    @Test
    public void hashCodeIsConsistentWithEquals() {
        final Rule<Context<Object>> rule1 = new RuleImplementation<>(RuleImplementationTest.NAME,
                predicate, consumer);
        final Rule<Context<Object>> rule2 = new RuleImplementation<>(RuleImplementationTest.NAME,
                predicate, consumer);

        Truth.assertThat(rule1).isEqualTo(rule2);
        Truth.assertThat(rule1.hashCode()).isEqualTo(rule2.hashCode());
    }

}
