/*
 * SPDX-FileCopyrightText: The reguloj Authors
 * SPDX-License-Identifier: 0BSD
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
