package rocks.byivo.passwordmeter.measure.score.additions;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import rocks.byivo.passwordmeter.measure.score.additions.NumberOfCharactersAdition;

public class NumberOfCharactersAditionTest {
    
    private NumberOfCharactersAdition numberOfCharactersAdition;
    
    @Before
    public void setUp() throws Exception {
	numberOfCharactersAdition = new NumberOfCharactersAdition();
    }

    @Test
    public void shouldMultiplyTheNumberOfCharactersBy4() {
	long bonusFromAdition = numberOfCharactersAdition.getTotalBonusFrom("aaa");
	assertThat(bonusFromAdition, is(12l));
    }
    
    @Test
    public void shouldMultiplyTheUppercaseCharactersAsWell() {
	long bonusFromAdition = numberOfCharactersAdition.getTotalBonusFrom("aaaA");
	assertThat(bonusFromAdition, is(16l));
    }
    
    @Test
    public void shouldMultiplyTheCountOfSymbolsOrNumbersToo() throws Exception {
	long bonusFromAdition = numberOfCharactersAdition.getTotalBonusFrom("$a26Z!");
	assertThat(bonusFromAdition, is(24l));
    }

}
