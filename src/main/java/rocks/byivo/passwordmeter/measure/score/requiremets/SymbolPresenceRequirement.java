package rocks.byivo.passwordmeter.measure.score.requiremets;

import org.springframework.stereotype.Service;

import rocks.byivo.passwordmeter.model.Requirement;
import rocks.byivo.passwordmeter.utils.RegexMatchCounter;

@Service
public class SymbolPresenceRequirement implements PasswordRequirement{

    private static final String SYMBOL_PATTERN = "[^\\w^ ]";

    @Override
    public Requirement getRequirement() {
	return Requirement.HAS_SYMBOLS;
    }

    @Override
    public boolean isTheMinimumRequirementReachedIn(String rawPassword) {
	RegexMatchCounter regexMatchCounter = new RegexMatchCounter(SYMBOL_PATTERN);
	long countOfSymbols = regexMatchCounter.countMatchesIn(rawPassword);
	return countOfSymbols > 0;
    }

}
