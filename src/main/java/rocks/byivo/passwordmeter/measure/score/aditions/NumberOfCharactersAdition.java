package rocks.byivo.passwordmeter.measure.score.aditions;

import org.springframework.stereotype.Service;

@Service
public class NumberOfCharactersAdition implements PasswordScoreAdition {

    public long getTotalBonusFrom(String rawPassword) {
	return 100l;
    }

}
