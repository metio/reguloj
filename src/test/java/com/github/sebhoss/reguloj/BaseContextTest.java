/*
 * Copyright © 2010 Sebastian Hoß <mail@shoss.de>
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See http://www.wtfpl.net/ for more details.
 */
package com.github.sebhoss.reguloj;

import com.github.sebhoss.warnings.CompilerWarnings;
import com.google.common.truth.Truth;

import org.junit.Test;

/**
 * Test cases for {@link BaseContext}.
 */
@SuppressWarnings({ CompilerWarnings.NLS, CompilerWarnings.STATIC_METHOD })
public class BaseContextTest {

    /**
     * Ensures that the given topic is returned.
     */
    @Test
    public void shouldReturnGivenTopic() {
        final Context<Object> context = new BaseContext<>("test");

        final Object topic = context.getTopic();

        Truth.assertThat(topic).isEqualTo("test");
    }

}
