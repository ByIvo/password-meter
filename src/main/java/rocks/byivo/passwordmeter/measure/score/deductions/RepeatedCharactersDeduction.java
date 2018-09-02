package rocks.byivo.passwordmeter.measure.score.deductions;

import org.springframework.stereotype.Service;

@Service
public class RepeatedCharactersDeduction implements PasswordScoreDeduction {

    @Override
    public long getTotalBonusFrom(String rawPassword) {
	String passwordWithNoSpaces = rawPassword.replace(" ", "");
	return calculateScoreFrom(passwordWithNoSpaces);
    }

    private long calculateScoreFrom(String normalizedPassword) {
	int passwordLength = normalizedPassword.length();
	RepetetitionFinalScore finalScore = new RepetetitionFinalScore(passwordLength);
	
	for (int i = 0; i < passwordLength; i++) {

	    InnerSequenceScore resultOfSumByRepeatedDistance = findAllRepetitionsOfActualIndexCharacter(normalizedPassword, i);
	    if (resultOfSumByRepeatedDistance.foundRepeated()) {
		finalScore = finalScore.sumRepeatedScore(resultOfSumByRepeatedDistance.totalInnerScore);
	    }
	}

	return (int) finalScore.finalScore;
    }

    private InnerSequenceScore findAllRepetitionsOfActualIndexCharacter(String rawPassword, int indexOnMainLoop) {
	InnerSequenceScore result = new InnerSequenceScore();
	int passwordLength = rawPassword.length();
	char actualOnMainLoop = rawPassword.charAt(indexOnMainLoop);
	
	for (int indexOnInnerLoop = 0; indexOnInnerLoop < passwordLength; indexOnInnerLoop++) {
	    char actualOnInnerLoop = rawPassword.charAt(indexOnInnerLoop);
	    
	    boolean isRepetitionsInDiferentIndex = actualOnMainLoop == actualOnInnerLoop && indexOnMainLoop != indexOnInnerLoop;
	    if (isRepetitionsInDiferentIndex) {
		double absoluteValueBasedOnDistance = Math.abs(passwordLength * 1.0 / (indexOnInnerLoop - indexOnMainLoop));
		result = result.addAbsoluteNumberOfRepetitionDistance(absoluteValueBasedOnDistance);
	    }
	}
	
	return result;
    }

    private final class InnerSequenceScore {

	final boolean foundRepeated;
	final double totalInnerScore;

	private InnerSequenceScore() {
	    foundRepeated = false;
	    totalInnerScore = .0;
	}

	public InnerSequenceScore(boolean foundRepeated, double totalInnerScore) {
	    super();
	    this.foundRepeated = foundRepeated;
	    this.totalInnerScore = totalInnerScore;
	}

	private final InnerSequenceScore addAbsoluteNumberOfRepetitionDistance(double newScore) {
	    double summedScore = totalInnerScore + newScore;
	    return new InnerSequenceScore(true, summedScore);
	}
	
	private final boolean foundRepeated() {
	    return this.foundRepeated;
	}
    }
    
    private final class RepetetitionFinalScore {

	final int countOfRepetitions;
	final double finalScore;
	final int passwordLength;

	private RepetetitionFinalScore(int passwordLength) {
	    this.passwordLength = passwordLength;
	    countOfRepetitions = 0;
	    finalScore = .0;
	}

	public RepetetitionFinalScore(int countOfRepetitions, double finalScore, int passwordLength) {
	    this.countOfRepetitions = countOfRepetitions;
	    this.finalScore = finalScore;
	    this.passwordLength = passwordLength;
	}

	private final RepetetitionFinalScore sumRepeatedScore(double newScore) {
	    double summedScore = finalScore + newScore;
	    int newNumberOfRepetitions = countOfRepetitions + 1;

	    int countOfUniqueChars = passwordLength - newNumberOfRepetitions;
	    if (countOfUniqueChars > 0) {
		summedScore = summedScore / countOfUniqueChars;
	    }

	    return new RepetetitionFinalScore(newNumberOfRepetitions, Math.ceil(summedScore), passwordLength);
	}
    }

}
