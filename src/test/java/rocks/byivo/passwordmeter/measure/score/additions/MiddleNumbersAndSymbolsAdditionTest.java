package rocks.byivo.passwordmeter.measure.score.additions;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import rocks.byivo.passwordmeter.measure.score.additions.MiddleNumbersAndSymbolsAddition;

public class MiddleNumbersAndSymbolsAdditionTest {
    
    private MiddleNumbersAndSymbolsAddition middleNumbersAndSymbolsAddition;
    
    @Before
    public void setUp() throws Exception {
	middleNumbersAndSymbolsAddition = new MiddleNumbersAndSymbolsAddition();
    }

    @Test
    public void shouldCountAllMiddleSymbolsAndNumbersAndMultiplyItBy2() {
	long middleNumberAndSymbolsScore = middleNumbersAndSymbolsAddition.getTotalBonusFrom("a1$6b");
	assertThat(middleNumberAndSymbolsScore, is(6l));
    }
    
    @Test
    public void shouldNotCountSymbolsAndNumbersInThePasswordBoundaries() {
	long middleNumberAndSymbolsScore = middleNumbersAndSymbolsAddition.getTotalBonusFrom("#6a%%a66");
	assertThat(middleNumberAndSymbolsScore, is(8l));
    }
    
    @Test
    public void whenTheLengthDoesNotAllowMiddlePasswordShouldScore0EvenIfTheBoudaryIsMadeOfSymbolsOrNumbers() {
	long middleNumberAndSymbolsScore = middleNumbersAndSymbolsAddition.getTotalBonusFrom("#6");
	assertThat(middleNumberAndSymbolsScore, is(0l));
    }
    
    @Test
    public void whenThePasswordIsEmptyShouldScore0() {
	long middleNumberAndSymbolsScore = middleNumbersAndSymbolsAddition.getTotalBonusFrom("");
	assertThat(middleNumberAndSymbolsScore, is(0l));
    }
    
    @Test
    public void shouldNotConsiderASpaceAsASymbol() {
	long middleNumberAndSymbolsScore = middleNumbersAndSymbolsAddition.getTotalBonusFrom("#ab 5 de#");
	assertThat(middleNumberAndSymbolsScore, is(2l));
    }

}
