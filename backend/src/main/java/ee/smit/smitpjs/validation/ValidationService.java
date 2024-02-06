package ee.smit.smitpjs.validation;

import ee.smit.smitpjs.infrastructure.exception.BusinessException;

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
