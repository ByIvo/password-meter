package rocks.byivo.passwordmeter.utils.consecutive;

public class ConsecutiveNumberVerifier extends ConsecutiveVerifier {

    @Override
    protected boolean canFormASequenceWith(char actualChar) {
	return actualChar >= '0' && actualChar <= '9';
    }

}
