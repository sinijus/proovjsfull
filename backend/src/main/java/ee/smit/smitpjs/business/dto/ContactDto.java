package ee.smit.smitpjs.business.dto;

import ee.smit.smitpjs.domain.Contact;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * DTO for {@link Contact}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContactDto implements Serializable {
    @NotNull
    @Size(max = 255)
    private String firstName;
    @NotNull
    @Size(max = 255)
    private String lastName;
    @NotNull
    @Size(max = 255)
    private String codename;
    @NotNull
    @Size(max = 255)
    private String phone;
}