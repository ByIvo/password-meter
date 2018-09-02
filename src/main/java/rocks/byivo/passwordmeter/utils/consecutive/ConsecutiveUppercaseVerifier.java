package rocks.byivo.passwordmeter.utils.consecutive;

public class ConsecutiveUppercaseVerifier extends ConsecutiveVerifier {

    @Override
    protected boolean canFormASequenceWith(char actualChar) {
	return actualChar >= 'A' && actualChar <= 'Z';
    }

}
