package wtf.metio.reguloj;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ContextRecordTest {

    @Test
    void shouldHaveTopic() {
        // given
        final var context = new SimpleContext<>("test");

        // when
        final var topic = context.topic();

        // then
        assertEquals("test", topic);
    }

}
