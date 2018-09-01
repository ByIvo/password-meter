package rocks.byivo.passwordmeter.measure.score.additions;

import org.springframework.stereotype.Service;

import rocks.byivo.passwordmeter.utils.RegexMatchCounter;

@Service
public class LowercaseLettersAddition implements PasswordScoreAddition {

    private static final String LOWER_LETTERS_PATTERN = "[a-z]";

    public long getTotalBonusFrom(String rawPassword) {
	long lowercaseLettersCount = countLowercaseLettersIn(rawPassword);
	
	if(lowercaseLettersCount == 0) {
	    return 0;
	}
	
	return (rawPassword.length() - lowercaseLettersCount) * 2;
    }

    private long countLowercaseLettersIn(String rawPassword) {
	RegexMatchCounter regexMatchCounter = new RegexMatchCounter(LOWER_LETTERS_PATTERN);
	return regexMatchCounter.countMatchesIn(rawPassword);
    }

}
