package wtf.metio.reguloj;

import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Predicate;

final class RuleImplementation<CONTEXT extends Context<?>> implements Rule<CONTEXT> {

    private final String name;
    private final Predicate<CONTEXT> predicate;
    private final Consumer<CONTEXT> consumer;

    RuleImplementation(final String name, final Predicate<CONTEXT> predicate, final Consumer<CONTEXT> consumer) {
        this.name = name;
        this.predicate = predicate;
        this.consumer = consumer;
    }

    @Override
    public void run(final CONTEXT context) {
        if (fires(context)) {
            consumer.accept(context);
        }
    }

    @Override
    public boolean fires(final CONTEXT context) {
        return predicate.test(context);
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, predicate, consumer);
    }

    @Override
    public boolean equals(final Object object) {
        if (object instanceof RuleImplementation) {
            final RuleImplementation<?> that = (RuleImplementation<?>) object;

            return Objects.equals(name, that.name) && Objects.equals(predicate, that.predicate)
                    && Objects.equals(consumer, that.consumer);
        }
        return false;
    }

}
