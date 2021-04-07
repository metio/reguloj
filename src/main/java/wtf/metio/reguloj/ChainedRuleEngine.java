package wtf.metio.reguloj;

import java.util.Collection;

final class ChainedRuleEngine<CONTEXT extends Context<?>> extends AbstractRuleEngine<CONTEXT> {

  @Override
  public void infer(final Collection<Rule<CONTEXT>> rules, final CONTEXT context) {
    while (analyze(rules, context)) {
      rules.forEach(rule -> rule.run(context));
    }
  }

}
