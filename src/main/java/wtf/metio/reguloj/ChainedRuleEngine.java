package wtf.metio.reguloj;

import java.util.Collection;

/**
 * Chained rule engine that repeatedly runs all rules until no rule fires anymore.
 *
 * @param <CONTEXT>
 */
final class ChainedRuleEngine<CONTEXT extends Context<?>> extends AbstractRuleEngine<CONTEXT> {

  @Override
  public void infer(final Collection<Rule<CONTEXT>> rules, final CONTEXT context) {
    while (analyze(rules, context)) {
      rules.stream().filter(rule -> rule.fires(context)).forEach(rule -> rule.run(context));
    }
  }

}
