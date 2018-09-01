package rocks.byivo.passwordmeter.measure.service;

import static rocks.byivo.passwordmeter.model.PasswordComplexity.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rocks.byivo.passwordmeter.measure.score.PasswordScoreCalculator;
import rocks.byivo.passwordmeter.model.PasswordComplexity;
import rocks.byivo.passwordmeter.model.PasswordMeasureRequest;
import rocks.byivo.passwordmeter.model.PasswordMeasureResult;

@Service
public class PasswordMeterServiceImpl implements PasswordMeterService {

    @Autowired
    private PasswordScoreCalculator passwordScoreCalculator;

    public PasswordMeasureResult measure(PasswordMeasureRequest passwordMeasureRequest) {
	String rawPassword = passwordMeasureRequest.getRawPassword();

	long passwordScore = passwordScoreCalculator.calculateScoreOf(rawPassword);
	PasswordComplexity passwordComplexity = chooseComplexityFromScore(passwordScore);

	return new PasswordMeasureResult(passwordScore, passwordComplexity);
    }

    private PasswordComplexity chooseComplexityFromScore(long passwordScore) {
	if (passwordScore < 20) {
	    return VERY_WEAK;
	} else if (passwordScore < 40) {
	    return WEAK;
	} else if (passwordScore < 60) {
	    return GOOD;
	} else if (passwordScore < 80) {
	    return STRONG;
	} else {
	    return VERY_STRONG;
	}
    }

}
