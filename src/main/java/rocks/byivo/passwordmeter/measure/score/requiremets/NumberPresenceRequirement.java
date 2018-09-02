package rocks.byivo.passwordmeter.measure.score.requiremets;

import org.springframework.stereotype.Service;

import rocks.byivo.passwordmeter.model.Requirement;
import rocks.byivo.passwordmeter.utils.RegexMatchCounter;

@Service
public class NumberPresenceRequirement implements PasswordRequirement{

    private static final String NUMBER_PATTERN = "[\\d]";

    @Override
    public Requirement getRequirement() {
	return Requirement.HAS_NUMBERS;
    }

    @Override
    public boolean isTheMinimumRequirementReachedIn(String rawPassword) {
	RegexMatchCounter regexMatchCounter = new RegexMatchCounter(NUMBER_PATTERN);
	long countOfNumbers = regexMatchCounter.countMatchesIn(rawPassword);
	return countOfNumbers > 0;
    }

}
