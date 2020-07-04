package wtf.metio.reguloj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;

import java.util.function.Consumer;
import java.util.function.Predicate;

final class RuleImplementationTest {

    private static final String NAME = "test rule";

    private Context<Object> context;
    private Predicate<Context<Object>> predicate;
    private Consumer<Context<Object>> consumer;

    @BeforeEach
    void setup() {
        context = Mockito.mock(Context.class);
        predicate = Mockito.mock(Predicate.class);
        consumer = Mockito.mock(Consumer.class);
    }

    @Test
    void shouldCreateRuleIfAllValuesAreSet() {
        final var rule = new RuleImplementation<>(NAME, predicate, consumer);
        Assertions.assertNotNull(rule);
    }

    @Test
    void shouldReturnFalseWhenPremiseDoesNotApply() {
        BDDMockito.given(predicate.test(context)).willReturn(Boolean.FALSE);
        final var rule = new RuleImplementation<>(NAME, predicate, consumer);
        Assertions.assertFalse(rule.fires(context));
    }

    @Test
    void shouldFireWhenPremiseApplies() {
        BDDMockito.given(predicate.test(context)).willReturn(Boolean.TRUE);
        final var rule = new RuleImplementation<>(NAME, predicate, consumer);
        Assertions.assertTrue(rule.fires(context));
    }

    @Test
    void shouldReturnTheSetName() {
        final var rule = new RuleImplementation<>(NAME, predicate, consumer);
        Assertions.assertEquals(NAME, rule.getName());
    }

    @Test
    void equalsIsReflexive() {
        final var rule = new RuleImplementation<>(NAME, predicate, consumer);
        Assertions.assertEquals(rule, rule);
    }

    @Test
    void equalsIsSymmetric() {
        final var rule1 = new RuleImplementation<>(NAME, predicate, consumer);
        final var rule2 = new RuleImplementation<>(NAME, predicate, consumer);
        Assertions.assertEquals(rule1, rule2);
        Assertions.assertEquals(rule2, rule1);
    }

    @Test
    void equalsIsTransitive() {
        final var rule1 = new RuleImplementation<>(NAME, predicate, consumer);
        final var rule2 = new RuleImplementation<>(NAME, predicate, consumer);
        final var rule3 = new RuleImplementation<>(NAME, predicate, consumer);
        Assertions.assertEquals(rule1, rule2);
        Assertions.assertEquals(rule2, rule3);
        Assertions.assertEquals(rule3, rule1);
    }

    @Test
    void equalsReturnFalseOnNull() {
        final var rule = new RuleImplementation<>(NAME, predicate, consumer);
        Assertions.assertNotEquals(null, rule);
    }

    @Test
    void equalsReturnFalseOnWrongClass() {
        final var rule = new RuleImplementation<>(NAME, predicate, consumer);
        Assertions.assertNotEquals("", rule);
    }

    @Test
    void equalsWorks() {
        final var rule1 = new RuleImplementation<>(NAME, predicate, consumer);
        final var rule2 = rule1;
        Assertions.assertEquals(rule1, rule2);
    }

    @Test
    void equalsWorksWithDifferentNames() {
        final var rule1 = new RuleImplementation<>(NAME, predicate, consumer);
        final var rule2 = new RuleImplementation<>("rule2", predicate, consumer); //$NON-NLS-1$
        Assertions.assertNotEquals(rule1, rule2);
    }

    @Test
    void equalsWorksWithDifferentPremises() {
        final var predicate2 = Mockito.mock(Predicate.class);
        final var rule1 = new RuleImplementation<>(NAME, predicate, consumer);
        final var rule2 = new RuleImplementation<>(NAME, predicate2, consumer);
        Assertions.assertNotEquals(rule1, rule2);
    }

    @Test
    void equalsWorksWithDifferentConclusions() {
        final var consumer2 = Mockito.mock(Consumer.class);
        final var rule1 = new RuleImplementation<>(NAME, predicate, consumer);
        final var rule2 = new RuleImplementation<>(NAME, predicate, consumer2);
        Assertions.assertNotEquals(rule1, rule2);
    }

    @Test
    void hashCodeIsConsistentWithEquals() {
        final var rule1 = new RuleImplementation<>(NAME, predicate, consumer);
        final var rule2 = new RuleImplementation<>(NAME, predicate, consumer);
        Assertions.assertEquals(rule1, rule2);
        Assertions.assertEquals(rule1.hashCode(), rule2.hashCode());
    }

}
