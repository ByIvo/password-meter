package rocks.byivo.passwordmeter.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonValue;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum Requirement {

    HAS_MINIMUM_LENGTH("Minimum Length"),
    HAS_UPPERCASE_LETTER("Uppercase Letters"),
    HAS_LOWERCASE_LETTER("Lowercase Letters"),
    HAS_NUMBERS("Numbers"),
    HAS_SYMBOLS("Symbols"),
    ;
    
    private String humanReadableName;

    private Requirement(String humanReadableName) {
	this.humanReadableName = humanReadableName;
    }
    
    @Override
    @JsonValue
    public String toString() {
        return humanReadableName;
    }
    
}
