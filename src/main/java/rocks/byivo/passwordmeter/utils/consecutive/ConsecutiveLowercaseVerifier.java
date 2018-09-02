package rocks.byivo.passwordmeter.utils.consecutive;

public class ConsecutiveLowercaseVerifier extends ConsecutiveVerifier {

    @Override
    protected boolean canFormASequenceWith(char actualChar) {
	return actualChar >= 'a' && actualChar <= 'z';
    }

}
