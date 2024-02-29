package ee.jaanussinivali.proovjs.business;

import ee.jaanussinivali.proovjs.domain.Contact;
import ee.jaanussinivali.proovjs.domain.ContactMapper;
import ee.jaanussinivali.proovjs.domain.ContactService;
import ee.jaanussinivali.proovjs.validation.ValidationService;
import ee.jaanussinivali.proovjs.business.dto.ContactDto;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactsService {

    @Resource
    private ContactService contactService;
    @Resource
    private ContactMapper contactMapper;

    public List<ContactDto> findAllContactsAndOrderBy(String sortParameter, String sortOrder) {
        List<Contact> contacts = contactService.getContactsAndOrderBy(sortParameter, sortOrder);
        return contactMapper.toContactDtos(contacts);
    }

    public List<ContactDto> findContactsByNameAndOrder(String name, String sortParameter, String sortOrder) {
        List<Contact> contacts = contactService.findContactsByNameAndOrder(name, sortParameter, sortOrder);
        return contactMapper.toContactDtos(contacts);
    }

    public List<ContactDto> searchContactsBy(String name, String sortParameter, String sortOrder) {
        if (name.isEmpty()) return findAllContactsAndOrderBy(sortParameter, sortOrder);
        else return findContactsByNameAndOrder(name, sortParameter, sortOrder);
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
        Boolean contactExists = contactService.existsByFirstNameAndLastName(request.getFirstName(), request.getLastName());
        ValidationService.validateThatNameIsAvailable(contactExists);
    }
}
