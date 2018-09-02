package rocks.byivo.passwordmeter.measure.service;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static rocks.byivo.passwordmeter.model.PasswordComplexity.GOOD;
import static rocks.byivo.passwordmeter.model.PasswordComplexity.STRONG;
import static rocks.byivo.passwordmeter.model.PasswordComplexity.TOO_SHORT;
import static rocks.byivo.passwordmeter.model.PasswordComplexity.VERY_STRONG;
import static rocks.byivo.passwordmeter.model.PasswordComplexity.VERY_WEAK;
import static rocks.byivo.passwordmeter.model.PasswordComplexity.WEAK;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import rocks.byivo.passwordmeter.measure.score.PasswordScoreCalculator;
import rocks.byivo.passwordmeter.model.PasswordMeasureRequest;
import rocks.byivo.passwordmeter.model.PasswordMeasureResult;

@RunWith(SpringRunner.class)
public class PasswordMeterServiceImplTest {

    @TestConfiguration
    static class PasswordMeterServiceImplTestContextConfiguration {

	@Bean
	public PasswordMeterService employeeService() {
	    return new PasswordMeterServiceImpl();
	}
    }

    private static final String PASSWORD_IN_REQUEST = "pass";

    @Autowired
    private PasswordMeterService passwordMeterService;

    @MockBean
    private PasswordScoreCalculator passwordScoreCalculator;

    private PasswordMeasureRequest passwordRequest;

    @Before
    public void setUp() {
	passwordRequest = new PasswordMeasureRequest(PASSWORD_IN_REQUEST);
    }

    @Test
    public void shouldCalculateTheScoreOfThePassword() {
	passwordMeterService.measure(passwordRequest);
	verify(passwordScoreCalculator).calculateScoreOf(PASSWORD_IN_REQUEST);
    }
    
    @Test
    public void shouldReturnTheCalculatedScoreOfThePassword() throws Exception {
	long scoreOfPassword = 25l;
	whenCallTheScoreCalculatorThenReturn(scoreOfPassword);
	PasswordMeasureResult passwordMeasureResult = passwordMeterService.measure(passwordRequest);

	assertThat(passwordMeasureResult.getScore(), is(25l));
    }

    private void whenCallTheScoreCalculatorThenReturn(long scoreOfPassword) {
	when(passwordScoreCalculator.calculateScoreOf(PASSWORD_IN_REQUEST)).thenReturn(scoreOfPassword);
    }
    
    @Test
    public void whenAnEmptyPasswordIsProvidedShouldReturnA0Score() throws Exception {
	
	PasswordMeasureRequest emptyPasswordRequest = new PasswordMeasureRequest("");
	PasswordMeasureResult passwordMeasureResult = passwordMeterService.measure(emptyPasswordRequest);
	
	assertThat(passwordMeasureResult.getScore(), is(0l));
    }
    
    @Test
    public void whenAnEmptyPasswordIsProvidedTheScoreShouldNotBeCalculated() throws Exception {
	
	PasswordMeasureRequest emptyPasswordRequest = new PasswordMeasureRequest("");
	passwordMeterService.measure(emptyPasswordRequest);
	
	verify(passwordScoreCalculator, never()).calculateScoreOf("");
    }
    
    @Test
    public void whenAnEmptyPasswordIsProvidedShouldReturnATooShortComplexity() throws Exception {
	
	PasswordMeasureRequest emptyPasswordRequest = new PasswordMeasureRequest("");
	PasswordMeasureResult passwordMeasureResult = passwordMeterService.measure(emptyPasswordRequest);
	
	assertThat(passwordMeasureResult.getComplexity(), is(TOO_SHORT));
    }

    @Test
    public void shouldReturnAVeryWeakPasswordWhenTheScoreIs0() throws Exception {
	whenCallTheScoreCalculatorThenReturn(0L);
	PasswordMeasureResult passwordMeasureResult = passwordMeterService.measure(passwordRequest);

	assertThat(passwordMeasureResult.getComplexity(), is(VERY_WEAK));
    }

    @Test
    public void shouldReturnAVeryWeakPasswordWhenTheScoreIsLessThen20() throws Exception {
	whenCallTheScoreCalculatorThenReturn(19l);
	PasswordMeasureResult passwordMeasureResult = passwordMeterService.measure(passwordRequest);

	assertThat(passwordMeasureResult.getComplexity(), is(VERY_WEAK));
    }

    @Test
    public void shouldReturnAWeakPasswordWhenTheScoreIs20() throws Exception {
	whenCallTheScoreCalculatorThenReturn(20l);
	PasswordMeasureResult passwordMeasureResult = passwordMeterService.measure(passwordRequest);

	assertThat(passwordMeasureResult.getComplexity(), is(WEAK));
    }

    @Test
    public void shouldReturnAWeakPasswordWhenTheScoreIsLessThan40() throws Exception {
	whenCallTheScoreCalculatorThenReturn(39l);
	PasswordMeasureResult passwordMeasureResult = passwordMeterService.measure(passwordRequest);

	assertThat(passwordMeasureResult.getComplexity(), is(WEAK));
    }

    @Test
    public void shouldReturnAGoodPasswordWhenTheScoreIs40() throws Exception {
	whenCallTheScoreCalculatorThenReturn(40L);
	PasswordMeasureResult passwordMeasureResult = passwordMeterService.measure(passwordRequest);

	assertThat(passwordMeasureResult.getComplexity(), is(GOOD));
    }

    @Test
    public void shouldReturnAGoodPasswordWhenTheScoreIsLessThan60() throws Exception {
	whenCallTheScoreCalculatorThenReturn(59l);
	PasswordMeasureResult passwordMeasureResult = passwordMeterService.measure(passwordRequest);

	assertThat(passwordMeasureResult.getComplexity(), is(GOOD));
    }

    @Test
    public void shouldReturnAStrongPasswordWhenTheScoreIs60() throws Exception {
	whenCallTheScoreCalculatorThenReturn(60l);
	PasswordMeasureResult passwordMeasureResult = passwordMeterService.measure(passwordRequest);

	assertThat(passwordMeasureResult.getComplexity(), is(STRONG));
    }

    @Test
    public void shouldReturnAStrongPasswordWhenTheScoreIsLessThan80() throws Exception {
	whenCallTheScoreCalculatorThenReturn(79l);
	PasswordMeasureResult passwordMeasureResult = passwordMeterService.measure(passwordRequest);

	assertThat(passwordMeasureResult.getComplexity(), is(STRONG));
    }

    @Test
    public void shouldReturnAVeryStrongPasswordWhenTheScoreIs80() throws Exception {
	whenCallTheScoreCalculatorThenReturn(80l);
	PasswordMeasureResult passwordMeasureResult = passwordMeterService.measure(passwordRequest);

	assertThat(passwordMeasureResult.getComplexity(), is(VERY_STRONG));
    }

    @Test
    public void shouldReturnAVeryStrongPasswordWhenTheScoreIsLessOrEqualThan100() throws Exception {
	whenCallTheScoreCalculatorThenReturn(100l);
	PasswordMeasureResult passwordMeasureResult = passwordMeterService.measure(passwordRequest);

	assertThat(passwordMeasureResult.getComplexity(), is(VERY_STRONG));
    }
    
}
