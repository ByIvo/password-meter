package rocks.byivo.passwordmeter.measure.score.deductions;

import org.springframework.stereotype.Service;

import rocks.byivo.passwordmeter.utils.consecutive.ConsecutiveLowercaseVerifier;
import rocks.byivo.passwordmeter.utils.consecutive.ConsecutiveVerifier;

@Service
public class ConsecutiveLowercaseLettersDeduction implements PasswordScoreDeduction {

    @Override
    public long getTotalBonusFrom(String rawPassword) {
	ConsecutiveVerifier sequenceVerifier = new ConsecutiveLowercaseVerifier();
	return sequenceVerifier.countSequencesIn(rawPassword) * 2;
    }

}
