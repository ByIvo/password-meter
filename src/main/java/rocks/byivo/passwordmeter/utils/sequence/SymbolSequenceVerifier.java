package rocks.byivo.passwordmeter.utils.sequence;

public class SymbolSequenceVerifier extends SequenceVerifier {

    private static final String SYMBOLS_SEQUENCE = ")!@#$%^&*";

    public SymbolSequenceVerifier(String content) {
	super(content);
    }

    @Override
    protected boolean canMakeASequenceWith(char actualChar) {
	return SYMBOLS_SEQUENCE.contains(actualChar + "");
    }

    @Override
    protected boolean isActualCharASequenceWithTheLast(char lastChar, char actualChar) {
	int indexOfLastChar = SYMBOLS_SEQUENCE.indexOf(lastChar);
	int indexOfActualChar = SYMBOLS_SEQUENCE.indexOf(actualChar);
	
	if(indexOfLastChar == -1) {
	    return false;
	} else {
	    return indexOfLastChar + 1 == indexOfActualChar;
	}
    }

}
