package ee.jaanussinivali.proovjs.domain;

import ee.jaanussinivali.proovjs.validation.Value;
import jakarta.annotation.Resource;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class ContactService {
    @Resource
    private ContactRepository contactRepository;

    public List<Contact> getContactsAndOrderBy(String sortParameter, String sortOrder) {
        Sort sort = setSortOrder(sortParameter, sortOrder);
        return contactRepository.findAll(sort);
    }

    public List<Contact> findContactsByNameAndOrder(String name, String sortParameter, String sortOrder) {
        Sort sort = setSortOrder(sortParameter, sortOrder);
        return contactRepository.findAllContactsContainingNameAndOrdered(name, sort);
    }

    private static Sort setSortOrder(String sortParameter, String sortOrder) {
        if (Objects.equals(sortOrder.toUpperCase(), Value.ASC.getMessage())) {
            return Sort.by(Sort.Direction.ASC, sortParameter);
        }
        if (Objects.equals(sortOrder.toUpperCase(), Value.DESC.getMessage())) {
            return Sort.by(Sort.Direction.DESC, sortParameter);
        }
        return Sort.unsorted();
    }

    public boolean existsByFirstNameAndLastName(String firstName, String lastName) {
        return contactRepository.existsByFirstNameAndLastName(firstName, lastName);
    }

    public void addContact(Contact contact) {
        contactRepository.save(contact);
    }
}
