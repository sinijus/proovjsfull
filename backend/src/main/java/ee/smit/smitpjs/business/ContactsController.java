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
    @Operation(summary = "Otsib kontakte",
            description = """
                    Otsib andmebaasist kõiki kontakte, valikuliselt otsisõna järgi ning sorteeritult.
                    Kui ühtegi kontakti ei leita tagastatakse tühi massiiv""")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "OK")})
    public List<ContactDto> searchContacts(@RequestParam(defaultValue = "") String filterName,
                                         @RequestParam(defaultValue = "firstName") String sortParameter,
                                         @RequestParam(defaultValue = "asc") String sortOrder) {
        return contactsService.searchContactsBy(filterName, sortParameter, sortOrder);
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
