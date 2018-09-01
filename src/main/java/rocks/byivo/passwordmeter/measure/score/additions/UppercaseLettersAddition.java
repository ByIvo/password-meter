package rocks.byivo.passwordmeter.measure.score.additions;

import org.springframework.stereotype.Service;

import rocks.byivo.passwordmeter.utils.RegexMatchCounter;

@Service
public class UppercaseLettersAddition implements PasswordScoreAddition {

    private static final String UPPERCASE_LETTERS_PATTERN = "[A-Z]";

    public long getTotalBonusFrom(String rawPassword) {
	long matchesCount = countAllOcorrencesOfUppercaseLetters(rawPassword);
	
	if(matchesCount == 0l) {
	    return 0l;
	}
	
	return (rawPassword.length() - matchesCount ) * 2;
    }

    private long countAllOcorrencesOfUppercaseLetters(String rawPassword) {
	RegexMatchCounter regexMatchCounter = new RegexMatchCounter(UPPERCASE_LETTERS_PATTERN);
	return regexMatchCounter.countMatchesIn(rawPassword);
    }

}
