package rocks.byivo.passwordmeter.measure.score.deductions;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ConsecutiveUppercaseLettersDeductionTest {
    
    private ConsecutiveUppercaseLettersDeduction consecutiveUppercaseLettersDeduction;
    
    @Before
    public void setUp() throws Exception {
	consecutiveUppercaseLettersDeduction = new ConsecutiveUppercaseLettersDeduction();
    }

    @Test
    public void shouldScoreTwoPointsForEachConsecutiveUppercaseLetterIgnoringTheFirst() {
	long consecutivesUppercaseLetters = consecutiveUppercaseLettersDeduction.getTotalBonusFrom("aaBBBccc");
	assertThat(consecutivesUppercaseLetters, is(4l));
    }
    
    @Test
    public void shouldRemoveTheSpacesBeforeCountingConsecutivesUppercaseLetters() {
	long consecutivesUppercaseLetters = consecutiveUppercaseLettersDeduction.getTotalBonusFrom("aaB BBBccc");
	assertThat(consecutivesUppercaseLetters, is(6l));
    }
    
    @Test
    public void shouldScoreTheConsecutiveUppercaseLettersEvenIfTheyAreDifferent() {
	long consecutivesUppercaseLetters = consecutiveUppercaseLettersDeduction.getTotalBonusFrom("aaA BCDE");
	assertThat(consecutivesUppercaseLetters, is(8l));
    }
    
    @Test
    public void ifThereIsTwoSectionsOfConsectiveUppercaseLettersIfShouldScoreOnlyTheConsectuivesAndIgnoreTheFirst() {
	long consecutivesUppercaseLetters = consecutiveUppercaseLettersDeduction.getTotalBonusFrom("abCDEfGHIJkL");
	assertThat(consecutivesUppercaseLetters, is(10l));
    }
    
    @Test
    public void ifThereIsNoConsecutiveUppercaseLetersShouldScore0() {
	long consecutivesUppercaseLetters = consecutiveUppercaseLettersDeduction.getTotalBonusFrom("aBcD1F2H3Kl");
	assertThat(consecutivesUppercaseLetters, is(0l));
    }
    
    @Test
    public void shouldScoreZeroInAEmptyPassword() {
	long consecutivesUppercaseLetters = consecutiveUppercaseLettersDeduction.getTotalBonusFrom("");
	assertThat(consecutivesUppercaseLetters, is(0l));
    }

}
