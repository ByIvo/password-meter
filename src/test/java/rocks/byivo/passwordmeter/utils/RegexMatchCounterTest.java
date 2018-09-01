package rocks.byivo.passwordmeter.utils;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class RegexMatchCounterTest {
    
    private RegexMatchCounter regexMatchCounter;
    
    @Before
    public void setUp() throws Exception {
	
    }

    @Test
    public void shouldCountAllAppearencesOfTheInformedPattern() {
	regexMatchCounter = new RegexMatchCounter("[a-z]");
	
	long matchCount = regexMatchCounter.countMatchesIn("aaaAAA");
	assertThat(matchCount, is(3l));
    }
    
    @Test
    public void shouldReturn0WhenThereIsNoMatch() {
	regexMatchCounter = new RegexMatchCounter("\\W");
	
	long matchCount = regexMatchCounter.countMatchesIn("aaaAAA");
	assertThat(matchCount, is(0l));
    }
    
}
