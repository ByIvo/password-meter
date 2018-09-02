package rocks.byivo.passwordmeter.measure.score.additions;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static rocks.byivo.passwordmeter.model.Requirement.HAS_LOWERCASE_LETTER;
import static rocks.byivo.passwordmeter.model.Requirement.HAS_SYMBOLS;
import static rocks.byivo.passwordmeter.model.Requirement.HAS_UPPERCASE_LETTER;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import rocks.byivo.passwordmeter.measure.score.requiremets.RequirementChecker;
import rocks.byivo.passwordmeter.model.Requirement;

@RunWith(SpringRunner.class)
public class RequirementsAdditionTest {
    
    private static final String RAW_PASSWORD = "123";

    private RequirementsAddition requirementsAddition;
    
    @Mock
    private RequirementChecker requirementChecker;
    
    @Before
    public void setUp() throws Exception {
	requirementsAddition = new RequirementsAddition(requirementChecker);
    }

    @Test
    public void shouldVerifyForAllPassedRequirements() throws Exception {
	requirementsAddition.getTotalBonusFrom(RAW_PASSWORD);
	verify(requirementChecker).checkForReachedRequirementsIn(RAW_PASSWORD);
    }
    
    @Test
    public void shouldCountAllPassedRequirementsAndMultiplyItBy2() {
	List<Requirement> passedRequirements = Arrays.asList(
		HAS_LOWERCASE_LETTER, 
		HAS_SYMBOLS, 
		HAS_UPPERCASE_LETTER);
	
	when(requirementChecker.checkForReachedRequirementsIn(RAW_PASSWORD)).thenReturn(passedRequirements);
	
	long bonusForPassedRequirements = requirementsAddition.getTotalBonusFrom(RAW_PASSWORD);
	assertThat(bonusForPassedRequirements, is(6L));
    }

}
