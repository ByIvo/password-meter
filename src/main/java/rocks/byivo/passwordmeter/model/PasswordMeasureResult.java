package rocks.byivo.passwordmeter.model;

public class PasswordMeasureResult {

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
