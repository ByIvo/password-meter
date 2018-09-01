package rocks.byivo.passwordmeter.measure.score.additions;

import org.springframework.stereotype.Service;

@Service
public class NumberOfCharactersAdition implements PasswordScoreAddition {

    public long getTotalBonusFrom(String rawPassword) {
	return rawPassword.length() * 4;
    }

}
