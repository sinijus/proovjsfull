package ee.jaanussinivali.proovjs.domain;

import ee.jaanussinivali.proovjs.business.dto.ContactDto;
import org.mapstruct.*;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface ContactMapper {

    Contact toContact(ContactDto contactDto);

    List<ContactDto> toContactDtos(List<Contact> contacts);

}