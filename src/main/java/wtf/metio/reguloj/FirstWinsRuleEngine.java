/*
 * This file is part of reguloj. It is subject to the license terms in the LICENSE file found in the top-level
 * directory of this distribution and at http://creativecommons.org/publicdomain/zero/1.0/. No part of reguloj,
 * including this file, may be copied, modified, propagated, or distributed except according to the terms contained
 * in the LICENSE file.
 */

package wtf.metio.reguloj;

import java.util.Collection;

/**
 * Runs the first rule that fires.
 *
 * @param <CONTEXT> The type of the context.
 * @see ChainedRuleEngine
 * @see LimitedRuleEngine
 */
final class FirstWinsRuleEngine<CONTEXT extends Context<?>> extends AbstractRuleEngine<CONTEXT> {

  @Override
  public void infer(final Collection<Rule<CONTEXT>> rules, final CONTEXT context) {
    rules.stream()
        .filter(rule -> rule.fires(context))
        .findFirst()
        .ifPresent(rule -> rule.run(context));
  }

}
