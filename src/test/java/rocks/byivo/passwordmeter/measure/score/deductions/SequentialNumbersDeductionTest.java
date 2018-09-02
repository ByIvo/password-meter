package rocks.byivo.passwordmeter.measure.score.deductions;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class SequentialNumbersDeductionTest {
    
    private SequentialNumbersDeduction sequentialNumbersDeduction;
    
    @Before
    public void setUp() throws Exception {
	sequentialNumbersDeduction = new SequentialNumbersDeduction();
    }

    @Test
    public void shoulCountASequenceSizeStartingFromThirdCharacterAndMultiplyItBy3() {
	long numerSequenceScore = sequentialNumbersDeduction.getTotalBonusFrom("1234567");
	assertThat(numerSequenceScore, is(15L));
    }
    
    @Test
    public void spacesShouldBreakTheSequenceCountage() {
	long numerSequenceScore = sequentialNumbersDeduction.getTotalBonusFrom("0123 456");
	assertThat(numerSequenceScore, is(6L));
    }
    
    @Test
    public void theZeroNumberShouldCountAsASequenceInTheBegginig() {
	long numerSequenceScore = sequentialNumbersDeduction.getTotalBonusFrom("012");
	assertThat(numerSequenceScore, is(3L));
    }
    
    @Test
    public void theZeroNumberShouldNotCountAsASequenceInTheEnding() {
	long numerSequenceScore = sequentialNumbersDeduction.getTotalBonusFrom("7890");
	assertThat(numerSequenceScore, is(3L));
    }
    
    @Test
    public void shouldCountSequencesInBackwards() {
	long numerSequenceScore = sequentialNumbersDeduction.getTotalBonusFrom("543210");
	assertThat(numerSequenceScore, is(12L));
    }
    
    @Test
    public void shouldCountOnlyTheGreaterSequenceWhenThereTwoOrMoreSequences() {
	long numerSequenceScore = sequentialNumbersDeduction.getTotalBonusFrom("12345abc987654");
	assertThat(numerSequenceScore, is(12L));
    }
    
    @Test
    public void shouldScoreZeroWhenThereIsNoNumberSequence() {
	long numerSequenceScore = sequentialNumbersDeduction.getTotalBonusFrom("1a2b3c4c6c5");
	assertThat(numerSequenceScore, is(0L));
    }
    
    @Test
    public void shouldScoreZeroWhenThereIsNoPassword() {
	long numerSequenceScore = sequentialNumbersDeduction.getTotalBonusFrom("");
	assertThat(numerSequenceScore, is(0L));
    }
    
}
