package ee.smit.smitpjs.domain;

import ee.smit.smitpjs.business.dto.ContactDto;
import org.mapstruct.*;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface ContactMapper {

    Contact toContact(ContactDto contactDto);

    List<ContactDto> toContactDtos(List<Contact> contacts);

}