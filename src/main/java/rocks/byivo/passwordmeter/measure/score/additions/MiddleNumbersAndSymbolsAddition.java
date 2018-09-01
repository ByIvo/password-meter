package rocks.byivo.passwordmeter.measure.score.additions;

import org.springframework.stereotype.Service;

import rocks.byivo.passwordmeter.utils.RegexMatchCounter;

@Service
public class MiddleNumbersAndSymbolsAddition implements PasswordScoreAddition {

    private static final String SYMBOL_REGEX = "[^a-z^A-Z^ ]";

    public long getTotalBonusFrom(String rawPassword) {
	if(rawPassword.length() > 2) {
	    return countMiddleSymbolsAndNumbers(rawPassword);
	} else {
	    return 0;
	}
    }

    private long countMiddleSymbolsAndNumbers(String rawPassword) {
	String middlePassword = removeFirstAndLastCharFrom(rawPassword);
	
	long symbolsCount = countAllNumbersAndSymbolsIn(middlePassword);
	
	return symbolsCount * 2;
    }

    private String removeFirstAndLastCharFrom(String rawPassword) {
	return rawPassword.substring(1, rawPassword.length() -1);
    }

    private long countAllNumbersAndSymbolsIn(String middlePassword) {
	RegexMatchCounter regexMatchCounter = new RegexMatchCounter(SYMBOL_REGEX);
	return regexMatchCounter.countMatchesIn(middlePassword);
    }

}
