package rocks.byivo.passwordmeter.measure.service;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import rocks.byivo.passwordmeter.measure.score.PasswordScoreCalculator;
import rocks.byivo.passwordmeter.model.PasswordComplexity;
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
    public void shouldReturnAVeryWeakPasswordWhenTheScoreIs0() throws Exception {
	whenCallTheScoreCalculatorThenReturn(0L);
	PasswordMeasureResult passwordMeasureResult = passwordMeterService.measure(passwordRequest);

	assertThat(passwordMeasureResult.getComplexity(), is(PasswordComplexity.VERY_WEAK));
    }

    @Test
    public void shouldReturnAVeryWeakPasswordWhenTheScoreIsLessThen20() throws Exception {
	whenCallTheScoreCalculatorThenReturn(19l);
	PasswordMeasureResult passwordMeasureResult = passwordMeterService.measure(passwordRequest);

	assertThat(passwordMeasureResult.getComplexity(), is(PasswordComplexity.VERY_WEAK));
    }

    @Test
    public void shouldReturnAWeakPasswordWhenTheScoreIs20() throws Exception {
	whenCallTheScoreCalculatorThenReturn(20l);
	PasswordMeasureResult passwordMeasureResult = passwordMeterService.measure(passwordRequest);

	assertThat(passwordMeasureResult.getComplexity(), is(PasswordComplexity.WEAK));
    }

    @Test
    public void shouldReturnAWeakPasswordWhenTheScoreIsLessThan40() throws Exception {
	whenCallTheScoreCalculatorThenReturn(39l);
	PasswordMeasureResult passwordMeasureResult = passwordMeterService.measure(passwordRequest);

	assertThat(passwordMeasureResult.getComplexity(), is(PasswordComplexity.WEAK));
    }

    @Test
    public void shouldReturnAGoodPasswordWhenTheScoreIs40() throws Exception {
	whenCallTheScoreCalculatorThenReturn(40L);
	PasswordMeasureResult passwordMeasureResult = passwordMeterService.measure(passwordRequest);

	assertThat(passwordMeasureResult.getComplexity(), is(PasswordComplexity.GOOD));
    }

    @Test
    public void shouldReturnAGoodPasswordWhenTheScoreIsLessThan60() throws Exception {
	whenCallTheScoreCalculatorThenReturn(59l);
	PasswordMeasureResult passwordMeasureResult = passwordMeterService.measure(passwordRequest);

	assertThat(passwordMeasureResult.getComplexity(), is(PasswordComplexity.GOOD));
    }

    @Test
    public void shouldReturnAStrongPasswordWhenTheScoreIs60() throws Exception {
	whenCallTheScoreCalculatorThenReturn(60l);
	PasswordMeasureResult passwordMeasureResult = passwordMeterService.measure(passwordRequest);

	assertThat(passwordMeasureResult.getComplexity(), is(PasswordComplexity.STRONG));
    }

    @Test
    public void shouldReturnAStrongPasswordWhenTheScoreIsLessThan80() throws Exception {
	whenCallTheScoreCalculatorThenReturn(79l);
	PasswordMeasureResult passwordMeasureResult = passwordMeterService.measure(passwordRequest);

	assertThat(passwordMeasureResult.getComplexity(), is(PasswordComplexity.STRONG));
    }

    @Test
    public void shouldReturnAVeryStrongPasswordWhenTheScoreIs80() throws Exception {
	whenCallTheScoreCalculatorThenReturn(80l);
	PasswordMeasureResult passwordMeasureResult = passwordMeterService.measure(passwordRequest);

	assertThat(passwordMeasureResult.getComplexity(), is(PasswordComplexity.VERY_STRONG));
    }

    @Test
    public void shouldReturnAVeryStrongPasswordWhenTheScoreIsLessOrEqualThan100() throws Exception {
	whenCallTheScoreCalculatorThenReturn(100l);
	PasswordMeasureResult passwordMeasureResult = passwordMeterService.measure(passwordRequest);

	assertThat(passwordMeasureResult.getComplexity(), is(PasswordComplexity.VERY_STRONG));
    }
    
}
