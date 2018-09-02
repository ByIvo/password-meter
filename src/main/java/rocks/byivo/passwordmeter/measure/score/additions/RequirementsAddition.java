package rocks.byivo.passwordmeter.measure.score.additions;

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
	return passedRequirements.size() * 2;
    }

}
