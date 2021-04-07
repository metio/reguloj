/*
 * This file is part of reguloj. It is subject to the license terms in the LICENSE file found in the top-level
 * directory of this distribution and at http://creativecommons.org/publicdomain/zero/1.0/. No part of reguloj,
 * including this file, may be copied, modified, propagated, or distributed except according to the terms contained
 * in the LICENSE file.
 */

package wtf.metio.reguloj;

import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * Plain implementation of the {@link RuleBuilder} interface.
 *
 * @param <CONTEXT> The type of the context.
 */
final class FluentRuleBuilder<CONTEXT extends Context<?>> implements RuleBuilder<CONTEXT> {

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
    return new JavaUtilFunctionRule<>(name, predicate, consumer);
  }

}
