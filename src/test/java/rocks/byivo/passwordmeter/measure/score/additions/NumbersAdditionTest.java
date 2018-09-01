package rocks.byivo.passwordmeter.measure.score.additions;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import rocks.byivo.passwordmeter.measure.score.additions.NumbersAddition;

public class NumbersAdditionTest {

    private NumbersAddition numbersAddition;
    
    @Before
    public void setUp() throws Exception {
	numbersAddition = new NumbersAddition();
    }
    
    @Test
    public void shouldMultiplyBy4TheTotalCountOfNumbersInRawPassword() {
	long bonusOfNumbers = numbersAddition.getTotalBonusFrom("a1b2c3d4e5f");
	assertThat(bonusOfNumbers, is(20L));
    }
    
    @Test
    public void ifThePasswordIsComposedOnlyByNumbersItShouldScores0() throws Exception {
	long bonusOfNumbers = numbersAddition.getTotalBonusFrom("111");
	assertThat(bonusOfNumbers, is(0L));
    }
    
    @Test
    public void shouldScore0WhenThereIsNoNumberInSentence() throws Exception {
	long bonusOfNumbers = numbersAddition.getTotalBonusFrom("abcdefg");
	assertThat(bonusOfNumbers, is(0L));
    }

}
