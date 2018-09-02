package rocks.byivo.passwordmeter.measure.score.requiremets;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import rocks.byivo.passwordmeter.model.Requirement;

public class UppercaseLetterPresenceRequirementTest {

    private UppercaseLetterPresenceRequirement uppercaseLetterPresenceRequirement;
    
    @Before
    public void setUp() throws Exception {
	uppercaseLetterPresenceRequirement = new UppercaseLetterPresenceRequirement();
    }

    @Test
    public void shouldReturnTheHasUppercaseLettersWhenGetsItsRequrement() {
	assertThat(uppercaseLetterPresenceRequirement.getRequirement(), is(Requirement.HAS_UPPERCASE_LETTER));
    }
    
    @Test
    public void shouldPassTheRequirementWhenHasAtLeastOneUppercaseLetter() throws Exception {
	boolean rechedTheRequirement = uppercaseLetterPresenceRequirement.isTheMinimumRequirementReachedIn("123Abc");
	assertThat(rechedTheRequirement, is(true));
    }
    
    @Test
    public void shouldNotPassTheRequirementIfThereIsNoUppercaseLetter() throws Exception {
	boolean rechedTheRequirement = uppercaseLetterPresenceRequirement.isTheMinimumRequirementReachedIn("123abc#%$");
	assertThat(rechedTheRequirement, is(false));
    }

}
