/*
 * This file is part of reguloj. It is subject to the license terms in the LICENSE file found in the top-level
 * directory of this distribution and at http://creativecommons.org/publicdomain/zero/1.0/. No part of reguloj,
 * including this file, may be copied, modified, propagated, or distributed except according to the terms contained
 * in the LICENSE file.
 */

package wtf.metio.reguloj.shoppingcart;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import wtf.metio.reguloj.Rule;
import wtf.metio.reguloj.RuleEngine;

class ShoppingCartTest {

  private static final Product TEST_PRODUCT = new Product("xPhone 37");

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

  private void assertPrice(final Cart cart, final int expectedPrice) {
    Assertions.assertAll("prices",
        () -> Assertions.assertNotNull(cart.prices()),
        () -> Assertions.assertEquals(1, cart.prices().size()),
        () -> Assertions.assertEquals(expectedPrice, cart.prices().get(0).price())
    );
  }

}
