/*
 * This file is part of reguloj. It is subject to the license terms in the LICENSE file found in the top-level
 * directory of this distribution and at http://creativecommons.org/publicdomain/zero/1.0/. No part of reguloj,
 * including this file, may be copied, modified, propagated, or distributed except according to the terms contained
 * in the LICENSE file.
 */

package wtf.metio.reguloj;

import java.util.Collection;

/**
 * Abstract rule engine which provides an implementation for the {@link #analyze(Collection, Context)} method. Therefore
 * implementors only have to write the {@link #infer(Collection, Context)} method.
 *
 * @param <CONTEXT> The context type.
 */
public abstract class AbstractRuleEngine<CONTEXT extends Context<?>> implements RuleEngine<CONTEXT> {

  /**
   * Checks whether a single rule fires for the given context.
   *
   * @param rules   The rules to check.
   * @param context The context to use.
   * @return true if any rule fired, false otherwise.
   */
  @Override
  public final boolean analyze(final Collection<Rule<CONTEXT>> rules, final CONTEXT context) {
    return rules.stream().anyMatch(rule -> rule.fires(context));
  }

}
