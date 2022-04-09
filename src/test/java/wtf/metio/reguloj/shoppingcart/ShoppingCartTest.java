/*
 * This file is part of reguloj. It is subject to the license terms in the LICENSE file found in the top-level
 * directory of this distribution and at https://creativecommons.org/publicdomain/zero/1.0/. No part of reguloj,
 * including this file, may be copied, modified, propagated, or distributed except according to the terms contained
 * in the LICENSE file.
 */

package wtf.metio.reguloj.shoppingcart;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import wtf.metio.reguloj.Rule;
import wtf.metio.reguloj.RuleEngine;

class ShoppingCartTest {

  private static final Product TEST_PRODUCT = new Product("xPhone 37");

  @Nested
  @DisplayName("Using RuleEngine#firstWins")
  class UsingFirstWins {

    private Collection<Rule<Cart>> rules;
    private RuleEngine<Cart> ruleEngine;

    @BeforeEach
    void setUp() {
      final var standardPrice = Rule.<Cart>called("single purchase uses standard price")
          .when(cart -> true) // always fires thus can be used as a fallback
          .then(cart -> cart.prices().add(new Price(TEST_PRODUCT, 100)));
      final var reducedPrice = Rule.<Cart>called("multiple purchases get reduced price")
          .when(cart -> cart.topic().size() > 1) // only fires for multiple products
          .then(cart -> cart.prices().add(new Price(TEST_PRODUCT, 75 * cart.topic().size())));
      // the order is important here, so we use a List
      rules = List.of(reducedPrice, standardPrice);
      // since we control the order, we can only run the first rule that fires
      ruleEngine = RuleEngine.firstWins();
    }

    @Test
    void shouldCalculatePrice() {
      // given
      final var cart = new Cart(List.of(TEST_PRODUCT), new ArrayList<>());

      // when
      ruleEngine.infer(rules, cart);

      // then
      assertPrice(cart, 100);
    }

    @Test
    void shouldCalculatePriceForMultipleProducts() {
      // given
      final var cart = new Cart(List.of(TEST_PRODUCT, TEST_PRODUCT), new ArrayList<>());

      // when
      ruleEngine.infer(rules, cart);

      // then
      assertPrice(cart, 150);
    }

  }

  @Nested
  @DisplayName("Using RuleEngine#limited")
  class UsingLimited {

    private Collection<Rule<Cart>> rules;
    private RuleEngine<Cart> ruleEngine;

    @BeforeEach
    void setUp() {
      final var standardPrice = Rule.<Cart>called("single purchase uses standard price")
          .when(cart -> cart.topic().size() == 1) // fires for single products
          .then(cart -> cart.prices().add(new Price(TEST_PRODUCT, 100)));
      final var reducedPrice = Rule.<Cart>called("multiple purchases get reduced price")
          .when(cart -> cart.topic().size() > 1) // fires for multiple products
          .then(cart -> cart.prices().add(new Price(TEST_PRODUCT, 75 * cart.topic().size())));
      // the order is no longer important
      rules = Set.of(standardPrice, reducedPrice);
      // we run any rule that fires at most once
      ruleEngine = RuleEngine.limited(1);
    }

    @Test
    void shouldCalculatePrice() {
      // given
      final var cart = new Cart(List.of(TEST_PRODUCT), new ArrayList<>());

      // when
      ruleEngine.infer(rules, cart);

      // then
      assertPrice(cart, 100);
    }

    @Test
    void shouldCalculatePriceForMultipleProducts() {
      // given
      final var cart = new Cart(List.of(TEST_PRODUCT, TEST_PRODUCT), new ArrayList<>());

      // when
      ruleEngine.infer(rules, cart);

      // then
      assertPrice(cart, 150);
    }

  }

  @Nested
  @DisplayName("Using RuleEngine#chained")
  class UsingChained {

    private Collection<Rule<Cart>> rules;
    private RuleEngine<Cart> ruleEngine;

    @BeforeEach
    void setUp() {
      final var standardPrice = Rule.<Cart>called("single purchase uses standard price")
          // we need an extra terminal operation to avoid an infinite loop
          .when(cart -> cart.topic().size() == 1 && cart.prices().size() == 0)
          .then(cart -> cart.prices().add(new Price(TEST_PRODUCT, 100)));
      final var reducedPrice = Rule.<Cart>called("multiple purchases get reduced price")
          // we need an extra terminal operation to avoid an infinite loop
          .when(cart -> cart.topic().size() > 1 && cart.prices().size() == 0)
          .then(cart -> cart.prices().add(new Price(TEST_PRODUCT, 75 * cart.topic().size())));
      // the order is no longer important
      rules = Set.of(standardPrice, reducedPrice);
      // we run all rules as often as they fire
      ruleEngine = RuleEngine.chained();
    }

    @Test
    void shouldCalculatePrice() {
      // given
      final var cart = new Cart(List.of(TEST_PRODUCT), new ArrayList<>());

      // when
      ruleEngine.infer(rules, cart);

      // then
      assertPrice(cart, 100);
    }

    @Test
    void shouldCalculatePriceForMultipleProducts() {
      // given
      final var cart = new Cart(List.of(TEST_PRODUCT, TEST_PRODUCT), new ArrayList<>());

      // when
      ruleEngine.infer(rules, cart);

      // then
      assertPrice(cart, 150);
    }

  }

  private static void assertPrice(final Cart cart, final int expectedPrice) {
    Assertions.assertAll("prices",
        () -> Assertions.assertNotNull(cart.prices()),
        () -> Assertions.assertEquals(1, cart.prices().size()),
        () -> Assertions.assertEquals(expectedPrice, cart.prices().get(0).price())
    );
  }

}
