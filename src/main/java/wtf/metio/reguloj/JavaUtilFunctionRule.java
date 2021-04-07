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
 * Implementation of the {@link Rule} interface that uses the java.util.function package.
 *
 * @param <CONTEXT>
 */
final record JavaUtilFunctionRule<CONTEXT extends Context<?>>(
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
