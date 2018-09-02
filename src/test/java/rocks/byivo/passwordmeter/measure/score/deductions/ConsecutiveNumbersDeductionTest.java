package rocks.byivo.passwordmeter.measure.score.deductions;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ConsecutiveNumbersDeductionTest {
    
    private ConsecutiveNumbersDeduction consecutiveNumbersDeduction;
    
    @Before
    public void setUp() throws Exception {
	consecutiveNumbersDeduction = new ConsecutiveNumbersDeduction();
    }

    @Test
    public void shouldScoreTwoPointForAllConsecutiveNumbers() {
	long consecutiveNumbersIn = consecutiveNumbersDeduction.getTotalBonusFrom("012345");
	assertThat(consecutiveNumbersIn, is(10L));
    }
    
    @Test
    public void theSpacesBetweenNumberSequencesShouldNotInterfeerInScore() {
	long consecutiveNumbersIn = consecutiveNumbersDeduction.getTotalBonusFrom("012 34 5");
	assertThat(consecutiveNumbersIn, is(10L));
    }
    
    @Test
    public void theSequenceShouBeScoreSinceItIsANumber() {
	long consecutiveNumbersIn = consecutiveNumbersDeduction.getTotalBonusFrom("6521");
	assertThat(consecutiveNumbersIn, is(6L));
    }

    @Test
    public void ifThereIsASpaceInTheSequencesTheCounterShouldRestartToSeekToSequences() {
	long consecutiveNumbersIn = consecutiveNumbersDeduction.getTotalBonusFrom("123ab456");
	assertThat(consecutiveNumbersIn, is(8L));
    }
    
    @Test
    public void shouldScore0IfThereIsNoSequenceNumbers() {
	long consecutiveNumbersIn = consecutiveNumbersDeduction.getTotalBonusFrom("1a2B3c4D5E");
	assertThat(consecutiveNumbersIn, is(0L));
    }
}
