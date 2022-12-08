<!--
SPDX-FileCopyrightText: The reguloj Authors
SPDX-License-Identifier: 0BSD
 -->

# reguloj [![Chat](https://img.shields.io/badge/matrix-%23talk.metio:matrix.org-brightgreen.svg?style=social&label=Matrix)](https://matrix.to/#/#talk.metio:matrix.org)

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

Note that custom implementations of the `Rule` interface don't necessary have to use the `java.util.function` package and are free to choose how their implementation looks like.

### Creating an inference context

An inference [context](https://github.com/metio/reguloj/blob/main/src/main/java/wtf/metio/reguloj/Context.java) contains information needed by predicates and/or consumers. This project supplies a simple implementation of the Context interface called `SimpleContext` which just wraps a given topic. The `AbstractContext` class can be used to create subclasses in case your rules need extra information. The API acknowledges this by using `<CONTEXT extends Context<?>>` as type parameter for all methods which expect a Context, thus allowing all context implementations to be used. See item 28 in Effective Java for more details.

```java
CONTEXT context = Context.of("some object");
```

## Example Use Case

The [wtf.metio.regoluj.shoppingcart](https://github.com/metio/reguloj/tree/main/src/test/java/wtf/metio/reguloj/shoppingcart) package contains [tests](https://github.com/metio/reguloj/blob/main/src/test/java/wtf/metio/reguloj/shoppingcart/ShoppingCartTest.java) for an example use case revolving around shopping carts, products, and their prices. It works as follows:

We have a custom `Context` implementation in the form of [wtf.metio.regoluj.shoppingcart.Cart](https://github.com/metio/reguloj/blob/main/src/test/java/wtf/metio/reguloj/shoppingcart/Cart.java) that holds a list of products, and a matching list of prices for those products. The list of products is its main topic. Various `Rules` are used to calculate the price per product in the shopping cart. Written as a `record`, the `Cart` could look like this:

```java
public record Cart(List<Product> topic, List<Price> prices) implements Context<List<Product>> {

}
```

As you can see, one of the `record` parameters must be named `topic` and use the type of the context in order to correctly implement the method contract of `Context`. Similar, a `Product` and `Price` could look like this:

```java
public record Product(String name) {

}

public record Price(Product product, int price) {

}
```

The initial state of a card contains just the products without any previously calculated prices in this example:

```java
final Cart singleProductCart = new Cart(List.of(TEST_PRODUCT), new ArrayList<>());
final Cart multiProductCart = new Cart(List.of(TEST_PRODUCT, TEST_PRODUCT), new ArrayList<>());
```

The constant `TEST_PRODUCT` is just some example data that represents objects of your actual business domain: `Product TEST_PRODUCT = new Product("xPhone 37");`. 

### Using RuleEngine#firstWins

```java
RuleEngine<Cart> ruleEngine = RuleEngine.firstWins();
```

While using a first-wins `RuleEngine`, our `Rules`s could look like this:

```java
final var standardPrice = Rule.<Cart>called("single purchase uses standard price")
    .when(cart -> true) // always fires thus can be used as a fallback
    .then(cart -> cart.prices().add(new Price(TEST_PRODUCT, 100)));
final var reducedPrice = Rule.<Cart>called("multiple purchases get reduced price")
    .when(cart -> cart.topic().size() > 1) // only fires for multiple products
    .then(cart -> cart.prices().add(new Price(TEST_PRODUCT, 75 * cart.topic().size())));
```

As you can see, we kept the implementation of the rules rather simple, in order to keep the example focused on the `reguloj` related classes. In a real world project, you don't want to specify a constant price for a single product, but rather use some database lookup or similar technique to calculate prices more dynamically. Since we need both a `Context` and a `Collection` of rules, we combine the above into a `List` with:

```java
Collection<Rule<Cart>> rules = List.of(reducedPrice, standardPrice);
```

The order is important here - we first test if we can apply the reduced priced, and only apply the full price as a fallback. In order to infer a price for our shopping carts, combine `Rules` and `Context` (carts) using the previously built `RuleEngine` as the following example shows:

```java
ruleEngine.infer(rules, singleProductCart);
ruleEngine.infer(rules, multiProductCart);
```

Since the above rules will only ever add one price, we can check whether everything works as expected like this:

```java
Assertions.assertEquals(100, singleProductCart.prices().get(0).price())
Assertions.assertEquals(150, multiProductCart.prices().get(0).price())
```

### Using RuleEngine#limited

```java
RuleEngine<Cart> ruleEngine = RuleEngine.limited(1);
```

While using a limited `RuleEngine`, our `Rules`s could look like this:

```java
final var standardPrice = Rule.<Cart>called("single purchase uses standard price")
    .when(cart -> cart.topic().size() == 1) // fires for single products
    .then(cart -> cart.prices().add(new Price(TEST_PRODUCT, 100)));
final var reducedPrice = Rule.<Cart>called("multiple purchases get reduced price")
    .when(cart -> cart.topic().size() > 1) // fires for multiple products
    .then(cart -> cart.prices().add(new Price(TEST_PRODUCT, 75 * cart.topic().size())));
```

The difference here is that the first rule only fires for carts that contain a single product (remember the topic of a cart is a list of products) since a limited `RuleEngine` will try ever rule a limited number of times and thus it won't stop after some rule fired as in the first example. Note that this implementation would have worked in the first example as well, however the first example would not work with a limited `RuleEngine`. The implementation for the second rule is exactly the same as the first example.

```java
Collection<Rule<Cart>> rules = Set.of(standardPrice, reducedPrice);
```

Since the order in which rules are fired does not matter, we can use a `Set` rather than  `List`. In case you are planning on creating rules dynamically based on some external data, like XML, YAML, a database, or your neighbours dog, make sure to be a specific as possible in your predicates in order to make your rules as widely usable as possible.

```java
ruleEngine.infer(rules, singleProductCart);
ruleEngine.infer(rules, multiProductCart);

Assertions.assertEquals(100, singleProductCart.prices().get(0).price())
Assertions.assertEquals(150, multiProductCart.prices().get(0).price())
```

Running the inference process is exactly the same no matter which `RuleEngine` you picked or how you `Rule`s are implemented.

### Using RuleEngine#chained

```java
RuleEngine<Cart> ruleEngine = RuleEngine.chained();
```

While using a chained `RuleEngine`, our `Rules`s could look like this:

```java
final var standardPrice = Rule.<Cart>called("single purchase uses standard price")
    .when(cart -> cart.topic().size() == 1 && cart.prices().size() == 0)
    .then(cart -> cart.prices().add(new Price(TEST_PRODUCT, 100)));
final var reducedPrice = Rule.<Cart>called("multiple purchases get reduced price")
    .when(cart -> cart.topic().size() > 1 && cart.prices().size() == 0)
    .then(cart -> cart.prices().add(new Price(TEST_PRODUCT, 75 * cart.topic().size())));
```

Since chained `RuleEngine`s will run all `Rule`s as often as they fire, we need an extra terminal condition to stop re-firing our rules. Since we are only calculating the price of a single product, we can always stop firing our `Rule`s in case there is already a price in our cart.

```java
Collection<Rule<Cart>> rules = Set.of(standardPrice, reducedPrice);
```

Again, the order of our rules do not matter, thus we are using a `Set`.

```java
ruleEngine.infer(rules, singleProductCart);
ruleEngine.infer(rules, multiProductCart);

Assertions.assertEquals(100, singleProductCart.prices().get(0).price())
Assertions.assertEquals(150, multiProductCart.prices().get(0).price())
```

Getting a final price for our carts is exactly the same again.

## Integration

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

## Requirements

| regoluj    | Java |
|------------|------|
| 2022.4.12+ | 17+  |
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
Permission to use, copy, modify, and/or distribute this software for any
purpose with or without fee is hereby granted.

THE SOFTWARE IS PROVIDED "AS IS" AND THE AUTHOR DISCLAIMS ALL WARRANTIES WITH
REGARD TO THIS SOFTWARE INCLUDING ALL IMPLIED WARRANTIES OF MERCHANTABILITY AND
FITNESS. IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR ANY SPECIAL, DIRECT,
INDIRECT, OR CONSEQUENTIAL DAMAGES OR ANY DAMAGES WHATSOEVER RESULTING FROM
LOSS OF USE, DATA OR PROFITS, WHETHER IN AN ACTION OF CONTRACT, NEGLIGENCE OR
OTHER TORTIOUS ACTION, ARISING OUT OF OR IN CONNECTION WITH THE USE OR
PERFORMANCE OF THIS SOFTWARE.
```

## Mirrors

- https://github.com/metio/reguloj
- https://gitlab.com/metio.wtf/reguloj
- https://codeberg.org/metio.wtf/reguloj
- https://bitbucket.org/metio-wtf/reguloj