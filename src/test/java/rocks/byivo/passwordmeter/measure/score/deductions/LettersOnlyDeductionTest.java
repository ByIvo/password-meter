package rocks.byivo.passwordmeter.measure.score.deductions;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class LettersOnlyDeductionTest {

    private LettersOnlyDeduction lettersOnlyDeduction;
    
    @Before	
    public void setUp() throws Exception {
	lettersOnlyDeduction = new LettersOnlyDeduction();
    }

    @Test
    public void shouldCountAndReturnTheAmountOfLettersWhenThePasswordIsCamposedOnlyByLetters() {
	long bonusFromOnlyLetter = lettersOnlyDeduction.getTotalBonusFrom("aaaaaaaaaa");
	assertThat(bonusFromOnlyLetter, is(10L));
    }
    
    @Test
    public void shouldConsiderSpaceAsLetter() {
	long bonusFromOnlyLetter = lettersOnlyDeduction.getTotalBonusFrom("bbbb  DDDD");
	assertThat(bonusFromOnlyLetter, is(10L));
    }
    
    @Test
    public void shouldScoreZeroIfThePasswordIsComposedOnlyBySpaces() {
	long bonusFromOnlyLetter = lettersOnlyDeduction.getTotalBonusFrom("     ");
	assertThat(bonusFromOnlyLetter, is(0L));
    }
    
    @Test
    public void ifThereIsANumberShouldScore0() {
	long bonusFromOnlyLetter = lettersOnlyDeduction.getTotalBonusFrom("aBCDE1");
	assertThat(bonusFromOnlyLetter, is(0L));
    }
    
    @Test
    public void ifThereIsASymbolShouldScore0() {
	long bonusFromOnlyLetter = lettersOnlyDeduction.getTotalBonusFrom("aB$$ED");
	assertThat(bonusFromOnlyLetter, is(0L));
    }

}
