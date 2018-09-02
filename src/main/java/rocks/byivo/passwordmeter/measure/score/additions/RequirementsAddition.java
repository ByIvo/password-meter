package rocks.byivo.passwordmeter.measure.score.additions;

import static rocks.byivo.passwordmeter.model.Requirement.HAS_MINIMUM_LENGTH;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rocks.byivo.passwordmeter.measure.score.requiremets.RequirementChecker;
import rocks.byivo.passwordmeter.model.Requirement;

@Service
public class RequirementsAddition implements PasswordScoreAddition {

    private RequirementChecker requirementChecker;

    @Autowired
    public RequirementsAddition(RequirementChecker requirementChecker) {
	this.requirementChecker = requirementChecker;
    }
    
    @Override
    public long getTotalBonusFrom(String rawPassword) {
	List<Requirement> passedRequirements = requirementChecker.checkForReachedRequirementsIn(rawPassword);
	
	boolean hasTheMinimumRequirements = hasTheMinimumRequirementsToScore(passedRequirements);
	
	if(hasTheMinimumRequirements) {
	    return passedRequirements.size() * 2;
	} else {
	    return 0;
	}
    }

    private boolean hasTheMinimumRequirementsToScore(List<Requirement> passedRequirements) {
	boolean hasMinimumLength = passedRequirements.contains(HAS_MINIMUM_LENGTH);
	boolean passedOtherThreeRequirements = passedRequirements.size() > 3;
	
	return hasMinimumLength && passedOtherThreeRequirements;
    }

}
