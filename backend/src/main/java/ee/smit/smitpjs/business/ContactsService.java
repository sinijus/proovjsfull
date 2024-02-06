package ee.smit.smitpjs.business;

import ee.smit.smitpjs.business.dto.ContactDto;
import ee.smit.smitpjs.domain.Contact;
import ee.smit.smitpjs.domain.ContactMapper;
import ee.smit.smitpjs.domain.ContactService;
import ee.smit.smitpjs.validation.ValidationService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactsService {

    @Resource
    private ContactService contactService;
    @Resource
    private ContactMapper contactMapper;


    public List<ContactDto> findAllContacts() {
        List<Contact> contacts = contactService.getContacts();
        return contactMapper.toContactDtos(contacts);
    }

    public List<ContactDto> findAllContactsAndOrderBy(String sortParameter, String sortOrder) {
        List<Contact> contacts = contactService.getContactsAndOrderBy(sortParameter, sortOrder);
        return contactMapper.toContactDtos(contacts);
    }

    public List<ContactDto> findContacts(String name) {
        List<Contact> contacts = contactService.findContacts(name);
        return contactMapper.toContactDtos(contacts);
    }

    public List<ContactDto> findContactsByNameAndOrder(String name, String sortParameter, String sortOrder) {
        List<Contact> contacts = contactService.findContactsByNameAndOrder(name, sortParameter, sortOrder);
        return contactMapper.toContactDtos(contacts);
    }

    public void postContact(ContactDto request) {
        validateRequest(request);
        setAndSaveContact(request);
    }

    private void validateRequest(ContactDto request) {
        validateRequestLength(request);
        validateNewContactNameIsAvailable(request);
    }

    private static void validateRequestLength(ContactDto request) {
        ValidationService.validateFieldLength(request.getFirstName());
        ValidationService.validateFieldLength(request.getLastName());
        ValidationService.validateFieldLength(request.getCodename());
        ValidationService.validateFieldLength(request.getPhone());
    }

    private void setAndSaveContact(ContactDto request) {
        Contact contact = contactMapper.toContact(request);
        contactService.addContact(contact);
    }

    private void validateNewContactNameIsAvailable(ContactDto request) {
        Boolean contactExists = contactService.doesContactExistBy(request.getFirstName(), request.getLastName());
        ValidationService.validateThatNameIsAvailable(contactExists);
    }
}
