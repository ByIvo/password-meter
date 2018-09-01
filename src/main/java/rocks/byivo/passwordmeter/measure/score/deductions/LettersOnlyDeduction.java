package rocks.byivo.passwordmeter.measure.score.deductions;

import org.springframework.stereotype.Service;

@Service
public class LettersOnlyDeduction implements PasswordScoreDeduction {

    @Override
    public long getTotalBonusFrom(String rawPassword) {
	return 0;
    }

}
