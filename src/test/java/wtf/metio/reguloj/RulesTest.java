package wtf.metio.reguloj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.util.function.Consumer;
import java.util.function.Predicate;

final class RulesTest {

    private static final String NAME = "test rule";

    @Test
    void shouldCreateBuilder() {
        Assertions.assertNotNull(Rules.rule());
    }

    @Test
    void shouldCreateRule() {
        final RuleBuilder<Context<Object>> builder = Rules.<Context<Object>>rule();
        builder.called(RulesTest.NAME).when(Mockito.mock(Predicate.class));

        final Rule<Context<Object>> rule = builder.then(Mockito.mock(Consumer.class));

        Truth.assertThat(rule).isNotNull();
    }

    @Test
    void shouldNotBeInvocable() {
        final Class<?> clazz = Rules.class;

        final Constructor<?>[] constructors = clazz.getDeclaredConstructors();

        for (final Constructor<?> constructor : constructors) {
            Truth.assertThat(constructor).isFalse();
        }
    }

    @Test
    void shouldBeInvocableViaReflection() throws Exception {
        final Class<?> clazz = Rules.class;
        final Constructor<?> constructor = clazz.getDeclaredConstructors()[0];

        constructor.setAccessible(true);
        final Object instance = constructor.newInstance((Object[]) null);

        Assert.assertNotNull(instance);
    }

}
