/*
 * This file is part of reguloj. It is subject to the license terms in the LICENSE file found in the top-level
 * directory of this distribution and at http://creativecommons.org/publicdomain/zero/1.0/. No part of reguloj,
 * including this file, may be copied, modified, propagated, or distributed except according to the terms contained
 * in the LICENSE file.
 */

package wtf.metio.reguloj;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class RuleEngineTest {

    @Test
    void chained() {
        assertNotNull(RuleEngine.chained());
    }

    @Test
    void limited() {
        assertNotNull(RuleEngine.limited(3));
    }

    @Test
    void firstWins() {
        assertNotNull(RuleEngine.firstWins());
    }

}
