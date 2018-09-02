package rocks.byivo.passwordmeter.utils.sequence;

public class NumberSequenceVerifier extends SequenceVerifier {

    public NumberSequenceVerifier(String content) {
	super(content);
    }

    @Override
    protected boolean canMakeASequenceWith(char actualChar) {
	return actualChar >= '0' && actualChar <= '9';
    }

    @Override
    protected boolean isActualCharASequenceWithTheLast(char lastChar, char actualChar) {
	return lastChar + 1 == actualChar;
    }

}
