package rocks.byivo.passwordmeter.measure.score;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rocks.byivo.passwordmeter.measure.score.aditions.PasswordScoreAdition;
import rocks.byivo.passwordmeter.measure.score.deductions.PasswordScoreDeduction;

@Service
public class PasswordScoreCalculator {

    private List<PasswordScoreAdition> scoreAditions;
    private List<PasswordScoreDeduction> allDeductions;

    @Autowired
    public PasswordScoreCalculator(List<PasswordScoreAdition> scoreAditions,
	    List<PasswordScoreDeduction> allDeductions) {
	super();
	this.scoreAditions = scoreAditions;
	this.allDeductions = allDeductions;
    }

    public long calculateScoreOf(String rawPassword) {

	long aditionsScore = calculateAllAditionsFrom(rawPassword);
	long deductionsScore = calculateAllDeductionsFrom(rawPassword);

	return aditionsScore - deductionsScore;
    }

    private long calculateAllAditionsFrom(String rawPassword) {
	Optional<Long> counter = scoreAditions.stream()
		.map(adition -> adition.getTotalBonusFrom(rawPassword))
		.reduce((last, actual) -> last + actual);
	
	return counter.get();
    }
    
    private long calculateAllDeductionsFrom(String rawPassword) {
	long counter = 0;
	
	for (PasswordScoreDeduction passwordScoreDeduction : allDeductions) {
	    counter +=passwordScoreDeduction.getTotalBonusFrom(rawPassword);
	}
	
	return counter;
    }
}
