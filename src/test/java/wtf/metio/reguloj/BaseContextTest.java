package wtf.metio.reguloj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

final class BaseContextTest {

    @Test
    void shouldReturnGivenTopic() {
        final var context = new BaseContext<>("test");
        final var topic = context.getTopic();
        Assertions.assertEquals("test", topic);
    }

}
