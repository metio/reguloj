/*
 * SPDX-FileCopyrightText: The reguloj Authors
 * SPDX-License-Identifier: 0BSD
 */
package wtf.metio.reguloj.shoppingcart;

import wtf.metio.reguloj.Context;

import java.util.List;

public record Cart(List<Product> topic, List<Price> prices) implements Context<List<Product>> {

}
