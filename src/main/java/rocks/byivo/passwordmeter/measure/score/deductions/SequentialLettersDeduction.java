package rocks.byivo.passwordmeter.measure.score.deductions;

import org.springframework.stereotype.Service;

import rocks.byivo.passwordmeter.utils.sequence.LetterSequenceVerifier;
import rocks.byivo.passwordmeter.utils.sequence.SequenceVerifier;

@Service
public class SequentialLettersDeduction implements PasswordScoreDeduction {

    @Override
    public long getTotalBonusFrom(String rawPassword) {
	SequenceVerifier sequenceVerifier = new LetterSequenceVerifier(rawPassword);
	int greaterSequenceInPassword = sequenceVerifier.getSizeOfGreaterBothWaySequence();
	
	return scoreFromSequencesSize(greaterSequenceInPassword);
    }

    private long scoreFromSequencesSize(int greaterSequenceSize) {
	if (greaterSequenceSize == 0) {
	    return 0;
	} else {
	    int sequenceSizeStartingFromSecondChar = greaterSequenceSize - 1;
	    return sequenceSizeStartingFromSecondChar * 3;
	}
    }
}
