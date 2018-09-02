package rocks.byivo.passwordmeter.utils.consecutive;

public abstract class ConsecutiveVerifier {

    private boolean lastCharWasASequenceCandidate;
    
    public ConsecutiveVerifier() {
	this.lastCharWasASequenceCandidate = false;
    }
    
    public long countSequencesIn(String strCandidate) {
	String strCandidateWithoutSpaces = strCandidate.replace(" ", "");
	return countSequencesInWithNormalizedInput(strCandidateWithoutSpaces);
    }

    private long countSequencesInWithNormalizedInput(String strCandidateWithoutSpaces) {
	long consecutiveCount = 0;

	for (int i = 0; i < strCandidateWithoutSpaces.length(); i++) {
	    char actualChar = strCandidateWithoutSpaces.charAt(i);
	    
	    if (this.lastCharIsASequenceWith(actualChar)) {
		consecutiveCount++;
	    }
	}

	return consecutiveCount;
    }

    private boolean lastCharIsASequenceWith(char actual) {
	boolean isASequenceCandidate = canFormASequenceWith(actual);
	
	boolean isAFormedSequence = isASequenceCandidate && lastCharWasASequenceCandidate;
	
	lastCharWasASequenceCandidate = isASequenceCandidate;
	
	return isAFormedSequence;
    }
    
    protected abstract boolean canFormASequenceWith(char actualChar);
}
