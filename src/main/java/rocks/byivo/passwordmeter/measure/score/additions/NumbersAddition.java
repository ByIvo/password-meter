package rocks.byivo.passwordmeter.measure.score.additions;

import org.springframework.stereotype.Service;

import rocks.byivo.passwordmeter.utils.RegexMatchCounter;

@Service
public class NumbersAddition implements PasswordScoreAddition {

    private static final String NUMBER_REGEX = "\\d";

    public long getTotalBonusFrom(String rawPassword) {
	long numbersInPassword = countAllNumbersIn(rawPassword);
	
	boolean passwordComposedOnlyByNumber = numbersInPassword == rawPassword.length();
	
	if(passwordComposedOnlyByNumber) {
	    return 0;
	}
	
	return numbersInPassword * 4;
    }

    private long countAllNumbersIn(String rawPassword) {
	RegexMatchCounter regexMatchCounter = new RegexMatchCounter(NUMBER_REGEX);
	return regexMatchCounter.countMatchesIn(rawPassword);
    }

}
