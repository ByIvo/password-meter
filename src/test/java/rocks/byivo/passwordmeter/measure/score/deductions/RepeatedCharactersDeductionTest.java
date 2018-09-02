package rocks.byivo.passwordmeter.measure.score.deductions;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class RepeatedCharactersDeductionTest {
    
    private RepeatedCharactersDeduction repeatedCharactersDeduction;
    
    @Before
    public void setUp() throws Exception {
	repeatedCharactersDeduction = new RepeatedCharactersDeduction();
    }

    @Test
    public void scoreAmountIfBasedOnPasswordLengthDividedByDifferenceOfDistanceBetweenIdenticalCharacters() {
	long totalDeductionFromPassword = repeatedCharactersDeduction.getTotalBonusFrom("aa");
	assertThat(totalDeductionFromPassword, is(4L));
    }
    
    @Test
    public void theScorShouldBeSmallerIfTheDistanceBetweenIdenticalCharactersAreBig() {
	long totalDeductionFromPassword = repeatedCharactersDeduction.getTotalBonusFrom("a1234a");
	assertThat(totalDeductionFromPassword, is(1l));
    }
    
    @Test
    public void theScoreGrowsWayBiggerIfThereIsManyOfOneCharacter() {
	long totalDeductionFromPassword = repeatedCharactersDeduction.getTotalBonusFrom("aaaaaaaaaa");
	assertThat(totalDeductionFromPassword, is(97l));
    }
    
    @Test
    public void theScoreDoesNotGrowTooMuchIfThereIsManySmallRepetitions() {
	long totalDeductionFromPassword = repeatedCharactersDeduction.getTotalBonusFrom("abcdeabcde");
	assertThat(totalDeductionFromPassword, is(6l));
    }
    
    @Test
    public void theRepetitionsCheckerProcessShouldBeCaseSensitive() {
	long totalDeductionFromPassword = repeatedCharactersDeduction.getTotalBonusFrom("AaAaA");
	assertThat(totalDeductionFromPassword, is(11l));
    }
    
    @Test
    public void theRepetitionsShouldAlsoScoreSymbols() {
	long totalDeductionFromPassword = repeatedCharactersDeduction.getTotalBonusFrom("####");
	assertThat(totalDeductionFromPassword, is(25l));
    }
    
    @Test
    public void theRepetitionsShouldAlsoScoreNumbers() {
	long totalDeductionFromPassword = repeatedCharactersDeduction.getTotalBonusFrom("1111");
	assertThat(totalDeductionFromPassword, is(25l));
    }
    
    @Test
    public void shouldNotConsiderSpacesAsRepetitions() throws Exception {
	long totalDeductionFromPassword = repeatedCharactersDeduction.getTotalBonusFrom("           ");
	assertThat(totalDeductionFromPassword, is(0l));
    }
}
