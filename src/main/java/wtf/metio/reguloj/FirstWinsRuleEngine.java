package wtf.metio.reguloj;

import java.util.Collection;

final class FirstWinsRuleEngine<CONTEXT extends Context<?>> extends AbstractRuleEngine<CONTEXT> {

    @Override
    public void infer(final Collection<Rule<CONTEXT>> rules, final CONTEXT context) {
        rules.stream().filter(rule -> rule.fires(context)).limit(1).forEach(rule -> rule.run(context));
    }

}
