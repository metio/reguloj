package wtf.metio.reguloj;

import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * Implementation of the {@link Rule} interface.
 *
 * @param <CONTEXT>
 */
final record RuleImplementation<CONTEXT extends Context<?>>(
    String name,
    Predicate<CONTEXT> predicate,
    Consumer<CONTEXT> consumer) implements Rule<CONTEXT> {

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

}
