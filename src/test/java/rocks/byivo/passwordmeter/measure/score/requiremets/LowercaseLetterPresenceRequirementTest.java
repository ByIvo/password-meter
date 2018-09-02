package rocks.byivo.passwordmeter.measure.score.requiremets;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import rocks.byivo.passwordmeter.model.Requirement;

public class LowercaseLetterPresenceRequirementTest {

    private LowercaseLetterPresenceRequirement lowercaseLetterPresenceRequirement;
    
    @Before
    public void setUp() throws Exception {
	lowercaseLetterPresenceRequirement = new LowercaseLetterPresenceRequirement();
    }

    @Test
    public void shouldReturnTheHasLowercaseLettersWhenGetsItsRequrement() {
	assertThat(lowercaseLetterPresenceRequirement.getRequirement(), is(Requirement.HAS_LOWERCASE_LETTER));
    }
    
    @Test
    public void shouldPassTheRequirementWhenHasAtLeastOneLowercaseLetter() throws Exception {
	boolean rechedTheRequirement = lowercaseLetterPresenceRequirement.isTheMinimumRequirementReachedIn("123aBC");
	assertThat(rechedTheRequirement, is(true));
    }
    
    @Test
    public void shouldNotPassTheRequirementIfThereIsNoLowercaseLetter() throws Exception {
	boolean rechedTheRequirement = lowercaseLetterPresenceRequirement.isTheMinimumRequirementReachedIn("123ABC#%$");
	assertThat(rechedTheRequirement, is(false));
    }

}
