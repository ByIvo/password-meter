package rocks.byivo.passwordmeter.model;

import java.io.Serializable;

public class PasswordMeasureRequest implements Serializable {
    private static final long serialVersionUID = 1L;

    private String rawPassword;

    public PasswordMeasureRequest(String rawPassword) {
	this.rawPassword = rawPassword;
    }

    public PasswordMeasureRequest() {
    }

    public String getRawPassword() {
	return rawPassword;
    }

    public void setRawPassword(String rawPassword) {
	this.rawPassword = rawPassword;
    }

}
