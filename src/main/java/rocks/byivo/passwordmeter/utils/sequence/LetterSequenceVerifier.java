package rocks.byivo.passwordmeter.utils.sequence;

public class LetterSequenceVerifier extends SequenceVerifier {

    public LetterSequenceVerifier(String content) {
	super(content);
    }

    @Override
    protected boolean canMakeASequenceWith(char actualChar) {
	return actualChar >= 'A' && actualChar <= 'z';
    }

    @Override
    protected boolean isActualCharASequenceWithTheLast(char lastChar, char actualChar) {
	return lastChar + 1 == actualChar;
    }

}
