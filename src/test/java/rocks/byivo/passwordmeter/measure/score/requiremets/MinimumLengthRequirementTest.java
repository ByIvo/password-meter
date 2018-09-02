package rocks.byivo.passwordmeter.measure.score.requiremets;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import rocks.byivo.passwordmeter.model.Requirement;

public class MinimumLengthRequirementTest {
    
    private MinimumLengthRequirement minimumLengthRequirement;
    
    @Before
    public void setUp() throws Exception {
	minimumLengthRequirement = new MinimumLengthRequirement();
    }

    @Test
    public void shouldReturnTheHasMinimumLengthRequirementAsItsType() {
	assertThat(minimumLengthRequirement.getRequirement(), is(Requirement.HAS_MINIMUM_LENGTH));
    }

    @Test
    public void shouldReachTheRequirementsWhenItsLengthIs8Characters() throws Exception {
	boolean passedRequirement = minimumLengthRequirement.isTheMinimumRequirementReachedIn("12345678");
	assertThat(passedRequirement, is(true));
    }
    
    @Test
    public void shouldReachTheRequirementsWhenItsLengthIsGreaterThan8Characters() throws Exception {
	boolean passedRequirement = minimumLengthRequirement.isTheMinimumRequirementReachedIn("0123456789876543210");
	assertThat(passedRequirement, is(true));
    }
    
    @Test
    public void shouldNotReachIfTheSizeIsLessThan8Characters() throws Exception {
	boolean passedRequirement = minimumLengthRequirement.isTheMinimumRequirementReachedIn("1234567");
	assertThat(passedRequirement, is(false));
    }
}
