package ee.jaanussinivali.proovjs.validation;

import lombok.Getter;

@Getter
public enum Value {
    ASC("ASC"),
    DESC("DESC");

    private final String message;

    Value(String message) {
        this.message = message;
    }
}
