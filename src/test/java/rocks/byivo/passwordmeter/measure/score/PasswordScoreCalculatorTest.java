package rocks.byivo.passwordmeter.measure.score;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import rocks.byivo.passwordmeter.measure.score.aditions.PasswordScoreAdition;
import rocks.byivo.passwordmeter.measure.score.deductions.PasswordScoreDeduction;

@RunWith(SpringRunner.class)
public class PasswordScoreCalculatorTest {
    
    private static final String RAW_PASSWORD = "pass";

    private PasswordScoreCalculator passwordScoreCalculator;
    
    @Mock
    private PasswordScoreAdition firstAdition, secondAdition;
    
    @Mock
    private PasswordScoreDeduction firstDeduction, secondDeduction;

    @Before
    public void setUp() {
	List<PasswordScoreAdition> allAditions = Arrays.asList(firstAdition, secondAdition);
	List<PasswordScoreDeduction> allDeductions = Arrays.asList(firstDeduction, secondDeduction);
	
	passwordScoreCalculator = new PasswordScoreCalculator(allAditions, allDeductions);
    }
    
    @Test
    public void shouldLookIntoEveryAditionBeforeCalculateScore() {
	passwordScoreCalculator.calculateScoreOf(RAW_PASSWORD);
	
	verify(firstAdition).getTotalBonusFrom(RAW_PASSWORD);
	verify(secondAdition).getTotalBonusFrom(RAW_PASSWORD);
    }
    
    @Test
    public void shouldLookIntoEveryDeductionBeforeCalculateScore() {
	passwordScoreCalculator.calculateScoreOf(RAW_PASSWORD);
	
	verify(firstDeduction).getTotalBonusFrom(RAW_PASSWORD);
	verify(secondDeduction).getTotalBonusFrom(RAW_PASSWORD);
    }

    @Test
    public void shouldSumAllAditionScore() throws Exception {
	when(firstAdition.getTotalBonusFrom(RAW_PASSWORD)).thenReturn(5l);
	when(secondAdition.getTotalBonusFrom(RAW_PASSWORD)).thenReturn(2l);
	
	long passwordScore = passwordScoreCalculator.calculateScoreOf(RAW_PASSWORD);
	assertThat(passwordScore, is(7l));
    }
    
    @Test
    public void shouldDescreaseAllDeductionScore() throws Exception {
	when(firstAdition.getTotalBonusFrom(RAW_PASSWORD)).thenReturn(5l);
	
	when(firstDeduction.getTotalBonusFrom(RAW_PASSWORD)).thenReturn(1l);
	when(secondDeduction.getTotalBonusFrom(RAW_PASSWORD)).thenReturn(2l);
	
	long passwordScore = passwordScoreCalculator.calculateScoreOf(RAW_PASSWORD);
	assertThat(passwordScore, is(2l));
    }
    
    @Test
    public void shouldReturn0IfTheFinalScoreCalculationIsNegative() throws Exception {
	when(firstDeduction.getTotalBonusFrom(RAW_PASSWORD)).thenReturn(10l);
	long passwordScore = passwordScoreCalculator.calculateScoreOf(RAW_PASSWORD);
	
	assertThat(passwordScore, is(0l));
    }
    
    @Test
    public void shouldReturn100IfTheFinalScoreCalcutionIsGreaterThan100() throws Exception {
	when(firstAdition.getTotalBonusFrom(RAW_PASSWORD)).thenReturn(101l);
	long passwordScore = passwordScoreCalculator.calculateScoreOf(RAW_PASSWORD);
	
	assertThat(passwordScore, is(100l));
    }
}
