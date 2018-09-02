package rocks.byivo.passwordmeter.measure.score.requiremets;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

import rocks.byivo.passwordmeter.model.Requirement;

public class NumberPresenceRequirementTest {

    private NumberPresenceRequirement numberPresenceRequirement;
    
    @Before
    public void setUp() throws Exception {
	numberPresenceRequirement = new NumberPresenceRequirement();
    }

    @Test
    public void shouldReturnTheHasNumberWhenGetsItsRequrement() {
	assertThat(numberPresenceRequirement.getRequirement(), is(Requirement.HAS_NUMBERS));
    }
    
    @Test
    public void shouldPassTheRequirementWhenHasAtLeastOneNumber() throws Exception {
	boolean rechedTheRequirement = numberPresenceRequirement.isTheMinimumRequirementReachedIn("as@#1aBC");
	assertThat(rechedTheRequirement, is(true));
    }
    
    @Test
    public void shouldNotPassTheRequirementIfThereIsNoNumber() throws Exception {
	boolean rechedTheRequirement = numberPresenceRequirement.isTheMinimumRequirementReachedIn("asdABC#%$");
	assertThat(rechedTheRequirement, is(false));
    }

}
