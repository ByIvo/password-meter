package rocks.byivo.passwordmeter.measure.score.deductions;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class SequentialSymbolssDeductionTest {
    
    private SequentialSymbolssDeduction sequentialSymbolssDeduction;

    @Before
    public void setUp() throws Exception {
	sequentialSymbolssDeduction = new SequentialSymbolssDeduction();
    }

    @Test
    public void shouldScore3PointsForEachSymbolKeyFromNumericKeyboardStartingCountingFromTheThirdSymbol() {
	long scoreForSymbols = sequentialSymbolssDeduction.getTotalBonusFrom(")!@#$%^&*abcd");
	assertThat(scoreForSymbols, is(21L));
    }
    
    @Test
    public void anyDifferenteCharacterShouldBreakTheSequenceCountage() {
	long scoreForSymbols = sequentialSymbolssDeduction.getTotalBonusFrom(")!@#$ ] ^&*");
	assertThat(scoreForSymbols, is(9L));
    }
    
    @Test
    public void shouldOnlyScoreTheGreatestSequenceWhenItFindsTwoOrMoreSequences() {
	long scoreForSymbols = sequentialSymbolssDeduction.getTotalBonusFrom(")!@#$%  )!@#$%^&");
	assertThat(scoreForSymbols, is(18L));
    }
    
    @Test
    public void shouldScoreForBackwardsSequencesAsWell() {
	long scoreForSymbols = sequentialSymbolssDeduction.getTotalBonusFrom("*&^%$#@!)");
	assertThat(scoreForSymbols, is(21L));
    }
    
    @Test
    public void shouldScoreZeroIfTheSequenceIsNotGreatEnough() {
	long scoreForSymbols = sequentialSymbolssDeduction.getTotalBonusFrom(")! @# $% ^& *");
	assertThat(scoreForSymbols, is(0L));
    }
    
    @Test
    public void shouldScoreZeroIfThereIsNoSymbolSequence() {
	long scoreForSymbols = sequentialSymbolssDeduction.getTotalBonusFrom(")!abc");
	assertThat(scoreForSymbols, is(0L));
    }
}
