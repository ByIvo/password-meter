package rocks.byivo.passwordmeter.measure.score.deductions;

import org.springframework.stereotype.Service;

import rocks.byivo.passwordmeter.utils.consecutive.ConsecutiveNumberVerifier;
import rocks.byivo.passwordmeter.utils.consecutive.ConsecutiveVerifier;

@Service
public class ConsecutiveNumbersDeduction implements PasswordScoreDeduction {

    @Override
    public long getTotalBonusFrom(String rawPassword) {
	ConsecutiveVerifier sequenceVerifier = new ConsecutiveNumberVerifier();
	return sequenceVerifier.countSequencesIn(rawPassword) * 2;
    }

}
