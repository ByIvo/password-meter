package rocks.byivo.passwordmeter.utils.sequence;

import java.util.Arrays;
import java.util.function.BinaryOperator;

public abstract class SequenceVerifier {

    private static final String NOT_SEQUENCE_FLAG = "0";
    private static final String SEQUENCE_FLAG = "1";
    private String content;
    
    public SequenceVerifier(String content) {
	this.content = content;
    }

    public int getSizeOfGreaterBothWaySequence() {
	String normalizedContent = normalizeContentToFindSequenceEvenInBackwards();

	String[] splittedSequences = splitAllSequencesIn(normalizedContent);

	return findTheGreatestSequenceIn(splittedSequences);
    }

    private String normalizeContentToFindSequenceEvenInBackwards() {
	String lowercaseContent = content.toLowerCase();
	String reversedLowercaseContent = new StringBuilder(lowercaseContent).reverse().toString();

	return lowercaseContent + reversedLowercaseContent;
    }

    private String[] splitAllSequencesIn(String sequenceCandidate) {
	StringBuilder sequenceFlagger = new StringBuilder();
	
	char lastChar = 0;
	for (int i = 0; i < sequenceCandidate.length(); i++) {
	    
	    char actualChar = sequenceCandidate.charAt(i);
	    boolean isNextCharInSequence = isActualCharASequenceWithTheLast(lastChar, actualChar);
	    
	    if (canMakeASequenceWith(actualChar) && isNextCharInSequence) {
		sequenceFlagger.append(SEQUENCE_FLAG);
	    } else {
		sequenceFlagger.append(NOT_SEQUENCE_FLAG);
	    }

	    lastChar = actualChar;
	}

	return sequenceFlagger.toString().split(NOT_SEQUENCE_FLAG);
    }

    private int findTheGreatestSequenceIn(String[] sequencesFoundInPassword) {
	String greaterSequence = Arrays.stream(sequencesFoundInPassword)
		.reduce(toGreatestSequence())
		.orElse("");

	return greaterSequence.length();
    }
    
    private BinaryOperator<String> toGreatestSequence() {
	return (last, actual) -> {
	    boolean lastSequenceGreaterThanActual = last.length() > actual.length();
	    return lastSequenceGreaterThanActual ? last : actual;
	};
    }
    
    protected abstract boolean canMakeASequenceWith(char actualChar);

    protected abstract boolean isActualCharASequenceWithTheLast(char lastChar, char actualChar);
}
