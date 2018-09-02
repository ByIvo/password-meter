package rocks.byivo.passwordmeter.measure.score.requiremets;

import org.springframework.stereotype.Service;

import rocks.byivo.passwordmeter.model.Requirement;

@Service
public class MinimumLengthRequirement implements PasswordRequirement{

    @Override
    public Requirement getRequirement() {
	return Requirement.HAS_MINIMUM_LENGTH;
    }

    @Override
    public boolean isTheMinimumRequirementReachedIn(String rawPassword) {
	return rawPassword.length() >= 8;
    }

}
