package ee.smit.smitpjs.business;

import ee.smit.smitpjs.business.dto.ContactDto;
import ee.smit.smitpjs.infrastructure.error.ApiError;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ContactsController {

    @Resource
    private ContactsService contactsService;

    @GetMapping("/contacts")
    @Operation(summary = "Otsib kõiki kontakte",
            description = " Otsib andmebaasist kõiki olemasolevaid kontakte.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "403", description = "Ühtegi kontakti ei leitud",
                    content = @Content(schema = @Schema(implementation = ApiError.class)))})
    public List<ContactDto> findAllContacts() {
        return contactsService.findAllContacts();
    }

    @GetMapping("/ordered-contacts")
    @Operation(summary = "Otsib kõiki kontakte järjestatult",
            description = """
                    Otsib andmebaasist kõiki olemasolevaid kontakte
                    ning järjestab need ees-, pere- või koodnime järgi tõusvalt või laskuvalt.
                    """)
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "OK") })
    public List<ContactDto> findAllContactsAndOrder(@RequestParam String sortParameter, @RequestParam String sortOrder) {
        return contactsService.findAllContactsAndOrderBy(sortParameter, sortOrder);

    }

    @GetMapping("/filtered-contacts")
    @Operation(summary = "Otsib kontakte otsisõna järgi",
            description = " Otsib andmebaasist kontakte otsisõna järgi nii nende päris kui ka koodnime alusel.")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "OK") })
    public List<ContactDto> filterContacts(@RequestParam String name) {
        return contactsService.findContacts(name);
    }

    @GetMapping("/filtered-ordered-contacts")
    @Operation(summary = "Otsib kontakte otsisõna järgi ja järjestatult",
            description = """
                    Otsib andmebaasist kontakte otsisõna järgi nii nende päris kui ka koodnime alusel
                    ning järjestab need ees-, pere- või koodnime järgi tõusvalt või laskuvalt.
                    """)
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "OK") })
    public List<ContactDto> filterContactsAndOrder(@RequestParam String name,
                                                 @RequestParam String sortParameter,
                                                 @RequestParam String sortOrder) {
        return contactsService.findContactsByNameAndOrder(name, sortParameter, sortOrder);
    }

    @PostMapping("/contact")
    @Operation(summary = "Lisab uue kontakti",
            description = " Lisab andmebaasi uue kontakti kui nime tähekombinatsiooniga kontakt juba ei eksisteeri.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "403", description = """
                    Sellise ees- ja perekonna nimega kontakt on juba olemas
                    või Sisestatud väljade pikkus ei tohi olla suurem kui 255 märki""",
                    content = @Content(schema = @Schema(implementation = ApiError.class)))
    })
    public void postContact(@RequestBody ContactDto request) {
        contactsService.postContact(request);
    }

}
