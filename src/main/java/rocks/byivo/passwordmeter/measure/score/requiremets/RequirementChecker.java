package rocks.byivo.passwordmeter.measure.score.requiremets;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rocks.byivo.passwordmeter.model.Requirement;

@Service
public class RequirementChecker {

    public final List<PasswordRequirement> requirements;

    @Autowired
    public RequirementChecker(List<PasswordRequirement> requirements) {
	super();
	this.requirements = requirements;
    }

    public List<Requirement> checkForReachedRequirementsIn(String rawPassword) {
	return requirements.stream()
		.filter(verifyIfPasswordReachesMinimumRequirement(rawPassword))
		.map(PasswordRequirement::getRequirement)
		.collect(Collectors.toList());
    }
    
    public Predicate<PasswordRequirement> verifyIfPasswordReachesMinimumRequirement(final String rawPassword) {
	return passRequirement -> passRequirement.isTheMinimumRequirementReachedIn(rawPassword);
    }
    
    
    
    
}
