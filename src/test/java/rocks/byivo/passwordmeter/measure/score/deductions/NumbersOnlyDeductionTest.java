package rocks.byivo.passwordmeter.measure.score.deductions;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class NumbersOnlyDeductionTest {
    
    private NumbersOnlyDeduction numbersOnlyDeduction;
    
    @Before
    public void setUp() throws Exception {
	numbersOnlyDeduction = new NumbersOnlyDeduction();
    }

    @Test
    public void shouldCountAndReturnTheAmountOfNumbersWhenThePasswordIsCamposedOnlyByNumbers() {
	long bonusFromOnlyNumbers = numbersOnlyDeduction.getTotalBonusFrom("111114567896528");
	assertThat(bonusFromOnlyNumbers, is(15L));
    }
    
    @Test
    public void shouldConsiderSpaceAsNumber() {
	long bonusFromOnlyNumber = numbersOnlyDeduction.getTotalBonusFrom("12 347   156 98");
	assertThat(bonusFromOnlyNumber, is(15L));
    }
    
    @Test
    public void ifThereIsALetterShouldScore0() {
	long bonusFromOnlyNumber = numbersOnlyDeduction.getTotalBonusFrom("156478a");
	assertThat(bonusFromOnlyNumber, is(0L));
    }
    
    @Test
    public void ifThereIsASymbolShouldScore0() {
	long bonusFromOnlyNumber = numbersOnlyDeduction.getTotalBonusFrom("54$854");
	assertThat(bonusFromOnlyNumber, is(0L));
    }

}
