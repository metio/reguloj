package com.github.sebhoss.reguloj;

import com.github.sebhoss.common.annotation.CompilerWarnings;

import org.junit.Assert;
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
        final Context<Object> context = new BaseContext<Object>("test");

        final Object topic = context.getTopic();

        Assert.assertEquals("test", topic);
    }

}
