package wtf.metio.reguloj;

import com.google.common.truth.Truth;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * Test cases for the RuleBuilderImplementation.
 */
public final class RuleBuilderImplementationTest {

    /**
     * <p>
     * Test method for RuleBuilderImplementation#then(Conclusion)
     * </p>
     * <p>
     * Ensures that rules can be created with a valid RuleBuilderImplementation.
     * </p>
     */
    @Test
    public void shouldCreateRuleIfAllValuesAreSet() {
        final RuleBuilder<Context<Object>> builder = new RuleBuilderImplementation<>();
        builder.called("test rule").when(Mockito.mock(Predicate.class)); //$NON-NLS-1$

        final Rule<Context<Object>> rule = builder.then(Mockito.mock(Consumer.class));

        Truth.assertThat(rule).isNotNull();
    }

}
