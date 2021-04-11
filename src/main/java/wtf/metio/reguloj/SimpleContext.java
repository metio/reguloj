/*
 * This file is part of reguloj. It is subject to the license terms in the LICENSE file found in the top-level
 * directory of this distribution and at http://creativecommons.org/publicdomain/zero/1.0/. No part of reguloj,
 * including this file, may be copied, modified, propagated, or distributed except according to the terms contained
 * in the LICENSE file.
 */

package wtf.metio.reguloj;

/**
 * Smallest possible context that only contains the topic of a rule engine.
 *
 * @param <TOPIC> The type of the topic.
 */
record SimpleContext<TOPIC>(TOPIC topic) implements Context<TOPIC> {

}
