package rocks.byivo.passwordmeter.measure.score.deductions;

import org.springframework.stereotype.Service;

import rocks.byivo.passwordmeter.utils.RegexMatchCounter;

@Service
public class NumbersOnlyDeduction implements PasswordScoreDeduction {

    private static final String NUMBER_COUNTER_REGEX = "[\\d ]";
    
    @Override
    public long getTotalBonusFrom(String rawPassword) {
	boolean isOnlySpacesPassword = isOnlySpacesPassword(rawPassword);

	if (isOnlySpacesPassword) {
	    return 0;
	} else {
	    return scoreAllNumbersAndSpaceIn(rawPassword);
	}
    }

    private boolean isOnlySpacesPassword(String rawPassword) {
	return rawPassword.trim().isEmpty();
    }

    private long scoreAllNumbersAndSpaceIn(String rawPassword) {
	long countOfNumbersAndSpaces = countNumbersAndSpacesIn(rawPassword);
	
	boolean passwordIsOnlyNumbersAndSpaces = countOfNumbersAndSpaces == rawPassword.length();
	
	if(passwordIsOnlyNumbersAndSpaces) {
	    return rawPassword.length();
	} else {
	    return 0;
	}
    }

    private long countNumbersAndSpacesIn(String rawPassword) {
	RegexMatchCounter regexMatchCounter = new RegexMatchCounter(NUMBER_COUNTER_REGEX);
	return regexMatchCounter.countMatchesIn(rawPassword);
    }

}
