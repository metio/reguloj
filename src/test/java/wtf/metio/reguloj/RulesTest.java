package wtf.metio.reguloj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

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
        final var builder = Rules.<Context<Object>>rule();
        builder.called(RulesTest.NAME).when(Mockito.mock(Predicate.class));
        final var rule = builder.then(Mockito.mock(Consumer.class));
        Assertions.assertNotNull(rule);
    }

    @Test
    void shouldNotBeInvocable() {
        final var clazz = Rules.class;
        final var constructors = clazz.getDeclaredConstructors();
        for (final Constructor<?> constructor : constructors) {
            Assertions.assertFalse(constructor.canAccess(this));
        }
    }

    @Test
    void shouldBeInvocableViaReflection() throws Exception {
        final var clazz = Rules.class;
        final var constructor = clazz.getDeclaredConstructors()[0];
        constructor.setAccessible(true);
        final var instance = constructor.newInstance((Object[]) null);
        Assertions.assertNotNull(instance);
    }

}
