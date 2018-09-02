package rocks.byivo.passwordmeter.measure.score.requiremets;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static rocks.byivo.passwordmeter.model.Requirement.HAS_MINIMUM_LENGTH;
import static rocks.byivo.passwordmeter.model.Requirement.HAS_SYMBOLS;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import rocks.byivo.passwordmeter.model.Requirement;

@RunWith(SpringRunner.class)
public class RequirementCheckerTest {
    
    private static final String RAW_PASSWORD = "123";

    private RequirementChecker requirementChecker;
    
    @Mock
    private PasswordRequirement sizeRequirement, numberRequirement, letterRequirement;
    
    @Before
    public void setUp() throws Exception {
	List<PasswordRequirement> allRequirements = Arrays.asList(
		sizeRequirement, 
		numberRequirement,
		letterRequirement );
	
	requirementChecker = new RequirementChecker(allRequirements);
    }

    @Test
    public void shouldCheckAllExistingsRequirements() {
	requirementChecker.checkForReachedRequirementsIn(RAW_PASSWORD);
	
	verify(sizeRequirement).isTheMinimumRequirementReachedIn(RAW_PASSWORD);
	verify(numberRequirement).isTheMinimumRequirementReachedIn(RAW_PASSWORD);
	verify(letterRequirement).isTheMinimumRequirementReachedIn(RAW_PASSWORD);
    }
    
    @Test
    public void shouldReturnOnlyThePasswordRequirementsThatReachesRequirements() {
	String rawPassword = RAW_PASSWORD;
	
	makeMockReturnTheRequirement(sizeRequirement, HAS_SYMBOLS);
	makeMockReturnTheRequirement(letterRequirement, Requirement.HAS_MINIMUM_LENGTH);
	
	List<Requirement> requirementsPassed = requirementChecker.checkForReachedRequirementsIn(rawPassword);
	
	List<Requirement> expecpedRechedRequirements = Arrays.asList(HAS_SYMBOLS, HAS_MINIMUM_LENGTH);
	assertThat(requirementsPassed, is(expecpedRechedRequirements));
    }

    private void makeMockReturnTheRequirement(PasswordRequirement passwordRequirementMock, Requirement requirementType) {
	when(passwordRequirementMock.isTheMinimumRequirementReachedIn(RAW_PASSWORD)).thenReturn(true);
	when(passwordRequirementMock.getRequirement()).thenReturn(requirementType);
    }

}
