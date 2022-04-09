/*
 * This file is part of reguloj. It is subject to the license terms in the LICENSE file found in the top-level
 * directory of this distribution and at https://creativecommons.org/publicdomain/zero/1.0/. No part of reguloj,
 * including this file, may be copied, modified, propagated, or distributed except according to the terms contained
 * in the LICENSE file.
 */

package wtf.metio.reguloj.shoppingcart;

import java.util.List;
import wtf.metio.reguloj.Context;

public record Cart(List<Product> topic, List<Price> prices) implements Context<List<Product>> {

}
