package rocks.byivo.passwordmeter.measure.score.additions;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import rocks.byivo.passwordmeter.measure.score.additions.LowercaseLettersAddition;

public class LowercaseLettersAdditionTest {
    
    private LowercaseLettersAddition lowercaseLettersAddition;
    
    @Before
    public void setUp() throws Exception {
	lowercaseLettersAddition = new LowercaseLettersAddition();
    }

    @Test
    public void shouldGetTheDifferenceBetweenThePasswordLengthAndLowercaseCountAndMultiplyBy2() {
	long passwordAddition = lowercaseLettersAddition.getTotalBonusFrom("aaaA");
	assertThat(passwordAddition, is(2l));
    }
    
    @Test
    public void howManyMoreOtherKindOfCharactersMoreTheAdditionWillGrow() {
	long passwordAddition = lowercaseLettersAddition.getTotalBonusFrom("AAAAAAAAAAa");
	assertThat(passwordAddition, is(20l));
    }
    
    @Test
    public void whenThereIsNoLowercaseLettersTheResultShouldBe0() throws Exception {
	long passwordAddition = lowercaseLettersAddition.getTotalBonusFrom("AAAAAA");
	assertThat(passwordAddition, is(0l));
    }
    
    @Test
    public void whenAllLettersAreLowercaseTheResultShouldBe0() throws Exception {
	long passwordAddition = lowercaseLettersAddition.getTotalBonusFrom("aaaaaa");
	assertThat(passwordAddition, is(0l));
    }

}
