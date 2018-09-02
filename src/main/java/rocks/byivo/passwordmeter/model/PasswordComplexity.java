package rocks.byivo.passwordmeter.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonValue;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum PasswordComplexity {
    TOO_SHORT("Too Short"),
    VERY_WEAK("Very Weak"), 
    WEAK("Weak"), 
    GOOD("Good"), 
    STRONG("Strong"), 
    VERY_STRONG("Very Strong"),
    ;

    private final String humanReadableName;

    private PasswordComplexity(String humanReadableName) {
	this.humanReadableName = humanReadableName;
    }

    @Override
    @JsonValue
    public String toString() {
	return humanReadableName;
    }

}
