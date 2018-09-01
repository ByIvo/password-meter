package rocks.byivo.passwordmeter.measure.score.additions;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import rocks.byivo.passwordmeter.measure.score.additions.SymbolsAddition;

public class SymbolsAdditionTest {
    
    private SymbolsAddition symbolsAddition;
    
    @Before
    public void setUp() throws Exception {
	symbolsAddition = new SymbolsAddition();
    }

    @Test
    public void shouldCountAllSymbolsInThePasswordAndMultiplyBy6() {
	long bonusFromSymbols = symbolsAddition.getTotalBonusFrom("a$b%c#d@e");
	assertThat(bonusFromSymbols, is(24l));
    }
    
    @Test
    public void shouldNotConsiderSpaceAsASymbol() {
	long bonusFromSymbols = symbolsAddition.getTotalBonusFrom("$ab34de fg$");
	assertThat(bonusFromSymbols, is(12l));
    }
    
    @Test
    public void evenAllSymbolPasswordShouldBeScored() {
	long bonusFromSymbols = symbolsAddition.getTotalBonusFrom("$$$$$$$$$$");
	assertThat(bonusFromSymbols, is(60l));
    }
    
    @Test
    public void shouldScore0WhenThereIsNoSymbol() {
	long bonusFromSymbols = symbolsAddition.getTotalBonusFrom("aBcD 4598");
	assertThat(bonusFromSymbols, is(0l));
    }

}
