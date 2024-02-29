package ee.jaanussinivali.proovjs.validation;

import lombok.Getter;

@Getter
public enum Error {
    NAME_TAKEN("Sellise nimega kontakt on juba olemas", 112),
    FIELD_TOO_LONG("Sisestatud väljade pikkus ei tohi olla suurem kui 255 märki", 113);

    private final String message;
    private final int errorCode;
    Error(String message, int errorCode) {

        this.message = message;
        this.errorCode = errorCode;
    }
}
