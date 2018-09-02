package rocks.byivo.passwordmeter.model;

import static rocks.byivo.passwordmeter.model.PasswordComplexity.TOO_SHORT;

public class PasswordMeasureResult {

    public static final PasswordMeasureResult NOT_POSSIBLE_TO_MEASURE;
    
    static {
	NOT_POSSIBLE_TO_MEASURE = new PasswordMeasureResult(0, TOO_SHORT);
    }
    
    private final long score;
    private final PasswordComplexity complexity;

    public PasswordMeasureResult(long score, PasswordComplexity complexity) {
	super();
	this.score = score;
	this.complexity = complexity;
    }

    public long getScore() {
	return score;
    }

    public PasswordComplexity getComplexity() {
	return complexity;
    }

}
