package rocks.byivo.passwordmeter.measure.score.requiremets;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

import rocks.byivo.passwordmeter.model.Requirement;

public class SymbolPresenceRequirementTest {

    private SymbolPresenceRequirement symbolPresenceRequirement;
    
    @Before
    public void setUp() throws Exception {
	symbolPresenceRequirement = new SymbolPresenceRequirement();
    }

    @Test
    public void shouldReturnTheHasSymbolsWhenGetsItsRequirement() {
	assertThat(symbolPresenceRequirement.getRequirement(), is(Requirement.HAS_SYMBOLS));
    }
    
    @Test
    public void shouldPassTheRequirementWhenHasAtLeastOneSymbol() throws Exception {
	boolean rechedTheRequirement = symbolPresenceRequirement.isTheMinimumRequirementReachedIn("as#asd2ASd");
	assertThat(rechedTheRequirement, is(true));
    }
    
    @Test
    public void shouldNotPassTheRequirementIfThereIsNoSymbol() throws Exception {
	boolean rechedTheRequirement = symbolPresenceRequirement.isTheMinimumRequirementReachedIn("as23dABC");
	assertThat(rechedTheRequirement, is(false));
    }
    
    @Test
    public void spacesShouldNotCountAsSymbols() throws Exception {
	boolean rechedTheRequirement = symbolPresenceRequirement.isTheMinimumRequirementReachedIn("a b 3 d e");
	assertThat(rechedTheRequirement, is(false));
    }

}
