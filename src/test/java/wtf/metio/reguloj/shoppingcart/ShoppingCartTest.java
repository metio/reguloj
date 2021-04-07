/*
 * This file is part of reguloj. It is subject to the license terms in the LICENSE file found in the top-level
 * directory of this distribution and at http://creativecommons.org/publicdomain/zero/1.0/. No part of reguloj,
 * including this file, may be copied, modified, propagated, or distributed except according to the terms contained
 * in the LICENSE file.
 */

package wtf.metio.reguloj.shoppingcart;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import wtf.metio.reguloj.Rule;
import wtf.metio.reguloj.RuleEngine;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

class ShoppingCartTest {

    private static final Product TEST_PRODUCT = new Product("xPhone 37");

    private Collection<Rule<Cart>> rules;
    private RuleEngine<Cart> ruleEngine;
    private List<Price> prices;

    @BeforeEach
    void setUp() {
        final var standardPrice = Rule.<Cart>called("single purchase uses standard price")
                .when(cart -> true)
                .then(cart -> cart.prices().add(new Price(TEST_PRODUCT, 100)));
        final var reducedPrice = Rule.<Cart>called("multiple purchases get reduced price")
                .when(cart -> cart.topic().size() > 1)
                .then(cart -> cart.prices().add(new Price(TEST_PRODUCT, 50)));
        prices = new ArrayList<>();
        rules = List.of(reducedPrice, standardPrice);
        ruleEngine = RuleEngine.firstWins();
    }

    @Test
    void shouldCalculatePrice() {
        // given
        final var cart = new Cart(List.of(TEST_PRODUCT), prices);

        // when
        ruleEngine.infer(rules, cart);

        // then
        Assertions.assertAll("prices",
                () -> Assertions.assertNotNull(cart.prices()),
                () -> Assertions.assertEquals(1, cart.prices().size()),
                () -> Assertions.assertEquals(100, cart.prices().get(0).price())
        );
    }

    @Test
    void shouldCalculatePriceForMultipleProducts() {
        // given
        final var cart = new Cart(List.of(TEST_PRODUCT, TEST_PRODUCT), prices);

        // when
        ruleEngine.infer(rules, cart);

        // then
        Assertions.assertAll("prices",
                () -> Assertions.assertNotNull(cart.prices()),
                () -> Assertions.assertEquals(1, cart.prices().size()),
                () -> Assertions.assertEquals(50, cart.prices().get(0).price())
        );
    }

}
