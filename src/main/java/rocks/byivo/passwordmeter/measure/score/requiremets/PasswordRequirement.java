package rocks.byivo.passwordmeter.measure.score.requiremets;

import rocks.byivo.passwordmeter.model.Requirement;

public interface PasswordRequirement {

    Requirement getRequirement();
    
    boolean isTheMinimumRequirementReachedIn(String rawPassword);
}
