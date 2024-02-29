package ee.jaanussinivali.proovjs.validation;

import ee.jaanussinivali.proovjs.infrastructure.exception.BusinessException;

public class ValidationService {

    public static void validateThatNameIsAvailable(Boolean contactExists) {
        if (Boolean.TRUE.equals(contactExists)) {
            throw new BusinessException(Error.NAME_TAKEN.getMessage(), Error.NAME_TAKEN.getErrorCode());
        }
    }

    public static void validateFieldLength(String field) {
        if (field.length() > 255) {
            throw new BusinessException(Error.FIELD_TOO_LONG.getMessage(), Error.FIELD_TOO_LONG.getErrorCode());
        }
    }
}
