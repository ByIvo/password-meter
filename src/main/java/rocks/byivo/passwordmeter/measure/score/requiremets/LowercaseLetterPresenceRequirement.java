package rocks.byivo.passwordmeter.measure.score.requiremets;

import org.springframework.stereotype.Service;

import rocks.byivo.passwordmeter.model.Requirement;
import rocks.byivo.passwordmeter.utils.RegexMatchCounter;

@Service
public class LowercaseLetterPresenceRequirement implements PasswordRequirement{

    private static final String LOWERCASE_PATTERN = "[a-z]";

    @Override
    public Requirement getRequirement() {
	return Requirement.HAS_LOWERCASE_LETTER;
    }

    @Override
    public boolean isTheMinimumRequirementReachedIn(String rawPassword) {
	RegexMatchCounter regexMatchCounter = new RegexMatchCounter(LOWERCASE_PATTERN);
	long countOfLowercaseLetters = regexMatchCounter.countMatchesIn(rawPassword);
	return countOfLowercaseLetters > 0;
    }

}
