/*
 * This file is part of reguloj. It is subject to the license terms in the LICENSE file found in the top-level
 * directory of this distribution and at http://creativecommons.org/publicdomain/zero/1.0/. No part of reguloj,
 * including this file, may be copied, modified, propagated, or distributed except according to the terms contained
 * in the LICENSE file.
 */

package wtf.metio.reguloj;

import java.util.Collection;

/**
 * Limits the total number of runs to a user supplied maximum.
 *
 * @param <CONTEXT> The type of the context.
 * @see ChainedRuleEngine
 * @see FirstWinsRuleEngine
 */
final class LimitedRuleEngine<CONTEXT extends Context<?>> extends AbstractRuleEngine<CONTEXT> {

  private final int maximumNumberOfRuns;

  LimitedRuleEngine(final int maximumNumberOfRuns) {
    this.maximumNumberOfRuns = maximumNumberOfRuns;
  }

  @Override
  public void infer(final Collection<Rule<CONTEXT>> rules, final CONTEXT context) {
    int currentRuns = 0;
    while (analyze(rules, context)) {
      rules.forEach(rule -> rule.run(context));

      if (++currentRuns >= maximumNumberOfRuns) {
        break;
      }
    }
  }

}
