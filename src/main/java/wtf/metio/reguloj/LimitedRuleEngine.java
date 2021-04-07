package wtf.metio.reguloj;

import java.util.Collection;

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
