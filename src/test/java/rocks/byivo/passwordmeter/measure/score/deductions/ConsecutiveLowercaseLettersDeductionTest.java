package rocks.byivo.passwordmeter.measure.score.deductions;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ConsecutiveLowercaseLettersDeductionTest {
    
    private ConsecutiveLowercaseLettersDeduction consecutiveLowercaseLettersDeduction;
    
    @Before
    public void setUp() throws Exception {
	consecutiveLowercaseLettersDeduction = new ConsecutiveLowercaseLettersDeduction();
    }

    @Test
    public void shouldScoreTwoPointsForEachConsecutiveLowercaseLetterIgnoringTheFirst() {
	long consecutivesLowercaseLetters = consecutiveLowercaseLettersDeduction.getTotalBonusFrom("AAbbbCCC");
	assertThat(consecutivesLowercaseLetters, is(4l));
    }
    
    @Test
    public void shouldScoreTheConsecutiveLowercaseLettersEvenIfTheyAreDifferent() {
	long consecutivesLowercaseLetters = consecutiveLowercaseLettersDeduction.getTotalBonusFrom("AAa bcde");
	assertThat(consecutivesLowercaseLetters, is(8l));
    }
    
    @Test
    public void ifThereIsTwoSectionsOfConsectiveLowerLettersIfShouldScoreOnlyTheConsecutivesAndIgnoreTheFirst() {
	long consecutivesLowercaseLetters = consecutiveLowercaseLettersDeduction.getTotalBonusFrom("ABcdeFghijKl");
	assertThat(consecutivesLowercaseLetters, is(10l));
    }
    
    @Test
    public void ifThereIsNoConsecutiveLowercaseLetersShouldScore0() {
	long consecutivesLowercaseLetters = consecutiveLowercaseLettersDeduction.getTotalBonusFrom("AbCd1f2h3kL");
	assertThat(consecutivesLowercaseLetters, is(0l));
    }
    
    @Test
    public void shouldScoreZeroInAEmptyPassword() {
	long consecutivesLowercaseLetters = consecutiveLowercaseLettersDeduction.getTotalBonusFrom("");
	assertThat(consecutivesLowercaseLetters, is(0l));
    }

}
