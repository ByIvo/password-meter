package rocks.byivo.passwordmeter.measure.score.requiremets;

import org.springframework.stereotype.Service;

import rocks.byivo.passwordmeter.model.Requirement;
import rocks.byivo.passwordmeter.utils.RegexMatchCounter;

@Service
public class UppercaseLetterPresenceRequirement implements PasswordRequirement{

    private static final String UPPERCASE_PATTERN = "[A-Z]";

    @Override
    public Requirement getRequirement() {
	return Requirement.HAS_UPPERCASE_LETTER;
    }

    @Override
    public boolean isTheMinimumRequirementReachedIn(String rawPassword) {
	RegexMatchCounter regexMatchCounter = new RegexMatchCounter(UPPERCASE_PATTERN);
	long countOfUppercaseLetters = regexMatchCounter.countMatchesIn(rawPassword);
	return countOfUppercaseLetters > 0;
    }

}
