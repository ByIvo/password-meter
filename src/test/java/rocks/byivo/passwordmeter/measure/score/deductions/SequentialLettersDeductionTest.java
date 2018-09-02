package rocks.byivo.passwordmeter.measure.score.deductions;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class SequentialLettersDeductionTest {

    private SequentialLettersDeduction sequentialLettersDeduction;
    
    @Before
    public void setUp() throws Exception {
	sequentialLettersDeduction = new SequentialLettersDeduction();
    }

    @Test
    public void shouldCountAllSequentialLettersGreaterThan2AndScore3PointsForEachRestElementOfSequence() {
	long sequenceCounter = sequentialLettersDeduction.getTotalBonusFrom("abcdefg");
	assertThat(sequenceCounter, is(15L));
    }
    
    @Test
    public void shouldScoreAsWellEvenIfTheSequenceIsBackwards() {
	long sequenceCounter = sequentialLettersDeduction.getTotalBonusFrom("gfedcba");
	assertThat(sequenceCounter, is(15L));
    }
    
    @Test
    public void theSpacesShouldInterfeerInSequenceCount() {
	long sequenceCounter = sequentialLettersDeduction.getTotalBonusFrom("ab cd ef g");
	assertThat(sequenceCounter, is(0L));
    }
    
    @Test
    public void whenThereIsMoreThanOneSequenceOnlyTheGreaterWillBeScored() {
	long sequenceCounter = sequentialLettersDeduction.getTotalBonusFrom("abcdefg abc");
	assertThat(sequenceCounter, is(15L));
    }
    
    @Test
    public void whenThereIsMoreThanOneSequenceOnlyTheGreaterWillBeScoredEvenIfTheGreaterIsInTheFinalOfPassword() {
	long sequenceCounter = sequentialLettersDeduction.getTotalBonusFrom("abc abcde ");
	assertThat(sequenceCounter, is(9L));
    }
    
    @Test
    public void theProcessShouldBeCaseInsensitiveAndScoreAllLetters() {
	long sequenceCounter = sequentialLettersDeduction.getTotalBonusFrom("abCDeF");
	assertThat(sequenceCounter, is(12L));
    }
    
    @Test
    public void shouldScore0IfThereIsNoCandidateSequence() {
	long sequenceCounter = sequentialLettersDeduction.getTotalBonusFrom("a12b23c5d");
	assertThat(sequenceCounter, is(0l));
    }
    
    @Test
    public void shouldScore0IfThereIsNoSequenceOfLetters() {
	long sequenceCounter = sequentialLettersDeduction.getTotalBonusFrom("adegh");
	assertThat(sequenceCounter, is(0l));
    }

}
