package rocks.byivo.passwordmeter.measure.score.additions;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static rocks.byivo.passwordmeter.model.Requirement.*;

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
		HAS_UPPERCASE_LETTER,
		HAS_MINIMUM_LENGTH);
	
	when(requirementChecker.checkForReachedRequirementsIn(RAW_PASSWORD)).thenReturn(passedRequirements);
	
	long bonusForPassedRequirements = requirementsAddition.getTotalBonusFrom(RAW_PASSWORD);
	assertThat(bonusForPassedRequirements, is(8L));
    }
    
    @Test
    public void shouldMaximumScoreWhenAllRequirementsPasses() {
	List<Requirement> passedRequirements = Arrays.asList(Requirement.values());
	
	when(requirementChecker.checkForReachedRequirementsIn(RAW_PASSWORD)).thenReturn(passedRequirements);
	
	long bonusForPassedRequirements = requirementsAddition.getTotalBonusFrom(RAW_PASSWORD);
	assertThat(bonusForPassedRequirements, is(10L));
    }
    
    @Test
    public void shouldScoreNoneIfMinimunLengthIsNotPresent() throws Exception {
	List<Requirement> passedRequirements = Arrays.asList(
		HAS_LOWERCASE_LETTER, 
		HAS_SYMBOLS, 
		HAS_UPPERCASE_LETTER);
	
	when(requirementChecker.checkForReachedRequirementsIn(RAW_PASSWORD)).thenReturn(passedRequirements);
	
	long bonusForPassedRequirements = requirementsAddition.getTotalBonusFrom(RAW_PASSWORD);
	assertThat(bonusForPassedRequirements, is(0L));
    }
    
    @Test
    public void shouldScoreNoneIfDidNotPassInThreeDifferentRequirementsBesidesTheMinimumLength() throws Exception {
	List<Requirement> passedRequirements = Arrays.asList(
		HAS_LOWERCASE_LETTER,  
		HAS_UPPERCASE_LETTER,
		HAS_MINIMUM_LENGTH);
	
	when(requirementChecker.checkForReachedRequirementsIn(RAW_PASSWORD)).thenReturn(passedRequirements);
	
	long bonusForPassedRequirements = requirementsAddition.getTotalBonusFrom(RAW_PASSWORD);
	assertThat(bonusForPassedRequirements, is(0L));
    }
    
}
