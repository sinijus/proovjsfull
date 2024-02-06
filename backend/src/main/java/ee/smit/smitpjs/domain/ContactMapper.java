package ee.smit.smitpjs.domain;

import ee.smit.smitpjs.business.dto.ContactDto;
import org.mapstruct.*;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface ContactMapper {

    Contact toContact(ContactDto contactDto);

    ContactDto toContactDto(Contact contact);

    List<ContactDto> toContactDtos(List<Contact> contacts);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Contact partialUpdate(ContactDto contactDto, @MappingTarget Contact contact);

}