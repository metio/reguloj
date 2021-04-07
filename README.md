# reguloj [![Chat](https://img.shields.io/badge/matrix-%23reguloj:matrix.org-brightgreen.svg?style=social&label=Matrix)](https://matrix.to/#/#reguloj:matrix.org) [![Mailing List](https://img.shields.io/badge/email-reguloj%40metio.groups.io%20-brightgreen.svg?style=social&label=Mail)](https://metio.groups.io/g/reguloj/topics)

`reguloj` is a small and lightweight Java rule engine.

## Usage

### Creating rule engines

A rule engine evaluates a set of rules in a specific context. The `RuleEngine` interface offers 3 factory methods to build rule engines:

```java
// All rules will be evaluated indefinitely until no further rule fires.
RuleEngine<CONTEXT> chained = RuleEngine.chained();

// All rules will be evaluated, but only a maximum number of 5 times.
RuleEngine<CONTEXT> limited = RuleEngine.limited(5);

// Evaluates all rules, stops after the first one that fires.
RuleEngine<CONTEXT> firstWins = RuleEngine.firstWins();
```

All provided rule engines are thread-safe and can be used as often as you like. If custom inference behavior is required, subclass `AbstractRuleEngine` and implement the `infer()` method. The following code example shows how to work with rule engines:

```java
// setup - more details later
RuleEngine<CONTEXT> engine = ...;
Collection<Rule<CONTEXT>> rules = ...;
CONTEXT context = ...;

// true if at least one rule can fired.
engine.analyze(rules, context);

// perform conclusions of those rules that fired.
engine.infer(rules, context);
```

Note that the order of the collection dictates the evaluation order of your rules - if order does matter, use `List` rather than `Set` as a `Collection` implementation.

### Creating rules

A [rule](https://github.com/metio/reguloj/blob/main/src/main/java/wtf/metio/reguloj/Rule.java) has a name and runs in a given context. Additionally, it can be checked whether a rule fires in a given context.

Either implement the `Rule` interface yourself and or use the supplied rule implementation and builder. A standard rule is composed of a `java.util.function.Predicate` and `java.util.function.Consumer`. Both interfaces require you to implement only a single method and do not restrict you in any way. Complex rules can be created by grouping or chaining predicates/consumers together with the help of several utility methods. The following example creates a rule composed of 2 predicates and 2 consumers:

```java
Rule<CONTEXT> rule = Rule.called(name)
                .when(predicate1.and(predicate2))
                .then(consumer1.andThen(consumer2));

// true if the rule would fire in the given context, e.g. the above predicate is true.
rule.fires(context);

// runs (applies) the rule in the given context
rule.run(context);
```

Using Java 8 lambdas is possible as well:

```java
Rule<CONTEXT> rule = Rule.called(name)
                .when(context -> context.check())
                .then(context -> context.action())
```

Note that custom implementations of the `Rule` interface don't necessary have to use the `java.util.function` package and are free to choose their implementation looks like.

### Creating an inference context

An inference [context](https://github.com/metio/reguloj/blob/main/src/main/java/wtf/metio/reguloj/Context.java) contains information needed by predicates and/or consumers. This project supplies a simple implementation of the Context interface called `SimpleContext` which just wraps a given topic. The `AbstractContext` class can be used to create subclasses in case your rules need extra information. The API acknowledges this by using `<CONTEXT extends Context<?>>` as type parameter for all methods which expect a Context, thus allowing all context implementations to be used. See item 28 in Effective Java for more details.

```java
CONTEXT context = Context.of("some object");
```

### Integration

```xml
<dependency>
  <groupId>wtf.metio.reguloj</groupId>
  <artifactId>reguloj</artifactId>
  <version>${version.reguloj}</version>
</dependency>
```

```kotlin
dependencies {
    implementation("wtf.metio.reguloj:reguloj:${version.reguloj}") {
        because("we want to use a lightweight rule engine")
    }
}
```

Replace `${version.reguloj}` with the [latest release](http://search.maven.org/#search%7Cga%7C1%7Cg%3Awtf.metio.reguloj%20a%3Areguloj).

### Requirements

| regoluj    | Java |
|------------|------|
| 2021.4.13+ | 16+  |

## Alternatives

In case `reguloj` is not what you are looking for, try these projects:

- [Dredd](https://github.com/amsterdatech/Dredd)
- [SmartParam](https://github.com/smartparam/smartparam)
- [ramen](https://github.com/asgarth/ramen)
- [nomin](https://github.com/dobrynya/nomin)
- [dvare](https://github.com/dvare/dvare-rules)
- [ruli](https://github.com/mediavrog/ruli)
- [MintRules](https://github.com/augusto/MintRules)
- [Jare](https://github.com/uwegeercken/jare)
- [tuProlog](http://alice.unibo.it/xwiki/bin/view/Tuprolog/)
- [drools](https://www.drools.org/)
- [Easy Rules](https://github.com/j-easy/easy-rules)
- [n-cube](https://github.com/jdereg/n-cube)
- [RuleBook](https://github.com/deliveredtechnologies/rulebook)
- [OpenL Tablets](http://openl-tablets.org/)
- [JSR 94](https://jcp.org/en/jsr/detail?id=94)
- [rules](https://github.com/rlangbehn/rules)

## License

```
To the extent possible under law, the author(s) have dedicated all copyright
and related and neighboring rights to this software to the public domain
worldwide. This software is distributed without any warranty.

You should have received a copy of the CC0 Public Domain Dedication along with
this software. If not, see http://creativecommons.org/publicdomain/zero/1.0/.
```

## Mirrors

- https://github.com/metio/reguloj
- https://repo.or.cz/reguloj.git
- https://codeberg.org/metio.wtf/reguloj
- https://gitlab.com/metio.wtf/reguloj
- https://bitbucket.org/metio-wtf/reguloj
