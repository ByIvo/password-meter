package rocks.byivo.passwordmeter.measure.score.deductions;

import org.springframework.stereotype.Service;

import rocks.byivo.passwordmeter.utils.RegexMatchCounter;

@Service
public class LettersOnlyDeduction implements PasswordScoreDeduction {

    private static final String LETTER_COUNTER_REGEX = "[a-zA-Z ]";
    
    @Override
    public long getTotalBonusFrom(String rawPassword) {
	long countOfLettersAndSpaces = countLettersAndSpaceIn(rawPassword);
	
	boolean passwordIsOnlyLettersAndSpaces = countOfLettersAndSpaces == rawPassword.length();
	
	if(passwordIsOnlyLettersAndSpaces) {
	    return rawPassword.length();
	} else {
	    return 0;
	}
    }

    private long countLettersAndSpaceIn(String rawPassword) {
	RegexMatchCounter regexMatchCounter = new RegexMatchCounter(LETTER_COUNTER_REGEX);
	return regexMatchCounter.countMatchesIn(rawPassword);
    }

}
