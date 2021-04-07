package wtf.metio.reguloj;

import java.util.function.Consumer;
import java.util.function.Predicate;

final class RuleBuilderImplementation<CONTEXT extends Context<?>> implements RuleBuilder<CONTEXT> {

  private String name;
  private Predicate<CONTEXT> predicate;

  @Override
  public RuleBuilder<CONTEXT> called(final String newName) {
    name = newName;
    return this;
  }

  @Override
  public RuleBuilder<CONTEXT> when(final Predicate<CONTEXT> newPredicate) {
    predicate = newPredicate;
    return this;
  }

  @Override
  public Rule<CONTEXT> then(final Consumer<CONTEXT> consumer) {
    return new RuleImplementation<>(name, predicate, consumer);
  }

}
