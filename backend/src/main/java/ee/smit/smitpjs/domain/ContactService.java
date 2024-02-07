package ee.smit.smitpjs.domain;

import ee.smit.smitpjs.validation.Value;
import jakarta.annotation.Resource;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class ContactService {
    @Resource
    private ContactRepository contactRepository;

//    public List<Contact> getContacts() {
//        return contactRepository.findAll();
//    }

    public List<Contact> getContactsAndOrderBy(String sortParameter, String sortOrder) {
        Sort sort = Sort.unsorted();
        sort = setSortOrder(sortParameter, sortOrder, sort);
        return contactRepository.findAll(sort);
    }

//    public List<Contact> findContacts(String name) {
//        return contactRepository.findAllContactsContaining(name);
//    }

    public List<Contact> findContactsByNameAndOrder(String name, String sortParameter, String sortOrder) {
        Sort sort = Sort.unsorted();
        sort = setSortOrder(sortParameter, sortOrder, sort);
        return contactRepository.findAllContactsContainingNameAndOrdered(name, sort);
    }

    private static Sort setSortOrder(String sortParameter, String sortOrder, Sort sort) {
        if (Objects.equals(sortOrder.toUpperCase(), Value.ASC.getMessage())) {
            sort = Sort.by(Sort.Direction.ASC, sortParameter);
        } else if (Objects.equals(sortOrder.toUpperCase(), Value.DESC.getMessage())) {
            sort = Sort.by(Sort.Direction.DESC, sortParameter);
        }
        return sort;
    }

    public boolean existsByFirstNameAndLastName(String firstName, String lastName) {
        return contactRepository.existsByFirstNameAndLastName(firstName, lastName);
    }

    public void addContact(Contact contact) {
        contactRepository.save(contact);
    }
}
