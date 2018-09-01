package rocks.byivo.passwordmeter.measure.score.additions;

import org.springframework.stereotype.Service;

import rocks.byivo.passwordmeter.utils.RegexMatchCounter;

@Service
public class SymbolsAddition implements PasswordScoreAddition {

    private static final String SYMBOL_REGEX = "[^\\w^ ]";

    public long getTotalBonusFrom(String rawPassword) {
	long symbolsCount = countAllSymbolsIn(rawPassword);
	return symbolsCount * 6;
    }

    private long countAllSymbolsIn(String rawPassword) {
	RegexMatchCounter regexMatchCounter = new RegexMatchCounter(SYMBOL_REGEX);
	return regexMatchCounter.countMatchesIn(rawPassword);
    }

}
