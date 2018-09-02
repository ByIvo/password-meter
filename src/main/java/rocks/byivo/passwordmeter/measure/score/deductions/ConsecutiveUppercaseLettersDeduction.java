package rocks.byivo.passwordmeter.measure.score.deductions;

import org.springframework.stereotype.Service;

import rocks.byivo.passwordmeter.utils.consecutive.ConsecutiveUppercaseVerifier;
import rocks.byivo.passwordmeter.utils.consecutive.ConsecutiveVerifier;

@Service
public class ConsecutiveUppercaseLettersDeduction implements PasswordScoreDeduction {

    @Override
    public long getTotalBonusFrom(String rawPassword) {
	ConsecutiveVerifier sequenceVerifier = new ConsecutiveUppercaseVerifier();
	return sequenceVerifier.countSequencesIn(rawPassword) * 2;
    }

}
