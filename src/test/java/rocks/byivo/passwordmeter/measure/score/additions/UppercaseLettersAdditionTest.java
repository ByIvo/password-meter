package rocks.byivo.passwordmeter.measure.score.additions;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import rocks.byivo.passwordmeter.measure.score.additions.UppercaseLettersAddition;

public class UppercaseLettersAdditionTest {
    
    private UppercaseLettersAddition uppercaseLettersAdition;
    
    @Before
    public void setUp() throws Exception {
	uppercaseLettersAdition = new UppercaseLettersAddition();
    }

    @Test
    public void shouldDecreaseTheUppercaseLetterCountFromTotalLengthAndMultiplyItBy2() {
	long totalBonus = uppercaseLettersAdition.getTotalBonusFrom("AAAa");
	assertThat(totalBonus, is(2l));
    }
    
    @Test
    public void aPasswordAllFormedFromUppercaseLettersShoulScoreAs0() {
	long totalBonus = uppercaseLettersAdition.getTotalBonusFrom("AAAAAAA");
	assertThat(totalBonus, is(0l));
    }
    
    @Test
    public void aPasswordWithoutUppercaseLettersShoulScoreAs0AsWell() {
	long totalBonus = uppercaseLettersAdition.getTotalBonusFrom("aaaaaa");
	assertThat(totalBonus, is(0l));
    }
    
    @Test
    public void theScoreGetsBiggerAccordingTheNumberOfUppercaseLettsIsSmallerAsTheNumberOfCharacters() throws Exception {
	long totalBonus = uppercaseLettersAdition.getTotalBonusFrom("!23%ab8A");
	assertThat(totalBonus, is(14l));
    }

}
