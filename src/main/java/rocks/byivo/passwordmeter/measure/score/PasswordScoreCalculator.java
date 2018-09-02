package rocks.byivo.passwordmeter.measure.score;

import java.util.List;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rocks.byivo.passwordmeter.measure.score.additions.PasswordScoreAddition;
import rocks.byivo.passwordmeter.measure.score.deductions.PasswordScoreDeduction;

@Service
public class PasswordScoreCalculator {

    private static final long SMALLEST_BOUNDARY = 0l;
    private static final long HIGHEST_BOUNDARY = 100l;
    
    private List<PasswordScoreAddition> scoreAditions;
    private List<PasswordScoreDeduction> scoreDeductions;

    @Autowired
    public PasswordScoreCalculator(List<PasswordScoreAddition> scoreAditions,
	    List<PasswordScoreDeduction> scoreDeductions) {
	super();
	this.scoreAditions = scoreAditions;
	this.scoreDeductions = scoreDeductions;
    }

    public long calculateScoreOf(String rawPassword) {
	long rawScore = calculateRawScore(rawPassword);
	return scoreIntoPercentageBoundaries(rawScore);
    }

    private long calculateRawScore(String rawPassword) {
	long aditionsScore = calculateAllAditionsFrom(rawPassword);
	long deductionsScore = calculateAllDeductionsFrom(rawPassword);

	return aditionsScore - deductionsScore;
    }

    private long calculateAllAditionsFrom(String rawPassword) {
	return sumAllCalculatedBonusFrom(scoreAditions, rawPassword);
    }
    
    private long calculateAllDeductionsFrom(String rawPassword) {
	return sumAllCalculatedBonusFrom(scoreDeductions, rawPassword);
    }
    
    private long sumAllCalculatedBonusFrom(List<? extends PasswordIntoBonus> scorableblePassword, String rawPassword) {
	return scorableblePassword.stream()
		.map(scoreFromPassword(rawPassword))
		.reduce(this::toSumOfAllResults)
		.orElse(0l);
    }
    
    private Function<PasswordIntoBonus, Long> scoreFromPassword(String rawPassword) {
	return passwordIntoBonus -> passwordIntoBonus.getTotalBonusFrom(rawPassword);
    }
    
    private Long toSumOfAllResults(Long last, Long actual) {
	return last + actual;
    }
    
    private long scoreIntoPercentageBoundaries(long rawScore) {
	if(rawScore < SMALLEST_BOUNDARY) {
  	    return SMALLEST_BOUNDARY;
  	} else if(rawScore > HIGHEST_BOUNDARY) {
  	    return HIGHEST_BOUNDARY;
  	} else {
  	    return rawScore;
  	}
  }
}
